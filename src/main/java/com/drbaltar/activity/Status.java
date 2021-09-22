package com.drbaltar.activity;

class Status {
    private final String text;
    private final String date;

    public Status(String text, String date) {
        this.text = text;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }
}
