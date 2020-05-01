package com.discussiongroup.voting;


public class Aspirant {
    private int aspirantId;
    private String aspirantName, aspirantPhoto;

    public Aspirant(int aspirantId, String aspirantName, String aspirantPhoto) {
        this.aspirantId = aspirantId;
        this.aspirantName = aspirantName;
        this.aspirantPhoto= aspirantPhoto;
    }

    public int getAspirantId() {
        return aspirantId;
    }

    public void setAspirantId(int aspirantId) {
        this.aspirantId = aspirantId;
    }

    public String getAspirantName() {
        return aspirantName;
    }

    public void setAspirantName(String aspirantName) {
        this.aspirantName = aspirantName;
    }

    public String getAspirantPhoto() {
        return aspirantPhoto;
    }

    public void setAspirantPhoto(String aspirantPhoto) {
        this.aspirantPhoto = aspirantPhoto;
    }
}