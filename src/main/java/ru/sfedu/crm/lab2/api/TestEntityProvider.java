package ru.sfedu.crm.lab2.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.sfedu.crm.Constants;
import ru.sfedu.crm.lab2.model.TestEntity;
import ru.sfedu.crm.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestEntityProvider {
    private static Logger log = LogManager.getLogger(TestEntityProvider.class);

    private Session getSession(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }

    public List<TestEntity> loadList(Class<TestEntity> entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            log.debug("loadList[0]: try to load list of entities from table Test_Entity:");
            Query query = session.createQuery(String.format(Constants.HQL_FROM, entity.getSimpleName()));
            List list = query.list();
            log.debug("loadList[0]: Loading success!");
            log.trace("List of records: " + list);
            transaction.commit();
            session.close();
            return list;
        } catch (Exception e) {
            log.error("loadBeanList[0]: Loading from table Test_Entity Error");
            log.error("loadBeanList[1]: " + e.getClass().getName() + ": " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public Optional<TestEntity> receiveRecordById(Class<TestEntity> entity, Long id) {
        Session session = this.getSession();
        try{
            log.debug("receiveRecordById[0]: try to receive entity by id:" + entity);
            TestEntity testEntity = session.get(entity, id);
            log.debug("receiveRecordById[0]: Receiving complete");
            session.close();
            return Optional.of(testEntity);
        }catch (NullPointerException e){
            log.error("receiveRecordById[0] Record with id = "+id+" not found");
            log.error("receiveRecordById[1]:" + e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
    }

    public Boolean deleteRecord(Class<TestEntity> entity, Long id) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            log.debug("deleteRecord[0]: try to delete entity:" + entity);
            TestEntity testEntity = session.get(entity, id);
            session.delete(testEntity);
            log.debug("deleteRecord[1]: entity successfully deleted!");
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            log.error("deleteRecord[0] Record with id = "+id+" not found");
            log.error("deleteRecord[1]:" + e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public Boolean updateRecord(TestEntity entity) {
        try {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        log.debug("updateRecord[0]: try to merge(update) entity:" + entity);
        session.merge(entity);
        log.debug("updateRecord[0]: entity updated!");
        transaction.commit();
        session.close();
            return true;
        }catch(Exception e){
            log.error("updateRecord[0]: updating error!");
            log.error("updateRecord[1]:" + e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public Boolean addRecord(TestEntity entity) {
        try {
            Session session = this.getSession();
            Transaction transaction = session.beginTransaction();
            log.debug("addRecord[0]: try to save entity:" + entity);
            session.save(entity);
            log.debug("addRecord[1]: entity saved!");
            transaction.commit();
            session.close();
            return true;
        }catch(Exception e){
            log.error("addRecord[0]: saving error!");
            log.error("addRecord[1]:" + e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public void clearTable(Class<TestEntity> entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        log.debug("clearTable[0]: try to clear table Test_Entity:");
        Query query = session.createQuery(String.format(Constants.HQL_DELETE_FROM,entity.getSimpleName()));
        query.executeUpdate();
        log.debug("clearTable[0]: clearing table Test_Entity success!");
        transaction.commit();
        session.close();
        return ;
    }


}
