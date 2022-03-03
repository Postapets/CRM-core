package ru.sfedu.crm.lab4.api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.crm.enums.OrderType;
import ru.sfedu.crm.lab4.model.collection.Order;
import ru.sfedu.crm.lab4.model.collection.UserCollection;

import java.util.*;


import static org.junit.Assert.*;
import static ru.sfedu.crm.Constants.*;

public class UserCollectionTest {

    private static Logger log = LogManager.getLogger(UserCollectionTest.class);
    DataProviderLab4 dp = new DataProviderLab4();

    public static void initRecord(DataProviderLab4 dp) {
        log.debug("initRecord[0]: Start initiate record");
        UserCollection user = createDefaultUser();
        dp.addRecord(user);
    }

    @Before
    public void beforeRun(){
        log.info("Before run:");
        dp.clearTableCollection(UserCollection.class);
    }

    @Test
    public void loadListPositive() {
        log.info("loadListPositive:");
        initRecord(dp);
        List list = dp.loadList(UserCollection.class);
        assertEquals(1, list.size());
    }

    @Test
    public void loadListNegative() {
        log.info("loadListNegative:");
        List list = dp.loadList(UserCollection.class);
        assertEquals(0, list.size());
    }

    @Test
    public void receiveRecordByIdPositive() {
        log.info("receiveRecordByIdPositive:");
        initRecord(dp);
        Optional<UserCollection> user= dp.receiveRecordById(UserCollection.class,dp.loadList(UserCollection.class).get(0).getId());
        assertNotSame(Optional.empty(), user);
    }

    @Test
    public void receiveRecordByIdNegative() {
        log.info("receiveRecordByIdNegative:");
        Optional<UserCollection> user= dp.receiveRecordById(UserCollection.class,1L);
        assertSame(Optional.empty(), user);
    }

    @Test
    public void deleteRecordPositive() {
        log.info("deleteRecordPositive:");
        initRecord(dp);
        assertTrue(dp.deleteRecord(UserCollection.class,dp.loadList(UserCollection.class).get(0).getId()));
    }

    @Test
    public void deleteRecordNegative() {
        log.info("deleteRecordNegative:");
        assertFalse(dp.deleteRecord(UserCollection.class,1L));
    }

    @Test
    public void updateRecordPositive() {
        log.info("updateRecordPositive:");
        initRecord(dp);
        UserCollection user = dp.loadList(UserCollection.class).get(0);
        user.setName("UPDATED NAME");
        dp.updateRecord(user);
        assertEquals(user.getName(),"UPDATED NAME");
    }
    @Test
    public void updateRecordNegative() {
        log.info("updateRecordNegative:");
        initRecord(dp);
        UserCollection user = new UserCollection();
        user.setId(200L);
        assertNull(dp.receiveRecordById(UserCollection.class, 200L).orElse(null));
        dp.updateRecord(user);
        assertNull(dp.receiveRecordById(UserCollection.class, 200L).orElse(null));
    }

    @Test
    public void addRecordPositive() {
        log.info("addRecordPositive:");
        log.info(UserCollection.class.getSimpleName());
        assertTrue(dp.loadList(UserCollection.class).isEmpty());
        initRecord(dp);
        assertFalse(dp.loadList(UserCollection.class).isEmpty());
    }

    public static UserCollection createDefaultUser(){
        UserCollection user = new UserCollection();
        Order order = createDefaultOrder();
        user.setName(DEFAULT_USERNAME);
        user.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        Set<Order> idSet = new HashSet<>();
        idSet.add(order);
        user.setOrder(idSet);
        return user;
    }

    public static Order createDefaultOrder(){
        Order order = new Order();
        order.setOrderType(OrderType.CLASSIC);
        order.setId(1L);
        return order;
    }
}
