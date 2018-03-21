/*
 * Copyright (c) 2018. Jason Telfer.
 */

package com.jmtelfer.varro.backing;

import com.jmtelfer.varro.session.CurrentUser;
import com.jmtelfer.varro.service.UserManager;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class Login implements Serializable {
    private String username;
    private String password;

    @Inject
    private UserManager users;

    @Inject
    private CurrentUser currentUser;

    public String login() {
        Long userId = users.login(username, password);

        if (userId.longValue() > 0L) {
            currentUser.setId(userId);
            return "/journal.xhtml?faces-redirect=true";
        }

        FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Login attempt failed"));
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
}
