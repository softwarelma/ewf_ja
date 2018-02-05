package com.softwarelma.ewf.client.cont;

import java.io.Serializable;
import java.util.List;

public class EwfContentBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private boolean comp;// or element
    private List<String> listStyleName;

    @Override
    public String toString() {
        return "EwfContentBean [name=" + name + ", comp=" + comp + ", listStyleName=" + listStyleName + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isComp() {
        return comp;
    }

    public void setComp(boolean comp) {
        this.comp = comp;
    }

    public boolean isElem() {
        return !this.comp;
    }

    public List<String> getListStyleName() {
        return listStyleName;
    }

    public void setListStyleName(List<String> listStyleName) {
        this.listStyleName = listStyleName;
    }

}
