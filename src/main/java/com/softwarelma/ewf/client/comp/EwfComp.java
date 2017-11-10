package com.softwarelma.ewf.client.comp;

import java.io.Serializable;
import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.entity.EwfEntityAbstract;
import com.softwarelma.ewf.client.EwfClientWeb;
import com.softwarelma.ewf.server.EwfServer;
import com.vaadin.ui.Component;

public interface EwfComp<T extends EwfEntityAbstract> extends Serializable {

    public EwfServer getServer() throws EpeAppException;

    public EwfClientWeb getWeb() throws EpeAppException;

    public Component getContent(T entity) throws EpeAppException;

    public Component getContent(List<T> listEntity) throws EpeAppException;

    public Component getContent(List<T> listEntity, List<String> listAttributeForDescription,
            List<String> listAttributeForSession) throws EpeAppException;

}
