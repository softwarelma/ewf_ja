package com.softwarelma.ewf.backend.old;

import java.io.Serializable;

import com.softwarelma.ewf.backend.entity.old.EwfEntityAbstract;

public class EwfBackendOperation implements Serializable {

    private static final long serialVersionUID = 1L;
    private Type type;
    private String daoClassName;
    private EwfEntityAbstract entity;
    private Long id;

    public enum Type {
        CREATE, UPDATE, DELETE, READ, READ_LIST
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDaoClassName() {
        return daoClassName;
    }

    public void setDaoClassName(String daoClassName) {
        this.daoClassName = daoClassName;
    }

    public EwfEntityAbstract getEntity() {
        return entity;
    }

    public void setEntity(EwfEntityAbstract entity) {
        this.entity = entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
