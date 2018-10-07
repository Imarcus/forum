package com.smack.forum.model;

import javax.persistence.*;

@Entity
public class ForumUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
    private String password;
    private int noThreads;
    private int noAnswers;

    public ForumUser() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getNoThreads() {
        return noThreads;
    }

    public int getNoAnswers() {
        return noAnswers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNoThreads(int noThreads) {
        this.noThreads = noThreads;
    }

    public void setNoAnswers(int noAnswers) {
        this.noAnswers = noAnswers;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
