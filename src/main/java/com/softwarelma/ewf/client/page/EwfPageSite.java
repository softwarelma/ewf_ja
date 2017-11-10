package com.softwarelma.ewf.client.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.entity.EwfEntitySite;
import com.softwarelma.ewf.backend.entity.EwfEntityUser;
import com.softwarelma.ewf.backend.entity.EwfEntityUserSite;
import com.softwarelma.ewf.client.EwfClientWeb;
import com.softwarelma.ewf.client.comp.EwfCompSecondaryInsertBean;
import com.softwarelma.ewf.client.comp.EwfCompTable;
import com.softwarelma.ewf.common.EwfCommonConstants;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;

public class EwfPageSite extends EwfPageAbstract {

    private static final long serialVersionUID = 1L;
    private final EwfCompTable<EwfEntitySite> tableSite;

    public EwfPageSite(EwfClientWeb web) throws EpeAppException {
        super(web);
        this.tableSite = new EwfCompTable<>(web);
    }

    @Override
    public Component getContent() throws EpeAppException {
        if (!this.getServer().isLoggedUser()) {
            return this.getWeb().getContent(EwfPageLogin.class.getName());
        }

        GridLayout gridLayout = this.buildGridLayoutTemplateCenter();
        Component compAssociations = this.getCompSitesCrud();
        gridLayout.addComponent(compAssociations, 0, 1);
        gridLayout.setComponentAlignment(compAssociations, Alignment.MIDDLE_CENTER);
        return gridLayout;
    }

    private Component getCompSitesCrud() throws EpeAppException {
        Component compSitesCrud = this.loadTableSite();
        // return this.buildGenericPanel(true, compSitesCrud.getWidth() + 30, compSitesCrud.getWidthUnits(), null,
        // compSitesCrud);
        return compSitesCrud;
    }

    private Component loadTableSite() throws EpeAppException {
        List<EwfEntitySite> listSite = this.getServer().readListSite((EwfEntityUser) this.getServer().getUser());
        List<String> listAttributeForDescription = Arrays.asList(new String[] { "name", "url" });
        List<String> listAttributeForSession = Arrays.asList(new String[] { "this" });
        List<EwfCompSecondaryInsertBean> listSecondaryInsertBean = this.retrieveListSecondaryInsertBean();
        this.tableSite.setListSecondaryInsertBean(listSecondaryInsertBean);
        // this.tableSite.setPanel(false);
        return this.tableSite.getContent(listSite, listAttributeForDescription, listAttributeForSession);
    }

    private List<EwfCompSecondaryInsertBean> retrieveListSecondaryInsertBean() throws EpeAppException {
        List<EwfCompSecondaryInsertBean> listSecondaryBean = new ArrayList<>();

        EwfCompSecondaryInsertBean secondaryBean = new EwfCompSecondaryInsertBean();
        secondaryBean.setClazz(EwfEntityUserSite.class);
        secondaryBean.setAttributeForEntityNew("site");
        Map<String, Object> mapAttributeAndValue = new HashMap<>();
        mapAttributeAndValue.put("name", EwfCommonConstants.ENTITY_NAME_UNUSED);
        mapAttributeAndValue.put("admin", this.getServer().getUser());
        secondaryBean.setMapAttributeAndValue(mapAttributeAndValue);
        listSecondaryBean.add(secondaryBean);

        return listSecondaryBean;
    }

    private void reloadTableSite() throws EpeAppException {
        List<EwfEntitySite> listSite = this.getServer().readListSite((EwfEntityUser) this.getServer().getUser());
        this.tableSite.refresh(listSite);
    }

}
