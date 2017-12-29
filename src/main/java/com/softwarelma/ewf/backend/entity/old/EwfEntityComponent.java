package com.softwarelma.ewf.backend.entity.old;

public class EwfEntityComponent extends EwfEntityAbstract {

    private static final long serialVersionUID = 1L;
    private EwfEntitySite site;
    private String type;
    private String label;
    private String title;
    private Boolean required;
    private Boolean editable;
    private String value;

    public EwfEntitySite getSite() {
        return site;
    }

    public void setSite(EwfEntitySite site) {
        this.site = site;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
