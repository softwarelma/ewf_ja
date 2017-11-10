package com.softwarelma.ewf.backend.entity;

public class EwfEntityTrigger extends EwfEntityAbstract {

    private static final long serialVersionUID = 1L;
    private EwfEntityTable table;
    private Boolean before;
    private Long operation;

    public EwfEntityTable getTable() {
        return table;
    }

    public void setTable(EwfEntityTable table) {
        this.table = table;
    }

    public Boolean getBefore() {
        return before;
    }

    public void setBefore(Boolean before) {
        this.before = before;
    }

    public Long getOperation() {
        return operation;
    }

    public void setOperation(Long operation) {
        this.operation = operation;
    }

}
