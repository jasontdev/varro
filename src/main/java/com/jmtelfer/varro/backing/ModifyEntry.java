/*
 * Copyright (c) 2018. Jason Telfer.
 */

package com.jmtelfer.varro.backing;

import com.jmtelfer.varro.entity.JournalEntry;
import com.jmtelfer.varro.service.JournalEntryRepository;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class ModifyEntry implements Serializable {
    @Inject
    JournalEntryRepository journalEntryRepository;

    private JournalEntry journalEntry;
    private String title;
    private String body;

    public String loadEntry(JournalEntry journalEntry) {
        this.journalEntry = journalEntry;
        this.title = journalEntry.getTitle();
        this.body = journalEntry.getBody();

        return null;
    }

    public String update() {
        journalEntry.setTitle(title);
        journalEntry.setBody(body);

        journalEntryRepository.updateEntry(journalEntry);

        return null;
    }


    public String deleteEntry(JournalEntry journalEntry) {
        journalEntryRepository.deleteEntry(journalEntry);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Journal entry removed"));

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

    public JournalEntry getJournalEntry() {
        return journalEntry;
    }

    public void setJournalEntry(JournalEntry journalEntry) {
        this.journalEntry = journalEntry;
    }
}
