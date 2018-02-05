package com.softwarelma.ewf.client.comp;

import java.util.List;

import com.softwarelma.ewf.client.cont.EwfContentBean;

public class EwfCompBean {

    private String classNameLayout;
    private List<EwfContentBean> listContentBean;

    @Override
    public String toString() {
        return "EwfCompBean [classNameLayout=" + classNameLayout + ", listContentBean=" + listContentBean + "]";
    }

    public String getClassNameLayout() {
        return classNameLayout;
    }

    public void setClassNameLayout(String classNameLayout) {
        this.classNameLayout = classNameLayout;
    }

    public List<EwfContentBean> getListContentBean() {
        return listContentBean;
    }

    public void setListContentBean(List<EwfContentBean> listContentBean) {
        this.listContentBean = listContentBean;
    }

}
