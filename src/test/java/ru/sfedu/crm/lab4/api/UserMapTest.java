package ru.sfedu.crm.lab4.api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import ru.sfedu.crm.enums.OrderType;
import ru.sfedu.crm.lab4.model.map.UserMap;

import java.util.*;


import static org.junit.Assert.*;
import static ru.sfedu.crm.Constants.*;

public class UserMapTest {

    private static Logger log = LogManager.getLogger(UserMapTest.class);
    DataProviderLab4 dp = new DataProviderLab4();

    public static void initRecord(DataProviderLab4 dp) {
        log.debug("initRecord[0]: Start initiate record");
        UserMap user = createDefaultUser();
        dp.addRecord(user);
    }

    @Before
    public void beforeRun(){
        log.info("Before run:");
        dp.clearTableMap(UserMap.class);
    }

    @Test
    public void loadListPositive() {
        log.info("loadListPositive:");
        initRecord(dp);
        List list = dp.loadList(UserMap.class);
        assertEquals(1, list.size());
    }

    @Test
    public void loadListNegative() {
        log.info("loadListNegative:");
        List list = dp.loadList(UserMap.class);
        assertEquals(0, list.size());
    }

    @Test
    public void receiveRecordByIdPositive() {
        log.info("receiveRecordByIdPositive:");
        initRecord(dp);
        Optional<UserMap> user= dp.receiveRecordById(UserMap.class,dp.loadList(UserMap.class).get(0).getId());
        assertNotSame(Optional.empty(), user);
    }

    @Test
    public void receiveRecordByIdNegative() {
        log.info("receiveRecordByIdNegative:");
        Optional<UserMap> user= dp.receiveRecordById(UserMap.class,1L);
        assertSame(Optional.empty(), user);
    }

    @Test
    public void deleteRecordPositive() {
        log.info("deleteRecordPositive:");
        initRecord(dp);
        assertTrue(dp.deleteRecord(UserMap.class,dp.loadList(UserMap.class).get(0).getId()));
    }

    @Test
    public void deleteRecordNegative() {
        log.info("deleteRecordNegative:");
        assertFalse(dp.deleteRecord(UserMap.class,1L));
    }

    @Test
    public void updateRecordPositive() {
        log.info("updateRecordPositive:");
        initRecord(dp);
        UserMap user = dp.loadList(UserMap.class).get(0);
        user.setName("UPDATED NAME");
        dp.updateRecord(user);
        assertEquals(user.getName(),"UPDATED NAME");
    }
    @Test
    public void updateRecordNegative() {
        log.info("updateRecordNegative:");
        initRecord(dp);
        UserMap user = new UserMap();
        user.setId(200L);
        assertNull(dp.receiveRecordById(UserMap.class, 200L).orElse(null));
        dp.updateRecord(user);
        assertNull(dp.receiveRecordById(UserMap.class, 200L).orElse(null));
    }

    @Test
    public void addRecordPositive() {
        log.info("addRecordPositive:");
        log.info(UserMap.class.getSimpleName());
        assertTrue(dp.loadList(UserMap.class).isEmpty());
        initRecord(dp);
        assertFalse(dp.loadList(UserMap.class).isEmpty());
    }

    public static UserMap createDefaultUser(){
        UserMap user = new UserMap();
        user.setName(DEFAULT_USERNAME);
        user.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        Map<Long, OrderType> idMap = new HashMap<>();
        idMap.put(1L, OrderType.CLASSIC);
        idMap.put(2L, OrderType.CLASSIC);
        idMap.put(3L, OrderType.AMBIENT);
        user.setOrdersId(idMap);
        return user;
    }
}
