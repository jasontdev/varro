/*
 * Copyright (c) 2018. Jason Telfer.
 */

package com.jmtelfer.varro.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="JOURNAL")
@NamedQuery(
        name="findAllEntries",
        query="SELECT entry FROM JournalEntry entry")

public class JournalEntry implements Serializable {
    @Id
    @Column(name = "ENTRY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entryID;

    @Column(name = "USER_ID")
    private Long id;

    @Column(name="DATE")
    private LocalDate date;

    @Column(name="TITLE")
    private String title;

    @Lob
    @Column(name="BODY")
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
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
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

    public void setEntryID(Long entry) {
        this.entryID = entryID;
    }
}
