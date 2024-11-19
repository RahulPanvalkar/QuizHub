package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {

    @Id
    private String roleId;

    @Column(nullable = false, unique = true)
    private String descr;

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
