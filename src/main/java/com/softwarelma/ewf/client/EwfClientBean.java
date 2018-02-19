package com.softwarelma.ewf.client;

import java.time.LocalDate;

public class EwfClientBean {

    private String pkg;
    private String artifact;
    private String version;
    private LocalDate date;

    public EwfClientBean() {
    }

    public EwfClientBean(String pkg, String artifact, String version, LocalDate date) {
        super();
        this.pkg = pkg;
        this.artifact = artifact;
        this.version = version;
        this.date = date;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getArtifact() {
        return artifact;
    }

    public void setArtifact(String artifact) {
        this.artifact = artifact;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
