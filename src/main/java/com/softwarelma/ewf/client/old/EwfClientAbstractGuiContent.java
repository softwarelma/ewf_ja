package com.softwarelma.ewf.client.old;

import java.io.Serializable;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Notification.Type;

public abstract class EwfClientAbstractGuiContent implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Component buildGenericPanel(boolean vertical, boolean marginTop, boolean marginRight,
            boolean marginBottom, boolean marginLeft, float width, Unit widthUnit, Alignment alignment, String idPanel,
            String idLayout, Component... arrayComp) throws EpeAppException {
        AbstractOrderedLayout layout = (AbstractOrderedLayout) this.buildGenericLayout(vertical, marginTop, marginRight,
                marginBottom, marginLeft, width, widthUnit, alignment, idLayout, arrayComp);
        Panel panel = new Panel();
        panel.setContent(layout);

        if (!EpeAppUtils.isEmpty(idPanel)) {
            panel.setId(idPanel);
        }

        panel.setWidth(layout.getWidth() + 10, layout.getWidthUnits());
        return panel;
    }

    protected Component buildGenericLayout(boolean vertical, boolean marginTop, boolean marginRight,
            boolean marginBottom, boolean marginLeft, float width, Unit widthUnit, Alignment alignment, String idLayout,
            Component... arrayComp) throws EpeAppException {
        EpeAppUtils.checkEmptyArray("arrayComp", arrayComp);
        alignment = alignment == null ? Alignment.MIDDLE_CENTER : alignment;
        AbstractOrderedLayout layout = vertical ? new VerticalLayout() : new HorizontalLayout();
        // layout.setSizeFull();
        layout.setMargin(new MarginInfo(marginTop, marginRight, marginBottom, marginLeft));
        // float heightPx = 0F;

        if (!EpeAppUtils.isEmpty(idLayout)) {
            layout.setId(idLayout);
        }

        for (Component comp : arrayComp) {
            // if (comp.getHeight() > 0 && Unit.PIXELS.equals(comp.getHeightUnits())) {
            // heightPx += comp.getHeight();
            // }

            layout.addComponent(comp);
            layout.setComponentAlignment(comp, alignment);
        }

        // layout.setHeight(heightPx, Unit.PIXELS);
        layout.setWidth(this.retrieveNewWidth(marginRight, marginLeft, layout.getWidth(), width, widthUnit),
                Unit.PIXELS);

        return layout;
    }

    private float retrieveNewWidth(boolean marginRight, boolean marginLeft, float currentWidth, float width,
            Unit widthUnit) {
        float newWidth = currentWidth;

        if (Unit.PIXELS.equals(widthUnit)) {
            newWidth = width;
            newWidth = marginRight ? newWidth + 37F : newWidth;
            newWidth = marginLeft ? newWidth + 37F : newWidth;
        }

        return newWidth;
    }

    protected void showAlert(boolean valid, String caption, String description, Type type) throws EpeAppException {
        EpeAppUtils.checkNull("caption", caption);
        EpeAppUtils.checkNull("description", description);
        EpeAppUtils.checkNull("type", type);
        if (!valid) {
            new Notification(caption, description, type, true).show(Page.getCurrent());
        }
    }

}
