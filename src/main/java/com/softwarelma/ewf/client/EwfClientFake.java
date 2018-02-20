package com.softwarelma.ewf.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;

import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

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

        ////////////////////////////////////////////////

        // Show uploaded file in this placeholder
        final Embedded image = new Embedded("");// "Uploaded Image"
//        image.setWidth("100px");
        image.setHeight("100px");
        image.setVisible(false);

        // Implement both receiver that saves upload in a file and
        // listener for successful upload
        class ImageUploader implements Receiver, SucceededListener {
            public File file;

            public OutputStream receiveUpload(String filename, String mimeType) {
                // Create upload stream
                FileOutputStream fos = null; // Stream to write to
                try {
                    // Open the file for writing.
                    file = new File("/home/ellison/Downloads/ewf/" + filename);
                    fos = new FileOutputStream(file);
                } catch (final java.io.FileNotFoundException e) {
                    new Notification("Could not open file<br/>", e.getMessage(), Notification.Type.ERROR_MESSAGE)
                            .show(Page.getCurrent());
                    return null;
                }
                return fos; // Return the output stream to write to
            }

            public void uploadSucceeded(SucceededEvent event) {
                // Show the uploaded file in the image viewer
                image.setVisible(true);
                image.setSource(new FileResource(file));
            }
        }

        ImageUploader receiver = new ImageUploader();

        // Create the upload with a caption and set receiver later
        Upload upload = new Upload("", receiver);// "Upload Image Here"
        upload.setButtonCaption("Start Upload");
        upload.addSucceededListener(receiver);

        // Put the components in a panel
        // Panel panel = new Panel("Cool Image Storage");
        // Layout panelContent = new VerticalLayout();
        // panelContent.addComponents(upload, image);
        // panel.setContent(panelContent);
        layout.addComponents(upload, image);
    }

    // TODO
    // vaadin file uploader
    // https://vaadin.com/docs/v7/framework/components/components-upload.html

}
