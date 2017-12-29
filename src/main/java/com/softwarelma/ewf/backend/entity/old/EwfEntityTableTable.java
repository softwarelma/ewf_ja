package com.softwarelma.ewf.backend.entity.old;

public class EwfEntityTableTable extends EwfEntityAbstract {

    private static final long serialVersionUID = 1L;
    private EwfEntityTable tableParent;
    private EwfEntityTable tableChild;

    public EwfEntityTable getTableParent() {
        return tableParent;
    }

    public void setTableParent(EwfEntityTable tableParent) {
        this.tableParent = tableParent;
    }

    public EwfEntityTable getTableChild() {
        return tableChild;
    }

    public void setTableChild(EwfEntityTable tableChild) {
        this.tableChild = tableChild;
    }

}
