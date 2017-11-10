package com.softwarelma.ewf.client.page;

import java.io.Serializable;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.EwfClientWeb;
import com.softwarelma.ewf.server.EwfServer;
import com.vaadin.ui.Component;

public interface EwfPage extends Serializable {

    public EwfServer getServer() throws EpeAppException;

    public EwfClientWeb getWeb() throws EpeAppException;

    public Component getContent() throws EpeAppException;

}
