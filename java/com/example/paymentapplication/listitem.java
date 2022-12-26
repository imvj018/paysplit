package com.example.paymentapplication;

public class listitem {
    public final String id, username, amount, date, time, ex_time, note;

    public listitem(String id, String username, String amount, String date, String time, String ex_time, String note) {
        this.id = id;
        this.username = username;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.ex_time = ex_time;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getEx_time() {
        return ex_time;
    }

    public String getNote() {
        return note;
    }
}
