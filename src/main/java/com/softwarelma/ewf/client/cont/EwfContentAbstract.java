package com.softwarelma.ewf.client.cont;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.client.EwfClient;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Component;

public abstract class EwfContentAbstract extends EwfContAbstract implements EwfContentInterface {

    private static final long serialVersionUID = 1L;
    private final Component component;// for a comp it is an AbstractLayout

    protected EwfContentAbstract(EwfClient client, String name, String componentClassName) throws EpeAppException {
        super(client, name);
        String compElem = null;
        String className = null;

        try {
            if (this.isComp()) {
                compElem = "layout";
                className = client.getClassNameLayoutNotNull(name);
                this.component = (AbstractLayout) Class.forName(className).newInstance();
            } else if (this.isElem()) {
                compElem = "Vaadin component";
                className = componentClassName;
                EpeAppUtils.checkEmpty("componentClassName", componentClassName);
                this.component = (AbstractComponent) Class.forName(className).newInstance();
            } else {
                throw new EpeAppException("A content should be either a comp or an elem.");
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new EpeAppException(
                    "Invalid " + compElem + " class name \"" + className + "\" for comp name \"" + name + "\".", e);
        }
    }

    @Override
    public Component getComponent() {
        return component;
    }

    @Override
    public boolean isContainer() {
        return false;
    }

    @Override
    public boolean isContent() {
        return true;
    }

}
