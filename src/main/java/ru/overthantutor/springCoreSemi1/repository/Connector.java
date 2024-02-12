package ru.overthantutor.springCoreSemi1.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.TimeZone;

/**
 * Class which help to connect to Database
 */
public class Connector {
    /**
     * Registry service
     */
    private final StandardServiceRegistry registry;
    /**
     * Session factory which help to build DB sessions
     */
    private final SessionFactory sessionFactory;

    /**
     * Connector constructor
     * Configuring registry service
     */
    public Connector() {
        registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    /**
     * Public method which give new DB session
     * @return new DB session
     */
    public Session getSession() {
        return sessionFactory.withOptions()
                .jdbcTimeZone(TimeZone.getTimeZone("UTC"))
                .openSession();
    }
}
