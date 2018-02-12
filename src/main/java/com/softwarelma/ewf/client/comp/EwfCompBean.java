package com.softwarelma.ewf.client.comp;

import java.util.List;

import com.softwarelma.ewf.client.cont.EwfContentBean;

public class EwfCompBean {

	private String classNameLayout;
	private List<EwfContentBean> listContentBean;

	@Override
	public String toString() {
		return "EwfCompBean [classNameLayout=" + classNameLayout + ", listContentBean=" + listContentBean + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classNameLayout == null) ? 0 : classNameLayout.hashCode());
		result = prime * result + ((listContentBean == null) ? 0 : listContentBean.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EwfCompBean other = (EwfCompBean) obj;
		if (classNameLayout == null) {
			if (other.classNameLayout != null)
				return false;
		} else if (!classNameLayout.equals(other.classNameLayout))
			return false;
		if (listContentBean == null) {
			if (other.listContentBean != null)
				return false;
		} else if (!listContentBean.equals(other.listContentBean))
			return false;
		return true;
	}

	public String getClassNameLayout() {
		return classNameLayout;
	}

	public void setClassNameLayout(String classNameLayout) {
		this.classNameLayout = classNameLayout;
	}

	public List<EwfContentBean> getListContentBean() {
		return listContentBean;
	}

	public void setListContentBean(List<EwfContentBean> listContentBean) {
		this.listContentBean = listContentBean;
	}

}
