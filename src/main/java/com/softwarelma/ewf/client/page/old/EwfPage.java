package com.softwarelma.ewf.client.page.old;

import java.io.Serializable;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.old.EwfClientWeb;
import com.softwarelma.ewf.server.old.EwfServer;
import com.vaadin.ui.Component;

public interface EwfPage extends Serializable {

    public EwfServer getServer() throws EpeAppException;

    public EwfClientWeb getWeb() throws EpeAppException;

    public Component getContent() throws EpeAppException;

}
