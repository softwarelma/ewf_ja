package com.softwarelma.ewf.client.elem;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppRuntimeException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.client.EwfClient;
import com.softwarelma.ewf.client.cont.EwfContentBean;
import com.softwarelma.ewf.common.EwfCommonConstants;
import com.softwarelma.ewf.main.EwfMain;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class EwfElemCustomMenuResponsive extends EwfElemAbstract {

    private static final long serialVersionUID = 1L;

    public EwfElemCustomMenuResponsive(EwfClient client, UI ui, EwfElemBean elemBean, EwfContentBean contentBean)
            throws EpeAppException {
        super(client, ui, elemBean, contentBean);
    }

    @Override
    public Component getComponent() throws EpeAppException {
        if (super.getComponent() != null) {
            return super.getComponent();
        }

        EpeAppUtils.checkNull("mapPageNameAndPageBean", getMapPageNameAndPageBean());
        CssLayout layout = new CssLayout();
        layout.setId("EwfElemCustomMenuResponsive");
        layout.setWidth("100%");
        layout.addStyleName("ewfToggleDisplay");
        Responsive.makeResponsive(layout);

        HorizontalLayout layoutHor = new HorizontalLayout();
        layoutHor.setId("EwfElemCustomMenuResponsive:horizontal");
        // layoutHor.setWidth("100%");
        layoutHor.addStyleName("ewfToggleDisplayBig");
        VerticalLayout layoutVert = new VerticalLayout();
        layoutVert.setId("EwfElemCustomMenuResponsive:vertical");
        layoutVert.addStyleName("ewfToggleDisplaySmall");
        this.addButtons(layoutHor);
        this.addButtonOpenClose(layoutVert);

        HorizontalLayout layoutHorExternal = new HorizontalLayout();
        layoutHorExternal.setWidth("100%");
        layoutHorExternal.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        layoutHorExternal.addComponent(layoutHor);

        layout.addComponent(layoutHorExternal);
        layout.addComponent(layoutVert);
        return layout;
    }

    private void addButtonOpenClose(AbstractLayout layout) {
        Button button = new Button("+");
        button.addClickListener(e -> {
            if (button.getCaption().equals("+")) {
                button.setCaption("-");
                try {
                    addButtons(layout);
                } catch (EpeAppException ex) {
                    throw new EpeAppRuntimeException(ex.getMessage(), ex);
                }
            } else {
                button.setCaption("+");
                layout.removeAllComponents();
                layout.addComponent(button);
            }
        });
        layout.addComponent(button);
    }

    private void addButtons(AbstractLayout layout) throws EpeAppException {
        String selectedPageName = EwfCommonConstants.PAGE_HOME;

        // TODO
    *    System.out.println("menu getIdSession(): " + ((EwfMain) getUi()).getIdSession());

        if (VaadinService.getCurrentRequest() != null) {
            selectedPageName = getClient().getSessionAttributeNotNull(EwfCommonConstants.SESSION_SELECTED_PAGE)
                    .toString();
        }

        for (String pageName : getMapPageNameAndPageBean().keySet()) {
            Button button = new Button(getMapPageNameAndPageBean().get(pageName).getDescription());

            if (pageName.equals(selectedPageName)) {
                button.addStyleName(EwfCommonConstants.STYLE_BACK_GREEN);
            } else {
                button.removeStyleName(EwfCommonConstants.STYLE_BACK_GREEN);
            }

            button.addClickListener(e -> {
                // getUi().getPage().setLocation("/" +
                // EwfCommonConstants.APPLICATION_NAME + "/" + pageName +
                // "/");
                try {
                    String idSession = ((EwfMain) getUi()).getIdSession();
                    getClient().loadPage(getUi(), idSession, pageName);
                    getClient().setSessionAttributeNotNull(EwfCommonConstants.SESSION_SELECTED_PAGE, pageName);
                } catch (EpeAppException ex) {
                    throw new EpeAppRuntimeException(ex.getMessage(), ex);
                }
            });
            layout.addComponent(button);
            // layout.setComponentAlignment(button, Alignment.MIDDLE_LEFT);
        }
    }

}
