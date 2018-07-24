package com.example.kapil.firebasegps;

import java.io.Serializable;

public class UserModel implements Serializable {
    private String name,email, password,number;

    public UserModel(String name, String email, String password, String number) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.number = number;
    }

    public UserModel(String email, String number) {
        this.email = email;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
