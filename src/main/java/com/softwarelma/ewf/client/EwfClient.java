package com.softwarelma.ewf.client;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppLogger;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.vaadin.server.VaadinService;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.UI;

public class EwfClient {

    public void setSessionAttributeNotNull(String name, Object value) throws EpeAppException {
        EpeAppUtils.checkNull("value", value);
        this.setSessionAttributeOrNull(name, value);
    }

    public void setSessionAttributeOrNull(String name, Object value) throws EpeAppException {
        EpeAppUtils.checkEmpty("name", name);
        EpeAppLogger.log("Setting session attribute with name: " + name + ", and value: " + value);
        this.getWrappedSession().setAttribute(name, value);
    }

    public Object getSessionAttributeNotNull(String name) throws EpeAppException {
        Object value = this.getSessionAttributeOrNull(name);
        EpeAppUtils.checkNull("value", value);
        return value;
    }

    public Object getSessionAttributeOrNull(String name) throws EpeAppException {
        EpeAppUtils.checkEmpty("name", name);
        return this.getWrappedSession().getAttribute(name);
    }

    public void removeSessionAttribute(String name) throws EpeAppException {
        EpeAppUtils.checkEmpty("name", name);
        this.getWrappedSession().removeAttribute(name);
    }

    private WrappedSession getWrappedSession() {
        return VaadinService.getCurrentRequest().getWrappedSession();
    }

    public void navigate(UI ui, String pageName) throws EpeAppException {
*
    }

}
