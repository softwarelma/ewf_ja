package com.softwarelma.ewf.client.page;

public class EwfPageBean {

    private String name;
    private String description;
    private String compName;

    @Override
    public String toString() {
        return "EwfPageBean [name=" + name + ", description=" + description + ", compName=" + compName + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

}
