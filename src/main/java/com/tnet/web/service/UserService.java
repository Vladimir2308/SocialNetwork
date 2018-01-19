package com.tnet.web.service;

import com.tnet.web.model.User;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final AtomicLong userIds = new AtomicLong();

    UseJDBC db = new UseJDBC();

    public User register(String email, String name, String surname, String patronymic, String pass, String phone) throws SQLException, ClassNotFoundException {
        db.insert(email, name, surname, patronymic, pass, phone);
        int id = db.getId(email);
        User user = new User(id, email, name, surname, patronymic, pass, phone);
        return user;
    }

    public boolean login(String email, String pass) throws SQLException {
        if (db.login(email).equals(pass)) {
            return true;
        }
        return false;
    }

    public int getId(String email) throws SQLException {
        return db.getId(email);
    }

    public boolean mailCheck(String email) throws SQLException, ClassNotFoundException {
        if (db.checkEmail(email)) {
            return true;
        }
        return false;
    }


    public ArrayList<User> getIterableListUsers() throws SQLException {
        return db.getListUsers();
    }

    public User getUser(String email) throws SQLException {
        return db.getUser(email);
    }

    public User getUser(int id) throws SQLException {
        return db.getUser(id);
    }

    public boolean requestFriends(int idUser1, int idUser2) throws SQLException {
        return db.friendReq(idUser1, idUser2);
    }

    public boolean requestFriendsFault(int idUser1, int idUser2) throws SQLException {
        return db.friendReqFault(idUser1, idUser2);
    }

    public boolean addFriends(int idUser1, int idUser2) throws SQLException {
        if (db.friendAdd(idUser1, idUser2)) {
            return db.friendReqFault(idUser1, idUser2);
        }
        return false;
    }

    public void setUserSession(int currentId, String sessionId) throws SQLException {
        db.setSessionId(currentId, sessionId);
    }


    public boolean delFriends(int id1, int id2) throws SQLException {
        return db.delFriend(id1, id2);

    }

    public ArrayList<User> getListFriends(int currentId) throws SQLException {
        return db.getListFriends(currentId);
    }

    public ArrayList<User> getListReqToFriends(int id) throws SQLException {
        return db.getListReqToFriends(id);
    }

    public String getUserSession(int idUser) throws SQLException {
        return db.getSessionId(idUser);
    }
}
