package com.example.disasterreliefsystem;

import android.telecom.PhoneAccountSuggestion;

public class AdminUser {
    private String name, surname, email, tcno, password;
    public AdminUser(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTcno() {
        return tcno;
    }

    public void setTcno(String tcno) {
        this.tcno = tcno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdminUser(String name, String surname, String email, String tcno, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.tcno = tcno;
        this.password = password;
    }
}
