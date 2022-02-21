package ru.sfedu.crm.lab3.api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.crm.enums.ClientStatus;
import ru.sfedu.crm.lab3.model.SingleTable.*;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static ru.sfedu.crm.Constants.*;

public class SingleTableTest {
    private static Logger log = LogManager.getLogger(SingleTableTest.class);
    DataProviderLab3 dp = new DataProviderLab3();

    public static void initRecord(DataProviderLab3 dp) {
        log.debug("initRecord[0]: Start initiate record");
        UserSingleTable user = createDefaultUser();
        ClientSingleTable client = createDefaultClient();
        WorkerSingleTable worker = createDefaultWorker();
        dp.addRecord(user);
        dp.addRecord(client);
        dp.addRecord(worker);
    }

    @Before
    public void beforeRun(){
        log.info("Before run:");
        dp.clearTable(UserSingleTable.class);
    }

    @Test
    public void loadListPositive() {
        log.info("loadListPositive:");
        initRecord(dp);
        List list = dp.loadList(UserSingleTable.class);
        assertEquals(3, list.size());
    }

    @Test
    public void loadListNegative() {
        log.info("loadListNegative:");
        List list = dp.loadList(UserSingleTable.class);
        assertTrue(list.isEmpty());
    }

    @Test
    public void receiveRecordByIdPositive() {
        log.info("receiveRecordByIdPositive:");
        initRecord(dp);
        Optional<UserSingleTable> user= dp.receiveRecordById(UserSingleTable.class,dp.loadList(UserSingleTable.class).get(0).getId());
        Optional<ClientSingleTable> client= dp.receiveRecordById(ClientSingleTable.class,dp.loadList(ClientSingleTable.class).get(0).getId());
        Optional<WorkerSingleTable> worker= dp.receiveRecordById(WorkerSingleTable.class,dp.loadList(WorkerSingleTable.class).get(0).getId());
        assertNotSame(Optional.empty(), user);
        assertNotSame(Optional.empty(), client);
        assertNotSame(Optional.empty(), worker);
    }

    @Test
    public void receiveRecordByIdNegative() {
        log.info("receiveRecordByIdNegative:");
        Optional<UserSingleTable> user= dp.receiveRecordById(UserSingleTable.class,1L);
        Optional<ClientSingleTable> client= dp.receiveRecordById(ClientSingleTable.class,1L);
        Optional<WorkerSingleTable> worker = dp.receiveRecordById(WorkerSingleTable.class,1L);
        assertSame(Optional.empty(), user);
        assertSame(Optional.empty(), client);
        assertSame(Optional.empty(), worker);
    }

    @Test
    public void deleteRecordPositive() {
        log.info("deleteRecordPositive:");
        initRecord(dp);
        assertTrue(dp.deleteRecord(UserSingleTable.class,dp.loadList(UserSingleTable.class).get(0).getId()));
        assertTrue(dp.deleteRecord(ClientSingleTable.class,dp.loadList(ClientSingleTable.class).get(0).getId()));
        assertTrue(dp.deleteRecord(WorkerSingleTable.class,dp.loadList(WorkerSingleTable.class).get(0).getId()));
    }

    @Test
    public void deleteRecordNegative() {
        log.info("deleteRecordNegative:");
        assertFalse(dp.deleteRecord(UserSingleTable.class,1L));
        assertFalse(dp.deleteRecord(ClientSingleTable.class,1L));
        assertFalse(dp.deleteRecord(WorkerSingleTable.class,1L));
    }

    @Test
    public void updateRecordPositive() {
        log.info("updateRecordPositive:");
        initRecord(dp);
        UserSingleTable user = dp.loadList(UserSingleTable.class).get(0);
        ClientSingleTable client = dp.loadList(ClientSingleTable.class).get(0);
        WorkerSingleTable worker = dp.loadList(WorkerSingleTable.class).get(0);
        user.setName("UPDATED NAME");
        client.setName("UPDATED NAME");
        worker.setName("UPDATED NAME");
        dp.updateRecord(user);
        dp.updateRecord(client);
        dp.updateRecord(worker);
        assertEquals(user.getName(),"UPDATED NAME");
        assertEquals(client.getName(),"UPDATED NAME");
        assertEquals(worker.getName(),"UPDATED NAME");
    }

    @Test
    public void updateRecordNegative() {
        log.info("updateRecordNegative:");
        initRecord(dp);
        UserSingleTable user = new UserSingleTable();
        user.setId(200L);
        ClientSingleTable client = new ClientSingleTable();
        client.setId(200L);
        WorkerSingleTable worker = new WorkerSingleTable();
        worker.setId(200L);
        assertNull(dp.receiveRecordById(UserSingleTable.class, 200L).orElse(null));
        assertNull(dp.receiveRecordById(ClientSingleTable.class, 200L).orElse(null));
        assertNull(dp.receiveRecordById(WorkerSingleTable.class, 200L).orElse(null));
        dp.updateRecord(user);
        dp.updateRecord(client);
        dp.updateRecord(worker);
        assertNull(dp.receiveRecordById(UserSingleTable.class, 200L).orElse(null));
        assertNull(dp.receiveRecordById(ClientSingleTable.class, 200L).orElse(null));
        assertNull(dp.receiveRecordById(WorkerSingleTable.class, 200L).orElse(null));
    }

    @Test
    public void addRecordPositive() {
        log.info("addRecordPositive:");
        assertTrue(dp.loadList(UserSingleTable.class).isEmpty());
        assertTrue(dp.loadList(ClientSingleTable.class).isEmpty());
        assertTrue(dp.loadList(WorkerSingleTable.class).isEmpty());
        initRecord(dp);
        assertFalse(dp.loadList(UserSingleTable.class).isEmpty());
        assertFalse(dp.loadList(ClientSingleTable.class).isEmpty());
        assertFalse(dp.loadList(WorkerSingleTable.class).isEmpty());
    }

    public static UserSingleTable createDefaultUser(){
        UserSingleTable user = new UserSingleTable();
        user.setName(DEFAULT_USERNAME);
        user.setBirthDate(DEFAULT_BIRTH_DATE);
        user.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        return user;
    }

    public static ClientSingleTable createDefaultClient(){
        ClientSingleTable client = new ClientSingleTable();
        client.setName(DEFAULT_USERNAME);
        client.setBirthDate(DEFAULT_BIRTH_DATE);
        client.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        client.setInn(DEFAULT_INN);
        client.setStatus(ClientStatus.NEW);
        return client;
    }

    public static WorkerSingleTable createDefaultWorker(){
        WorkerSingleTable worker = new WorkerSingleTable();
        worker.setName(DEFAULT_USERNAME);
        worker.setBirthDate(DEFAULT_BIRTH_DATE);
        worker.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        worker.setPosition(DEFAULT_POSITION);
        worker.setEmploymentDate(DEFAULT_EMPLOYMENT_DATE);
        worker.setLeaveDate(DEFAULT_LEAVE_DATE);
        return worker;
    }
}
