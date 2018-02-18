package com.softwarelma.ewf.client;

public class EwfClientBean {

    private String pkg;
    private String artifact;
    private String version;

    public EwfClientBean() {
    }

    public EwfClientBean(String pkg, String artifact, String version) {
        super();
        this.pkg = pkg;
        this.artifact = artifact;
        this.version = version;
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

}
