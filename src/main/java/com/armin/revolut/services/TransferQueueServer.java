package com.armin.revolut.services;

import com.armin.revolut.Dependencies;
import com.armin.revolut.exceptions.AccountNotFoundException;
import com.armin.revolut.exceptions.ExceptionMiddleware;
import com.armin.revolut.exceptions.InsufficientFundsException;
import com.armin.revolut.stores.TransferStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.concurrent.LinkedBlockingQueue;

public class TransferQueueServer {
    public static final Logger log = LoggerFactory.getLogger(ExceptionMiddleware.class);

    private LinkedBlockingQueue<TransferOrder> queue;
    private boolean running;
    private TransferStore store;

    public TransferQueueServer() {
        store = Dependencies.getInjector().getInstance(TransferStore.class);
    }

    public void start() {
        queue = new LinkedBlockingQueue<>();
        running = true;
        Thread serverThread = new Thread(this::serve);
        serverThread.start();
        log.info("Transfer queue server started...");
    }

    public void stop() {
        running = false;
        queue.offer(TransferOrder.StopServe.command());
        log.info("Transfer queue server stopped");
    }


    public boolean isRunning() {
        return running;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    private void serve() {
        try {
            while (running) {
                TransferOrder order = null;

                order = queue.take();

                if (order instanceof TransferOrder.StopServe) return;

                doTransfer(order);
            }
        } catch (InterruptedException e) {
            log.error("TransferService Interrupted", e);
            e.printStackTrace();
        }
    }

    private void doTransfer(TransferOrder order) {
        try {
            if (order instanceof TransferSourceOrder) {
                store.transfer((TransferSourceOrder) order);
            } else {
                TransferDestinationOrder deposit = (TransferDestinationOrder) order;
                store.deposit(deposit.getTo(), deposit.getAmount());
            }
            informUser(order, null);
        } catch (InsufficientFundsException | AccountNotFoundException e) {
            informUser(order, e);
        } catch (Exception e) {
            log.error("Serve API error", e);
        }
    }

    private void informUser(TransferOrder order, Exception e) {
        /**
         * Inform user with other channels, ex: WebSocket
         * I didn't implemented to keep it simple and there was no definition about this
         * I assume it was not a part of task description
         * TODO: Implement this
         */
    }

    public void transfer(String fromAccountCode, String toAccountCode, BigDecimal amount) {
        transfer(new TransferSourceOrder(fromAccountCode, toAccountCode, amount));
    }

    public void deposit(String toAccountCode, BigDecimal amount) {
        transfer(new TransferDestinationOrder(toAccountCode, amount));
    }

    public void transfer(TransferOrder order) {
        queue.offer(order);
    }


}
