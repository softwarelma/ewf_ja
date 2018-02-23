package com.softwarelma.ewf.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import com.softwarelma.ewf.common.EwfCommonConstants;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.renderers.AbstractRenderer;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ClickableRenderer.RendererClickEvent;
import com.vaadin.ui.renderers.ClickableRenderer.RendererClickListener;
import com.vaadin.ui.renderers.ImageRenderer;
import com.vaadin.ui.renderers.TextRenderer;

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

        List<Column> listColumn = new ArrayList<>();

        Column column = grid.addColumn(EwfClientBean::getPkg).setCaption("PKG").setEditorComponent(new TextField(),
                EwfClientBean::setPkg);
        listColumn.add(column);

        // column = grid.addColumn(e -> {
        // Button button = new Button("Click me!");
        // button.addClickListener(click -> Notification.show("Clicked: " +
        // e.getFileName()));
        // return button;
        // // return new Label("aaaa");
        // }).setCaption("IMAGE");
        // grid.setHeightByRows(60);

        column = grid.addColumn(e -> getResource(e.getFileName()), new ImageRenderer(getClick())).setCaption("IMAGE");
        listColumn.add(column);

        column = grid.addColumn(EwfClientBean::getVersion).setCaption("VERSION").setEditorComponent(new TextField(),
                EwfClientBean::setVersion);
        listColumn.add(column);

        column = grid.addColumn(EwfClientBean::getDate).setCaption("DATE").setEditorComponent(new DateField(),
                EwfClientBean::setDate);
        listColumn.add(column);

        List<EwfClientBean> listBean = new ArrayList<>(Arrays.asList(
                new EwfClientBean[] { new EwfClientBean("com.vaadin 1", "robot.jpeg", "7.4.0-1", LocalDate.now()),
                        new EwfClientBean("com.vaadin 2", "04-icona-guille-madrid.png", "7.4.0-2", LocalDate.now()) }));

        this.addFilters(grid, listColumn);

        // Render a button that deletes the data row (item)
        grid.addColumn(e -> "Delete", new ButtonRenderer(clickEvent -> {
            listBean.remove(clickEvent.getItem());
            // listBean.add(new EwfClientBean("com.vaadin 3",
            // "Screenshot-parametri.png", "7.4.0-1", LocalDate.now()));
            grid.setItems(listBean);
        }));

        grid.setItems(listBean);

        grid.getEditor().setEnabled(true);

        grid.getEditor().addSaveListener(e -> {
            System.out.println("pkg: " + e.getBean().getPkg());
        });

        grid.setSelectionMode(SelectionMode.MULTI);
        // grid.setSelectionMode(SelectionMode.SINGLE);

        // grid.listener

        this.addUploader(layout);
    }

    private RendererClickListener getClick() {
        return new RendererClickListener() {
            @Override
            public void click(RendererClickEvent event) {
                System.out.println("img click");
            }
        };
    }

    private ThemeResource getThemeResource() {
        return new ThemeResource("img/robot.jpeg");
    }

    /**
     * from
     * https://stackoverflow.com/questions/35949232/insert-image-to-vaadin-grid
     */
    private Resource getResource(String fileName) {
        String base64 = this.encodeFileToBase64Binary(EwfCommonConstants.IMAGES_FOLDER + fileName);
        String[] arrayExtension = fileName.split("\\.");
        String extension = arrayExtension[arrayExtension.length - 1];
        base64 = "data:image/" + extension + ";base64," + base64;
        return new ExternalResource(base64);
        // "data:image/png;base64,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"
    }

    private String encodeFileToBase64Binary(String fileName) {
        try {
            File file = new File(fileName);
            byte[] bytes = loadFile(file);
            byte[] encoded = Base64.getEncoder().encode(bytes);
            String encodedString = new String(encoded);
            return encodedString;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
        return bytes;
    }

    private void addFilters(Grid grid, List<Column> listColumn) {
        HeaderRow headerRow = grid.appendHeaderRow();

        for (Column column : listColumn) {
            HeaderCell cell = headerRow.getCell(column);
            Component component;
            if (column.getCaption().equals("IMAGE")) {
                component = this.getUpload();
            } else {
                TextField textField = new TextField();
                textField.addValueChangeListener(new ValueChangeListener<String>() {
                    @Override
                    public void valueChange(ValueChangeEvent<String> event) {
                        System.out.println("filtering col " + column.getCaption() + " by: " + event.getValue());
                    }
                });
                component = textField;
            }
            cell.setComponent(component);
        }
    }

    private Upload getUpload() {
        return null;
    }

    private void addUploader(AbstractLayout layout) {
        // Show uploaded file in this placeholder
        final Embedded image = new Embedded("");// "Uploaded Image"
        // image.setWidth("100px");//adaptive
        image.setHeight("100px");
        image.setVisible(false);

        // Implement both receiver that saves upload in a file and
        // listener for successful upload
        class ImageUploader implements Receiver, SucceededListener {

            private static final long serialVersionUID = 1L;
            public File file;

            public OutputStream receiveUpload(String filename, String mimeType) {
                // Create upload stream
                FileOutputStream fos = null; // Stream to write to
                try {
                    // Open the file for writing.
                    file = new File(EwfCommonConstants.IMAGES_FOLDER + filename);
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
