package com.softwarelma.ewf.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.client.page.EwfPage;
import com.softwarelma.ewf.client.page.EwfPageAdmin;
import com.softwarelma.ewf.client.page.EwfPageAdminHome;
import com.softwarelma.ewf.client.page.EwfPageFinalPage;
import com.softwarelma.ewf.client.page.EwfPageLogin;
import com.softwarelma.ewf.client.page.EwfPageSite;
import com.softwarelma.ewf.main.EwfMainWeb;
import com.softwarelma.ewf.server.EwfServer;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Component;

public class EwfClientWeb implements Serializable {

    private static final long serialVersionUID = 1L;
    private final EwfServer server;
    private final Map<String, EwfPage> mapPageNameAndPage = new HashMap<>();

    public EwfClientWeb(EwfServer server) throws EpeAppException {
        EpeAppUtils.checkNull("server", server);
        this.server = server;
        this.init();
    }

    private void init() throws EpeAppException {
        this.mapPageNameAndPage.put(EwfPageLogin.class.getName(), new EwfPageLogin(this));
        this.mapPageNameAndPage.put(EwfPageFinalPage.class.getName(), new EwfPageFinalPage(this));
        this.mapPageNameAndPage.put(EwfPageAdminHome.class.getName(), new EwfPageAdminHome(this));
        this.mapPageNameAndPage.put(EwfPageAdmin.class.getName(), new EwfPageAdmin(this));
        this.mapPageNameAndPage.put(EwfPageSite.class.getName(), new EwfPageSite(this));
        this.navigate(EwfPageLogin.class.getName());
    }

    public void navigate(String pageName) throws EpeAppException {
        EwfMainWeb mainWeb = (EwfMainWeb) VaadinService.getCurrentRequest().getWrappedSession()
                .getAttribute(EwfMainWeb.class.getName());
        EpeAppUtils.checkNull("mainWeb", mainWeb);
        mainWeb.setContent(this.getContent(pageName));
    }

    public Component getContent(String pageName) throws EpeAppException {
        EpeAppUtils.checkEmpty("pageName", pageName);
        EwfPage page = this.mapPageNameAndPage.get(pageName);
        EpeAppUtils.checkNull("page", page);
        Component content = page.getContent();
        EpeAppUtils.checkNull("content", content);
        return content;
    }

    public EwfServer getServer() {
        return server;
    }

}
