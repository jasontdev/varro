/*
 * Copyright (c) 2018. Jason Telfer.
 */
package com.jmtelfer.varro.backing;

import com.jmtelfer.varro.service.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class Registration implements Serializable {

    private String newPassword;
    private String newUsername;

    @Inject
    private UserRepository users;

    public String registerUser() {
        //Reject blank username or password
        if (newUsername.length() == 0 || newPassword.length() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Please choose a username and password"));
            return (null);
        }

        //Reject passwords shorter than 6 characters
        if (newPassword.length() < 6) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Password should be at least 6 characters"));
            return (null);
        }

        //Validate email
        if (!usernameIsValidEmail()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Please enter a valid email address"));
            return (null);
        }

        //Check if user already exists
        if (users.userExists(newUsername)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Email address already in use"));

            return (null);
        }
        
        // Attempt to register the new user
        if (users.addNewCredentials(newUsername, newPassword)) {
            // Registration was successful
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Info", "Registration successful")); 
            return "/login.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to register user"));
        }

        return (null);
    }

    private boolean usernameIsValidEmail() {
        //No @ or .  
        return !(!newUsername.contains(".") || !newUsername.contains("@"));
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
