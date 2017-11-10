package com.softwarelma.ewf.backend.entity;

public class EwfEntityBinding extends EwfEntityAbstract {

    private static final long serialVersionUID = 1L;
    private EwfEntitySite site;
    private EwfEntityComponent component;
    private EwfEntityColumn columnSource;
    private EwfEntityColumn columnDestination;

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

    public EwfEntityColumn getColumnSource() {
        return columnSource;
    }

    public void setColumnSource(EwfEntityColumn columnSource) {
        this.columnSource = columnSource;
    }

    public EwfEntityColumn getColumnDestination() {
        return columnDestination;
    }

    public void setColumnDestination(EwfEntityColumn columnDestination) {
        this.columnDestination = columnDestination;
    }

}
