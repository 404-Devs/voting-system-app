package com.discussiongroup.voting;


public class Aspirants {
    int profileImg;
    String aspirantName;
    String aspirantAspirants;

    public Aspirants(int profileImg, String aspirantName, String aspirantAspirants) {
        this.profileImg = profileImg;
        this.aspirantName = aspirantName;
        this.aspirantAspirants = aspirantAspirants;
    }


    public int getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(int profileImg) {
        this.profileImg = profileImg;
    }

    public String getAspirantName() {
        return aspirantName;
    }

    public void setAspirantName(String aspirantName) {
        this.aspirantName = aspirantName;
    }

    public String getAspirantAspirants() {
        return aspirantAspirants;
    }

    public void setAspirantAspirants(String aspirantAspirants) {
        this.aspirantAspirants = aspirantAspirants;
    }
}