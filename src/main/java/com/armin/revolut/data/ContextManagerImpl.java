package com.armin.revolut.data;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.flywaydb.core.Flyway;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class ContextManagerImpl implements ContextManager {
    public static final Logger log = LoggerFactory.getLogger(ContextManagerImpl.class);
    private static final String PROP_FILE = "db/db.properties";


    private DataSource dataSource;
    private DSLContext dslContext;


    public ContextManagerImpl() {
        setup();
    }

    public void setup() {
        ComboPooledDataSource cDataSource;
        try {
            Properties properties = new Properties();
            InputStream propertiesStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROP_FILE);
            properties.load(propertiesStream);
            cDataSource = new ComboPooledDataSource();

            cDataSource.setDriverClass(properties.getProperty("db.driver"));
            cDataSource.setJdbcUrl(properties.getProperty("db.url"));
            this.dataSource = cDataSource;
            this.dslContext = DSL.using(cDataSource, SQLDialect.H2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        runMigrations();
    }

    private void runMigrations() {
        log.info("Run migrations...");
        Flyway flyway = Flyway.configure()
                .dataSource(this.dataSource)
                .load();
        flyway.migrate();
        log.info("Migrations finished");
    }


    @Override
    public DSLContext getDslContext() {
        return dslContext;
    }

}
