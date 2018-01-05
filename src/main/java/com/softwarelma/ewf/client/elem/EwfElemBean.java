package com.softwarelma.ewf.client.elem;

import java.io.Serializable;
import java.util.Map;

public class EwfElemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// DEFAULT

	private String componentClassName;// vaadin
	private String text;
	private String fileName;

	// CUSTOM

	private String elemCustomClassName;// ewf
	public Map<String, String> mapPageAndDescription;

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

	public Map<String, String> getMapPageAndDescription() {
		return mapPageAndDescription;
	}

	public void setMapPageAndDescription(Map<String, String> mapPageAndDescription) {
		this.mapPageAndDescription = mapPageAndDescription;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
