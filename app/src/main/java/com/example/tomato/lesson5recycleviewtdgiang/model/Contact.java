package com.example.tomato.lesson5recycleviewtdgiang.model;

public class Contact {
    private String mName;
    private String mNumberPhone;

    public Contact() {
    }

    public Contact(String mName, String mNumberPhone) {
        this.mName = mName;
        this.mNumberPhone = mNumberPhone;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmNumberPhone() {
        return mNumberPhone;
    }

    public void setmNumberPhone(String mNumberPhone) {
        this.mNumberPhone = mNumberPhone;
    }
}
