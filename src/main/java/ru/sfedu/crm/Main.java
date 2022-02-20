package ru.sfedu.crm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.crm.enums.ClientStatus;
import ru.sfedu.crm.lab1.api.HibernateMetadataProvider;
import ru.sfedu.crm.lab2.api.TestEntityProvider;
import ru.sfedu.crm.lab2.model.TestEntity;
import ru.sfedu.crm.lab3.api.DataProviderLab3;
import ru.sfedu.crm.lab3.model.MappedSuperclass.*;

import static ru.sfedu.crm.Constants.*;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Main[0]: Start");
        if (args.length >= 1){
            log.info(executeCommand(args));
        }
        else{
            log.error("Illegal arguments, check input data");
        }
        log.info("Main[1]: End");
    }

    public static String executeCommand(String[] args) {
        try{
            switch (args[0].toLowerCase()){
                case Constants.LAB1-> {
                    lab1();
                }
                case Constants.LAB2-> {
                    lab2(args);
                }
                case Constants.LAB3-> {
                    lab3(args);
                }
                default -> {
                    return ILLEGAL_ARGUMENTS;
                }
            }
        }
        catch (Exception e){
            return ILLEGAL_ARGUMENTS;
        }
        return EXECUTED;
    }

    //lab1
    private static void lab1(){
        HibernateMetadataProvider hmp = new HibernateMetadataProvider();
        hmp.getSchemas();
        hmp.getUsers();
        hmp.getTables();
        hmp.getColumns();
    }

    //lab2
    private static void lab2(String[] args){
        TestEntityProvider tep = new TestEntityProvider();
        TestEntity entity = new TestEntity();
        switch (args[1].toLowerCase()){
            case LOAD_LIST->{
                log.info(tep.loadList(TestEntity.class));
            }
            case CLEAR_TABLE->{
                tep.clearTable(TestEntity.class);
            }
            case RECEIVE_RECORD_BY_ID->{
                log.info(tep.receiveRecordById(TestEntity.class, Long.parseLong(args[2])));
            }
            case DELETE_RECORD->{
                 tep.deleteRecord(TestEntity.class,Long.parseLong(args[2]));
            }
            case UPDATE_RECORD->{
                entity.setName("Any Name");
                entity.setDescription("Any Description");
                tep.addRecord(entity);
                entity.setName(args[2]);
                tep.updateRecord(entity);
                log.info(tep.receiveRecordById(TestEntity.class, entity.getId()));
            }
            case ADD_RECORD->{
                entity.setName(args[2]);
                entity.setDescription(args[3]);
                tep.addRecord(entity);
                log.info(tep.receiveRecordById(TestEntity.class,entity.getId()));
            }
        }
//        TestEntityProvider tep = new TestEntityProvider();
//        TestEntity entity = new TestEntity(); entity.setControl(true);entity.setDescription("3123");entity.setName("John");
//        tep.addRecord(entity);
//        log.info(tep.receiveRecordById(TestEntity.class,1L));
//        entity.setName("UPDATED NAME");
//        tep.updateRecord(entity);
//        log.info(tep.receiveRecordById(TestEntity.class,1L));
//        tep.deleteRecord(TestEntity.class, 1L);
//        log.info(tep.receiveRecordById(TestEntity.class,1L));
//        tep.addRecord(entity);
//        log.info(tep.loadList(TestEntity.class));
//        tep.clearTable(TestEntity.class);
//        log.info(tep.loadList(TestEntity.class));
    }

    //lab3
    private static void lab3(String[] args) {
        DataProviderLab3 dp = new DataProviderLab3();
        switch (args[1].toLowerCase()){
            case "mappedsuperclass" -> {
                ru.sfedu.crm.lab3.model.MappedSuperclass.Client client = new ru.sfedu.crm.lab3.model.MappedSuperclass.Client();
                client.setName("PETER");client.setBirthDate(1123123L);
                client.setPhoneNumber("3123123");client.setStatus(ClientStatus.NEW);
                client.setInn(123123123L);
                dp.addRecord(client);
                log.info(dp.receiveRecordById(ru.sfedu.crm.lab3.model.MappedSuperclass.Client.class,client.getId()));
            }
            case "joinedtable" -> {
                ru.sfedu.crm.lab3.model.JoinedTable.User user = new ru.sfedu.crm.lab3.model.JoinedTable.User();
                user.setName("Sanya");
                user.setPhoneNumber("3123123");
                user.setBirthDate(123123312L);
                dp.addRecord(user);
                log.info(dp.receiveRecordById(ru.sfedu.crm.lab3.model.JoinedTable.User.class,user.getId()));
            }
            case "singletable" -> {
                ru.sfedu.crm.lab3.model.SingleTable.Client client = new ru.sfedu.crm.lab3.model.SingleTable.Client();
                client.setName("PETER");client.setBirthDate(1123123L);
                client.setPhoneNumber("3123123");client.setStatus(ClientStatus.NEW);
                client.setInn(123123123L);
                dp.addRecord(client);
                log.info(dp.receiveRecordById(ru.sfedu.crm.lab3.model.SingleTable.Client.class,client.getId()));

            }
            case "tableperclass" -> {
                ru.sfedu.crm.lab3.model.TablePerClass.Client client = new ru.sfedu.crm.lab3.model.TablePerClass.Client();
                client.setName("PETER");client.setBirthDate(1123123L);
                client.setPhoneNumber("3123123");client.setStatus(ClientStatus.NEW);
                client.setInn(123123123L);
                dp.addRecord(client);
                log.info(dp.receiveRecordById(ru.sfedu.crm.lab3.model.TablePerClass.Client.class,client.getId()));
            }
        }
//        dp.addRecord(user);
//        log.info(dp.receiveRecordById(User.class, 1L));
//        user.setName("NO SANYA");
//        dp.updateRecord(user);
//        log.info(dp.receiveRecordById(User.class, 1L));
//        dp.deleteRecord(User.class, 1L);
//        log.info(dp.receiveRecordById(User.class, 1L));
//        dp.addRecord(user);
//        log.info(dp.loadList(User.class));
//        dp.clearTable(User.class);
//        log.info(dp.loadList(User.class));
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