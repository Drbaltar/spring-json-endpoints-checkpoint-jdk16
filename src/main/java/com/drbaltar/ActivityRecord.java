package com.drbaltar;

import com.drbaltar.activity.ActivityViews;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
import java.util.List;

public class ActivityRecord {
    public static record Activities(ArrayList<Activity> activities) {
    }

    public record Activity(User user, Status status) {
        @JsonView(ActivityViews.DetailedView.class)
        @JsonProperty("userId")
        public int getUserID() {
            return user.id();
        }

        @JsonView(ActivityViews.CompactView.class)
        @JsonProperty("user")
        public String getUser() {
            return user.username();
        }

        @JsonView(ActivityViews.DetailedView.class)
        @JsonProperty("email")
        public String getPrimaryEmail() {
            for (Email email : user.emails()) {
                if (email.primary())
                    return email.address();
            }
            return "";
        }

        @JsonView(ActivityViews.CompactView.class)
        @JsonProperty("date")
        public String getActivityDate() {
            return status.date();
        }

        @JsonView(ActivityViews.CompactView.class)
        @JsonProperty("statusText")
        public String getActivityStatus() {
            return status.text();
        }

        @JsonView(ActivityViews.MailingList.class)
        @JsonProperty("username")
        public String getUserName() {
            return user.username();
        }

        @JsonView(ActivityViews.MailingList.class)
        @JsonProperty("addresses")
        public List<String> getEmailAddressList() {
            ArrayList<String> emailList = new ArrayList<>();
            for (Email email : user.emails()) {
                emailList.add(email.address());
            }
            return emailList;
        }
    }

    public record User(int id, String username, ArrayList<Email> emails) {
    }

    public record Status(String text, String date) {
    }

    public record Email(int id, String address, boolean primary) {
    }
}
