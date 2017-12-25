package com.mkyong.web.service;

import com.mkyong.web.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final AtomicLong userIds = new AtomicLong();
    private HashMap<Long,User> listUsers=new HashMap<>();

    public User register( String email, String name, String surname, String patronymic, String pass) {

        User user= new User(userIds.incrementAndGet(), email, name, surname, patronymic, pass);
        listUsers.put(user.getId(),user);

        return user;
    }
}
