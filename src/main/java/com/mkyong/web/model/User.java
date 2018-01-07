package com.mkyong.web.model;

import java.util.ArrayList;

public class User {
    private long id;
    private String email;
    private String name;
    private String surname;
    private String patronymic;
    private String pass;
    private int phone;
    private ArrayList<User>listFriends;

    public User() {
        listFriends = new ArrayList<>();
    }
    public void addFriend(User user){

    }
    public ArrayList getListFriends() {

        return listFriends;
    }

    public long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public User(long id, String email, String name, String surname, String patronymic, String pass) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.pass = pass;
    }
}