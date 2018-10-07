package com.smack.forum.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
    private ArrayList<Long> threadIds = new ArrayList<>();

    public Board() {}

    public Board(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Long> getThreadIds() {
        return threadIds;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setThreadIds(ArrayList<Long> threadIds) {
        this.threadIds = threadIds;
    }

    public void addThreadId(Long threadId) {
        this.threadIds.add(threadId);
    }
}
