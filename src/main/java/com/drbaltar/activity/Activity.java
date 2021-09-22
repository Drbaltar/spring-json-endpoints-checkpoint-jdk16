package com.drbaltar.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

class Activity {
    private final User user;
    private final Status status;

    public Activity(User user, Status status) {
        this.user = user;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public Status getStatus() {
        return status;
    }

    @JsonView(ActivityViews.DetailedView.class)
    @JsonProperty("userId")
    public int getUserID() {
        return user.getId();
    }

    @JsonView(ActivityViews.CompactView.class)
    @JsonProperty("user")
    public String getUserName() {
        return user.getUsername();
    }

    @JsonView(ActivityViews.DetailedView.class)
    @JsonProperty("email")
    public String getPrimaryEmail() {
        for (Email email : user.getEmails()) {
            if (email.isPrimary())
                return email.getAddress();
        }
        return "";
    }

    @JsonView(ActivityViews.CompactView.class)
    @JsonProperty("date")
    public String getActivityDate() {
        return status.getDate();
    }

    @JsonView(ActivityViews.CompactView.class)
    @JsonProperty("statusText")
    public String getActivityStatus() {
        return status.getText();
    }
}
