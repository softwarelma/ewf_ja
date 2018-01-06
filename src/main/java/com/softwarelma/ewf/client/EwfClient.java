package com.softwarelma.ewf.client;

import java.util.ArrayList;
import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppLogger;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.client.cont.EwfContentBean;
import com.softwarelma.ewf.client.elem.EwfElemBean;
import com.softwarelma.ewf.client.page.EwfPageDefault;
import com.softwarelma.ewf.client.page.EwfPageInterface;
import com.softwarelma.ewf.common.EwfCommonConstants;
import com.vaadin.server.VaadinService;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

public class EwfClient {

	public void setSessionAttributeNotNull(String name, Object value) throws EpeAppException {
		EpeAppUtils.checkNull("value", value);
		this.setSessionAttributeOrNull(name, value);
	}

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

	public Object getSessionAttributeOrNull(String name) throws EpeAppException {
		EpeAppUtils.checkEmpty("name", name);
		return this.getWrappedSession().getAttribute(name);
	}

	public void removeSessionAttribute(String name) throws EpeAppException {
		EpeAppUtils.checkEmpty("name", name);
		this.getWrappedSession().removeAttribute(name);
	}

	private WrappedSession getWrappedSession() {
		return VaadinService.getCurrentRequest().getWrappedSession();
	}

	public void loadPage(UI ui, String pageName) throws EpeAppException {
		EpeAppUtils.checkNull("ui", ui);
		EpeAppUtils.checkEmpty("pageName", pageName);
		EwfPageInterface page = new EwfPageDefault(this, ui, pageName);
		Component content = page.getComp().getLayout();
		ui.setContent(content);
	}

	public String getCompNameNotNull(String pageName) throws EpeAppException {
		return this.getCompNameNotNullFake(pageName);
	}

	public String getClassNameLayoutNotNull(String compName) throws EpeAppException {
		return this.getClassNameLayoutNotNullFake(compName);
	}

	public List<EwfContentBean> getListContentBeanNotNull(String compName) throws EpeAppException {
		return this.getListContentBeanNotNullFake(compName);
	}

	public EwfElemBean getElemBeanNotNull(String elemName) throws EpeAppException {
		return this.getElemBeanNotNullFake(elemName);
	}

	////////////////////////////////////////////////////////////

	private String getCompNameNotNullFake(String pageName) throws EpeAppException {
		if (EwfCommonConstants.mapPageAndDescription.containsKey(pageName)) {
			return pageName + "Layout";
		} else {
			throw new EpeAppException("Invalid page " + pageName);
		}
	}

	private String getClassNameLayoutNotNullFake(String compName) throws EpeAppException {
		EpeAppUtils.checkEmpty("compName", compName);
		String lay1 = "com.vaadin.ui.CssLayout";
		String lay2 = "com.vaadin.ui.VerticalLayout";

		if (compName.endsWith("Layout")
				&& EwfCommonConstants.mapPageAndDescription.containsKey(compName.substring(0, compName.length() - 6))) {
			return lay2;
		}

		if ("saluteOlistica".equals(compName)) {
			return lay1;
		} else if ("benesserePsicofisico".equals(compName)) {
			return lay2;
		} else if ("sportivo".equals(compName)) {
			return lay2;
		} else if ("svedese".equals(compName)) {
			return lay2;
		} else if ("custom".equals(compName)) {
			return lay2;
		} else {
			throw new EpeAppException("Invalid comp " + compName);
		}
	}

	private EwfContentBean getMenu() {
		EwfContentBean contentBean = new EwfContentBean();
		contentBean.setComp(false);
		contentBean.setName(EwfCommonConstants.MENU_CLASS_NAME);
		return contentBean;
	}

	private List<EwfContentBean> getListContentBeanNotNullFake(String compName) throws EpeAppException {
		List<EwfContentBean> listContentBean = new ArrayList<>();
		EwfContentBean contentBean;
		List<String> listStyleName = new ArrayList<>();

		if ("homeLayout".equals(compName)) {
			listContentBean.add(this.getMenu());

			contentBean = new EwfContentBean();
			contentBean.setComp(true);
			listStyleName.add("backColorGreen");
			contentBean.setListStyleName(listStyleName);
			contentBean.setName("saluteOlistica");
			listContentBean.add(contentBean);

			contentBean = new EwfContentBean();
			contentBean.setComp(true);
			listStyleName.add("backColorGreen");
			contentBean.setListStyleName(listStyleName);
			contentBean.setName("benesserePsicofisico");
			listContentBean.add(contentBean);
		} else if ("trattamentiLayout".equals(compName)) {
			listContentBean.add(this.getMenu());

			contentBean = new EwfContentBean();
			contentBean.setComp(true);
			listStyleName.add("backColorGreen");
			contentBean.setListStyleName(listStyleName);
			contentBean.setName("sportivo");
			listContentBean.add(contentBean);

			contentBean = new EwfContentBean();
			contentBean.setComp(true);
			listStyleName.add("backColorGreen");
			contentBean.setListStyleName(listStyleName);
			contentBean.setName("svedese");
			listContentBean.add(contentBean);

			contentBean = new EwfContentBean();
			contentBean.setComp(true);
			listStyleName.add("backColorGreen");
			contentBean.setListStyleName(listStyleName);
			contentBean.setName("custom");
			listContentBean.add(contentBean);
		} else if ("chisonoLayout".equals(compName)) {
			listContentBean.add(this.getMenu());

			contentBean = new EwfContentBean();
			contentBean.setComp(false);
			contentBean.setName("chisonoLabel");
			listContentBean.add(contentBean);
		} else if ("blogLayout".equals(compName)) {
			listContentBean.add(this.getMenu());

			contentBean = new EwfContentBean();
			contentBean.setComp(false);
			contentBean.setName("blogLabel");
			listContentBean.add(contentBean);
		} else if ("contattiLayout".equals(compName)) {
			listContentBean.add(this.getMenu());

			contentBean = new EwfContentBean();
			contentBean.setComp(false);
			contentBean.setName("contattiLabel");
			listContentBean.add(contentBean);
		} else if ("mappaLayout".equals(compName)) {
			listContentBean.add(this.getMenu());

			contentBean = new EwfContentBean();
			contentBean.setComp(false);
			contentBean.setName("mappaLabel");
			listContentBean.add(contentBean);
		} else if ("saluteOlistica".equals(compName)) {
			contentBean = new EwfContentBean();
			contentBean.setComp(false);
			listStyleName.add("fontTitle");
			contentBean.setListStyleName(listStyleName);
			contentBean.setName("saluteOlisticaLabel1");
			listContentBean.add(contentBean);

			contentBean = new EwfContentBean();
			contentBean.setComp(false);
			listStyleName.add("fontSubtitle");
			contentBean.setListStyleName(listStyleName);
			contentBean.setName("saluteOlisticaLabel2");
			listContentBean.add(contentBean);

			contentBean = new EwfContentBean();
			contentBean.setComp(false);
			listStyleName.add("fontText");
			listStyleName.add("ewfItembox");
			contentBean.setListStyleName(listStyleName);
			contentBean.setName("saluteOlisticaLabel3");
			listContentBean.add(contentBean);

			contentBean = new EwfContentBean();
			contentBean.setComp(false);
			// listStyleName.add("backColorGreen");
			listStyleName.add("ewfItembox");
			contentBean.setListStyleName(listStyleName);
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

	public EwfElemBean getElemBeanNotNullFake(String elemName) throws EpeAppException {
		EpeAppUtils.checkEmpty("elemName", elemName);
		EwfElemBean elemBean = new EwfElemBean();
		String classNameComponent = "com.vaadin.ui.Label";

		if (elemName.startsWith("com.softwarelma.ewf.client.elem.EwfElemCustom")) {
			elemBean.setElemCustomClassName(elemName);

			if (elemName.startsWith("com.softwarelma.ewf.client.elem.EwfElemCustomMenu")) {
				elemBean.setMapPageAndDescription(EwfCommonConstants.mapPageAndDescription);
			} else if (elemName.startsWith("com.softwarelma.ewf.client.elem.EwfElemCustomImage")) {
				elemBean.setFileName("culo.jpeg");
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

}
