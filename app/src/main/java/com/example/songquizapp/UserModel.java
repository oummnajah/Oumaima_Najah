package com.example.songquizapp;

public class UserModel {
    private String name,number,email;

    public UserModel(String name,  String number,String email ) {
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public UserModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


