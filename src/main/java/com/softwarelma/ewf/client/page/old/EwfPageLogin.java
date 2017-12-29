package com.softwarelma.ewf.client.page.old;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.dao.old.EwfDaoUser;
import com.softwarelma.ewf.backend.entity.old.EwfEntityAbstract;
import com.softwarelma.ewf.backend.entity.old.EwfEntityUser;
import com.softwarelma.ewf.client.comp.old.EwfCompFooter;
import com.softwarelma.ewf.client.old.EwfClientWeb;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class EwfPageLogin extends EwfPageAbstract {

	private static final long serialVersionUID = 1L;
	private final EwfCompFooter<EwfEntityAbstract> footer;

	public EwfPageLogin(EwfClientWeb web) throws EpeAppException {
		super(web);
		this.footer = new EwfCompFooter<>(web);
	}

	private Panel getPanelLogin() throws EpeAppException {
		Panel panelLogin = new Panel();
		panelLogin.setWidth("240px");

		TextField tfUsername = new TextField();
		tfUsername.setValue("ua");// FIXME remove
		tfUsername.setPlaceholder("Username");
		tfUsername.setWidth("200px");
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.addComponent(tfUsername);

		PasswordField pfPassword = new PasswordField();
		pfPassword.setValue("pa");// FIXME remove
		pfPassword.setPlaceholder("Password");
		pfPassword.setWidth("200px");
		verticalLayout.addComponent(pfPassword);

		CheckBox cbAdmin = new CheckBox();
		cbAdmin.setValue(true);// FIXME remove
		cbAdmin.setCaption("Admin");
		verticalLayout.addComponent(cbAdmin);

		Button bLogin = new Button("Login");
		bLogin.setWidth("200px");

		bLogin.addClickListener(e -> {
			String username = tfUsername.getValue();
			String password = pfPassword.getValue();
			boolean admin = cbAdmin.getValue();

			List<Map.Entry<String, Object>> listAttNameAndValue = new ArrayList<>();
			listAttNameAndValue.add(new AbstractMap.SimpleEntry<>("username", username));
			listAttNameAndValue.add(new AbstractMap.SimpleEntry<>("password", password));

			try {
				String daoClassName = EwfDaoUser.class.getName();
				List<EwfEntityAbstract> listEntity = this.getServer().readList(daoClassName, listAttNameAndValue);
				String pageName;

				if (listEntity.size() == 0) {
					pageName = EwfPageHome.class.getName();
				} else {
					EwfEntityUser user = (EwfEntityUser) listEntity.get(0);
					this.getServer().setSessionAttribute(EwfEntityUser.class.getName(), user);
					pageName = admin ? EwfPageAdminHome.class.getName() : EwfPageFinalPage.class.getName();
				}

				this.getWeb().navigate(pageName);
			} catch (EpeAppException e1) {
				// nothing
			}
		});

		verticalLayout.addComponent(bLogin);

		verticalLayout.setComponentAlignment(tfUsername, Alignment.MIDDLE_CENTER);
		verticalLayout.setComponentAlignment(pfPassword, Alignment.MIDDLE_CENTER);
		verticalLayout.setComponentAlignment(cbAdmin, Alignment.MIDDLE_CENTER);
		verticalLayout.setComponentAlignment(bLogin, Alignment.MIDDLE_CENTER);
		panelLogin.setContent(verticalLayout);
		return panelLogin;
	}

	@Override
	public Component getContent() throws EpeAppException {
		this.getServer().removeSessionAttribute(EwfEntityUser.class.getName());
		GridLayout gridLayout = new GridLayout(1, 2);
		gridLayout.setSizeFull();
		gridLayout.setRowExpandRatio(0, 1f);
		gridLayout.setRowExpandRatio(1, 0f);

		Panel panelLogin = this.getPanelLogin();
		gridLayout.addComponent(panelLogin, 0, 0);
		gridLayout.setComponentAlignment(panelLogin, Alignment.MIDDLE_CENTER);

		EwfEntityAbstract entity = null;
		Component compFooter = this.footer.getContent(entity);
		gridLayout.addComponent(compFooter, 0, 1);
		gridLayout.setComponentAlignment(compFooter, Alignment.BOTTOM_CENTER);

		return gridLayout;
	}

}
