package com.drbaltar.activity;

class Email {
    private final int id;
    private final String address;
    private final boolean primary;

    public Email(int id, String address, boolean primary) {
        this.id = id;
        this.address = address;
        this.primary = primary;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public boolean isPrimary() {
        return primary;
    }
}
