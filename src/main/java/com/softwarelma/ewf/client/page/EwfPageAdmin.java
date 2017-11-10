package com.softwarelma.ewf.client.page;

import java.util.Arrays;
import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.backend.dao.EwfDaoUser;
import com.softwarelma.ewf.backend.dao.EwfDaoUserSite;
import com.softwarelma.ewf.backend.entity.EwfEntitySite;
import com.softwarelma.ewf.backend.entity.EwfEntityUser;
import com.softwarelma.ewf.backend.entity.EwfEntityUserSite;
import com.softwarelma.ewf.client.EwfClientWeb;
import com.softwarelma.ewf.client.comp.EwfCompTable;
import com.softwarelma.ewf.common.EwfCommonConstants;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;

public class EwfPageAdmin extends EwfPageAbstract {

    private static final long serialVersionUID = 1L;
    private final EwfCompTable<EwfEntityUserSite> table;

    public EwfPageAdmin(EwfClientWeb web) throws EpeAppException {
        super(web);
        this.table = new EwfCompTable<>(web);
    }

    @Override
    public Component getContent() throws EpeAppException {
        if (!this.getServer().isLoggedUser()) {
            return this.getWeb().getContent(EwfPageLogin.class.getName());
        }

        GridLayout gridLayout = this.buildGridLayoutTemplateCenter();
        Component compAssociations = this.getCompAssocAndLeaveAndNav();
        gridLayout.addComponent(compAssociations, 0, 1);
        gridLayout.setComponentAlignment(compAssociations, Alignment.MIDDLE_CENTER);
        return gridLayout;
    }

    private Component getCompAssocAndLeaveAndNav() throws EpeAppException {
        Component compTableAssoc = this.loadTable();
        Component compNewAdmin = this.getCompNewAdmin();
        Component compLeaveAndNav = this.getCompLeaveAndNav();

        return this.buildGenericPanel(true, false, false, true, false, compTableAssoc.getWidth(),
                compTableAssoc.getWidthUnits(), null, null, null, compTableAssoc, compNewAdmin, compLeaveAndNav);
    }

    private Component loadTable() throws EpeAppException {
        List<EwfEntityUserSite> listAdminSite = this.getServer()
                .readListAdminSiteBySites((EwfEntityUser) this.getServer().getUser());
        List<String> listAttributeForDescription = Arrays
                .asList(new String[] { "admin.retrieveDescriptionShort()", "site.retrieveDescriptionShort()" });
        List<String> listAttributeForSession = Arrays.asList(new String[] { "admin", "site" });
        this.table.setIdPanel("idAdminTablePanel");// FIXME remove
        this.table.setIdLayout("idAdminTableLayout");// FIXME remove
        this.table.setIdTable("idAdminTableGrid");// FIXME remove
        this.table.setPanel(false);// FIXME panel false
        this.table.setClear(false);
        this.table.setCreate(false);
        this.table.setUpdate(false);
        this.table.setDelete(false);
        return this.table.getContent(listAdminSite, listAttributeForDescription, listAttributeForSession);
    }

    private void reloadTable() throws EpeAppException {
        List<EwfEntityUserSite> listAdminSite = this.getServer()
                .readListAdminSiteBySites((EwfEntityUser) this.getServer().getUser());
        this.table.refresh(listAdminSite);
    }

    private Component getCompNewAdmin() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        TextField tfNewAdmin = new TextField();
        tfNewAdmin.setWidth("140px");
        tfNewAdmin.setPlaceholder("Site's admin");
        horizontalLayout.addComponent(tfNewAdmin);

        Button buttonAddToSite = new Button("Add to site");
        buttonAddToSite.addClickListener(e -> {
            String username = tfNewAdmin.getValue();

            if (EpeAppUtils.isEmpty(username)) {
                return;
            }

            try {
                List<EwfEntityUser> listAdmin = getServer().readList(EwfDaoUser.class.getName(),
                        Arrays.asList(new String[] { "username" }), Arrays.asList(new Object[] { username }));

                if (listAdmin.size() != 1) {
                    this.showAlert(false, "Invalid username", "Insert a valid admin's username please.",
                            Type.ERROR_MESSAGE);
                    return;
                }

                EwfEntityUser admin = listAdmin.get(0);
                EwfEntitySite site = (EwfEntitySite) this.getServer()
                        .getSessionAttribute(EwfEntitySite.class.getName());
                List<EwfEntityUserSite> listAdminSite = getServer().readList(EwfDaoUserSite.class.getName(),
                        Arrays.asList(new String[] { "admin", "site" }), Arrays.asList(new Object[] { admin, site }));

                if (!listAdminSite.isEmpty()) {
                    this.showAlert(false, "Invalid username", "The admin is already associated to the site.",
                            Type.ERROR_MESSAGE);
                    return;
                }

                EwfEntityUserSite adminSite = new EwfEntityUserSite();
                adminSite.setName(EwfCommonConstants.ENTITY_NAME_UNUSED);
                adminSite.setUser(admin);
                adminSite.setSite(site);
                this.getServer().create(EwfDaoUserSite.class.getName(), adminSite);
                this.reloadTable();
            } catch (EpeAppException e1) {
                // nothing
            }
        });
        buttonAddToSite.setWidth("140px");
        horizontalLayout.addComponent(buttonAddToSite);

        return horizontalLayout;
    }

    private Component getCompLeaveAndNav() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        Button buttonLeave = new Button("Leave");
        buttonLeave.addClickListener(e -> {
            try {
                EwfEntityUser admin = (EwfEntityUser) getServer().getSessionAttribute(EwfEntityUser.class.getName());
                EwfEntitySite site = (EwfEntitySite) getServer()
                        .getSessionAttributeOrNull(EwfEntitySite.class.getName());

                if (site == null) {
                    return;
                }

                List<String> listAttName = Arrays.asList(new String[] { "admin", "site" });
                List<Object> listAttValue = Arrays.asList(new Object[] { admin, site });
                List<EwfEntityUserSite> listAdminSite = getServer().readList(EwfDaoUserSite.class.getName(),
                        listAttName, listAttValue);

                // it should exist only one site assoc
                if (listAdminSite.size() == 1) {
                    this.getServer().delete(EwfDaoUserSite.class.getName(), listAdminSite.get(0));
                    this.reloadTable();
                }
            } catch (EpeAppException e1) {
                // nothing
            }
        });
        buttonLeave.setWidth("140px");
        horizontalLayout.addComponent(buttonLeave);

        Button buttonNav = new Button("-> Sites");
        buttonNav.addClickListener(e -> {
            try {
                getWeb().navigate(EwfPageSite.class.getName());
            } catch (EpeAppException e1) {
                // nothing
            }
        });
        buttonNav.setWidth("140px");
        horizontalLayout.addComponent(buttonNav);

        return horizontalLayout;
    }

}
