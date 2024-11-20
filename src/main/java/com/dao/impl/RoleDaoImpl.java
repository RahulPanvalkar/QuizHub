package com.dao.impl;

import com.dao.RoleDao;
import com.entities.Role;
import com.entities.User;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional
    public Optional<Role> findByRoleId(String roleId) {
        return hibernateTemplate.execute(session -> {
            Query<Role> query = session.createQuery("FROM Role WHERE roleId = ?1", Role.class);
            query.setParameter(1, roleId);
            return query.uniqueResultOptional();
        });
    }

}
