package com.softwarelma.ewf.backend.entity;

public class EwfEntityTable extends EwfEntityAbstract {

    private static final long serialVersionUID = 1L;
    private EwfEntitySite site;

    public EwfEntitySite getSite() {
        return site;
    }

    public void setSite(EwfEntitySite site) {
        this.site = site;
    }

}
