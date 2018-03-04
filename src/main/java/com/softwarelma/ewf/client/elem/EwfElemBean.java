package com.softwarelma.ewf.client.elem;

import java.io.Serializable;
import java.util.Map;

import com.softwarelma.ewf.client.page.EwfPageBean;

public class EwfElemBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // DEFAULT

    private String componentClassName;// vaadin
    private String text;
    private String fileName;
    private String querySelect;
    private String queryTable;

    // CUSTOM

    private String elemCustomClassName;// ewf
    private Map<String, EwfPageBean> mapPageNameAndPageBean;

    @Override
    public String toString() {
        return "EwfElemBean [componentClassName=" + componentClassName + ", text=" + text + ", fileName=" + fileName
                + ", querySelect=" + querySelect + ", queryTable=" + queryTable + ", elemCustomClassName="
                + elemCustomClassName + ", mapPageNameAndPageBean=" + mapPageNameAndPageBean + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((componentClassName == null) ? 0 : componentClassName.hashCode());
        result = prime * result + ((elemCustomClassName == null) ? 0 : elemCustomClassName.hashCode());
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
        result = prime * result + ((querySelect == null) ? 0 : querySelect.hashCode());
        result = prime * result + ((queryTable == null) ? 0 : queryTable.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
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
        EwfElemBean other = (EwfElemBean) obj;
        if (componentClassName == null) {
            if (other.componentClassName != null)
                return false;
        } else if (!componentClassName.equals(other.componentClassName))
            return false;
        if (elemCustomClassName == null) {
            if (other.elemCustomClassName != null)
                return false;
        } else if (!elemCustomClassName.equals(other.elemCustomClassName))
            return false;
        if (fileName == null) {
            if (other.fileName != null)
                return false;
        } else if (!fileName.equals(other.fileName))
            return false;
        if (querySelect == null) {
            if (other.querySelect != null)
                return false;
        } else if (!querySelect.equals(other.querySelect))
            return false;
        if (queryTable == null) {
            if (other.queryTable != null)
                return false;
        } else if (!queryTable.equals(other.queryTable))
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        return true;
    }

    public String getComponentClassName() {
        return componentClassName;
    }

    public void setComponentClassName(String componentClassName) {
        this.componentClassName = componentClassName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getElemCustomClassName() {
        return elemCustomClassName;
    }

    public void setElemCustomClassName(String elemCustomClassName) {
        this.elemCustomClassName = elemCustomClassName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Map<String, EwfPageBean> getMapPageNameAndPageBean() {
        return mapPageNameAndPageBean;
    }

    public void setMapPageNameAndPageBean(Map<String, EwfPageBean> mapPageNameAndPageBean) {
        this.mapPageNameAndPageBean = mapPageNameAndPageBean;
    }

    public String getQuerySelect() {
        return querySelect;
    }

    public void setQuerySelect(String querySelect) {
        this.querySelect = querySelect;
    }

    public String getQueryTable() {
        return queryTable;
    }

    public void setQueryTable(String queryTable) {
        this.queryTable = queryTable;
    }

}
