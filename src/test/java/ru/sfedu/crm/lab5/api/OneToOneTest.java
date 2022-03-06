package ru.sfedu.crm.lab5.api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.crm.enums.Rate;
import ru.sfedu.crm.enums.RequestStatus;
import ru.sfedu.crm.lab4.model.set.UserSet;
import ru.sfedu.crm.lab5.model.one_to_one.Feedback;
import ru.sfedu.crm.lab5.model.one_to_one.Request;

import java.util.*;


import static org.junit.Assert.*;
import static ru.sfedu.crm.Constants.*;

public class OneToOneTest {

    private static Logger log = LogManager.getLogger(OneToOneTest.class);
    DataProviderLab5 dp = new DataProviderLab5();

    public static void initRecord(DataProviderLab5 dp) {
        log.debug("initRecord[0]: Start initiate record");
        Request request = createDefaultRequest();
        Feedback feedback = createDefaultFeedback(request);
        dp.addRecord(request);
        dp.addRecord(feedback);
    }

    @Before
    public void beforeRun(){
        log.info("Before run:");
        dp.clearTable(Feedback.class);
        dp.clearTable(Request.class);
    }

    @Test
    public void loadListPositive() {
        log.info("loadListPositive:");
        initRecord(dp);
        List list = dp.loadList(Feedback.class);
        assertEquals(1, list.size());
        list = dp.loadList(Request.class);
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

    public static Request createDefaultRequest(){
        Request request = new Request();
        request.setDate(DEFAULT_DATE);
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
