/*
 * Copyright (c) 2018. Jason Telfer.
 */

package com.jmtelfer.varro.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class JournalEntry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entryID;

    private Long id;

    private LocalDate date;

    private String title;

    @Lob
    private String body;

    public JournalEntry() {
    }

    public JournalEntry(Long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = LocalDate.now();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String dateToString() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getEntryID() {
        return entryID;
    }

    public void setEntryID(Long entryID) {
        this.entryID = entryID;
    }
}
