package com.softwarelma.ewf.client.elem;

import java.io.Serializable;

public class EwfElemBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String componentClassName;// vaadin
	private String text;
	private String elemCustomClassName;// ewf

	public String getComponentClassName() {
		return componentClassName;
	}

	public void setComponentClassName(String componentClassName) {
		this.componentClassName = componentClassName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getElemCustomClassName() {
		return elemCustomClassName;
	}

	public void setElemCustomClassName(String elemCustomClassName) {
		this.elemCustomClassName = elemCustomClassName;
	}

}
