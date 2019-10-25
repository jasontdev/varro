/*
 * Copyright (c) 2018. Jason Telfer.
 */

package com.jmtelfer.varro.backing;

import com.jmtelfer.varro.service.UserRepository;
import com.jmtelfer.varro.session.CurrentUser;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Named
@RequestScoped
public class Login implements Serializable {
    public static final long serialVersionUID = 1L;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @Inject
    private UserRepository users;

    @Inject
    private CurrentUser currentUser;

    public String login() {
        Long userId = users.login(username, password);

        if (userId > 0L) {
            currentUser.setId(userId);
            return "/journal.html?faces-redirect=true";
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
