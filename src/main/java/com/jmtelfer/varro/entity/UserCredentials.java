/*
 * Copyright (c) 2018. Jason Telfer.
 */
package com.jmtelfer.varro.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

@Entity
public class UserCredentials implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Entity fields
    private String userName;

    @Lob
    private byte[] hashedPassword;

    @Lob
    private byte[] salt = new byte[32];

    public UserCredentials() {
    }

    public UserCredentials(String userName, String unhashedPassword) {
        this.userName = userName;

        final Random r = new SecureRandom();
        r.nextBytes(salt);

        //Salted password creation
        this.hashedPassword = hashPassword(unhashedPassword, salt);
    }

    private static byte[] hashPassword(String unhashedPassword, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            return md.digest(unhashedPassword.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException encountered by "
                    + "UserCredentials.UserCredentials()");

            return null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(byte[] hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public boolean matches(String userName, String password) {
        if (userName.equals(this.userName)) {
            return Arrays.equals(hashPassword(password, salt), getHashedPassword());
        }
        return false;
    }
}
