package com.mkyong.web.service;

import com.mkyong.web.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final AtomicLong userIds = new AtomicLong();
    private HashMap<String, User> listUsers = new HashMap<>();
    private HashMap<Integer, String> listidUsers = new HashMap<>();

    public User register(String email, String name, String surname, String patronymic, String pass) {
        int id = (int) userIds.incrementAndGet();
        User user = new User(id, email, name, surname, patronymic, pass);
        listUsers.put(email, user);
        listidUsers.put(id, email);
        return user;
    }


    public boolean login(String email, String pass) {
        if (listUsers.get(email).getPass().equals(pass)) {
            return true;
        }
        return false;
    }

    public int getId(String email) {
        if (listUsers.containsKey(email)) {
            return listUsers.get(email).getId();
        }
        return -1;
    }

    public boolean mailCheck(String email) {
        if (listUsers.containsKey(email)) {
            return true;
        }
        return false;
    }

    public String getName(String email) {
        return listUsers.get(email).getName();
    }

    public HashMap<String, User> getListUsers() {
        return listUsers;

    }

    public ArrayList<User> getIterableListUsers() {
        ArrayList<User> list = new ArrayList<>();
        for (Map.Entry entry : listUsers.entrySet()
                ) {
            list.add((User) entry.getValue());
        }
        return list;
    }


    public User getUser(int id) {
        if (listidUsers.containsKey(id)){
            if (listUsers.containsKey(listidUsers.get(id))){
               return listUsers.get(listidUsers.get(id));
            }
        }
        return null;
    }

    public boolean requestFriends(int idUser1, int idUser2) {
        System.out.println("requestFriends(int idUser1, int idUser2)");
       return getUser(idUser1).requestFriends(getUser(idUser2));
    }

    public boolean addFriends(int idUser1, int idUser2) {
        if(getUser(idUser1).addFriend(getUser(idUser2))){
            return getUser(idUser2).requestFriendsRefusal(idUser1);
        }
        return false;
    }

    public void setUserSession(User user, String id) {
        user.setSessionId(id);
    }
    public boolean isUserSession(User user, String id) {
       return user.getSessionId().equals(id);
    }
}
