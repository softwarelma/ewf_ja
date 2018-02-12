package com.softwarelma.ewf.client.cont;

import java.io.Serializable;
import java.util.List;

public class EwfContentBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;// child comp or elem
	private boolean comp;// or element
	private List<String> listStyleName;

	@Override
	public String toString() {
		return "EwfContentBean [name=" + name + ", comp=" + comp + ", listStyleName=" + listStyleName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (comp ? 1231 : 1237);
		result = prime * result + ((listStyleName == null) ? 0 : listStyleName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		EwfContentBean other = (EwfContentBean) obj;
		if (comp != other.comp)
			return false;
		if (listStyleName == null) {
			if (other.listStyleName != null)
				return false;
		} else if (!listStyleName.equals(other.listStyleName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isComp() {
		return comp;
	}

	public void setComp(boolean comp) {
		this.comp = comp;
	}

	public boolean isElem() {
		return !this.comp;
	}

	public List<String> getListStyleName() {
		return listStyleName;
	}

	public void setListStyleName(List<String> listStyleName) {
		this.listStyleName = listStyleName;
	}

}
