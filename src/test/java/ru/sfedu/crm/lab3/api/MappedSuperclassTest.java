package ru.sfedu.crm.lab3.api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.crm.enums.ClientStatus;
import ru.sfedu.crm.lab3.model.MappedSuperclass.*;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static ru.sfedu.crm.Constants.*;

public class MappedSuperclassTest {
    private static Logger log = LogManager.getLogger(MappedSuperclassTest.class);
    DataProviderLab3 dp = new DataProviderLab3();

    public static void initRecord(DataProviderLab3 dp) {
        log.debug("initRecord[0]: Start initiate record");
        ClientMappedSuperclass client = createDefaultClient();
        WorkerMappedSuperclass worker = createDefaultWorker();
        dp.addRecord(client);
        dp.addRecord(worker);
    }

    @Before
    public void beforeRun(){
        log.info("Before run:");
        dp.clearTable(ClientMappedSuperclass.class);
        dp.clearTable(WorkerMappedSuperclass.class);
    }

    @Test
    public void loadListPositive() {
        log.info("loadListPositive:");
        initRecord(dp);
        List list = dp.loadList(ClientMappedSuperclass.class);
        assertFalse(list.isEmpty());
        list = dp.loadList(WorkerMappedSuperclass.class);
        assertFalse(list.isEmpty());
    }

    @Test
    public void loadListNegative() {
        log.info("loadListNegative:");
        List list = dp.loadList(ClientMappedSuperclass.class);
        assertTrue(list.isEmpty());
        list = dp.loadList(WorkerMappedSuperclass.class);
        assertTrue(list.isEmpty());
    }

    @Test
    public void receiveRecordByIdPositive() {
        log.info("receiveRecordByIdPositive:");
        initRecord(dp);
        Optional<ClientMappedSuperclass> client= dp.receiveRecordById(ClientMappedSuperclass.class,dp.loadList(ClientMappedSuperclass.class).get(0).getId());
        Optional<WorkerMappedSuperclass> worker= dp.receiveRecordById(WorkerMappedSuperclass.class,dp.loadList(WorkerMappedSuperclass.class).get(0).getId());
        log.info(client);
        log.info(worker);
        assertNotSame(Optional.empty(), client);
        assertNotSame(Optional.empty(), worker);
    }

    @Test
    public void receiveRecordByIdNegative() {
        log.info("receiveRecordByIdNegative:");
        Optional<ClientMappedSuperclass> client= dp.receiveRecordById(ClientMappedSuperclass.class,1L);
        Optional<WorkerMappedSuperclass> worker = dp.receiveRecordById(WorkerMappedSuperclass.class,1L);
        assertSame(Optional.empty(), client);
        assertSame(Optional.empty(), worker);
    }

    @Test
    public void deleteRecordPositive() {
        log.info("deleteRecordPositive:");
        initRecord(dp);
        assertTrue(dp.deleteRecord(ClientMappedSuperclass.class,dp.loadList(ClientMappedSuperclass.class).get(0).getId()));
        assertTrue(dp.deleteRecord(WorkerMappedSuperclass.class,dp.loadList(WorkerMappedSuperclass.class).get(0).getId()));
    }

    @Test
    public void deleteRecordNegative() {
        log.info("deleteRecordNegative:");
        assertFalse(dp.deleteRecord(ClientMappedSuperclass.class,1L));
        assertFalse(dp.deleteRecord(WorkerMappedSuperclass.class,1L));
    }

    @Test
    public void updateRecordPositive() {
        log.info("updateRecordPositive:");
        initRecord(dp);
        ClientMappedSuperclass client = dp.loadList(ClientMappedSuperclass.class).get(0);
        WorkerMappedSuperclass worker = dp.loadList(WorkerMappedSuperclass.class).get(0);
        client.setName("UPDATED NAME");
        worker.setName("UPDATED NAME");
        dp.updateRecord(client);
        dp.updateRecord(worker);
        assertEquals(client.getName(),"UPDATED NAME");
        assertEquals(worker.getName(),"UPDATED NAME");
    }

    @Test
    public void updateRecordNegative() {
        log.info("updateRecordNegative:");
        initRecord(dp);
        ClientMappedSuperclass client = new ClientMappedSuperclass();
        client.setId(200L);
        WorkerMappedSuperclass worker = new WorkerMappedSuperclass();
        worker.setId(200L);
        assertNull(dp.receiveRecordById(ClientMappedSuperclass.class, 200L).orElse(null));
        assertNull(dp.receiveRecordById(WorkerMappedSuperclass.class, 200L).orElse(null));
        dp.updateRecord(client);
        dp.updateRecord(worker);
        assertNull(dp.receiveRecordById(ClientMappedSuperclass.class, 200L).orElse(null));
        assertNull(dp.receiveRecordById(WorkerMappedSuperclass.class, 200L).orElse(null));
    }

    @Test
    public void addRecordPositive() {
        log.info("addRecordPositive:");
        assertTrue(dp.loadList(ClientMappedSuperclass.class).isEmpty());
        assertTrue(dp.loadList(WorkerMappedSuperclass.class).isEmpty());
        initRecord(dp);
        assertFalse(dp.loadList(ClientMappedSuperclass.class).isEmpty());
        assertFalse(dp.loadList(WorkerMappedSuperclass.class).isEmpty());
    }

    public static UserMappedSuperclass createDefaultUser(){
        UserMappedSuperclass user = new UserMappedSuperclass();
        user.setName(DEFAULT_USERNAME);
        user.setBirthDate(DEFAULT_BIRTH_DATE);
        user.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        return user;
    }

    public static ClientMappedSuperclass createDefaultClient(){
        ClientMappedSuperclass client = new ClientMappedSuperclass();
        client.setName(DEFAULT_USERNAME);
        client.setBirthDate(DEFAULT_BIRTH_DATE);
        client.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        client.setInn(DEFAULT_INN);
        client.setStatus(ClientStatus.NEW);
        return client;
    }

    public static WorkerMappedSuperclass createDefaultWorker(){
        WorkerMappedSuperclass worker = new WorkerMappedSuperclass();
        worker.setName(DEFAULT_USERNAME);
        worker.setBirthDate(DEFAULT_BIRTH_DATE);
        worker.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        worker.setPosition(DEFAULT_POSITION);
        worker.setEmploymentDate(DEFAULT_EMPLOYMENT_DATE);
        worker.setLeaveDate(DEFAULT_LEAVE_DATE);
        return worker;
    }
}
