package com.smack.forum.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private MainPost mainPost;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Post> answers = new ArrayList<>();

    public Thread() {
    }

    public Thread(MainPost mainPost) {
        this.mainPost = mainPost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MainPost getMainPost() {
        return mainPost;
    }

    public void setMainPost(MainPost mainPost) {
        this.mainPost = mainPost;
    }

    public List<Post> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Post> answers) {
        this.answers = answers;
    }

    public void addAnswer(Post answer) {
        this.answers.add(answer);
    }
}
