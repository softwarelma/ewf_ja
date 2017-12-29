package com.softwarelma.ewf.client.old;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.client.page.old.EwfPage;
import com.softwarelma.ewf.client.page.old.EwfPageAdmin;
import com.softwarelma.ewf.client.page.old.EwfPageAdminHome;
import com.softwarelma.ewf.client.page.old.EwfPageFinalPage;
import com.softwarelma.ewf.client.page.old.EwfPageHome;
import com.softwarelma.ewf.client.page.old.EwfPageHomeP1;
import com.softwarelma.ewf.client.page.old.EwfPageHomeP2;
import com.softwarelma.ewf.client.page.old.EwfPageLogin;
import com.softwarelma.ewf.client.page.old.EwfPageSite;
import com.softwarelma.ewf.main.old.EwfMainWeb;
import com.softwarelma.ewf.server.old.EwfServer;
import com.vaadin.server.VaadinService;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

public class EwfClientWeb implements Serializable {

	private static final long serialVersionUID = 1L;
	private final EwfServer server;
	private final Map<String, EwfPage> mapPageNameAndPage = new HashMap<>();

	public EwfClientWeb(EwfServer server, String home) throws EpeAppException {
		EpeAppUtils.checkNull("server", server);
		this.server = server;
		this.init(home);
	}

	private void init(String home) throws EpeAppException {
		this.mapPageNameAndPage.put(EwfPageLogin.class.getName(), new EwfPageLogin(this));
		this.mapPageNameAndPage.put(EwfPageFinalPage.class.getName(), new EwfPageFinalPage(this));
		this.mapPageNameAndPage.put(EwfPageAdminHome.class.getName(), new EwfPageAdminHome(this));
		this.mapPageNameAndPage.put(EwfPageAdmin.class.getName(), new EwfPageAdmin(this));
		this.mapPageNameAndPage.put(EwfPageSite.class.getName(), new EwfPageSite(this));

		this.mapPageNameAndPage.put(EwfPageHome.class.getName(), new EwfPageHome(this));
		this.mapPageNameAndPage.put(EwfPageHomeP1.class.getName(), new EwfPageHomeP1(this));
		this.mapPageNameAndPage.put(EwfPageHomeP2.class.getName(), new EwfPageHomeP2(this));

		if ("home".equals(home)) {
			this.navigate(EwfPageHome.class.getName());
		} else if ("p1".equals(home)) {
			this.navigate(EwfPageHomeP1.class.getName());
		} else if ("p2".equals(home)) {
			this.navigate(EwfPageHomeP2.class.getName());
		} else {
			this.navigate(EwfPageLogin.class.getName());
		}
	}

	public void navigate(String pageName) throws EpeAppException {
		WrappedSession session = VaadinService.getCurrentRequest().getWrappedSession();
		System.out.println("session id = " + session.getId());
		UI ewfUi = (UI) session.getAttribute("ewfUi");
		EpeAppUtils.checkNull("ewfUi", ewfUi);
		ewfUi.setContent(this.getContent(pageName));
	}

	public void navigateOld(String pageName) throws EpeAppException {
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
