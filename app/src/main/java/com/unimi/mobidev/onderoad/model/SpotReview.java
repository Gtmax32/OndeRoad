package com.unimi.mobidev.onderoad.model;

/**
 * Created by Pc-Utente on 30/09/2016.
 */

public class SpotReview {

    private User ownerReview;
    private SpotInfo spotReview;
    private String reviewText;

    public SpotReview(User ownerReview, SpotInfo spotReview, String reviewText) {
        this.ownerReview = ownerReview;
        this.spotReview = spotReview;
        this.reviewText = reviewText;
    }

    public User getOwnerReview() {
        return ownerReview;
    }

    public void setOwnerReview(User ownerReview) {
        this.ownerReview = ownerReview;
    }

    public SpotInfo getSpotReview() {
        return spotReview;
    }

    public void setSpotReview(SpotInfo spotReview) {
        this.spotReview = spotReview;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
