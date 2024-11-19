package com.dao;

import com.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void saveUserBySessionFactory(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            System.out.println("user >> "+user);
            session.persist(user);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void saveUser(User user) {
        hibernateTemplate.save(user);
        // Explicitly flush the Hibernate session to execute the SQL immediately
        hibernateTemplate.flush();
    }

    public Optional<User> findByUsername(String username) {
        return hibernateTemplate.execute(session -> {
            Query<User> query = session.createQuery("FROM User WHERE username = ?1", User.class);
            query.setParameter(1, username);
            return query.uniqueResultOptional();
        });
    }

    public Optional<User> findByEmailId(String emailId) {
        return hibernateTemplate.execute(session -> {
            Query<User> query = session.createQuery("FROM User WHERE emailId = ?1", User.class);
            query.setParameter(1, emailId);
            return query.uniqueResultOptional();
        });
    }

    public User getUserById(Long id) {
        return hibernateTemplate.get(User.class, id);
    }

    public void updateUser(User user) {
        hibernateTemplate.update(user);
    }

    public void deleteUser(User user) {
        hibernateTemplate.delete(user);
    }
}

