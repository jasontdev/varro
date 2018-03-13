package com.jmtelfer.varro;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NamedQuery(
        name="findAllEntries",
        query="SELECT entry FROM JournalEntry entry")
public class JournalEntry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Entity fields
    @Column(name="USER_ID")
    @Lob
    private byte[] userId;

    @Column(name="DATE")
    private LocalDate createdOn;

    @Column(name="TITLE")
    private String title;

    @Lob
    @Column(name="BODY")
    private String body;

    public JournalEntry() {
    }

    public JournalEntry(byte[] userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
        this.createdOn = LocalDate.now();
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
}
