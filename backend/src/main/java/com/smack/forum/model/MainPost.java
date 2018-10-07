package com.smack.forum.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MainPost extends Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String title;

    public MainPost() {
    }

    public MainPost(String content, long userId, Date date, String title) {
        super(content, userId, date);
        this.title = title;
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
}
