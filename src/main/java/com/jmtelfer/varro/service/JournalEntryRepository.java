/*
 * Copyright (c) 2018. Jason Telfer.
 */

package com.jmtelfer.varro.service;

import com.jmtelfer.varro.entity.JournalEntry;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Stateless
public class JournalEntryRepository implements Serializable {

    public static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "VarroProductionPU")
    EntityManager entries;

    public void addEntry(JournalEntry newJournalEntry) {
        entries.persist(newJournalEntry);
    }

    public void updateEntry(JournalEntry updatedJournalEntry) {
        entries.merge(updatedJournalEntry);
    }

    public void deleteEntry(JournalEntry journalEntryToDelete) {
        entries.remove(entries.contains(journalEntryToDelete) ? journalEntryToDelete : entries.merge(journalEntryToDelete));
    }

    public List<JournalEntry> getAllEntriesByUser(Long id) {
        TypedQuery<JournalEntry> query =
                entries.createQuery("SELECT journalEntry FROM JournalEntry journalEntry " +
                        "WHERE journalEntry.id =:arg1 ORDER by journalEntry.entryID DESC", JournalEntry.class);

        query.setParameter("arg1", id);
        return query.getResultList();
    }

    public JournalEntry getEntryByID(Long entryID) {
        return entries.find(JournalEntry.class, entryID);
    }
}
