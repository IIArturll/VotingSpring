package dao.util;

import dao.api.IConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ConnectionManager implements IConnection {

    EntityManagerFactory factory;

    public ConnectionManager(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public EntityManager open() {
        return factory.createEntityManager();
    }

    @Override
    public void close() {
        factory.close();
    }
}