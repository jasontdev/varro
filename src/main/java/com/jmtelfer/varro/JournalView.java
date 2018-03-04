package com.jmtelfer.varro;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class JournalView implements Serializable {
    public static final long serialVersionUID = 1L;

    @Inject
    private Journal journal;

    public List<JournalEntry> getAllJournalEntries() {
        return journal.getAllEntries();
    }
}
