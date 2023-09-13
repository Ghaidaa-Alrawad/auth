package com.jBCrypt.auth.model;

import javax.persistence.*;

@Entity
public class PostModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SiteUserModel userId;

    private String textContent;

    public PostModel(){}

    public PostModel(SiteUserModel userId, String textContent) {
        this.userId = userId;
        this.textContent = textContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SiteUserModel getUserId() {
        return userId;
    }

    public void setUserId(SiteUserModel userId) {
        this.userId = userId;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
