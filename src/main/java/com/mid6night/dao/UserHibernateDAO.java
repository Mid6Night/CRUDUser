package com.mid6night.dao;

import com.mid6night.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private Session session;
    private static UserHibernateDAO userHibernateDAO;
    private static SessionFactory singleSessionFactory;

    private UserHibernateDAO() {

    }

    public static UserHibernateDAO getInstance() {
        singleSessionFactory = getSessionFactory();
        if (userHibernateDAO == null) {
            userHibernateDAO =
                    new UserHibernateDAO();
        }
        return userHibernateDAO;
    }

    @Override
    public List<User> getAllUser() {
        session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        List<User> cars = criteria.list();
        session.close();
        return cars;
    }

    @Override
    public User getUser(long id) {
        session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        User user = (User) criteria.add(Restrictions.eq("id", id))
                .uniqueResult();
        session = sessionFactory.openSession();
        return user;
    }

    @Override
    public void deleteUser(long id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM " + User.class.getName()
                + " WHERE id = " + id).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void addUser(User user) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    @SuppressWarnings("UnusedDeclaration")
    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/example?serverTimezone=Europe/Moscow&user=root&password=root&useSSL=false");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        return configuration;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
