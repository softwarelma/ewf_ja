package com.softwarelma.ewf.client.comp;

import java.io.Serializable;
import java.util.Map;

public class EwfCompSecondaryInsertBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Class<?> clazz;
    private Map<String, Object> mapAttributeAndValue;
    private String attributeForEntityNew;

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Map<String, Object> getMapAttributeAndValue() {
        return mapAttributeAndValue;
    }

    public void setMapAttributeAndValue(Map<String, Object> mapAttributeAndValue) {
        this.mapAttributeAndValue = mapAttributeAndValue;
    }

    public String getAttributeForEntityNew() {
        return attributeForEntityNew;
    }

    public void setAttributeForEntityNew(String attributeForEntityNew) {
        this.attributeForEntityNew = attributeForEntityNew;
    }

}
