package com.softwarelma.ewf.backend.entity;

import com.softwarelma.epe.p1.app.EpeAppException;

public class EwfEntityUser extends EwfEntityAbstract {

    private static final long serialVersionUID = 1L;
    private Boolean admin;
    private String surname;
    private String username;
    private String password;
    private String email;

    @Override
    public String toString() {
        try {
            return this.retrieveDescriptionShort();
        } catch (EpeAppException e) {
            return null;
        }
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String retrieveDescriptionShort() throws EpeAppException {
        return this.getName() + " " + this.surname;
    }

    @Override
    public String retrieveDescriptionLong() throws EpeAppException {
        return this.retrieveDescriptionShort() + " (" + this.username + ")";
    }

}
