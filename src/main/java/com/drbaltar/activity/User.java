package com.drbaltar.activity;

import java.util.ArrayList;

class User {

    private final int id;
    private final String username;
    private final ArrayList<Email> emails;

    public User(int id, String username, ArrayList<Email> emails) {
        this.id = id;
        this.username = username;
        this.emails = emails;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Email> getEmails() {
        return emails;
    }
}
