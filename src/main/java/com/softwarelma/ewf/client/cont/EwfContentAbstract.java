package com.softwarelma.ewf.client.cont;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.EwfClient;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Component;

public abstract class EwfContentAbstract extends EwfContAbstract implements EwfContentInterface {

    private static final long serialVersionUID = 1L;
    private final Component component;// for a comp it is an AbstractLayout

    protected EwfContentAbstract(EwfClient client, String name) throws EpeAppException {
        super(client, name);
        String componentClassName = null;

        try {
            if (this.isComp()) {
                componentClassName = client.getClassNameLayoutNotNull(name);
                this.component = (AbstractLayout) Class.forName(componentClassName).newInstance();
            } else if (this.isElem()) {
                componentClassName = client.getClassNameComponentNotNull(name);
                this.component = (AbstractComponent) Class.forName(componentClassName).newInstance();
            } else {
                throw new EpeAppException("A content should be either a comp or an elem.");
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new EpeAppException(
                    "Invalid layout class name \"" + componentClassName + "\" for comp name \"" + name + "\".", e);
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
