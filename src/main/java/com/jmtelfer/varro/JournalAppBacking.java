package com.jmtelfer.varro;

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

    public List<JournalEntry> getAllJournalEntries() {
        return journalManager.getAllEntries();
    }
}
