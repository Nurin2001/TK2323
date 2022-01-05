package com.huawei.hms.loginui;

public class UserDetail {

    public String name, addr, email, contact;

    public UserDetail(String name, String addr, String email, String contact) {
        this.name = name;
        this.addr = addr;
        this.email = email;
        this.contact = contact;
    }

    public UserDetail(String name, String addr, String contact) {
        this.name = name;
        this.addr = addr;
        this.contact = contact;
    }

    public UserDetail() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
