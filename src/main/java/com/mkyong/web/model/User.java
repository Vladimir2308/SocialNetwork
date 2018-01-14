package com.mkyong.web.model;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class User {
    private int id;
    private String email;
    private String SessionId;
    private String name;
    private String surname;
    private String patronymic;
    private String pass;
    private String phone;

    public String getSessionId() {
        return SessionId;
    }

    public void setSessionId(String sessionId) {
        SessionId = sessionId;
    }


    private ArrayList<User>listFriends;
    private  ArrayList<User> listRequestAddToFriends;

    public User() {
        listFriends = new ArrayList<>();
        listRequestAddToFriends = new ArrayList<>();
    }

    public boolean addFriend(User user ) {
        ArrayDeque<String> messages=new ArrayDeque<>();
        return listFriends.add(user);

    }
    public boolean delFriend(User user ){
       return listFriends.remove(user);

    }

    public boolean requestFriends(User user ){
        System.out.println(" listRequestAddToFriends "+  user.getId());
       return listRequestAddToFriends.add(user);


    }
    public boolean requestFriendsRefusal(User user ){
       return listRequestAddToFriends.remove(user);

    }
    public ArrayList<User> getListFriends() {
        return listFriends;
    }
    public  ArrayList<User> getListRequestAddToFriends() {
        return listRequestAddToFriends;
    }

    public int getId() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User(int id, String email, String name, String surname, String patronymic, String pass, String phone) {
        this();
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.pass = pass;
        this.phone = phone;
    }


}