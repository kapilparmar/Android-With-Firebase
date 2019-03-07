package com.example.kapil.firebasegps;

import java.io.Serializable;

public class UserModel implements Serializable {
    private String name,fullname,phone, password,cpassword,number,code, latlong, time;

    public UserModel(String name, String fullname,String phone, String password, String cpassword) {
        this.name = name;
        this.fullname = fullname;
        this.phone=phone;
        this.password = password;
        this.number=number;
        this.cpassword=cpassword;
    }

    public UserModel(String latlong, String time) {
        this.latlong = latlong;
        this.time = time;
    }

    public UserModel(String name, String number,String nam) {
        this.name = name;
        this.number = number;
    }

    public String getLatlong() {
        return latlong;
    }

    public void setLatlong(String latlong) {
        this.latlong = latlong;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String number) {
        this.code = code;
    }

}
