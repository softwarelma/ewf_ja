package com.softwarelma.ewf.client.comp.old;

import java.io.Serializable;

public class EwfCompSecondaryDeleteBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String daoClassNameChildUndeletable;
    private String attributeNameThisUndeletable;
    private String daoClassNameChildDeletable;
    private String attributeNameThisDeletable;
    private String attributeNameConditionDeletable;
    private Long idConditionDeletable;

    public String getDaoClassNameChildUndeletable() {
        return daoClassNameChildUndeletable;
    }

    public void setDaoClassNameChildUndeletable(String daoClassNameChildUndeletable) {
        this.daoClassNameChildUndeletable = daoClassNameChildUndeletable;
    }

    public String getAttributeNameThisUndeletable() {
        return attributeNameThisUndeletable;
    }

    public void setAttributeNameThisUndeletable(String attributeNameThisUndeletable) {
        this.attributeNameThisUndeletable = attributeNameThisUndeletable;
    }

    public String getDaoClassNameChildDeletable() {
        return daoClassNameChildDeletable;
    }

    public void setDaoClassNameChildDeletable(String daoClassNameChildDeletable) {
        this.daoClassNameChildDeletable = daoClassNameChildDeletable;
    }

    public String getAttributeNameThisDeletable() {
        return attributeNameThisDeletable;
    }

    public void setAttributeNameThisDeletable(String attributeNameThisDeletable) {
        this.attributeNameThisDeletable = attributeNameThisDeletable;
    }

    public String getAttributeNameConditionDeletable() {
        return attributeNameConditionDeletable;
    }

    public void setAttributeNameConditionDeletable(String attributeNameConditionDeletable) {
        this.attributeNameConditionDeletable = attributeNameConditionDeletable;
    }

    public Long getIdConditionDeletable() {
        return idConditionDeletable;
    }

    public void setIdConditionDeletable(Long idConditionDeletable) {
        this.idConditionDeletable = idConditionDeletable;
    }

}
