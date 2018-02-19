package com.softwarelma.ewf.client;

import java.time.LocalDate;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.TextField;

public class EwfClientFake {

    public void addGridFake(Component content) {
        // TODO
        AbstractLayout layout = (AbstractLayout) content;
        Grid<EwfClientBean> grid = new Grid<>("My data grid");
        layout.addComponent(grid);
        grid.setSizeFull();

        if (layout instanceof AbstractOrderedLayout) {
            ((AbstractOrderedLayout) layout).setExpandRatio(grid, 1);
        }

        grid.addColumn(EwfClientBean::getPkg).setCaption("PKG").setEditorComponent(new TextField(),
                EwfClientBean::setPkg);
        grid.addColumn(EwfClientBean::getArtifact).setCaption("ARTIFACT").setEditorComponent(new TextField(),
                EwfClientBean::setArtifact);
        grid.addColumn(EwfClientBean::getVersion).setCaption("VERSION").setEditorComponent(new TextField(),
                EwfClientBean::setVersion);
        grid.addColumn(EwfClientBean::getDate).setCaption("DATE").setEditorComponent(new DateField(),
                EwfClientBean::setDate);

        grid.setItems(new EwfClientBean("com.vaadin 1", "vaadin-server", "7.4.0-1", LocalDate.now()),
                new EwfClientBean("com.vaadin 2", "vaadin-client-compiled", "7.4.0-2", LocalDate.now()));

        grid.getEditor().setEnabled(true);

        grid.getEditor().addSaveListener(e -> {
            System.out.println("pkg: " + e.getBean().getPkg());
        });

        grid.setSelectionMode(SelectionMode.MULTI);
        // grid.setSelectionMode(SelectionMode.SINGLE);

        // grid.listener
    }

    // TODO
    // vaadin file uploader
    // https://vaadin.com/docs/v7/framework/components/components-upload.html

}
