package com.softwarelma.ewf.client;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;

public class EwfClientFake {

    public void addGridFake(Component content) {
        // TODO
        AbstractLayout layout = (AbstractLayout) content;
        Grid<String> grid = new Grid<>("My data grid");
        layout.addComponent(grid);
        grid.setSizeFull();

        if (layout instanceof AbstractOrderedLayout) {
            ((AbstractOrderedLayout) layout).setExpandRatio(grid, 1);
        }
        
        grid.addRow("com.vaadin", "vaadin-server", "7.4.0");
        grid.addRow("com.vaadin", "vaadin-client-compiled", "7.4.0");
    }

}
