package ru.sfedu.crm.lab3.api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.crm.enums.ClientStatus;
import ru.sfedu.crm.lab3.model.TablePerClass.*;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static ru.sfedu.crm.Constants.*;

public class TablePerClassTest {
    private static Logger log = LogManager.getLogger(TablePerClassTest.class);
    DataProviderLab3 dp = new DataProviderLab3();

    public static void initRecord(DataProviderLab3 dp) {
        log.debug("initRecord[0]: Start initiate record");
        UserTablePerClass user = createDefaultUser();
        ClientTablePerClass client = createDefaultClient();
        WorkerTablePerClass worker = createDefaultWorker();
        dp.addRecord(user);
        dp.addRecord(client);
        dp.addRecord(worker);
    }

    @Before
    public void beforeRun(){
        log.info("Before run:");
        dp.clearTable(UserTablePerClass.class);
        dp.clearTable(ClientTablePerClass.class);
        dp.clearTable(WorkerTablePerClass.class);
    }

    @Test
    public void loadListPositive() {
        log.info("loadListPositive:");
        initRecord(dp);
        List list = dp.loadList(UserTablePerClass.class);
        assertEquals(3, list.size());
        list = dp.loadList(ClientTablePerClass.class);
        assertEquals(1, list.size());
        list = dp.loadList(WorkerTablePerClass.class);
        assertEquals(1, list.size());
    }

    @Test
    public void loadListNegative() {
        log.info("loadListNegative:");
        List list = dp.loadList(UserTablePerClass.class);
        assertTrue(list.isEmpty());
        list = dp.loadList(ClientTablePerClass.class);
        assertTrue(list.isEmpty());
        list = dp.loadList(WorkerTablePerClass.class);
        assertTrue(list.isEmpty());
    }

    @Test
    public void receiveRecordByIdPositive() {
        log.info("receiveRecordByIdPositive:");
        initRecord(dp);
        Optional<UserTablePerClass> user= dp.receiveRecordById(UserTablePerClass.class,dp.loadList(UserTablePerClass.class).get(0).getId());
        Optional<ClientTablePerClass> client= dp.receiveRecordById(ClientTablePerClass.class,dp.loadList(ClientTablePerClass.class).get(0).getId());
        Optional<WorkerTablePerClass> worker= dp.receiveRecordById(WorkerTablePerClass.class,dp.loadList(WorkerTablePerClass.class).get(0).getId());
        assertNotSame(Optional.empty(), user);
        assertNotSame(Optional.empty(), client);
        assertNotSame(Optional.empty(), worker);
    }

    @Test
    public void receiveRecordByIdNegative() {
        log.info("receiveRecordByIdNegative:");
        Optional<UserTablePerClass> user= dp.receiveRecordById(UserTablePerClass.class,1L);
        Optional<ClientTablePerClass> client= dp.receiveRecordById(ClientTablePerClass.class,1L);
        Optional<WorkerTablePerClass> worker = dp.receiveRecordById(WorkerTablePerClass.class,1L);
        assertSame(Optional.empty(), user);
        assertSame(Optional.empty(), client);
        assertSame(Optional.empty(), worker);
    }

    @Test
    public void deleteRecordPositive() {
        log.info("deleteRecordPositive:");
        initRecord(dp);
        assertTrue(dp.deleteRecord(UserTablePerClass.class,dp.loadList(UserTablePerClass.class).get(0).getId()));
        assertTrue(dp.deleteRecord(ClientTablePerClass.class,dp.loadList(ClientTablePerClass.class).get(0).getId()));
        assertTrue(dp.deleteRecord(WorkerTablePerClass.class,dp.loadList(WorkerTablePerClass.class).get(0).getId()));
    }

    @Test
    public void deleteRecordNegative() {
        log.info("deleteRecordNegative:");
        assertFalse(dp.deleteRecord(UserTablePerClass.class,1L));
        assertFalse(dp.deleteRecord(ClientTablePerClass.class,1L));
        assertFalse(dp.deleteRecord(WorkerTablePerClass.class,1L));
    }

    @Test
    public void updateRecordPositive() {
        log.info("updateRecordPositive:");
        initRecord(dp);
        UserTablePerClass user = dp.loadList(UserTablePerClass.class).get(0);
        ClientTablePerClass client = dp.loadList(ClientTablePerClass.class).get(0);
        WorkerTablePerClass worker = dp.loadList(WorkerTablePerClass.class).get(0);
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
        UserTablePerClass user = new UserTablePerClass();
        user.setId(200L);
        ClientTablePerClass client = new ClientTablePerClass();
        client.setId(200L);
        WorkerTablePerClass worker = new WorkerTablePerClass();
        worker.setId(200L);
        assertNull(dp.receiveRecordById(UserTablePerClass.class, 200L).orElse(null));
        assertNull(dp.receiveRecordById(ClientTablePerClass.class, 200L).orElse(null));
        assertNull(dp.receiveRecordById(WorkerTablePerClass.class, 200L).orElse(null));
        dp.updateRecord(user);
        dp.updateRecord(client);
        dp.updateRecord(worker);
        assertNull(dp.receiveRecordById(UserTablePerClass.class, 200L).orElse(null));
        assertNull(dp.receiveRecordById(ClientTablePerClass.class, 200L).orElse(null));
        assertNull(dp.receiveRecordById(WorkerTablePerClass.class, 200L).orElse(null));
    }

    @Test
    public void addRecordPositive() {
        log.info("addRecordPositive:");
        assertTrue(dp.loadList(UserTablePerClass.class).isEmpty());
        assertTrue(dp.loadList(ClientTablePerClass.class).isEmpty());
        assertTrue(dp.loadList(WorkerTablePerClass.class).isEmpty());
        initRecord(dp);
        assertFalse(dp.loadList(UserTablePerClass.class).isEmpty());
        assertFalse(dp.loadList(ClientTablePerClass.class).isEmpty());
        assertFalse(dp.loadList(WorkerTablePerClass.class).isEmpty());
    }

    public static UserTablePerClass createDefaultUser(){
        UserTablePerClass user = new UserTablePerClass();
        user.setName(DEFAULT_USERNAME);
        user.setBirthDate(DEFAULT_BIRTH_DATE);
        user.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        return user;
    }

    public static ClientTablePerClass createDefaultClient(){
        ClientTablePerClass client = new ClientTablePerClass();
        client.setName(DEFAULT_USERNAME);
        client.setBirthDate(DEFAULT_BIRTH_DATE);
        client.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        client.setInn(DEFAULT_INN);
        client.setStatus(ClientStatus.NEW);
        return client;
    }

    public static WorkerTablePerClass createDefaultWorker(){
        WorkerTablePerClass worker = new WorkerTablePerClass();
        worker.setName(DEFAULT_USERNAME);
        worker.setBirthDate(DEFAULT_BIRTH_DATE);
        worker.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        worker.setPosition(DEFAULT_POSITION);
        worker.setEmploymentDate(DEFAULT_EMPLOYMENT_DATE);
        worker.setLeaveDate(DEFAULT_LEAVE_DATE);
        return worker;
    }
}
