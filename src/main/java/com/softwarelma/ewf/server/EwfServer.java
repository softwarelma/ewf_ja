package com.softwarelma.ewf.server;

import java.io.Serializable;
import java.util.Map;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.EwfBackend;
import com.softwarelma.ewf.client.EwfClient;
import com.softwarelma.ewf.client.page.EwfPageBean;
import com.vaadin.ui.UI;

public class EwfServer implements Serializable {

    private static final long serialVersionUID = 1L;
    private static EwfServer server;
    private final EwfBackend backend = new EwfBackend();
    private final EwfServerPage serverPage = new EwfServerPage(this.backend);
    private final EwfClient client = new EwfClient(this);

    public static EwfServer getInstance() throws EpeAppException {
        if (EwfServer.server != null) {
            return EwfServer.server;
        }

        synchronized (EwfServer.class) {
            if (EwfServer.server != null) {
                return EwfServer.server;
            }

            EwfServer server = new EwfServer();
            EwfServer.server = server;
        }

        return EwfServer.server;
    }

    private EwfServer() throws EpeAppException {
    }

    public void setSessionAttributeNotNull(String name, Object value) throws EpeAppException {
        this.client.setSessionAttributeNotNull(name, value);
    }

    public void setSessionAttributeOrNull(String name, Object value) throws EpeAppException {
        this.client.setSessionAttributeOrNull(name, value);
    }

    public Object getSessionAttributeNotNull(String name) throws EpeAppException {
        return this.client.getSessionAttributeNotNull(name);
    }

    public Object getSessionAttributeOrNull(String name) throws EpeAppException {
        return this.client.getSessionAttributeOrNull(name);
    }

    public void removeSessionAttribute(String name) throws EpeAppException {
        this.client.removeSessionAttribute(name);
    }

    public void loadPage(UI ui, String pageName) throws EpeAppException {
        this.client.loadPage(ui, pageName);
    }

    public Map<String, EwfPageBean> retrieveMapPageNameAndPageBean() throws EpeAppException {
        return this.serverPage.retrieveMapPageNameAndPageBean();
    }

}
