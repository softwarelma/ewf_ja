package com.softwarelma.ewf.backend.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.epe.p3.db.EpeDbMetaDataColumn;

public class EwfEntityMetaData implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String table;
    private final List<String> listAttribute;
    private final Map<String, EpeDbMetaDataColumn> mapAttAndMetaAtt;

    public EwfEntityMetaData(String table, List<String> listAtrribute,
            Map<String, EpeDbMetaDataColumn> mapAttAndMetaAtt) throws EpeAppException {
        EpeAppUtils.checkEmpty("table", table);
        List<String> listAttributeNorm = this.retrieveListAttributeNorm(listAtrribute);
        Map<String, EpeDbMetaDataColumn> mapAttAndMetaAttNorm = this.retrieveMapAttAndMetaAttNorm(mapAttAndMetaAtt);
        EpeAppUtils.checkEquals("listAttribute", "mapAttAndMetaAtt.keySet", listAttributeNorm,
                mapAttAndMetaAttNorm.keySet());
        this.table = table.toUpperCase();
        this.listAttribute = listAttributeNorm;
        this.mapAttAndMetaAtt = mapAttAndMetaAttNorm;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((table == null) ? 0 : table.hashCode());
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
        EwfEntityMetaData other = (EwfEntityMetaData) obj;
        if (table == null) {
            if (other.table != null)
                return false;
        } else if (!table.equals(other.table))
            return false;
        return true;
    }

    public void validateSetAttribute(Set<String> setAttribute) throws EpeAppException {
        EpeAppUtils.checkEquals("listAttribute", "setAttribute", this.listAttribute, setAttribute);
    }

    private Map<String, EpeDbMetaDataColumn> retrieveMapAttAndMetaAttNorm(
            Map<String, EpeDbMetaDataColumn> mapAttAndMetaAtt) throws EpeAppException {
        EpeAppUtils.checkEmptyMap("mapAttAndMetaAtt", mapAttAndMetaAtt);
        Map<String, EpeDbMetaDataColumn> mapAttAndMetaAttNorm = new HashMap<>();
        EpeDbMetaDataColumn metaAtt;
        String attributeUpper;

        for (String attribute : mapAttAndMetaAtt.keySet()) {
            EpeAppUtils.checkEmpty("attribute", attribute);
            metaAtt = mapAttAndMetaAtt.get(attribute);
            EpeAppUtils.checkNull("metaAtt", metaAtt);
            attributeUpper = attribute.toUpperCase();
            EpeAppUtils.checkEquals("attributeUpper", "metaAtt.attribute", attributeUpper, metaAtt.getColumn());
            mapAttAndMetaAttNorm.put(attributeUpper, metaAtt);
        }

        return mapAttAndMetaAttNorm;
    }

    private List<String> retrieveListAttributeNorm(List<String> listAttribute) throws EpeAppException {
        EpeAppUtils.checkEmptyList("listAttribute", listAttribute);
        List<String> listAttributeNorm = new ArrayList<>();

        for (String attribute : listAttribute) {
            EpeAppUtils.checkEmpty("attribute", attribute);
            listAttributeNorm.add(attribute.toUpperCase());
        }

        return listAttributeNorm;
    }

    public List<String> retrieveListAttributeNoFK() throws EpeAppException {
        List<String> listAttribute = new ArrayList<>();

        for (String attribute : this.listAttribute) {
            if (!attribute.startsWith(EwfEntityColumns.COLUMN_PREFIX_ID)) {
                listAttribute.add(attribute);
            }
        }

        return listAttribute;
    }

    public List<String> retrieveListAttributeFK() throws EpeAppException {
        List<String> listAttribute = new ArrayList<>();

        for (String attribute : this.listAttribute) {
            if (attribute.startsWith(EwfEntityColumns.COLUMN_PREFIX_ID)) {
                listAttribute.add(attribute);
            }
        }

        return listAttribute;
    }

    public boolean isSystemTable() {
        return this.table.startsWith(EwfEntityTables.TABLE_PREFIX_SYS);
    }

    public boolean isAdminTable() {
        return this.table.startsWith(EwfEntityTables.TABLE_PREFIX_DMN);
    }

    public boolean isFinalTable() {
        return this.table.startsWith(EwfEntityTables.TABLE_PREFIX_FNL);
    }

    public String getTable() {
        return this.table;
    }

    public int getCols() {
        return this.listAttribute.size();
    }

    public String getAttribute(int index) throws EpeAppException {
        EpeAppUtils.checkRange(index, 0, this.listAttribute.size(), false, true);
        return this.listAttribute.get(index);
    }

    public String getClassName(int index) throws EpeAppException {
        return this.mapAttAndMetaAtt.get(this.getAttribute(index)).getClassName();
    }

    public String getClassName(String attribute) throws EpeAppException {
        EpeDbMetaDataColumn metaAtt = this.mapAttAndMetaAtt.get(attribute);
        EpeAppUtils.checkNull("metaAtt", metaAtt);
        return metaAtt.getClassName();
    }

    public int getPrecision(int index) throws EpeAppException {
        return this.mapAttAndMetaAtt.get(this.getAttribute(index)).getPrecision();
    }

    public int getPrecision(String attribute) throws EpeAppException {
        EpeDbMetaDataColumn metaAtt = this.mapAttAndMetaAtt.get(attribute);
        EpeAppUtils.checkNull("metaAtt", metaAtt);
        return metaAtt.getPrecision();
    }

    public int getScale(int index) throws EpeAppException {
        return this.mapAttAndMetaAtt.get(this.getAttribute(index)).getScale();
    }

    public int getScale(String attribute) throws EpeAppException {
        EpeDbMetaDataColumn metaAtt = this.mapAttAndMetaAtt.get(attribute);
        EpeAppUtils.checkNull("metaAtt", metaAtt);
        return metaAtt.getScale();
    }

}
