package com.softwarelma.ewf.backend.entity.old;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;

public class EwfEntityUserSite extends EwfEntityAbstract {

    private static final long serialVersionUID = 1L;
    private EwfEntityUser user;
    private EwfEntitySite site;

    public EwfEntityUserSite() {
    }

    public EwfEntityUser getUser() {
        return user;
    }

    public void setUser(EwfEntityUser user) {
        this.user = user;
    }

    public EwfEntitySite getSite() {
        return site;
    }

    public void setSite(EwfEntitySite site) {
        this.site = site;
    }

    @Override
    public String retrieveDescription(String attribute) throws EpeAppException {
        EpeAppUtils.checkEmpty("attribute", attribute);

        if (attribute.equals("user.retrieveDescriptionShort()")) {
            return this.user.retrieveDescriptionShort();
        } else if (attribute.equals("user.retrieveDescriptionLong()")) {
            return this.user.retrieveDescriptionLong();
        } else if (attribute.equals("site.retrieveDescriptionShort()")) {
            return this.site.retrieveDescriptionShort();
        } else if (attribute.equals("site.retrieveDescriptionLong()")) {
            return this.site.retrieveDescriptionLong();
        } else {
            throw new EpeAppException("Unknown attribute: " + attribute);
        }
    }

    @Override
    public EwfEntityAbstract retrieveEntity(String attribute) throws EpeAppException {
        EpeAppUtils.checkEmpty("attribute", attribute);

        if (attribute.equals("user")) {
            return this.user;
        } else if (attribute.equals("site")) {
            return this.site;
        } else {
            throw new EpeAppException("Unknown attribute: " + attribute);
        }
    }

    @Override
    public String retrieveEntityClassName(String attribute) throws EpeAppException {
        EpeAppUtils.checkEmpty("attribute", attribute);

        if (attribute.equals("user")) {
            return EwfEntityUser.class.getName();
        } else if (attribute.equals("site")) {
            return EwfEntitySite.class.getName();
        } else {
            throw new EpeAppException("Unknown attribute: " + attribute);
        }
    }

    @Override
    public void introduceValue(String attribute, Object value) throws EpeAppException {
        EpeAppUtils.checkEmpty("attribute", attribute);

        if (attribute.equals("user")) {
            EpeAppUtils.checkInstanceOf("user", value, EwfEntityUser.class);
            this.user = (EwfEntityUser) value;
        } else if (attribute.equals("site")) {
            EpeAppUtils.checkInstanceOf("site", value, EwfEntitySite.class);
            this.site = (EwfEntitySite) value;
        } else {
            super.introduceValue(attribute, value);
        }
    }

}
