package ru.sfedu.crm.utils;

import org.h2.mvstore.cache.CacheLongKeyLIRS;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.util.config.ConfigurationException;
import org.hibernate.service.ServiceRegistry;
import ru.sfedu.crm.Constants;
import ru.sfedu.crm.lab2.model.TestEntity;
import ru.sfedu.crm.lab3.model.JoinedTable.Client;
import ru.sfedu.crm.lab3.model.JoinedTable.User;
import ru.sfedu.crm.lab3.model.JoinedTable.Worker;

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
        metadataSources.addAnnotatedClass(ru.sfedu.crm.lab3.model.MappedSuperclass.User.class);
        metadataSources.addAnnotatedClass(ru.sfedu.crm.lab3.model.MappedSuperclass.Worker.class);
        metadataSources.addAnnotatedClass(ru.sfedu.crm.lab3.model.MappedSuperclass.Client.class);
        metadataSources.addAnnotatedClass(ru.sfedu.crm.lab3.model.JoinedTable.User.class);
        metadataSources.addAnnotatedClass(ru.sfedu.crm.lab3.model.JoinedTable.Worker.class);
        metadataSources.addAnnotatedClass(ru.sfedu.crm.lab3.model.JoinedTable.Client.class);
        metadataSources.addAnnotatedClass(ru.sfedu.crm.lab3.model.SingleTable.User.class);
        metadataSources.addAnnotatedClass(ru.sfedu.crm.lab3.model.SingleTable.Worker.class);
        metadataSources.addAnnotatedClass(ru.sfedu.crm.lab3.model.SingleTable.Client.class);
        metadataSources.addAnnotatedClass(ru.sfedu.crm.lab3.model.TablePerClass.User.class);
        metadataSources.addAnnotatedClass(ru.sfedu.crm.lab3.model.TablePerClass.Worker.class);
        metadataSources.addAnnotatedClass(ru.sfedu.crm.lab3.model.TablePerClass.Client.class);
    }

}
