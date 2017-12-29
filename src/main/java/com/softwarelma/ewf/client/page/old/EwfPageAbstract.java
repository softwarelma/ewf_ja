package com.softwarelma.ewf.client.page.old;

import java.util.ArrayList;
import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.backend.entity.old.EwfEntityAbstract;
import com.softwarelma.ewf.client.comp.old.EwfCompFooter;
import com.softwarelma.ewf.client.comp.old.EwfCompHeader;
import com.softwarelma.ewf.client.old.EwfClientAbstractGuiContent;
import com.softwarelma.ewf.client.old.EwfClientWeb;
import com.softwarelma.ewf.server.old.EwfServer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;

public abstract class EwfPageAbstract extends EwfClientAbstractGuiContent implements EwfPage {

    private static final long serialVersionUID = 1L;
    private final EwfClientWeb web;

    protected EwfPageAbstract(EwfClientWeb web) throws EpeAppException {
        EpeAppUtils.checkNull("web", web);
        this.web = web;
    }

    @Override
    public EwfServer getServer() {
        return this.web.getServer();
    }

    @Override
    public EwfClientWeb getWeb() throws EpeAppException {
        return web;
    }

    /**
     * all layouts contanins header and footer with a 100% of width
     * 
     * center | westCenter | eastCenter | westEastCenter
     */
    protected GridLayout buildGridLayoutTemplateCenter() throws EpeAppException {
        GridLayout gridLayout = new GridLayout(1, 3);
        gridLayout.setSizeFull();
        gridLayout.setRowExpandRatio(0, 0f);
        gridLayout.setRowExpandRatio(1, 1f);
        gridLayout.setRowExpandRatio(2, 0f);

        List<EwfEntityAbstract> listEntity = new ArrayList<>();
        EwfCompHeader<EwfEntityAbstract> header = new EwfCompHeader<>(web);
        Component compHeader = header.getContent(listEntity);
        gridLayout.addComponent(compHeader, 0, 0);
        gridLayout.setComponentAlignment(compHeader, Alignment.TOP_CENTER);

        EwfEntityAbstract entity = null;
        EwfCompFooter<EwfEntityAbstract> footer = new EwfCompFooter<>(web);
        Component compFooter = footer.getContent(entity);
        gridLayout.addComponent(compFooter, 0, 2);
        gridLayout.setComponentAlignment(compFooter, Alignment.BOTTOM_CENTER);

        return gridLayout;
    }

}
