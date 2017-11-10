package com.softwarelma.ewf.backend.entity;

public class EwfEntityListener extends EwfEntityAbstract {

    private static final long serialVersionUID = 1L;
    private EwfEntityComponent componentListener;
    private EwfEntityComponent componentSender;
    private String attribute;

    public EwfEntityComponent getComponentListener() {
        return componentListener;
    }

    public void setComponentListener(EwfEntityComponent componentListener) {
        this.componentListener = componentListener;
    }

    public EwfEntityComponent getComponentSender() {
        return componentSender;
    }

    public void setComponentSender(EwfEntityComponent componentSender) {
        this.componentSender = componentSender;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

}
