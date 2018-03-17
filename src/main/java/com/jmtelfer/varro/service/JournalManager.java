/*
 * Copyright (c) 2018. Jason Telfer.
 */

package com.jmtelfer.varro.service;

import com.jmtelfer.varro.entity.JournalEntry;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@Singleton
public class JournalManager implements Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "persistenceUnit")
    EntityManager entries;

    public void addEntry(JournalEntry newEntry) {
        entries.persist(newEntry);

    }

    public void updateEntry(JournalEntry updatedEntry) {
        entries.merge(updatedEntry);
    }

    public void deleteEntry(JournalEntry entryToDelete) {
        entries.remove(entries.contains(entryToDelete) ? entryToDelete : entries.merge(entryToDelete));
    }

    public List<JournalEntry> getAllEntriesByUser(Long id) {
        Query query = entries.createQuery("SELECT entry FROM JournalEntry entry " +
                "WHERE entry.id =:arg1 ORDER by entry.entryID DESC");

        query.setParameter("arg1", id);

        return query.getResultList();
    }

    public List<JournalEntry> getAllEntries() {
        return entries.createNamedQuery("findAllEntries").getResultList();
    }

    public JournalEntry getEntryByID(Long entryID) {
        return entries.find(JournalEntry.class, entryID);
    }
}
