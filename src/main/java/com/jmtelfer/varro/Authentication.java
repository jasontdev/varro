package com.jmtelfer.varro;

import org.primefaces.context.RequestContext;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped

public class Authentication implements Serializable {
    private String username;
    private String password;
    private String newPassword;
    private String newUsername;

    @EJB
    private UserManager users;

    public String registerUser() {
        if(users.addNewCredentials(newUsername, newPassword)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Info", "Registration successful"));
            return "login.xhtml";
         } else {
            FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Username already taken"));
        }
        return (null);
    }

    public String login() {
        if(users.login(username, password))
            return "journal.xhtml";

        FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Login attempt failed"));
                RequestContext.getCurrentInstance().execute("");
        return null;
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }
}
