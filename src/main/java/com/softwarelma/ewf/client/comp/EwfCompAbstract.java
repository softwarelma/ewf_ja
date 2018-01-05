package com.softwarelma.ewf.client.comp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.client.EwfClient;
import com.softwarelma.ewf.client.cont.EwfContentAbstract;
import com.softwarelma.ewf.client.cont.EwfContentBean;
import com.softwarelma.ewf.client.cont.EwfContentInterface;
import com.softwarelma.ewf.client.elem.EwfElemAbstract;
import com.softwarelma.ewf.client.elem.EwfElemBean;
import com.softwarelma.ewf.client.elem.EwfElemDefault;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.UI;

public abstract class EwfCompAbstract extends EwfContentAbstract implements EwfCompInterface {

	private static final long serialVersionUID = 1L;
	private final List<EwfContentInterface> listContent;

	protected EwfCompAbstract(EwfClient client, UI ui, String name, EwfContentBean contentBean) throws EpeAppException {
		super(client, ui, null, contentBean);
		this.listContent = new ArrayList<>();
		this.init();
	}

	private void init() throws EpeAppException {
		List<EwfContentBean> listContentBean = this.getClient().getListContentBeanNotNull(this.getName());

		for (EwfContentBean contentBean : listContentBean) {
			EpeAppUtils.checkNull("contentBean", contentBean);
			EwfContentInterface content;

			if (contentBean.isComp()) {
				content = new EwfCompDefault(this.getClient(), this.getUi(), contentBean.getName(), contentBean);
			} else {
				if (contentBean.getName().startsWith("com.softwarelma.ewf.client.elem.EwfElemCustom")) {
					try {
						Constructor<?> constructor = Class.forName(contentBean.getName())
								.getConstructor(EwfClient.class, UI.class, EwfElemBean.class, EwfContentBean.class);
						content = (EwfElemAbstract) constructor.newInstance(this.getClient(), this.getUi(),
								this.getClient().getElemBeanNotNull(contentBean.getName()), contentBean);
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| NoSuchMethodException | SecurityException | IllegalArgumentException
							| InvocationTargetException e) {
						throw new EpeAppException("Invalid content name: " + contentBean.getName(), e);
					}
				} else {
					content = new EwfElemDefault(this.getClient(), this.getUi(),
							this.getClient().getElemBeanNotNull(contentBean.getName()), contentBean);
				}
			}

			this.listContent.add(content);
			this.getLayout().addComponent(content.getComponent());
		}
	}

	@Override
	public AbstractLayout getLayout() throws EpeAppException {
		return (AbstractLayout) this.getComponent();
	}

	@Override
	public boolean isPage() {
		return false;
	}

	@Override
	public boolean isComp() {
		return true;
	}

	@Override
	public boolean isElem() {
		return false;
	}

}
