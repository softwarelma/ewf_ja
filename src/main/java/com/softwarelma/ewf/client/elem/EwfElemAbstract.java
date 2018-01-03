package com.softwarelma.ewf.client.elem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.EwfClient;
import com.softwarelma.ewf.client.cont.EwfContentAbstract;
import com.vaadin.ui.UI;

public abstract class EwfElemAbstract extends EwfContentAbstract implements EwfElemInterface {

	private static final long serialVersionUID = 1L;

	protected EwfElemAbstract(EwfClient client, UI ui, String name, EwfElemBean elemBean) throws EpeAppException {
		super(client, ui, name, elemBean);
		this.init(elemBean == null ? null : elemBean.getText());
	}

	private void init(String text) throws EpeAppException {
		if (text == null) {
			return;
		}

		try {
			Method method = this.getComponent().getClass().getMethod("setValue", String.class);
			method.invoke(this.getComponent(), text);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new EpeAppException("Invalid method setValue", e);
		}
	}

	@Override
	public boolean isPage() {
		return false;
	}

	@Override
	public boolean isComp() {
		return false;
	}

	@Override
	public boolean isElem() {
		return true;
	}

}
