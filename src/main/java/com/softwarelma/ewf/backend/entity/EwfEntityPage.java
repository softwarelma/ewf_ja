package com.softwarelma.ewf.backend.entity;

public class EwfEntityPage extends EwfEntityAbstract {

    private static final long serialVersionUID = 1L;
    private EwfEntitySite site;
    private EwfEntityComponent component;

    public EwfEntitySite getSite() {
        return site;
    }

    public void setSite(EwfEntitySite site) {
        this.site = site;
    }

    public EwfEntityComponent getComponent() {
        return component;
    }

    public void setComponent(EwfEntityComponent component) {
        this.component = component;
    }

}
