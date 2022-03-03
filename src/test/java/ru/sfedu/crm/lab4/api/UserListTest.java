package ru.sfedu.crm.lab4.api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.crm.lab4.model.list.UserList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.Assert.*;
import static ru.sfedu.crm.Constants.*;

public class UserListTest {

    private static Logger log = LogManager.getLogger(UserListTest.class);
    DataProviderLab4 dp = new DataProviderLab4();

    public static void initRecord(DataProviderLab4 dp) {
        log.debug("initRecord[0]: Start initiate record");
        UserList user = createDefaultUser();
        dp.addRecord(user);
    }

    @Before
    public void beforeRun(){
        log.info("Before run:");
        dp.clearTableList(UserList.class);
    }

    @Test
    public void loadListPositive() {
        log.info("loadListPositive:");
        initRecord(dp);
        List list = dp.loadList(UserList.class);
        assertEquals(1, list.size());
    }

    @Test
    public void loadListNegative() {
        log.info("loadListNegative:");
        List list = dp.loadList(UserList.class);
        assertEquals(0, list.size());
    }

    @Test
    public void receiveRecordByIdPositive() {
        log.info("receiveRecordByIdPositive:");
        initRecord(dp);
        Optional<UserList> user= dp.receiveRecordById(UserList.class,dp.loadList(UserList.class).get(0).getId());
        assertNotSame(Optional.empty(), user);
    }

    @Test
    public void receiveRecordByIdNegative() {
        log.info("receiveRecordByIdNegative:");
        Optional<UserList> user= dp.receiveRecordById(UserList.class,1L);
        assertSame(Optional.empty(), user);
    }

    @Test
    public void deleteRecordPositive() {
        log.info("deleteRecordPositive:");
        initRecord(dp);
        assertTrue(dp.deleteRecord(UserList.class,dp.loadList(UserList.class).get(0).getId()));
    }

    @Test
    public void deleteRecordNegative() {
        log.info("deleteRecordNegative:");
        assertFalse(dp.deleteRecord(UserList.class,1L));
    }

    @Test
    public void updateRecordPositive() {
        log.info("updateRecordPositive:");
        initRecord(dp);
        UserList user = dp.loadList(UserList.class).get(0);
        user.setName("UPDATED NAME");
        dp.updateRecord(user);
        assertEquals(user.getName(),"UPDATED NAME");
    }
    @Test
    public void updateRecordNegative() {
        log.info("updateRecordNegative:");
        initRecord(dp);
        UserList user = new UserList();
        user.setId(200L);
        assertNull(dp.receiveRecordById(UserList.class, 200L).orElse(null));
        dp.updateRecord(user);
        assertNull(dp.receiveRecordById(UserList.class, 200L).orElse(null));
    }

    @Test
    public void addRecordPositive() {
        log.info("addRecordPositive:");
        log.info(UserList.class.getSimpleName());
        assertTrue(dp.loadList(UserList.class).isEmpty());
        initRecord(dp);
        assertFalse(dp.loadList(UserList.class).isEmpty());
    }

    public static UserList createDefaultUser(){
        UserList user = new UserList();
        user.setName(DEFAULT_USERNAME);
        user.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        List<Long> idList = new ArrayList<>();
        idList.add(1L);
        idList.add(2L);
        idList.add(3L);
        user.setOrdersId(idList);
        return user;
    }
}
