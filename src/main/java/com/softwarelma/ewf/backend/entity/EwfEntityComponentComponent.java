package com.softwarelma.ewf.backend.entity;

public class EwfEntityComponentComponent extends EwfEntityAbstract {

    private static final long serialVersionUID = 1L;
    private EwfEntityComponent componentParent;
    private EwfEntityComponent componentChild;

    public EwfEntityComponent getComponentParent() {
        return componentParent;
    }

    public void setComponentParent(EwfEntityComponent componentParent) {
        this.componentParent = componentParent;
    }

    public EwfEntityComponent getComponentChild() {
        return componentChild;
    }

    public void setComponentChild(EwfEntityComponent componentChild) {
        this.componentChild = componentChild;
    }

}
