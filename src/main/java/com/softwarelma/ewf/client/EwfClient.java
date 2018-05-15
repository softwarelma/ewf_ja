package com.softwarelma.ewf.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;

import com.softwarelma.epe.p1.app.EpeAppConstants;
import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppLogger;
import com.softwarelma.epe.p1.app.EpeAppRuntimeException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.epe.p3.db.EpeDbEntity;
import com.softwarelma.epe.p3.db.EpeDbMetaDataEntity;
import com.softwarelma.ewf.client.comp.EwfCompBean;
import com.softwarelma.ewf.client.cont.EwfContentBean;
import com.softwarelma.ewf.client.elem.EwfElemBean;
import com.softwarelma.ewf.client.page.EwfPageBean;
import com.softwarelma.ewf.client.page.EwfPageDefault;
import com.softwarelma.ewf.client.page.EwfPageInterface;
import com.softwarelma.ewf.common.EwfCommonConstants;
import com.softwarelma.ewf.main.EwfMain;
import com.softwarelma.ewf.server.EwfServer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

public class EwfClient {

    private Map<String, EwfPageBean> mapPageNameAndPageBean;
    private Map<String, EwfCompBean> mapCompNameAndCompBean;
    private Map<String, EwfElemBean> mapElemNameAndElemBean;
    private final Map<Long, WrappedSession> mapIdThreadAndSession = new HashMap<>();
    private final Map<String, Map<String, EwfPageInterface>> mapIdSessionAndMapPageName = new HashMap<>();
    private final Map<String, String> mapIdSessionAndPageName = new HashMap<>();
    private final EwfServer server;

    public EwfClient(EwfServer server) throws EpeAppException {
        this.server = server;
        this.init();
        this.launchThread();
    }

    private void init() throws EpeAppException {
        Map<String, EwfPageBean> mapPageNameAndPageBean = this.server.retrieveMapPageNameAndPageBean();
        Map<String, EwfCompBean> mapCompNameAndCompBean = this.server.retrieveMapCompNameAndCompBean();
        Map<String, EwfElemBean> mapElemNameAndElemBean = this.server.retrieveMapElemNameAndElemBean();

        if (this.equals(mapPageNameAndPageBean, mapCompNameAndCompBean, mapElemNameAndElemBean))
            return;

        this.mapElemNameAndElemBean = mapElemNameAndElemBean;
        this.mapCompNameAndCompBean = mapCompNameAndCompBean;
        this.mapPageNameAndPageBean = mapPageNameAndPageBean;

        for (String idSession : this.mapIdSessionAndPageName.keySet()) {
            EpeAppUtils.checkEmpty("idSession", idSession);
            String pageName = this.mapIdSessionAndPageName.get(idSession);
            EpeAppUtils.checkEmpty("pageName", pageName);
            EwfPageInterface page = this.mapIdSessionAndMapPageName.get(idSession).get(pageName);
            loadPage(page.getUi(), idSession, pageName);
        }
    }

    private boolean equals(Map<String, EwfPageBean> mapPageNameAndPageBean,
            Map<String, EwfCompBean> mapCompNameAndCompBean, Map<String, EwfElemBean> mapElemNameAndElemBean) {
        if (this.mapPageNameAndPageBean == null || mapPageNameAndPageBean.size() != this.mapPageNameAndPageBean.size())
            return false;
        for (String name : mapPageNameAndPageBean.keySet()) {
            if (!mapPageNameAndPageBean.get(name).equals(this.mapPageNameAndPageBean.get(name)))
                return false;
        }

        if (this.mapCompNameAndCompBean == null || mapCompNameAndCompBean.size() != this.mapCompNameAndCompBean.size())
            return false;
        for (String name : mapCompNameAndCompBean.keySet()) {
            if (!mapCompNameAndCompBean.get(name).equals(this.mapCompNameAndCompBean.get(name)))
                return false;
        }

        if (this.mapElemNameAndElemBean == null || mapElemNameAndElemBean.size() != this.mapElemNameAndElemBean.size())
            return false;
        for (String name : mapElemNameAndElemBean.keySet()) {
            if (!mapElemNameAndElemBean.get(name).equals(this.mapElemNameAndElemBean.get(name)))
                return false;
        }

        return true;
    }

    private void launchThread() {
        new Thread() {

            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(5000);// TODO from param
                        System.out.println("on thread...");// FIXME remove
                        init();
                        System.out.println();
                    }
                } catch (InterruptedException | EpeAppException e) {
                    throw new EpeAppRuntimeException("EwfClient.launchThread()", e);
                }
            }

        }.start();
    }

    public EpeDbMetaDataEntity retrieveListEntity(String select, String table, List<EpeDbEntity> listEntity)
            throws EpeAppException {
        return this.server.retrieveListEntity(select, table, listEntity);
    }

    public void setSessionAttributeNotNull(String name, Object value) throws EpeAppException {
        EpeAppUtils.checkNull("value", value);
        this.setSessionAttributeOrNull(name, value);
    }

    // FIXME
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

    // FIXME
    public Object getSessionAttributeOrNull(String name) throws EpeAppException {
        EpeAppUtils.checkEmpty("name", name);
        return this.getWrappedSession().getAttribute(name);
    }

    public void removeSessionAttribute(String name) throws EpeAppException {
        EpeAppUtils.checkEmpty("name", name);
        this.getWrappedSession().removeAttribute(name);
    }

    private WrappedSession getWrappedSession() throws EpeAppException {
        VaadinRequest request = VaadinService.getCurrentRequest();
        EpeAppUtils.checkNull("request", request);
        WrappedSession session = request.getWrappedSession();
        EpeAppUtils.checkNull("session", session);
        return session;
    }

    public String getIdSession() throws EpeAppException {
        return this.getWrappedSession().getId();
    }

    public void saveSession() throws EpeAppException {
        WrappedSession session = this.getWrappedSession();
        this.mapIdThreadAndSession.put(Thread.currentThread().getId(), session);
    }

    ////////////////////////////////////////////////////////////
    ///////////// PAGE LOADING
    ////////////////////////////////////////////////////////////

    public void loadPage(UI ui, String idSession, String pageName) throws EpeAppException {
        // Init done, update the UI after doing locking
        ui.access(new Runnable() {
            @Override
            public void run() {
                // Here the UI is locked and can be updated
                try {
                    loadPageInternal(ui, idSession, pageName);
                    // setContent(new Label("Hello World!"));
                } catch (EpeAppException e) {
                }
            }
        });
    }

    private void loadPageInternal(UI ui, String idSession, String pageName) throws EpeAppException {
        EpeAppUtils.checkNull("ui", ui);
        EpeAppUtils.checkNull("idSession", idSession);
        EpeAppUtils.checkEmpty("pageName", pageName);

        if (VaadinService.getCurrentRequest() != null)
            setSessionAttributeNotNull(EwfCommonConstants.SESSION_SELECTED_PAGE, pageName);

        // LOADING
        EwfPageInterface page = new EwfPageDefault();
        Component content;

        boolean showExceptions = EpeAppConstants.SHOW_EXCEPTIONS;
        EpeAppConstants.SHOW_EXCEPTIONS = false;

        try {
            page.init(this, ui, pageName);
            content = page.getComp().getLayout();
        } catch (EpeAppException e) {
            content = EwfMain.getDefaultContent("Site under construction");
        }

        EpeAppConstants.SHOW_EXCEPTIONS = showExceptions;

        // new EwfClientFake().addGridFake(content); // TODO
        System.out.println("\tloading page " + pageName + ", id session " + idSession);// TODO
        ui.setContent(content);

        // CACHING
        // String idSession = this.getIdSessionOrNull();
        // if (idSession == null)
        // return;
        Map<String, EwfPageInterface> mapPageName = this.mapIdSessionAndMapPageName.get(idSession);
        if (mapPageName == null)
            this.mapIdSessionAndMapPageName.put(idSession, mapPageName = new HashMap<>());
        this.mapIdSessionAndPageName.put(idSession, pageName);
        mapPageName.put(pageName, page);
    }

    public void insertBlank(String table) throws EpeAppException {
        this.server.insertBlank(table);
    }

    public void update(EpeDbEntity entity) throws EpeAppException {
        this.server.update(entity);
    }

    public void delete(EpeDbEntity entity) throws EpeAppException {
        this.server.delete(entity);
    }

    ////////////////////////////////////////////////////////////
    ///////////// 3 LEVELS
    ////////////////////////////////////////////////////////////

    public EwfPageBean getPageBeanNotNull(String pageName) throws EpeAppException {
        EwfPageBean pageBean = mapPageNameAndPageBean.get(pageName);
        EpeAppUtils.checkNull("pageBean", pageBean);
        return pageBean;
        // return this.getPageBeanNotNullFake(pageName);
    }

    public EwfCompBean getCompBeanNotNull(String compName) throws EpeAppException {
        EwfCompBean compBean = this.mapCompNameAndCompBean.get(compName);
        EpeAppUtils.checkNull("compBean", compBean);
        return compBean;
        // return this.getCompBeanNotNullFake(compName);
    }

    public EwfElemBean getElemBeanNotNull(String elemName) throws EpeAppException {
        EwfElemBean elemBean = this.mapElemNameAndElemBean.get(elemName);
        EpeAppUtils.checkNull("elemBean", elemBean);

        if (elemBean.getElemCustomClassName() != null
                && elemBean.getElemCustomClassName().startsWith("com.softwarelma.ewf.client.elem.EwfElemCustomMenu")) {
            elemBean.setMapPageNameAndPageBean(mapPageNameAndPageBean);
        }

        // if
        // (elemName.startsWith("com.softwarelma.ewf.client.elem.EwfElemCustomMenu"))
        // {
        // elemBean.setMapPageNameAndPageBean(mapPageNameAndPageBean);
        // }

        return elemBean;
        // return this.getElemBeanNotNullFake(elemName);
    }

    ////////////////////////////////////////////////////////////
    ///////////// FAKE - 1
    ////////////////////////////////////////////////////////////

    @SuppressWarnings("unused")
    private EwfPageBean getPageBeanNotNullFake(String pageName) throws EpeAppException {
        if (mapPageNameAndPageBean.containsKey(pageName)) {
            EwfPageBean pageBean = new EwfPageBean();
            pageBean.setCompName(pageName + "Layout");
            return pageBean;
        } else {
            throw new EpeAppException("Invalid page " + pageName);
        }
    }

    @SuppressWarnings("unused")
    private EwfCompBean getCompBeanNotNullFake(String compName) throws EpeAppException {
        EpeAppUtils.checkEmpty("compName", compName);
        EwfCompBean compBean = new EwfCompBean();
        String lay1 = "com.vaadin.ui.CssLayout";
        String lay2 = "com.vaadin.ui.VerticalLayout";
        String lay3 = "com.vaadin.ui.HorizontalLayout";

        if (compName.endsWith("Layout")
                && mapPageNameAndPageBean.containsKey(compName.substring(0, compName.length() - 6))) {
            compBean.setClassNameLayout(lay2);
        } else if ("saluteOlistica".equals(compName)) {
            compBean.setClassNameLayout(lay2);
        } else if ("saluteOlisticaInterno1".equals(compName)) {
            compBean.setClassNameLayout(lay1);
        } else if ("saluteOlisticaInterno2".equals(compName)) {
            compBean.setClassNameLayout(lay1);
        } else if ("saluteOlisticaInterno3".equals(compName)) {
            compBean.setClassNameLayout(lay1);
        } else if ("benesserePsicofisico".equals(compName)) {
            compBean.setClassNameLayout(lay2);
        } else if ("sportivo".equals(compName)) {
            compBean.setClassNameLayout(lay2);
        } else if ("svedese".equals(compName)) {
            compBean.setClassNameLayout(lay2);
        } else if ("custom".equals(compName)) {
            compBean.setClassNameLayout(lay2);
        } else {
            throw new EpeAppException("Invalid comp " + compName);
        }

        compBean.setListContentBean(this.getListContentBeanNotNullFake(compName));
        return compBean;
    }

    public EwfElemBean getElemBeanNotNullFake(String elemName) throws EpeAppException {
        EpeAppUtils.checkEmpty("elemName", elemName);
        EwfElemBean elemBean = new EwfElemBean();
        String classNameComponent = "com.vaadin.ui.Label";

        if (elemName.startsWith("com.softwarelma.ewf.client.elem.EwfElemCustom")) {
            elemBean.setElemCustomClassName(elemName);

            if (elemName.startsWith("com.softwarelma.ewf.client.elem.EwfElemCustomMenu")) {
                elemBean.setMapPageNameAndPageBean(mapPageNameAndPageBean);
            } else if (elemName.startsWith("com.softwarelma.ewf.client.elem.EwfElemCustomImage")) {
                elemBean.setFileName("robot.jpeg");
            }
        } else {
            if (elemName.startsWith("saluteOlisticaLabel1")) {
                elemBean.setComponentClassName(classNameComponent);
                elemBean.setText("Titolo ksad lskjfownvo slnfow");
            } else if (elemName.startsWith("saluteOlisticaLabel2")) {
                elemBean.setComponentClassName(classNameComponent);
                elemBean.setText("Sottotitolo slkfjslfjowinvom fiwjf wo fwif aljfoffn af owifhwoi");
            } else if (elemName.startsWith("saluteOlisticaLabel3")) {
                elemBean.setComponentClassName(classNameComponent);
                elemBean.setText(
                        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Intro slf slfj slfjslk  jlsjwonlsoiw  lsj flsaj fsljf lsj fsljf lska jfsljf as jfñasljpfoiqjhp9hgpqpuiga hFH AH A HFOAIF JHUIQPHPFA  ñafjasñoifjw lksj fslkfj op jalfj alfj alfj afioqjfoiwjhfowi flf alj. <br>"
                                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Svil  slf slfj slfjslk  jlsjwonlsoiw  lsj flsaj fsljf lsj fsljf lska jfsljf as jfñasljpfoiqjhp9hgpqpuiga hFH AH A HFOAIF JHUIQPHPFA  ñafjasñoifjw lksj fslkfj op jalfj alfj alfj afioqjfoiwjhfowi flf alj. <br>"
                                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Fine  slf slfj slfjslk  jlsjwonlsoiw  lsj flsaj fsljf lsj fsljf lska jfsljf as jfñasljpfoiqjhp9hgpqpuiga hFH AH A HFOAIF JHUIQPHPFA  ñafjasñoifjw lksj fslkfj op jalfj alfj alfj afioqjfoiwjhfowi flf alj.");
            } else {
                elemBean.setComponentClassName(classNameComponent);
                elemBean.setText(elemName + " text");
            }
        }

        return elemBean;
    }

    ////////////////////////////////////////////////////////////
    ///////////// FAKE - 2
    ////////////////////////////////////////////////////////////

    private EwfContentBean getMenuFake() {
        EwfContentBean contentBean = new EwfContentBean();
        contentBean.setComp(false);
        contentBean.setName(EwfCommonConstants.MENU_CLASS_NAME);
        return contentBean;
    }

    private List<EwfContentBean> getListContentBeanNotNullFake(String compName) throws EpeAppException {
        List<EwfContentBean> listContentBean = new ArrayList<>();
        EwfContentBean contentBean;

        if ("homeLayout".equals(compName)) {
            listContentBean.add(this.getMenuFake());

            contentBean = new EwfContentBean();
            contentBean.setComp(true);
            contentBean.setListStyleName(Arrays.asList("ewfBackColorGreen"));
            contentBean.setName("saluteOlistica");
            listContentBean.add(contentBean);

            contentBean = new EwfContentBean();
            contentBean.setComp(true);
            contentBean.setListStyleName(Arrays.asList("ewfBackColorGreen"));
            contentBean.setName("benesserePsicofisico");
            listContentBean.add(contentBean);
        } else if ("trattamentiLayout".equals(compName)) {
            listContentBean.add(this.getMenuFake());

            contentBean = new EwfContentBean();
            contentBean.setComp(true);
            contentBean.setListStyleName(Arrays.asList("ewfBackColorGreen"));
            contentBean.setName("sportivo");
            listContentBean.add(contentBean);

            contentBean = new EwfContentBean();
            contentBean.setComp(true);
            contentBean.setListStyleName(Arrays.asList("ewfBackColorGreen"));
            contentBean.setName("svedese");
            listContentBean.add(contentBean);

            contentBean = new EwfContentBean();
            contentBean.setComp(true);
            contentBean.setListStyleName(Arrays.asList("ewfBackColorGreen"));
            contentBean.setName("custom");
            listContentBean.add(contentBean);
        } else if ("chisonoLayout".equals(compName)) {
            listContentBean.add(this.getMenuFake());

            contentBean = new EwfContentBean();
            contentBean.setComp(false);
            contentBean.setName("chisonoLabel");
            listContentBean.add(contentBean);
        } else if ("blogLayout".equals(compName)) {
            listContentBean.add(this.getMenuFake());

            contentBean = new EwfContentBean();
            contentBean.setComp(false);
            contentBean.setName("blogLabel");
            listContentBean.add(contentBean);
        } else if ("contattiLayout".equals(compName)) {
            listContentBean.add(this.getMenuFake());

            contentBean = new EwfContentBean();
            contentBean.setComp(false);
            contentBean.setName("contattiLabel");
            listContentBean.add(contentBean);
        } else if ("mappaLayout".equals(compName)) {
            listContentBean.add(this.getMenuFake());

            contentBean = new EwfContentBean();
            contentBean.setComp(false);
            contentBean.setName("mappaLabel");
            listContentBean.add(contentBean);
        } else if ("saluteOlistica".equals(compName)) {
            contentBean = new EwfContentBean();
            contentBean.setComp(true);
            contentBean.setListStyleName(Arrays.asList("ewfResponsiveTitle"));
            contentBean.setName("saluteOlisticaInterno1");
            listContentBean.add(contentBean);

            contentBean = new EwfContentBean();
            contentBean.setComp(true);
            contentBean.setListStyleName(Arrays.asList("ewfResponsiveSubtitle"));
            contentBean.setName("saluteOlisticaInterno2");
            listContentBean.add(contentBean);

            contentBean = new EwfContentBean();
            contentBean.setComp(true);
            contentBean.setListStyleName(Arrays.asList("ewfFlexWrap"));
            contentBean.setName("saluteOlisticaInterno3");
            listContentBean.add(contentBean);
        } else if ("saluteOlisticaInterno1".equals(compName)) {
            contentBean = new EwfContentBean();
            contentBean.setComp(false);
            contentBean.setListStyleName(Arrays.asList("ewfFontTitle"));
            contentBean.setName("saluteOlisticaLabel1");
            listContentBean.add(contentBean);
        } else if ("saluteOlisticaInterno2".equals(compName)) {
            contentBean = new EwfContentBean();
            contentBean.setComp(false);
            contentBean.setListStyleName(Arrays.asList("ewfFontSubtitle"));
            contentBean.setName("saluteOlisticaLabel2");
            listContentBean.add(contentBean);
        } else if ("saluteOlisticaInterno3".equals(compName)) {
            contentBean = new EwfContentBean();
            contentBean.setComp(false);
            contentBean.setListStyleName(Arrays.asList("ewfItembox"));
            contentBean.setName("saluteOlisticaLabel3");
            listContentBean.add(contentBean);

            contentBean = new EwfContentBean();
            contentBean.setComp(false);
            // listStyleName.add("backColorGreen");
            contentBean.setListStyleName(Arrays.asList("ewfItembox"));
            contentBean.setName("com.softwarelma.ewf.client.elem.EwfElemCustomImage");
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

}
