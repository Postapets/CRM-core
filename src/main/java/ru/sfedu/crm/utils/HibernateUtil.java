package ru.sfedu.crm.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.util.config.ConfigurationException;
import org.hibernate.service.ServiceRegistry;
import ru.sfedu.crm.Constants;

import ru.sfedu.crm.lab2.model.TestEntity;

import ru.sfedu.crm.lab3.model.JoinedTable.*;
import ru.sfedu.crm.lab3.model.MappedSuperclass.*;
import ru.sfedu.crm.lab3.model.SingleTable.*;
import ru.sfedu.crm.lab3.model.TablePerClass.*;
import ru.sfedu.crm.lab4.model.collection.UserCollection;
import ru.sfedu.crm.lab4.model.list.UserList;
import ru.sfedu.crm.lab4.model.map.UserMap;
import ru.sfedu.crm.lab4.model.set.UserSet;
import ru.sfedu.crm.lab5.model.one_to_one.Feedback;
import ru.sfedu.crm.lab5.model.one_to_one.Request;

import java.io.File;
import java.io.IOException;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            Configuration configuration;
            try {
                File file  = new File(ConfigurationUtil.getConfigurationEntry(Constants.DEFAULT_HIBERNATE_PATH));
                configuration = new Configuration().configure(file);
            }catch (IOException | ConfigurationException e){
                configuration = new Configuration().configure();
            }

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            MetadataSources metadataSources = new MetadataSources(serviceRegistry);
            bindEntities(metadataSources);
            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        }
        return sessionFactory;
    }
    public static void bindEntities(MetadataSources metadataSources){
        metadataSources.addAnnotatedClass(TestEntity.class);

        metadataSources.addAnnotatedClass(UserMappedSuperclass.class);
        metadataSources.addAnnotatedClass(ClientMappedSuperclass.class);
        metadataSources.addAnnotatedClass(WorkerMappedSuperclass.class);

        metadataSources.addAnnotatedClass(UserSingleTable.class);
        metadataSources.addAnnotatedClass(WorkerSingleTable.class);
        metadataSources.addAnnotatedClass(ClientSingleTable.class);

        metadataSources.addAnnotatedClass(UserTablePerClass.class);
        metadataSources.addAnnotatedClass(ClientTablePerClass.class);
        metadataSources.addAnnotatedClass(WorkerTablePerClass.class);

        metadataSources.addAnnotatedClass(UserJoinedTable.class);
        metadataSources.addAnnotatedClass(ClientJoinedTable.class);
        metadataSources.addAnnotatedClass(WorkerJoinedTable.class);

        metadataSources.addAnnotatedClass(UserList.class);
        metadataSources.addAnnotatedClass(UserSet.class);
        metadataSources.addAnnotatedClass(UserMap.class);
        metadataSources.addAnnotatedClass(UserCollection.class);

        metadataSources.addAnnotatedClass(Feedback.class);
        metadataSources.addAnnotatedClass(Request.class);
    }

}
