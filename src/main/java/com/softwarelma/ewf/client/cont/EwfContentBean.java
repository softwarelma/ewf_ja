package com.softwarelma.ewf.client.cont;

import java.io.Serializable;

public class EwfContentBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private boolean comp;// or element

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

}
