package com.moringaschool.thewatchlist.models;

public class Reviews {
    private String review;
    private Float rating;
    private String user;

    public Reviews(String review, Float rating) {
        this.review = review;
        this.rating = rating;
    }

    public Reviews(String review, Float rating, String user) {
        this.review = review;
        this.rating = rating;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
