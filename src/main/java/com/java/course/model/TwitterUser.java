package com.java.course.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TwitterUser {
    @JsonProperty("screen_name")
    private String username;
    private String description;
    @JsonProperty("followers_count")
    private int followersCount;

    public TwitterUser(String username, String description, int followersCount) {
        this.username = username;
        this.description = description;
        this.followersCount = followersCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }
}
