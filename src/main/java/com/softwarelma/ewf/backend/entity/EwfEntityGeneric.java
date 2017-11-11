package com.softwarelma.ewf.backend.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.common.EwfCommonConstants;

public class EwfEntityGeneric implements Serializable {

    private static final long serialVersionUID = 1L;
    private final EwfEntityMetaData metaData;
    private final Map<String, Object> mapAttAndValue;//TODO to list

    public EwfEntityGeneric(EwfEntityMetaData metaData, Map<String, Object> mapAttAndValue) throws EpeAppException {
        EpeAppUtils.checkNull("metaData", metaData);
        EpeAppUtils.checkEmptyMap("mapAttAndValue", mapAttAndValue);
        metaData.validateSetAttribute(mapAttAndValue.keySet());
        this.metaData = metaData;
        // TODO validate types
        this.mapAttAndValue = new HashMap<>(mapAttAndValue);
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " " + this.mapAttAndValue.toString();
    }

    public EwfEntityMetaData getMetaData() {
        return metaData;
    }

    @SuppressWarnings("unchecked")
    public <T> T getWithType(String attribute, Class<T> clazz) throws EpeAppException {
        Object value = this.get(attribute);
        EpeAppUtils.checkInstanceOf(attribute, value, clazz);
        return (T) value;
    }

    public Long getLong(String attribute) throws EpeAppException {
        return this.getWithType(attribute, Long.class);
    }

    public String getString(String attribute) throws EpeAppException {
        return this.getWithType(attribute, String.class);
    }

    // TODO validate types
    public Object get(String attribute) throws EpeAppException {
        EpeAppUtils.checkEmpty("attribute", attribute);
        attribute = attribute.toUpperCase();
        EpeAppUtils.checkContains(this.mapAttAndValue.keySet(), "attribute", attribute);
        return this.mapAttAndValue.get(attribute);
    }

    // TODO validate types
    public void set(String attribute, Object value) throws EpeAppException {
        EpeAppUtils.checkEmpty("attribute", attribute);
        attribute = attribute.toUpperCase();
        EpeAppUtils.checkContains(this.mapAttAndValue.keySet(), "attribute", attribute);
        this.mapAttAndValue.put(attribute, value);
    }

    public String retrieveDescriptionShort() throws EpeAppException {
        // TODO take the pattern
        String descriptionShort = null;//TODO this.getString(EwfEntityColumns);

        if (descriptionShort == null) {
            return this.getString(EwfEntityColumns.NAME);
        }

        StringBuilder sb = new StringBuilder();
        String[] arrayAttribute = descriptionShort.split(Pattern.quote(","));

        for (String attribute : arrayAttribute) {
            sb.append(this.getString(attribute));
        }

        return null;// TODO
    }

    public String retrieveDescriptionLong() throws EpeAppException {
        // return "(" + this.getId() + ") " + this.getName();
        return null;// TODO
    }

    public String retrieveDescription(String attribute) throws EpeAppException {
        EpeAppUtils.checkEmpty("attribute", attribute);
        Object value = null;//TODO this.mapAttributeAndValue.get(attribute);

//        if (this.mapAttributeAndValue.containsKey(attribute.toUpperCase())) {
//            EpeAppUtils.checkNull("value", value);
//            return value + "";
//        } else if (attribute.equals("retrieveDescriptionShort()")) {
//            return this.retrieveDescriptionShort();
//        } else if (attribute.equals("retrieveDescriptionLong()")) {
//            return this.retrieveDescriptionLong();
//        } else {
//            throw new EpeAppException("Unknown attribute: " + attribute);
//        }
        return null;
    }

    public EwfEntityGeneric retrieveClone() throws EpeAppException {
        // TODO
        throw new EpeAppException("Invalid invocation");
    }

}
