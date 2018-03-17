package com.jmtelfer.varro;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped

public class JournalAppBacking implements Serializable {
    public static final long serialVersionUID = 1L;

    @Inject
    private JournalManager journalManager;

    @Inject
    private UserSession userSession;

    @PostConstruct
    public void init() {
        if(userSession.getId().equals(-1L)) {
            logout();
        }
    }

    public List<JournalEntry> getAllJournalEntries() {
        return journalManager.getAllEntries();
    }
    public List<JournalEntry> getJournalEntriesByUser() {
        return journalManager.getAllEntriesByUser(userSession.getId());
    }
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }
}
