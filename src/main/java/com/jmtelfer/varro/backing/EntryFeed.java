/*
 * Copyright (c) 2018. Jason Telfer.
 */

package com.jmtelfer.varro.backing;

import com.jmtelfer.varro.entity.JournalEntry;
import com.jmtelfer.varro.service.JournalManager;
import com.jmtelfer.varro.session.CurrentUser;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped

public class EntryFeed implements Serializable {
    public static final long serialVersionUID = 1L;

    @Inject
    private JournalManager journalManager;

    @Inject
    private CurrentUser currentUser;

    public String loggedInCheck() {
        if(currentUser.getId().equals(-1L)) {
            return logout();
        }

        return null;
    }

    public List<JournalEntry> getAllJournalEntries() {
        return journalManager.getAllEntries();
    }
    public List<JournalEntry> getJournalEntriesByUser() {
        return journalManager.getAllEntriesByUser(currentUser.getId());
    }
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }
}
