package com.mkyong.web.service;

import com.mkyong.web.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final AtomicLong userIds = new AtomicLong();
    private HashMap<String,User> listUsers=new HashMap<>();

    public User register( String email, String name, String surname, String patronymic, String pass) {

        User user= new User(userIds.incrementAndGet(), email, name, surname, patronymic, pass);
        listUsers.put(email,user);

        return user;
    }

    public boolean login(String email, String pass) {
        if (listUsers.get(email).getPass().equals(pass)){
            return true;
        }
        return false;
    }
    public boolean mailCheck(String email) {
        if (listUsers.containsKey(email)){
            return true;
        }
        return false;
    }

    public String getName(String email) {
        return listUsers.get(email).getName();
    }
    public HashMap<String,User> getListUsers(){
        return listUsers;

    }
    public ArrayList<User> getIterableListUsers() {
        ArrayList<User> list = new ArrayList<>();
        for (Map.Entry entry:listUsers.entrySet()
             ) {
            list.add((User)entry.getValue());
        }
        return list;
    }
}
