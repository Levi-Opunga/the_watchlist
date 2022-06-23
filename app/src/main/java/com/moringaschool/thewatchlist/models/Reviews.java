package com.moringaschool.thewatchlist.models;

public class Reviews {
    private String review;
    private Float rating;
    private String user;
    private String userImage;

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public Reviews() {

    }

    public Reviews(String review, Float rating) {
        this.review = review;
        this.rating = rating;
    }

    public Reviews(String review, Float rating, String user) {
        this.review = review;
        this.rating = rating;
        this.user = user;
        this.userImage = "https://cdn-icons-png.flaticon.com/512/149/149071.png";
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
