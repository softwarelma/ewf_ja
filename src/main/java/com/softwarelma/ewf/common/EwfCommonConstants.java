package com.softwarelma.ewf.common;

import java.util.LinkedHashMap;
import java.util.Map;

public class EwfCommonConstants {

	public static final String APPLICATION_NAME = "ewf";

	public static final String PAGE_NAME_HOME = "home";
	public static final String PAGE_NAME_TRATTAMENTI = "trattamenti";
	public static final String PAGE_NAME_CHI_SONO = "chisono";
	public static final String PAGE_NAME_BLOG = "blog";
	public static final String PAGE_NAME_CONTATTI = "contatti";
	public static final String PAGE_NAME_MAPPA = "mappa";

	public static final Map<String, String> mapPageAndDescription = new LinkedHashMap<>();
	static {
		mapPageAndDescription.put(PAGE_NAME_HOME, "Home");
		mapPageAndDescription.put(PAGE_NAME_TRATTAMENTI, "Trattamenti");
		mapPageAndDescription.put(PAGE_NAME_CHI_SONO, "Chi Sono");
		mapPageAndDescription.put(PAGE_NAME_BLOG, "Blog");
		mapPageAndDescription.put(PAGE_NAME_CONTATTI, "Contatti");
		mapPageAndDescription.put(PAGE_NAME_MAPPA, "Mappa");
	}

}
