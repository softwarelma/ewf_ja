package com.softwarelma.ewf.client.comp.old;

import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.backend.entity.old.EwfEntityAbstract;
import com.softwarelma.ewf.client.old.EwfClientAbstractGuiContent;
import com.softwarelma.ewf.client.old.EwfClientWeb;
import com.softwarelma.ewf.server.old.EwfServer;

public abstract class EwfCompAbstract<T extends EwfEntityAbstract> extends EwfClientAbstractGuiContent
        implements EwfComp<T> {

    private static final long serialVersionUID = 1L;
    private final EwfClientWeb web;
    private T entity;
    private List<T> listEntity;

    protected EwfCompAbstract(EwfClientWeb web) throws EpeAppException {
        EpeAppUtils.checkNull("web", web);
        this.web = web;
    }

    @Override
    public EwfServer getServer() {
        return this.web.getServer();
    }

    @Override
    public EwfClientWeb getWeb() {
        return web;
    }

    protected T getEntity() throws EpeAppException {
        return entity;
    }

    protected void setEntity(T entity) throws EpeAppException {
        EpeAppUtils.checkNull("entity", entity);
        this.entity = entity;
    }

    protected List<T> getListEntity() throws EpeAppException {
        return listEntity;
    }

    protected void setListEntity(List<T> listEntity) throws EpeAppException {
        EpeAppUtils.checkNull("listEntity", listEntity);
        this.listEntity = listEntity;
    }

}
