package com.softwarelma.ewf.backend.entity;

public class EwfEntityColumn extends EwfEntityAbstract {

    private static final long serialVersionUID = 1L;
    private EwfEntityTable table;

    public EwfEntityTable getTable() {
        return table;
    }

    public void setTable(EwfEntityTable table) {
        this.table = table;
    }

}
