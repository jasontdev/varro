package com.jmtelfer.varro;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class AddEntryBacking implements Serializable {

    @Inject
    private JournalManager journalManager;

    @Inject
    private UserSession userSession;

    private static final long serialVersionUID = 1L;

    private String title;
    private String body;

    public String processInput() {
        if(title.equals(""))
            title = "Untitled entry";

        journalManager.addEntry(new JournalEntry(userSession.getId(), title, body));

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
