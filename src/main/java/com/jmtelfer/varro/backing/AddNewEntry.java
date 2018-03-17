/*
 * Copyright (c) 2018. Jason Telfer.
 */

package com.jmtelfer.varro.backing;

import com.jmtelfer.varro.entity.JournalEntry;
import com.jmtelfer.varro.service.JournalManager;
import com.jmtelfer.varro.session.CurrentUser;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class AddNewEntry implements Serializable {

    @Inject
    private JournalManager journalManager;

    @Inject
    private CurrentUser currentUser;

    private static final long serialVersionUID = 1L;

    private String title;
    private String body;

    public String processInput() {
        if(title.equals(""))
            title = "Untitled entry";

        journalManager.addEntry(new JournalEntry(currentUser.getId(), title, body));

        title = "";
        body = "";

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_INFO, "Info", "New journal entry created"));

        return null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
