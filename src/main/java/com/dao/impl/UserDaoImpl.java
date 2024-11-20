package com.dao.impl;

import com.dao.UserDao;
import com.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional
    public User saveUser(User user) {
        hibernateTemplate.save(user);
        // Explicitly flush the Hibernate session to execute the SQL immediately
        hibernateTemplate.flush();
        return user; // The user is already updated with the ID after save
    }


    @Override
    @Transactional
    public Optional<User> findByUsername(String username) {
        return hibernateTemplate.execute(session -> {
            Query<User> query = session.createQuery("FROM User WHERE username = ?1", User.class);
            query.setParameter(1, username);
            return query.uniqueResultOptional();
        });
    }

    @Override
    @Transactional
    public Optional<User> findByEmailId(String emailId) {
        return hibernateTemplate.execute(session -> {
            Query<User> query = session.createQuery("FROM User WHERE emailId = ?1", User.class);
            query.setParameter(1, emailId);
            return query.uniqueResultOptional();
        });
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return hibernateTemplate.get(User.class, id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        hibernateTemplate.update(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        hibernateTemplate.delete(user);
    }
}

