package com.jmtelfer.varro;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;

@Singleton
public class UserManager implements Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "persistenceUnit")
    EntityManager users;

    public UserManager() {

    }

    public boolean addNewCredentials(String username, String password) {
        UserCredentials credentials = new UserCredentials(username, password);

        if (!userExists(credentials.getUserName())) {
            System.out.println("Creating new user: " + username + "\n");
            users.persist(credentials);
            return true;
        }
        System.out.println("Failed to create new user: " + username + "\n");
        return false;
    }

    public boolean userExists(String username) {
        Query query = users.createQuery("SELECT COUNT(credentials) FROM UserCredentials credentials " +
                "WHERE credentials.userName =:arg1");

        query.setParameter("arg1", username);

        if ((Long) query.getSingleResult() > 0) {
            return true;
        }
        return false;
    }

    public Long login(String username, String password) {
        if(userExists(username)) {
            Query query = users.createQuery("SELECT credentials FROM UserCredentials credentials " +
                                            "WHERE credentials.userName =:arg1");
            query.setParameter("arg1", username);

            UserCredentials storedCredentials = (UserCredentials) query.getSingleResult();

            if(storedCredentials.matches(username, password)) {
                System.out.println("User: " + username + " successfully authenticated\n");
                return storedCredentials.getId();
            } else {
                System.out.println("Password for user: " + username + " is invalid\n");
                return -1L;
            }
        } else {
            System.out.println("User: " + username + " does not exist\n");
            return -1L;
        }
    }
}
