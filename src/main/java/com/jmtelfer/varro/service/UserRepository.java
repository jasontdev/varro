/*
 * Copyright (c) 2018. Jason Telfer.
 */

package com.jmtelfer.varro.service;

import com.jmtelfer.varro.entity.JournalEntry;
import com.jmtelfer.varro.entity.UserCredentials;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;

@Stateless
public class UserRepository implements Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "VarroProductionPU")
    EntityManager users;

    @Inject
    JournalEntryRepository journalEntryRepository;

    public UserRepository() {
    }

    public boolean addNewCredentials(String username, String password) {
        UserCredentials credentials = new UserCredentials(username, password);

        if (!userExists(credentials.getUserName())) {
            System.out.println("Creating new user: " + username + "\n");
            users.persist(credentials);

            String welcomeEntry = "Hello and welcome to Varro.";
            JournalEntry newUserEntry = new JournalEntry(credentials.getId(),
                    "Welcome to Varro",
                    welcomeEntry);

            journalEntryRepository.addEntry(newUserEntry);
            return true;
        }

        System.out.println("Failed to create new user: " + username + "\n");
        return false;
    }

    public boolean userExists(String username) {
        Query query = users.createQuery("SELECT COUNT(credentials) FROM UserCredentials credentials " +
                "WHERE credentials.userName =:arg1");

        query.setParameter("arg1", username);

        return (Long) query.getSingleResult() > 0;
    }

    public Long login(String username, String password) {
        if (userExists(username)) {
            Query query = users.createQuery("SELECT credentials FROM UserCredentials credentials " +
                    "WHERE credentials.userName =:arg1");
            query.setParameter("arg1", username);

            UserCredentials storedCredentials = (UserCredentials) query.getSingleResult();

            if (storedCredentials.matches(username, password)) {
                System.out.println("CurrentUser: " + username + " successfully authenticated\n");
                return storedCredentials.getId();
            } else {
                System.out.println("Password for user: " + username + " is invalid\n");
                return -1L;
            }
        } else {
            System.out.println("CurrentUser: " + username + " does not exist\n");
            return -1L;
        }
    }
}
