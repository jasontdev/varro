package com.jmtelfer.varro;

import org.primefaces.context.RequestContext;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
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
        //Reject blank username or password
        if(newUsername.length() == 0 || newPassword.length() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Please choose a username and password"));
            return (null);
        }

        //Reject passwords shorter than 6 characters
        if(newPassword.length() < 6) {
            FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Password should be at least 6 characters"));
            return (null);
        }

        //Validate email
        if(!usernameIsValidEmail()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Please enter a valid email address"));
            return (null);
        }

        //Check if user already exists
        if(users.userExists(newUsername)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Email address already in use"));

            return (null);
        }

        if(users.addNewCredentials(newUsername, newPassword)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Info", "Registration successful"));
            return "login.xhtml";
         } else {
            FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to register user"));
        }
        return (null);
    }

    public String login() {
        if(users.login(username, password))
            return "journal.xhtml";

        FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Login attempt failed"));
        return null;
    }

    private boolean usernameIsValidEmail() {
        //No @ or .
        if (!newUsername.contains(".") || !newUsername.contains("@")) return false;
        return true;
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
