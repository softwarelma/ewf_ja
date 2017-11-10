package com.softwarelma.ewf.backend.entity;

public class EwfEntityNavigation extends EwfEntityAbstract {

    private static final long serialVersionUID = 1L;
    private EwfEntitySite site;
    private EwfEntityComponent component;
    private EwfEntityPage pageSource;
    private EwfEntityPage pageDestination;

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

    public EwfEntityPage getPageSource() {
        return pageSource;
    }

    public void setPageSource(EwfEntityPage pageSource) {
        this.pageSource = pageSource;
    }

    public EwfEntityPage getPageDestination() {
        return pageDestination;
    }

    public void setPageDestination(EwfEntityPage pageDestination) {
        this.pageDestination = pageDestination;
    }

}
