package ru.sfedu.crm.lab5.api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.crm.enums.Rate;
import ru.sfedu.crm.enums.RequestStatus;
import ru.sfedu.crm.lab5.model.Feedback;
import ru.sfedu.crm.lab5.model.Privilege;
import ru.sfedu.crm.lab5.model.Request;
import ru.sfedu.crm.lab5.model.User;

import java.security.PrivilegedExceptionAction;
import java.util.*;


import static org.junit.Assert.*;
import static ru.sfedu.crm.Constants.*;

public class DataProviderLab5Test {

    private static Logger log = LogManager.getLogger(DataProviderLab5Test.class);
    DataProviderLab5 dp = new DataProviderLab5();

    public static void initRecord(DataProviderLab5 dp) {
        log.debug("initRecord[0]: Start initiate record");
        User user = createDefaultUser();
        Privilege privilege = createDefaultPrivilege(user);
        Request request = createDefaultRequest(user);
        Feedback feedback = createDefaultFeedback(request);
        dp.addRecord(user);
        dp.addRecord(privilege);
        dp.addRecord(request);
        dp.addRecord(feedback);
    }

    @Before
    public void beforeRun(){
        log.info("Before run:");
        dp.clearTable(Feedback.class);
        dp.clearTable(Request.class);
        dp.clearTable(Privilege.class);
        dp.clearTable(User.class);
    }

    @Test
    public void loadListPositive() {
        log.info("loadListPositive:");
        initRecord(dp);
        List list = dp.loadList(User.class);
        assertEquals(1, list.size());
        list = dp.loadList(Privilege.class);
        assertEquals(1, list.size());
        list = dp.loadList(Request.class);
        assertEquals(1, list.size());
        list = dp.loadList(Feedback.class);
        assertEquals(1, list.size());
    }

    @Test
    public void loadListNativeSql() {
        log.info("loadListNativeSql:");
        initRecord(dp);
        List list = dp.loadListNativeSql(User.class);
        assertEquals(1, list.size());
        list = dp.loadListNativeSql(Privilege.class);
        assertEquals(1, list.size());
        list = dp.loadListNativeSql(Request.class);
        assertEquals(1, list.size());
        list = dp.loadListNativeSql(Feedback.class);
        assertEquals(1, list.size());
    }
    @Test
    public void loadListCriteria() {
        log.info("loadListCriteria:");
        initRecord(dp);
        List list = dp.loadListCriteria(User.class);
        assertEquals(1, list.size());
        list = dp.loadListCriteria(Privilege.class);
        assertEquals(1, list.size());
        list = dp.loadListCriteria(Request.class);
        assertEquals(1, list.size());
        list = dp.loadListCriteria(Feedback.class);
        assertEquals(1, list.size());
    }

    @Test
    public void loadListNegative() {
        log.info("loadListNegative:");
        List list = dp.loadList(User.class);
        assertEquals(0, list.size());
        list = dp.loadList(Privilege.class);
        assertEquals(0, list.size());
        list = dp.loadList(Request.class);
        assertEquals(0, list.size());
        list = dp.loadList(Feedback.class);
        assertEquals(0, list.size());
    }

    @Test
    public void receiveRecordByIdPositive() {
        log.info("receiveRecordByIdPositive:");
        initRecord(dp);
        Optional<User> user= dp.receiveRecordById(User.class,dp.loadList(User.class).get(0).getId());
        assertNotSame(Optional.empty(), user);
        Optional<Privilege> privilege= dp.receiveRecordById(Privilege.class,dp.loadList(Privilege.class).get(0).getId());
        assertNotSame(Optional.empty(), privilege);
        Optional<Request> request= dp.receiveRecordById(Request.class,dp.loadList(Request.class).get(0).getId());
        assertNotSame(Optional.empty(), request);
        Optional<Feedback> feedback= dp.receiveRecordById(Feedback.class,dp.loadList(Feedback.class).get(0).getId());
        assertNotSame(Optional.empty(), feedback);
    }

    @Test
    public void receiveRecordByIdNegative() {
        log.info("receiveRecordByIdNegative:");
        Optional<User> user= dp.receiveRecordById(User.class,1L);
        assertSame(Optional.empty(), user);
        Optional<Privilege> privilege= dp.receiveRecordById(Privilege.class,1L);
        assertSame(Optional.empty(), privilege);
        Optional<Request> request= dp.receiveRecordById(Request.class,1L);
        assertSame(Optional.empty(), request);
        Optional<Feedback> feedback= dp.receiveRecordById(Feedback.class,1L);
        assertSame(Optional.empty(), feedback);
    }

    @Test
    public void deleteRecordPositive() {
        log.info("deleteRecordPositive:");
        initRecord(dp);
        assertTrue(dp.deleteRecord(Feedback.class,dp.loadList(Feedback.class).get(0).getId()));
        assertTrue(dp.deleteRecord(Request.class,dp.loadList(Request.class).get(0).getId()));
        assertTrue(dp.deleteRecord(Privilege.class,dp.loadList(Privilege.class).get(0).getId()));
        assertTrue(dp.deleteRecord(User.class,dp.loadList(User.class).get(0).getId()));
    }

    @Test
    public void deleteRecordNegative() {
        log.info("deleteRecordNegative:");
        assertFalse(dp.deleteRecord(Feedback.class,1L));
        assertFalse(dp.deleteRecord(Request.class,1L));
        assertFalse(dp.deleteRecord(Privilege.class,1L));
        assertFalse(dp.deleteRecord(User.class,1L));
    }

    @Test
    public void updateRecordPositive() {
        log.info("updateRecordPositive:");
        initRecord(dp);
        User user = dp.loadList(User.class).get(0);
        Privilege privilege = dp.loadList(Privilege.class).get(0);
        Feedback feedback = dp.loadList(Feedback.class).get(0);
        Request request = dp.loadList(Request.class).get(0);
        user.setName("UPDATED NAME");
        privilege.setPrivilegeName("UPDATED NAME");
        feedback.setMessage("UPDATED MESSAGE");
        request.setDescription("UPDATED DESCRIPTION");
        dp.updateRecord(user);
        dp.updateRecord(privilege);
        dp.updateRecord(feedback);
        dp.updateRecord(request);
        assertEquals(user.getName(),"UPDATED NAME");
        assertEquals(privilege.getPrivilegeName(),"UPDATED NAME");
        assertEquals(feedback.getMessage(),"UPDATED MESSAGE");
        assertEquals(request.getDescription(),"UPDATED DESCRIPTION");
    }
    @Test
    public void updateRecordNegative() {
        log.info("updateRecordNegative:");
        initRecord(dp);
        User user = new User();
        user.setId(200L);
        Privilege privilege = new Privilege();
        privilege.setId(200L);
        Feedback feedback = new Feedback();
        feedback.setId(200L);
        Request request = new Request();
        request.setId(200L);
        assertNull(dp.receiveRecordById(User.class, 200L).orElse(null));
        assertNull(dp.receiveRecordById(Privilege.class, 200L).orElse(null));
        assertNull(dp.receiveRecordById(Feedback.class, 200L).orElse(null));
        assertNull(dp.receiveRecordById(Request.class, 200L).orElse(null));
        dp.updateRecord(user);
        dp.updateRecord(privilege);
        dp.updateRecord(feedback);
        dp.updateRecord(request);
        assertNull(dp.receiveRecordById(User.class, 200L).orElse(null));
        assertNull(dp.receiveRecordById(Privilege.class, 200L).orElse(null));
        assertNull(dp.receiveRecordById(Feedback.class, 200L).orElse(null));
        assertNull(dp.receiveRecordById(Request.class, 200L).orElse(null));
    }

    @Test
    public void addRecordPositive() {
        log.info("addRecordPositive:");
        assertTrue(dp.loadList(User.class).isEmpty());
        assertTrue(dp.loadList(Privilege.class).isEmpty());
        assertTrue(dp.loadList(Feedback.class).isEmpty());
        assertTrue(dp.loadList(Request.class).isEmpty());
        initRecord(dp);
        assertFalse(dp.loadList(User.class).isEmpty());
        assertFalse(dp.loadList(Privilege.class).isEmpty());
        assertFalse(dp.loadList(Feedback.class).isEmpty());
        assertFalse(dp.loadList(Request.class).isEmpty());
    }

    @Test
    public void SpeedDifferenceHQl(){
        long n,m,result;
        n = System.currentTimeMillis();
        loadListPositive();
        m = System.currentTimeMillis();
        result = m-n;
        log.info("Time spent on HQL:"+result);
    }

    @Test
    public void SpeedDifferenceNativeSql(){
        long n,m,result;
        n = System.currentTimeMillis();
        loadListNativeSql();
        m = System.currentTimeMillis();
        result = m-n;
        log.info("Time spent on NativeSql:"+result);
    }
    @Test
    public void SpeedDifferenceCriteria(){
        long n,m,result;
        n = System.currentTimeMillis();
        loadListCriteria();
        m = System.currentTimeMillis();
        result = m-n;
        log.info("Time spent on Criteria:"+result);
    }

    public static User createDefaultUser(){
        User user = new User();
        user.setName(DEFAULT_USERNAME);
        user.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        return user;
    }

    public static Privilege createDefaultPrivilege(User user){
        Privilege privilege = new Privilege();
        privilege.setPrivilegeName(DEFAULT_PRIVILEGENAME);
        privilege.setStatus(true);
        Set<User> set = new HashSet<>();
        set.add(user);
        privilege.setUsers(set);
        return privilege;
    }

    public static Request createDefaultRequest(User user){
        Request request = new Request();
        request.setDate(DEFAULT_DATE);
        request.setUser(user);
        request.setDescription(DEFAULT_DESCRIPTION);
        request.setStatus(RequestStatus.DONE);
        return request;
    }

    public static Feedback createDefaultFeedback(Request request){
        Feedback feedback = new Feedback();
        feedback.setMessage(DEFAULT_DESCRIPTION);
        feedback.setRate(Rate.AVERAGE);
        feedback.setRequest(request);
        return feedback;
    }
}
