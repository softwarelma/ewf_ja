package com.softwarelma.ewf.client.page.old;

import java.util.ArrayList;
import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.entity.old.EwfEntityAbstract;
import com.softwarelma.ewf.backend.entity.old.EwfEntitySite;
import com.softwarelma.ewf.backend.entity.old.EwfEntityUser;
import com.softwarelma.ewf.client.comp.old.EwfCompFooter;
import com.softwarelma.ewf.client.comp.old.EwfCompHeader;
import com.softwarelma.ewf.client.comp.old.EwfCompList;
import com.softwarelma.ewf.client.old.EwfClientWeb;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

public class EwfPageAdminHome extends EwfPageAbstract {

    private static final long serialVersionUID = 1L;
    private final EwfCompHeader<EwfEntityAbstract> header;
    private final EwfCompFooter<EwfEntityAbstract> footer;
    private final EwfCompList<EwfEntityUser> listAdmin;
    private final EwfCompList<EwfEntitySite> listSite;

    public EwfPageAdminHome(EwfClientWeb web) throws EpeAppException {
        super(web);
        this.header = new EwfCompHeader<>(web);
        this.footer = new EwfCompFooter<>(web);
        this.listAdmin = new EwfCompList<>(web);
        this.listSite = new EwfCompList<>(web);
    }

    @Override
    public Component getContent() throws EpeAppException {
        if (!this.getServer().isLoggedUser()) {
            return this.getWeb().getContent(EwfPageLogin.class.getName());
        }

        GridLayout gridLayout = new GridLayout(5, 11);
        gridLayout.setSizeFull();
        gridLayout.setRowExpandRatio(0, 0f);
        gridLayout.setRowExpandRatio(1, 0f);
        gridLayout.setRowExpandRatio(2, 0f);
        gridLayout.setRowExpandRatio(3, 0f);
        gridLayout.setRowExpandRatio(4, 0f);
        gridLayout.setRowExpandRatio(5, 1f);
        gridLayout.setRowExpandRatio(6, 0f);
        gridLayout.setRowExpandRatio(7, 0f);
        gridLayout.setRowExpandRatio(8, 0f);
        gridLayout.setRowExpandRatio(9, 0f);
        gridLayout.setRowExpandRatio(10, 0f);

        List<EwfEntityAbstract> listEntity = new ArrayList<>();
        Component compHeader = this.header.getContent(listEntity);
        gridLayout.addComponent(compHeader, 0, 0, 4, 0);
        gridLayout.setComponentAlignment(compHeader, Alignment.TOP_CENTER);

        this.addLabels(gridLayout);

        Component compAdmins = this.getContentAdmins();
        gridLayout.addComponent(compAdmins, 4, 1, 4, 3);
        gridLayout.setComponentAlignment(compAdmins, Alignment.TOP_RIGHT);

        Component compSites = this.getContentSites();
        gridLayout.addComponent(compSites, 3, 3, 3, 5);
        gridLayout.setComponentAlignment(compSites, Alignment.TOP_RIGHT);

        EwfEntityAbstract entity = null;
        Component compFooter = this.footer.getContent(entity);
        gridLayout.addComponent(compFooter, 0, 10, 4, 10);
        gridLayout.setComponentAlignment(compFooter, Alignment.BOTTOM_CENTER);

        return gridLayout;
    }

    private void addLabels(GridLayout gridLayout) {
        Label label;

        label = new Label("111");
        gridLayout.addComponent(label, 1, 1);
        gridLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        label = new Label("222");
        gridLayout.addComponent(label, 1, 2);
        gridLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        label = new Label("333");
        gridLayout.addComponent(label, 1, 3);
        gridLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        label = new Label("444");
        gridLayout.addComponent(label, 2, 1);
        gridLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        label = new Label("555");
        gridLayout.addComponent(label, 2, 2);
        gridLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        label = new Label("666");
        gridLayout.addComponent(label, 3, 1);
        gridLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        label = new Label("777");
        gridLayout.addComponent(label, 1, 9);
        gridLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        label = new Label("888");
        gridLayout.addComponent(label, 2, 9);
        gridLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        label = new Label("999");
        gridLayout.addComponent(label, 2, 8);
        gridLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        label = new Label("10 10");
        gridLayout.addComponent(label, 3, 9);
        gridLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        label = new Label("11 11");
        gridLayout.addComponent(label, 3, 8);
        gridLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        label = new Label("12 12");
        gridLayout.addComponent(label, 3, 7);
        gridLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        // FIXME remove from here

        label = new Label("***");
        gridLayout.addComponent(label, 2, 4);
        gridLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        label = new Label("--------");
        gridLayout.addComponent(label, 2, 5);
        gridLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        label = new Label("******");
        gridLayout.addComponent(label, 2, 6);
        gridLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
    }

    public Component getContentAdmins() throws EpeAppException {
        List<EwfEntityUser> listAdmin = this.getServer().readListAdmin((EwfEntityUser) this.getServer().getUser());
        this.listAdmin.setSearchPlaceholder("Admin");
        this.listAdmin.setNavigationCaption("-> Admins");
        this.listAdmin.setNavigationClassName(EwfPageAdmin.class.getName());
        return this.listAdmin.getContent(listAdmin);
    }

    public Component getContentSites() throws EpeAppException {
        List<EwfEntitySite> listSite = this.getServer().readListSite((EwfEntityUser) this.getServer().getUser());
        this.listSite.setSearchPlaceholder("Site");
        this.listSite.setNavigationCaption("-> Sites");
        this.listSite.setNavigationClassName(EwfPageSite.class.getName());
        return this.listSite.getContent(listSite);
    }

}
