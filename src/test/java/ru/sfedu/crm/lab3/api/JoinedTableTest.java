package ru.sfedu.crm.lab3.api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.crm.enums.ClientStatus;
import ru.sfedu.crm.lab3.model.JoinedTable.*;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static ru.sfedu.crm.Constants.*;

public class JoinedTableTest {
    private static Logger log = LogManager.getLogger(JoinedTableTest.class);
    DataProviderLab3 dp = new DataProviderLab3();

    public static void initRecord(DataProviderLab3 dp) {
        log.debug("initRecord[0]: Start initiate record");
        UserJoinedTable user = createDefaultUser();
        ClientJoinedTable client = createDefaultClient();
        WorkerJoinedTable worker = createDefaultWorker();
        dp.addRecord(user);
        dp.addRecord(client);
        dp.addRecord(worker);
    }

    @Before
    public void beforeRun(){
        log.info("Before run:");
        dp.clearTable(UserJoinedTable.class);
        dp.clearTable(ClientJoinedTable.class);
        dp.clearTable(WorkerJoinedTable.class);
    }

    @Test
    public void loadListPositive() {
        log.info("loadListPositive:");
        initRecord(dp);
        List list = dp.loadList(UserJoinedTable.class);
        assertEquals(3, list.size());
        list = dp.loadList(ClientJoinedTable.class);
        assertEquals(1, list.size());
        list = dp.loadList(WorkerJoinedTable.class);
        assertEquals(1, list.size());
    }

    @Test
    public void loadListNegative() {
        log.info("loadListNegative:");
        List list = dp.loadList(UserJoinedTable.class);
        assertTrue(list.isEmpty());
        list = dp.loadList(ClientJoinedTable.class);
        assertTrue(list.isEmpty());
        list = dp.loadList(WorkerJoinedTable.class);
        assertTrue(list.isEmpty());
    }

    @Test
    public void receiveRecordByIdPositive() {
        log.info("receiveRecordByIdPositive:");
        initRecord(dp);
        Optional<UserJoinedTable> user= dp.receiveRecordById(UserJoinedTable.class,dp.loadList(UserJoinedTable.class).get(0).getId());
        Optional<ClientJoinedTable> client= dp.receiveRecordById(ClientJoinedTable.class,dp.loadList(ClientJoinedTable.class).get(0).getId());
        Optional<WorkerJoinedTable> worker= dp.receiveRecordById(WorkerJoinedTable.class,dp.loadList(WorkerJoinedTable.class).get(0).getId());
        assertNotSame(Optional.empty(), user);
        assertNotSame(Optional.empty(), client);
        assertNotSame(Optional.empty(), worker);
    }

    @Test
    public void receiveRecordByIdNegative() {
        log.info("receiveRecordByIdNegative:");
        Optional<UserJoinedTable> user= dp.receiveRecordById(UserJoinedTable.class,1L);
        Optional<ClientJoinedTable> client= dp.receiveRecordById(ClientJoinedTable.class,1L);
        Optional<WorkerJoinedTable> worker = dp.receiveRecordById(WorkerJoinedTable.class,1L);
        assertSame(Optional.empty(), user);
        assertSame(Optional.empty(), client);
        assertSame(Optional.empty(), worker);
    }

    @Test
    public void deleteRecordPositive() {
        log.info("deleteRecordPositive:");
        initRecord(dp);
        assertTrue(dp.deleteRecord(UserJoinedTable.class,dp.loadList(UserJoinedTable.class).get(0).getId()));
        assertTrue(dp.deleteRecord(ClientJoinedTable.class,dp.loadList(ClientJoinedTable.class).get(0).getId()));
        assertTrue(dp.deleteRecord(WorkerJoinedTable.class,dp.loadList(WorkerJoinedTable.class).get(0).getId()));
    }

    @Test
    public void deleteRecordNegative() {
        log.info("deleteRecordNegative:");
        assertFalse(dp.deleteRecord(UserJoinedTable.class,1L));
        assertFalse(dp.deleteRecord(ClientJoinedTable.class,1L));
        assertFalse(dp.deleteRecord(WorkerJoinedTable.class,1L));
    }

    @Test
    public void updateRecordPositive() {
        log.info("updateRecordPositive:");
        initRecord(dp);
        UserJoinedTable user = dp.loadList(UserJoinedTable.class).get(0);
        ClientJoinedTable client = dp.loadList(ClientJoinedTable.class).get(0);
        WorkerJoinedTable worker = dp.loadList(WorkerJoinedTable.class).get(0);
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
        UserJoinedTable user = new UserJoinedTable();
        user.setId(200L);
        ClientJoinedTable client = new ClientJoinedTable();
        client.setId(200L);
        WorkerJoinedTable worker = new WorkerJoinedTable();
        worker.setId(200L);
        assertNull(dp.receiveRecordById(UserJoinedTable.class, 200L).orElse(null));
        assertNull(dp.receiveRecordById(ClientJoinedTable.class, 200L).orElse(null));
        assertNull(dp.receiveRecordById(WorkerJoinedTable.class, 200L).orElse(null));
        dp.updateRecord(user);
        dp.updateRecord(client);
        dp.updateRecord(worker);
        assertNull(dp.receiveRecordById(UserJoinedTable.class, 200L).orElse(null));
        assertNull(dp.receiveRecordById(ClientJoinedTable.class, 200L).orElse(null));
        assertNull(dp.receiveRecordById(WorkerJoinedTable.class, 200L).orElse(null));
    }

    @Test
    public void addRecordPositive() {
        log.info("addRecordPositive:");
        assertTrue(dp.loadList(UserJoinedTable.class).isEmpty());
        assertTrue(dp.loadList(ClientJoinedTable.class).isEmpty());
        assertTrue(dp.loadList(WorkerJoinedTable.class).isEmpty());
        initRecord(dp);
        assertFalse(dp.loadList(UserJoinedTable.class).isEmpty());
        assertFalse(dp.loadList(ClientJoinedTable.class).isEmpty());
        assertFalse(dp.loadList(WorkerJoinedTable.class).isEmpty());
    }

    public static UserJoinedTable createDefaultUser(){
        UserJoinedTable user = new UserJoinedTable();
        user.setName(DEFAULT_USERNAME);
        user.setBirthDate(DEFAULT_BIRTH_DATE);
        user.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        return user;
    }

    public static ClientJoinedTable createDefaultClient(){
        ClientJoinedTable client = new ClientJoinedTable();
        client.setName(DEFAULT_USERNAME);
        client.setBirthDate(DEFAULT_BIRTH_DATE);
        client.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        client.setInn(DEFAULT_INN);
        client.setStatus(ClientStatus.NEW);
        return client;
    }

    public static WorkerJoinedTable createDefaultWorker(){
        WorkerJoinedTable worker = new WorkerJoinedTable();
        worker.setName(DEFAULT_USERNAME);
        worker.setBirthDate(DEFAULT_BIRTH_DATE);
        worker.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        worker.setPosition(DEFAULT_POSITION);
        worker.setEmploymentDate(DEFAULT_EMPLOYMENT_DATE);
        worker.setLeaveDate(DEFAULT_LEAVE_DATE);
        return worker;
    }
}
