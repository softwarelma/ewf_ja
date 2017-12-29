package com.softwarelma.ewf.backend.entity.old;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;

public class EwfEntitySite extends EwfEntityAbstract {

    private static final long serialVersionUID = 1L;
    private String url;

    @Override
    public String toString() {
        try {
            return this.retrieveDescriptionShort();
        } catch (EpeAppException e) {
            return null;
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String retrieveDescriptionShort() throws EpeAppException {
        return this.getName();
    }

    @Override
    public String retrieveDescriptionLong() throws EpeAppException {
        return this.getName() + " (" + this.url + ")";
    }

    @Override
    public String retrieveDescription(String attribute) throws EpeAppException {
        EpeAppUtils.checkEmpty("attribute", attribute);

        if (attribute.equals("name")) {
            return this.getName();
        } else if (attribute.equals("url")) {
            return this.url;
        } else {
            throw new EpeAppException("Unknown attribute: " + attribute);
        }
    }

    @Override
    public EwfEntityAbstract retrieveEntity(String attribute) throws EpeAppException {
        EpeAppUtils.checkEmpty("attribute", attribute);

        if (attribute.equals("this")) {
            return this;
        } else {
            throw new EpeAppException("Unknown attribute: " + attribute);
        }
    }

    @Override
    public String retrieveEntityClassName(String attribute) throws EpeAppException {
        EpeAppUtils.checkEmpty("attribute", attribute);

        if (attribute.equals("this")) {
            return this.getClass().getName();
        } else {
            throw new EpeAppException("Unknown attribute: " + attribute);
        }
    }

    @Override
    public EwfEntityAbstract retrieveClone() throws EpeAppException {
        EwfEntitySite site = new EwfEntitySite();

        site.setId(this.getId());
        site.setName(this.getName());
        site.setUrl(this.getUrl());

        return site;
    }

    @Override
    public void introduceValueString(String attribute, String valueString) throws EpeAppException {
        EpeAppUtils.checkEmpty("attribute", attribute);

        if (attribute.equals("url")) {
            this.url = valueString;
        } else {
            super.introduceValueString(attribute, valueString);
        }
    }

}
