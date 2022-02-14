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
import java.sql.SQLException;
import java.util.List;

import static ru.sfedu.crm.Constants.*;
import static ru.sfedu.crm.utils.ConfigurationUtil.getConfigurationEntry;

public class HibernateMetadataProvider implements IMetadataProvider {

    private static Logger log = LogManager.getLogger(HibernateMetadataProvider.class);

    public HibernateMetadataProvider(){
        log.trace("Initialize database connection, getting driver:");
        try{
            Class.forName(getConfigurationEntry(DB_DRIVER));
            log.trace("Creating tables:");
            Connection connection = connection();
            connection.createStatement().executeUpdate(CREATE_TABLE_USER);
            connection.createStatement().executeUpdate(CREATE_TABLE_CLIENT);
            connection.createStatement().executeUpdate(CREATE_TABLE_WORKER);
            connection.createStatement().executeUpdate(CREATE_TABLE_REQUEST);
            connection.createStatement().executeUpdate(CREATE_TABLE_REQUEST_CATEGORY);
            connection.createStatement().executeUpdate(CREATE_TABLE_FEEDBACK);

        }
        catch(Exception e){
            log.error("Database initialization Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private Connection connection() throws IOException, SQLException {
        log.debug("connection[0]: Initialize db connection:");
        Connection connection = DriverManager.getConnection(getConfigurationEntry(DB_URL)
                , getConfigurationEntry(DB_USER)
                , getConfigurationEntry(DB_PASSWORD));
        log.debug("connection[1]: Connection success");
        return connection;
    }
    private Session getSession(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }

    public List getRecords(String str) {
        Session session = getSession();
        NativeQuery query = session.createSQLQuery(str);
        List resultList = query.getResultList();
        log.debug("getRecords[]: resultList: " + resultList.toString());
        session.close();
        return resultList;
    }

    /**
     * Returns all schemas
     *
     * @return list
     */
    @Override
    public List getSchemas() {
        List resultList = getRecords(SQL_ALL_SCHEMAS);
        log.debug("getRecords[]: resultList: " + resultList.toString());
        return resultList;
    }

    /**
     * Returns all users
     *
     * @return list
     */
    @Override
    public List getUsers() {
        return null;
    }

    /**
     * Returns all languages
     *
     * @return list
     */
    @Override
    public List getLanguages() {
        return null;
    }

    /**
     * Returns all tables
     *
     * @return list
     */
    @Override
    public List getTables() {
        return null;
    }

    /**
     * Returns all columns
     *
     * @return list
     */
    @Override
    public List getColumns() {
        return null;
    }



}
