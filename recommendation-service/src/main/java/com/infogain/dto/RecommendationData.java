package com.infogain.dto;

public class RecommendationData {
    
    private Integer id;
    private Integer likes;

    public RecommendationData() {
    }

    public RecommendationData(Integer id, Integer likes) {
        this.id = id;
        this.likes = likes;
    }

    public Integer getId() {
        return id;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
    
}
