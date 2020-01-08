create table account
(
	id long AUTO_INCREMENT PRIMARY KEY NOT NULL,
	code VARCHAR(50) not null UNIQUE,
 	user_id INTEGER not null,
	balance DECIMAL DEFAULT 0 not null,

	created_on timestamp DEFAULT CURRENT_TIMESTAMP() NOT NULL,
    updated_on timestamp NULL --

);

create table records
(
    id long AUTO_INCREMENT PRIMARY KEY NOT NULL,
    account_id long NOT NULL,
    credit DECIMAL DEFAULT 0 not null,
    debit DECIMAL DEFAULT 0 not null,
    reason enum('deposit', 'transfer', 'payout', 'etc') NULL,
    created_on timestamp DEFAULT CURRENT_TIMESTAMP() NOT NULL,

    CONSTRAINT FK_ACCOUNT_ID FOREIGN KEY (account_id) REFERENCES account(id)
);


INSERT INTO account (id, user_id, code, balance)
VALUES
(1, 1, '111111', 1000),
(2, 2, '222222', 0),
(3, 3, '333333', 0),
(4, 1, '444444', 0);


INSERT INTO records (id, account_id, credit, reason)
VALUES
(1, 1, 1000, 'deposit');