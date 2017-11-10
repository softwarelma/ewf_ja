package com.softwarelma.ewf.backend.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;

public abstract class EwfEntityAbstract implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((this.getClass().getName() == null) ? 0 : this.getClass().getName().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EwfEntityAbstract other = (EwfEntityAbstract) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (this.getClass().getName() == null) {
            if (other.getClass().getName() != null)
                return false;
        } else if (!this.getClass().getName().equals(other.getClass().getName()))
            return false;
        return true;
    }

    public boolean isAdminTable() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String retrieveDescriptionShort() throws EpeAppException {
        throw new EpeAppException("Invalid invocation");
    }

    public String retrieveDescriptionLong() throws EpeAppException {
        throw new EpeAppException("Invalid invocation");
    }

    public String retrieveDescription(String attribute) throws EpeAppException {
        EpeAppUtils.checkEmpty("attribute", attribute);

        if (attribute.equalsIgnoreCase("id")) {
            return id + "";
        } else if (attribute.equalsIgnoreCase("name")) {
            return name;
        } else if (attribute.equals("retrieveDescriptionShort()")) {
            return this.retrieveDescriptionShort();
        } else if (attribute.equals("retrieveDescriptionLong()")) {
            return this.retrieveDescriptionLong();
        } else {
            throw new EpeAppException("Unknown attribute: " + attribute);
        }
    }

    /**
     * @return all attributes
     */
    public List<String> retrieveListAttribute() throws EpeAppException {
        // List<String> listAttribute = new ArrayList<>();
        // listAttribute.add("ID");
        // listAttribute.add("NAME");
        // return listAttribute;
        throw new EpeAppException("Invalid invocation");
    }

    /**
     * id is considered atomic
     * 
     * @return all non-entity non-foreign attributes
     */
    public List<String> retrieveListAttributeAtomic() throws EpeAppException {
        throw new EpeAppException("Invalid invocation");
    }

    /**
     * @return all entity attributes
     */
    public List<String> retrieveListAttributeEntity() throws EpeAppException {
        throw new EpeAppException("Invalid invocation");
    }

    /**
     * @return all foreign attributes
     */
    public List<String> retrieveListAttributeForeignKey() throws EpeAppException {
        // admin tables do not have foreign keys, only atomics and entities
        return new ArrayList<>();
    }

    /**
     * @return also null
     */
    public Object retrieveValue(String attribute) throws EpeAppException {
        EpeAppUtils.checkEmpty("attribute", attribute);

        if (attribute.equalsIgnoreCase("id")) {
            return id;
        } else if (attribute.equalsIgnoreCase("name")) {
            return name;
        } else {
            throw new EpeAppException("Unknown attribute: " + attribute);
        }
    }

    /**
     * @return also null
     */
    public Object retrieveAtomic(String attribute) throws EpeAppException {
        EpeAppUtils.checkEmpty("attribute", attribute);

        if (attribute.equalsIgnoreCase("id")) {
            return id;
        } else if (attribute.equalsIgnoreCase("name")) {
            return name;
        } else {
            throw new EpeAppException("Unknown attribute: " + attribute);
        }
    }

    /**
     * @return also null
     */
    public EwfEntityAbstract retrieveEntity(String attribute) throws EpeAppException {
        // todo on specific admin entities
        throw new EpeAppException("Invalid invocation");
    }

    /**
     * @return also null
     */
    public Object retrieveForeignKey(String attribute) throws EpeAppException {
        // admin tables do not have foreign keys, only atomics and entities
        throw new EpeAppException("Invalid invocation");
    }

    public String retrieveEntityClassName(String attribute) throws EpeAppException {
        throw new EpeAppException("Invalid invocation");
    }

    public EwfEntityAbstract retrieveClone() throws EpeAppException {
        throw new EpeAppException("Invalid invocation");
    }

    public void introduceValue(String attribute, Object value) throws EpeAppException {
        EpeAppUtils.checkEmpty("attribute", attribute);

        if (attribute.equalsIgnoreCase("id")) {
            EpeAppUtils.checkInstanceOf("id", value, Long.class);
            this.id = (Long) value;
        } else if (attribute.equalsIgnoreCase("name")) {
            EpeAppUtils.checkInstanceOf("name", value, String.class);
            this.name = (String) value;
        } else {
            throw new EpeAppException("Unknown attribute: " + attribute);
        }
    }

    public void introduceValueString(String attribute, String valueString) throws EpeAppException {
        EpeAppUtils.checkEmpty("attribute", attribute);

        if (attribute.equalsIgnoreCase("id")) {
            this.id = valueString == null ? null : Long.parseLong(valueString);
        } else if (attribute.equalsIgnoreCase("name")) {
            this.name = valueString;
        } else {
            throw new EpeAppException("Unknown attribute: " + attribute);
        }
    }

}
