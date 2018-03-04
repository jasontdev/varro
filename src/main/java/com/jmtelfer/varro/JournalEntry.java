package com.jmtelfer.varro;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(
        name="findAllEntries",
        query="SELECT entry FROM JournalEntry entry")
public class JournalEntry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Entity fields
    @Column(name="TITLE")
    private String title;

    @Lob
    @Column(name="BODY")
    private String body;

    public JournalEntry() {
    }

    public JournalEntry(String title, String body) {
        this.title = title;
        this.body = body;
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
