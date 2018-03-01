package com.softwarelma.ewf.client.elem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p3.db.EpeDbEntity;
import com.softwarelma.ewf.client.EwfClient;
import com.softwarelma.ewf.client.cont.EwfContentAbstract;
import com.softwarelma.ewf.client.cont.EwfContentBean;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;

public abstract class EwfElemAbstract extends EwfContentAbstract implements EwfElemInterface {

    private static final long serialVersionUID = 1L;

    protected EwfElemAbstract(EwfClient client, UI ui, EwfElemBean elemBean, EwfContentBean contentBean)
            throws EpeAppException {
        super(client, ui, elemBean, contentBean);
        this.init(client, ui, elemBean);
    }

    private void init(EwfClient client, UI ui, EwfElemBean elemBean) throws EpeAppException {
        if (elemBean == null)
            return;
        this.initText(elemBean);
        this.initQuery(client, ui, elemBean);
    }

    private void initText(EwfElemBean elemBean) throws EpeAppException {
        if (elemBean.getText() == null)
            return;
        try {
            Method method = this.getComponent().getClass().getMethod("setValue", String.class);
            method.invoke(this.getComponent(), elemBean.getText());
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            throw new EpeAppException("Invalid method setValue", e);
        }
    }

    private void initQuery(EwfClient client, UI ui, EwfElemBean elemBean) throws EpeAppException {
        if (elemBean.getQuerySelect() == null)
            return;
        if (this.getComponent() instanceof Grid) {
            @SuppressWarnings("unchecked")
            Grid<EpeDbEntity> grid = (Grid<EpeDbEntity>) this.getComponent();
            EwfElemAbstractGrid elemGrid = new EwfElemAbstractGrid();
            elemGrid.initGrid(client, grid, elemBean.getQuerySelect(), elemBean.getQueryTable());
        } else {
            throw new EpeAppException("Unknown class instance: " + this.getComponent().getClass().getName());
        }
    }

    @Override
    public boolean isPage() {
        return false;
    }

    @Override
    public boolean isComp() {
        return false;
    }

    @Override
    public boolean isElem() {
        return true;
    }

}
