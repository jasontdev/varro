package com.jmtelfer.varro;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
public class Journal implements Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "persistenceUnit")
    EntityManager entries;

    public void addEntry(JournalEntry newEntry) {
        entries.persist(newEntry);

    }

    @Override
    public String toString() {
        return "Injected journal bean";
    }

    public List<JournalEntry> getAllEntries() {
        return entries.createNamedQuery("findAllEntries").getResultList();
    }
}
