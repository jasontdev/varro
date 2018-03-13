package com.jmtelfer.varro;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class JournalInput implements Serializable {

    @Inject
    private Journal journal;

    private static final long serialVersionUID = 1L;

    private String title;
    private String body;

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

    public String processInput() {
        journal.addEntry(new JournalEntry("abracadabra".getBytes(), title, body));

        title = "";
        body = "";

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_INFO, "Info", "New journal entry created"));

        return null;
    }
}
