package ru.sfedu.crm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.crm.lab1.api.HibernateMetadataProvider;
import ru.sfedu.crm.lab2.api.TestEntityProvider;
import ru.sfedu.crm.lab2.model.TestEntity;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import static ru.sfedu.crm.Constants.*;
import static ru.sfedu.crm.utils.ConfigurationUtil.getConfigurationEntry;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Main[0]: Start");
        Main main = new Main();
        HibernateMetadataProvider hmp = new HibernateMetadataProvider();
        main.getServiceInformation(hmp);
        main.lab2();
        log.info("Main[1]: End");
    }
    //lab1
    private void getServiceInformation(HibernateMetadataProvider hmp){
        hmp.getSchemas();
        hmp.getUsers();
        hmp.getTables();
        hmp.getColumns();
    }
    private void lab2(){
        TestEntityProvider tep = new TestEntityProvider();
        TestEntity entity = new TestEntity(); entity.setControl(true);entity.setDescription("3123");entity.setName("John");
        tep.addRecord(entity);
        log.info(tep.receiveRecordById(TestEntity.class,1L));
        entity.setName("UPDATED NAME");
        tep.updateRecord(entity);
        log.info(tep.receiveRecordById(TestEntity.class,1L));
        tep.deleteRecord(TestEntity.class, 1L);
        log.info(tep.receiveRecordById(TestEntity.class,1L));
        tep.addRecord(entity);
        log.info(tep.loadList(TestEntity.class));
        tep.clearTable(TestEntity.class);
        log.info(tep.loadList(TestEntity.class));
    }
//    private void initDatabase() {
//        try{
//        Connection connection = DriverManager.getConnection(getConfigurationEntry(DB_URL)
//                , getConfigurationEntry(DB_USER)
//                , getConfigurationEntry(DB_PASSWORD));
//        log.debug("initDataBase[0]: Connection success");
//            Class.forName(getConfigurationEntry(DB_DRIVER));
//            log.debug("initDataBase[1]: Creating tables:");
//            connection.createStatement().executeUpdate(CREATE_TABLE_TEST_ENTITY);
//            log.debug("initDataBase[2]: Database initiation success");
//        }
//        catch(Exception e){
//            log.error("initDataBase[0]: Database initialization Error");
//            log.error(e.getClass().getName() + ": " + e.getMessage());
//        }
//    }
}