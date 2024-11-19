package com.dao;

import com.entities.Role;
import com.entities.User;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public Optional<Role> findByRoleId(String roleId) {
        return hibernateTemplate.execute(session -> {
            Query<Role> query = session.createQuery("FROM Role WHERE roleId = ?1", Role.class);
            query.setParameter(1, roleId);
            return query.uniqueResultOptional();
        });
    }

}
