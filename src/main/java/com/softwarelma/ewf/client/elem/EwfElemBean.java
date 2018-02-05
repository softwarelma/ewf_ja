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

    // CUSTOM

    private String elemCustomClassName;// ewf
    private Map<String, EwfPageBean> mapPageNameAndPageBean;

    @Override
    public String toString() {
        return "EwfElemBean [componentClassName=" + componentClassName + ", text=" + text + ", fileName=" + fileName
                + ", elemCustomClassName=" + elemCustomClassName + ", mapPageNameAndPageBean=" + mapPageNameAndPageBean
                + "]";
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

}
