package com.softwarelma.ewf.client.cont;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.client.EwfClient;
import com.softwarelma.ewf.client.elem.EwfElemBean;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

public abstract class EwfContentAbstract extends EwfContAbstract implements EwfContentInterface {

	private static final long serialVersionUID = 1L;
	private final Component component;// for a comp it is an AbstractLayout

	protected EwfContentAbstract(EwfClient client, UI ui, String name, EwfElemBean elemBean) throws EpeAppException {
		super(client, ui, name);
		String compElem = null;
		String className = null;

		try {
			if (this.isComp()) {
				compElem = "layout";
				className = client.getClassNameLayoutNotNull(name);
				this.component = (AbstractLayout) Class.forName(className).newInstance();
			} else if (this.isElem()) {
				EpeAppUtils.checkNull("elemBean", elemBean);

				if (elemBean.getElemCustomClassName() == null) {
					// default elem
					compElem = "default Vaadin component";
					className = elemBean.getComponentClassName();
					EpeAppUtils.checkEmpty("componentClassName", className);
					this.component = (AbstractComponent) Class.forName(className).newInstance();
				} else {
					// custom elem
					compElem = "custom Vaadin component";
					className = elemBean.getElemCustomClassName();
					EpeAppUtils.checkEmpty("elemCustomClassName", className);
					this.component = this.getComponent();
					EpeAppUtils.checkNull("this.component", this.component);
				}
			} else {
				throw new EpeAppException("A content should be either a comp or an elem.");
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new EpeAppException(
					"Invalid " + compElem + " class name \"" + className + "\" for comp name \"" + name + "\".", e);
		}
	}

	@Override
	public Component getComponent() {
		return component;
	}

	@Override
	public boolean isContainer() {
		return false;
	}

	@Override
	public boolean isContent() {
		return true;
	}

}
