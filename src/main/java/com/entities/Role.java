package com.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {

    @Id
    @Column(length = 4)
    private String roleId;

    @Column(nullable = false, unique = true)
    private String descr;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private Set<User> users = new HashSet<>();



    public Role() {
    }

    public Role(String roleId, String descr) {
        this.roleId = roleId;
        this.descr = descr;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public String toString() {
        return String.format("RoleId : %s, Descr : %s", roleId, descr);
    }
}
