package ru.sfedu.crm.lab1.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import ru.sfedu.crm.Constants;
import ru.sfedu.crm.utils.HibernateUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ru.sfedu.crm.Constants.*;
import static ru.sfedu.crm.utils.ConfigurationUtil.getConfigurationEntry;

public class HibernateMetadataProvider implements IMetadataProvider {

    private static Logger log = LogManager.getLogger(HibernateMetadataProvider.class);

    private Session getSession(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }

    /**
     * @param string query
     * @return resultList list of needed values
     */
    public List getRecords(String string) {
        Session session = getSession();
        NativeQuery query = session.createSQLQuery(string);
        List resultList = query.getResultList();
        log.trace("getRecords[]: resultList: " + resultList);
        session.close();
        return resultList;
    }

    /**
     * Returns all schemas
     * @return list
     */
    @Override
    public List getSchemas() {
        List resultList = getRecords(SQL_ALL_SCHEMAS);
        log.info("getSchemas[]: resultList: " + resultList.toString());
        return resultList;
    }

    /**
     * Returns all users
     * @return list
     */
    @Override
    public List getUsers() {
        List resultList = getRecords(SQL_ALL_USERS);
        log.info("getUsers[]: resultList: " + resultList);
        return resultList;
    }


    /**
     * Returns all tables
     * @return list
     */
    @Override
    public List getTables() {
        List resultList = getRecords(SQL_ALL_TABLES);
        log.info("getTables[]: resultList: " + resultList);
        return resultList;
    }

    /**
     * Returns all columns
     * @return list
     */
    @Override
    public List getColumns() {
        List resultList = getRecords(SQL_ALL_COLUMNS);
        log.info("getColumns[]: resultList: " + resultList.toString());
        return resultList;
    }


//    public HibernateMetadataProvider(){
//        log.trace("Initialize database connection, getting driver:");
//        try{
//            Class.forName(getConfigurationEntry(DB_DRIVER));
//            log.trace("Creating tables:");
//            Connection connection = connection();
//            connection.createStatement().executeUpdate(CREATE_TABLE_USER);
//            connection.createStatement().executeUpdate(CREATE_TABLE_CLIENT);
//            connection.createStatement().executeUpdate(CREATE_TABLE_WORKER);
//            connection.createStatement().executeUpdate(CREATE_TABLE_REQUEST);
//            connection.createStatement().executeUpdate(CREATE_TABLE_REQUEST_CATEGORY);
//            connection.createStatement().executeUpdate(CREATE_TABLE_FEEDBACK);
//        }
//        catch(Exception e){
//            log.error("Database initialization Error");
//            log.error(e.getClass().getName() + ": " + e.getMessage());
//        }
//    }
//
//    private Connection connection() throws IOException, SQLException {
//        log.debug("connection[0]: Initialize db connection:");
//        Connection connection = DriverManager.getConnection(getConfigurationEntry(DB_URL)
//                , getConfigurationEntry(DB_USER)
//                , getConfigurationEntry(DB_PASSWORD));
//        log.debug("connection[1]: Connection success");
//        return connection;
//    }

}
