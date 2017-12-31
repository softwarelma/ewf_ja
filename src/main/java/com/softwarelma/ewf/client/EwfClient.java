package com.softwarelma.ewf.client;

import java.util.ArrayList;
import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppLogger;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.client.cont.EwfContentBean;
import com.softwarelma.ewf.client.page.EwfPageDefault;
import com.softwarelma.ewf.client.page.EwfPageInterface;
import com.vaadin.server.VaadinService;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

public class EwfClient {

    public void setSessionAttributeNotNull(String name, Object value) throws EpeAppException {
        EpeAppUtils.checkNull("value", value);
        this.setSessionAttributeOrNull(name, value);
    }

    public void setSessionAttributeOrNull(String name, Object value) throws EpeAppException {
        EpeAppUtils.checkEmpty("name", name);
        EpeAppLogger.log("Setting session attribute with name: " + name + ", and value: " + value);
        this.getWrappedSession().setAttribute(name, value);
    }

    public Object getSessionAttributeNotNull(String name) throws EpeAppException {
        Object value = this.getSessionAttributeOrNull(name);
        EpeAppUtils.checkNull("value", value);
        return value;
    }

    public Object getSessionAttributeOrNull(String name) throws EpeAppException {
        EpeAppUtils.checkEmpty("name", name);
        return this.getWrappedSession().getAttribute(name);
    }

    public void removeSessionAttribute(String name) throws EpeAppException {
        EpeAppUtils.checkEmpty("name", name);
        this.getWrappedSession().removeAttribute(name);
    }

    private WrappedSession getWrappedSession() {
        return VaadinService.getCurrentRequest().getWrappedSession();
    }

    public void loadPage(UI ui, String pageName) throws EpeAppException {
        EpeAppUtils.checkNull("ui", ui);
        EpeAppUtils.checkEmpty("pageName", pageName);
        EwfPageInterface page = new EwfPageDefault(this, pageName);
        Component content = page.getComp().getLayout();
        ui.setContent(content);
    }

    public String getCompNameNotNull(String pageName) throws EpeAppException {
        return this.getCompNameNotNullFake(pageName);
    }

    public String getClassNameLayoutNotNull(String compName) throws EpeAppException {
        return this.getClassNameLayoutNotNullFake(compName);
    }

    public List<EwfContentBean> getListContentBeanNotNull(String compName) throws EpeAppException {
        return this.getListContentBeanNotNullFake(compName);
    }

    public String getClassNameComponentNotNull(String elemName) throws EpeAppException {
        return this.getClassNameComponentNotNullFake(elemName);
    }

    public String getTextNotNull(String elemName) throws EpeAppException {
        return this.getTextNotNullFake(elemName);
    }

    ////////////////////////////////////////////////////////////

    private String getCompNameNotNullFake(String pageName) throws EpeAppException {
        if ("home".equals(pageName)) {
            return "homeLayout";
        } else if ("trattamenti".equals(pageName)) {
            return "trattamentiLayout";
        } else {
            throw new EpeAppException("Invalid page " + pageName);
        }
    }

    private String getClassNameLayoutNotNullFake(String compName) throws EpeAppException {
        String lay1 = "com.vaadin.ui.CssLayout";
        String lay2 = "com.vaadin.ui.VerticalLayout";

        if ("homeLayout".equals(compName)) {
            return lay2;
        } else if ("trattamentiLayout".equals(compName)) {
            return lay2;
        } else if ("saluteOlistica".equals(compName)) {
            return lay2;
        } else if ("benesserePsicofisico".equals(compName)) {
            return lay2;
        } else if ("sportivo".equals(compName)) {
            return lay2;
        } else if ("svedese".equals(compName)) {
            return lay2;
        } else if ("custom".equals(compName)) {
            return lay2;
        } else {
            throw new EpeAppException("Invalid comp " + compName);
        }
    }

    private List<EwfContentBean> getListContentBeanNotNullFake(String compName) throws EpeAppException {
        List<EwfContentBean> listContentBean = new ArrayList<>();
        EwfContentBean contentBean;

        if ("homeLayout".equals(compName)) {
            contentBean = new EwfContentBean();
            contentBean.setComp(true);
            contentBean.setName("saluteOlistica");
            listContentBean.add(contentBean);

            contentBean = new EwfContentBean();
            contentBean.setComp(true);
            contentBean.setName("benesserePsicofisico");
            listContentBean.add(contentBean);
        } else if ("trattamentiLayout".equals(compName)) {
            contentBean = new EwfContentBean();
            contentBean.setComp(true);
            contentBean.setName("sportivo");
            listContentBean.add(contentBean);

            contentBean = new EwfContentBean();
            contentBean.setComp(true);
            contentBean.setName("svedese");
            listContentBean.add(contentBean);

            contentBean = new EwfContentBean();
            contentBean.setComp(true);
            contentBean.setName("custom");
            listContentBean.add(contentBean);
        } else if ("saluteOlistica".equals(compName)) {
            contentBean = new EwfContentBean();
            contentBean.setComp(false);
            contentBean.setName("saluteOlisticaLabel");
            listContentBean.add(contentBean);
        } else if ("benesserePsicofisico".equals(compName)) {
            contentBean = new EwfContentBean();
            contentBean.setComp(false);
            contentBean.setName("benesserePsicofisicoLabel");
            listContentBean.add(contentBean);
        } else if ("sportivo".equals(compName)) {
            contentBean = new EwfContentBean();
            contentBean.setComp(false);
            contentBean.setName("sportivoLabel");
            listContentBean.add(contentBean);
        } else if ("svedese".equals(compName)) {
            contentBean = new EwfContentBean();
            contentBean.setComp(false);
            contentBean.setName("svedeseLabel");
            listContentBean.add(contentBean);
        } else if ("custom".equals(compName)) {
            contentBean = new EwfContentBean();
            contentBean.setComp(false);
            contentBean.setName("customLabel");
            listContentBean.add(contentBean);
        } else {
            throw new EpeAppException("Invalid comp " + compName);
        }

        return listContentBean;
    }

    private String getClassNameComponentNotNullFake(String elemName) throws EpeAppException {
        String elem = "com.vaadin.ui.Label";

        if ("saluteOlisticaLabel".equals(elemName)) {
            return elem;
        } else if ("benesserePsicofisicoLabel".equals(elemName)) {
            return elem;
        } else if ("sportivoLabel".equals(elemName)) {
            return elem;
        } else if ("svedeseLabel".equals(elemName)) {
            return elem;
        } else if ("customLabel".equals(elemName)) {
            return elem;
        } else {
            throw new EpeAppException("Invalid elem " + elemName);
        }
    }

    private String getTextNotNullFake(String elemName) throws EpeAppException {
        if ("saluteOlisticaLabel".equals(elemName)) {
            return elemName + " text";
        } else if ("benesserePsicofisicoLabel".equals(elemName)) {
            return elemName + " text";
        } else if ("sportivoLabel".equals(elemName)) {
            return elemName + " text";
        } else if ("svedeseLabel".equals(elemName)) {
            return elemName + " text";
        } else if ("customLabel".equals(elemName)) {
            return elemName + " text";
        } else {
            throw new EpeAppException("Invalid elem " + elemName);
        }
    }

}
