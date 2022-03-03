package ru.sfedu.crm.lab4.api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.crm.lab4.model.set.UserSet;

import java.util.*;


import static org.junit.Assert.*;
import static ru.sfedu.crm.Constants.*;

public class UserSetTest {

    private static Logger log = LogManager.getLogger(UserSetTest.class);
    DataProviderLab4 dp = new DataProviderLab4();

    public static void initRecord(DataProviderLab4 dp) {
        log.debug("initRecord[0]: Start initiate record");
        UserSet user = createDefaultUser();
        dp.addRecord(user);
    }

    @Before
    public void beforeRun(){
        log.info("Before run:");
        dp.clearTableSet(UserSet.class);
    }

    @Test
    public void loadListPositive() {
        log.info("loadListPositive:");
        initRecord(dp);
        List list = dp.loadList(UserSet.class);
        assertEquals(1, list.size());
    }

    @Test
    public void loadListNegative() {
        log.info("loadListNegative:");
        List list = dp.loadList(UserSet.class);
        assertEquals(0, list.size());
    }

    @Test
    public void receiveRecordByIdPositive() {
        log.info("receiveRecordByIdPositive:");
        initRecord(dp);
        Optional<UserSet> user= dp.receiveRecordById(UserSet.class,dp.loadList(UserSet.class).get(0).getId());
        assertNotSame(Optional.empty(), user);
    }

    @Test
    public void receiveRecordByIdNegative() {
        log.info("receiveRecordByIdNegative:");
        Optional<UserSet> user= dp.receiveRecordById(UserSet.class,1L);
        assertSame(Optional.empty(), user);
    }

    @Test
    public void deleteRecordPositive() {
        log.info("deleteRecordPositive:");
        initRecord(dp);
        assertTrue(dp.deleteRecord(UserSet.class,dp.loadList(UserSet.class).get(0).getId()));
    }

    @Test
    public void deleteRecordNegative() {
        log.info("deleteRecordNegative:");
        assertFalse(dp.deleteRecord(UserSet.class,1L));
    }

    @Test
    public void updateRecordPositive() {
        log.info("updateRecordPositive:");
        initRecord(dp);
        UserSet user = dp.loadList(UserSet.class).get(0);
        user.setName("UPDATED NAME");
        dp.updateRecord(user);
        assertEquals(user.getName(),"UPDATED NAME");
    }
    @Test
    public void updateRecordNegative() {
        log.info("updateRecordNegative:");
        initRecord(dp);
        UserSet user = new UserSet();
        user.setId(200L);
        assertNull(dp.receiveRecordById(UserSet.class, 200L).orElse(null));
        dp.updateRecord(user);
        assertNull(dp.receiveRecordById(UserSet.class, 200L).orElse(null));
    }

    @Test
    public void addRecordPositive() {
        log.info("addRecordPositive:");
        log.info(UserSet.class.getSimpleName());
        assertTrue(dp.loadList(UserSet.class).isEmpty());
        initRecord(dp);
        assertFalse(dp.loadList(UserSet.class).isEmpty());
    }

    public static UserSet createDefaultUser(){
        UserSet user = new UserSet();
        user.setName(DEFAULT_USERNAME);
        user.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        Set<Long> idSet = new HashSet<>();
        idSet.add(1L);
        idSet.add(2L);
        idSet.add(3L);
        user.setOrdersId(idSet);
        return user;
    }
}
