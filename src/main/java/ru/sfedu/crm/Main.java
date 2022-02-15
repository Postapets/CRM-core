package ru.sfedu.crm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.crm.lab1.api.HibernateMetadataProvider;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Main[0]: Start");
        HibernateMetadataProvider hmp = new HibernateMetadataProvider();
        hmp.getSchemas();
        hmp.getUsers();
        hmp.getTables();
        hmp.getColumns();
        log.info("Main[1]: End");
    }
}