package com.jmtelfer.varro;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    public List<JournalEntry> getAllEntries() {
        return entries.createNamedQuery("findAllEntries").getResultList();
    }
}
