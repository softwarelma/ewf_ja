package com.softwarelma.ewf.backend.entity.old;

public class EwfEntityCss extends EwfEntityAbstract {

    private static final long serialVersionUID = 1L;
    private EwfEntityComponent component;
    private String className;

    public EwfEntityComponent getComponent() {
        return component;
    }

    public void setComponent(EwfEntityComponent component) {
        this.component = component;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
