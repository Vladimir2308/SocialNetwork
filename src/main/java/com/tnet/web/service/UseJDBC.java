package com.tnet.web.service;


import com.tnet.web.model.User;

import java.sql.*;
import java.util.ArrayList;


public class UseJDBC {
    private final String url = "jdbc:postgresql://localhost:5432/users";
    private final String login = "postgres";
    private final String password = "1";

    public UseJDBC() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insert(String email, String name, String surname, String patronymic,
                       String pass, String phone) throws SQLException, ClassNotFoundException {


        Connection con = DriverManager.getConnection(url, login, password);
        PreparedStatement stmt = con.prepareStatement("INSERT INTO users_table (user_id, surname, firstname, " +
                "patronymic, phone, email, pass) VALUES (DEFAULT,?, ?, ?, ?, ?, ?)");
        stmt.setString(1, surname);
        stmt.setString(2, name);
        stmt.setString(3, patronymic);
        stmt.setString(4, phone);
        stmt.setString(5, email);
        stmt.setString(6, pass);
        stmt.executeUpdate();
        stmt.close();
    }

    public boolean checkEmail(String email) throws SQLException, ClassNotFoundException {
        boolean exist = false;

        Connection con = DriverManager.getConnection(url, login, password);
        PreparedStatement stmt = con.prepareStatement("SELECT true FROM users_table WHERE email = ? ");

        stmt.setString(1, email);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                exist = true;
            }
        }
        stmt.close();
        return exist;
    }

    public int getId(String email) throws SQLException {

        Connection con = DriverManager.getConnection(url, login, password);

        PreparedStatement stmt = con.prepareStatement("SELECT * FROM users_table WHERE email = ?  ");

        stmt.setString(1, email);
        int id = -1;
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                id = rs.getInt("user_id");
            }
        }
        stmt.close();
        return id;
    }

    public String login(String email) throws SQLException {

        Connection con = DriverManager.getConnection(url, login, password);
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM users_table WHERE email = ?   ");
        stmt.setString(1, email);
        String passFromDB = "";
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                passFromDB = rs.getString("pass");
            }
        }
        stmt.close();
        return passFromDB;
    }

    public ArrayList<User> getListUsers() throws SQLException {

        Connection con = DriverManager.getConnection(url, login, password);
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM users_table");
        ArrayList<User> listUsers = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String email = rs.getString("email");
                String name = rs.getString("firstname");
                String surname = rs.getString("surname");
                String patronymic = rs.getString("patronymic");
                String pass = rs.getString("pass");
                String phone = rs.getString("phone");
                listUsers.add(new User(id, email, name, surname, patronymic, pass, phone));
            }
        }
        stmt.close();
        return listUsers;
    }


    public User getUser(int id) throws SQLException {
        Connection con = DriverManager.getConnection(url, login, password);
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM users_table WHERE user_id = ?  ");
        stmt.setInt(1, id);
        User user = null;
        try (ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                String email = rs.getString("email");
                String name = rs.getString("firstname");
                String surname = rs.getString("surname");
                String patronymic = rs.getString("patronymic");
                String pass = rs.getString("pass");
                String phone = rs.getString("phone");
                user = new User(id, email, name, surname, patronymic, pass, phone);
            }
        }
        stmt.close();
        return user;

    }

    public boolean friendReq(int idUser1, int idUser2) throws SQLException {
        Connection con = DriverManager.getConnection(url, login, password);
        PreparedStatement stmt = con.prepareStatement("INSERT INTO friends_req (id_1,id_2 ) VALUES ( ?, ?)");
        stmt.setInt(1, idUser1);
        stmt.setInt(2, idUser2);

        stmt.executeUpdate();
        stmt.close();
        return true;
    }

    public boolean friendAdd(int idUser1, int idUser2) throws SQLException {
        Connection con = DriverManager.getConnection(url, login, password);
        PreparedStatement stmt = con.prepareStatement("INSERT INTO friends (id_1,id_2 ) VALUES ( ?, ?), (?,?)");
        stmt.setInt(1, idUser1);
        stmt.setInt(2, idUser2);
        stmt.setInt(3, idUser2);
        stmt.setInt(4, idUser1);
        stmt.executeUpdate();
        stmt.close();
        return true;
    }

    public boolean friendReqFault(int idUser1, int idUser2) throws SQLException {
        Connection con = DriverManager.getConnection(url, login, password);
        PreparedStatement stmt = con.prepareStatement("DELETE FROM  friends_req WHERE id_1 = ? AND  id_2 = ? ");
        stmt.setInt(1, idUser1);
        stmt.setInt(2, idUser2);
        stmt.executeUpdate();
        stmt.close();
        return true;
    }

    public void setSessionId(int id, String sessionId) throws SQLException {
        Connection con = DriverManager.getConnection(url, login, password);
        PreparedStatement stmt = con.prepareStatement("UPDATE users_table SET session=? WHERE user_id=?");
        stmt.setString(1, sessionId);
        stmt.setInt(2, id);
        stmt.executeUpdate();
        stmt.close();
    }

    public ArrayList<User> getListFriends(int currentId) throws SQLException {
        Connection con = DriverManager.getConnection(url, login, password);
        PreparedStatement stmt = con.prepareStatement("SELECT users_table.user_id, users_table.firstname, users_table.surname, users_table.email, users_table.patronymic, users_table.phone, users_table.pass FROM friends JOIN users_table ON friends.id_2=users_table.user_id WHERE friends.id_1=?");
        stmt.setInt(1, currentId);
        ArrayList<User> listFriends = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String email = rs.getString("email");
                String name = rs.getString("firstname");
                String surname = rs.getString("surname");
                String patronymic = rs.getString("patronymic");
                String pass = rs.getString("pass");
                String phone = rs.getString("phone");
                listFriends.add(new User(id, email, name, surname, patronymic, pass, phone));
            }
        }
        stmt.close();
        return listFriends;
    }

    public boolean delFriend(int id1, int id2) throws SQLException {
        Connection con = DriverManager.getConnection(url, login, password);
        PreparedStatement stmt = con.prepareStatement("DELETE FROM friends WHERE (id_1=? AND id_2=?) " +
                "OR (id_1=?  AND id_2=?)");
        stmt.setInt(1, id1);
        stmt.setInt(2, id2);
        stmt.setInt(3, id2);
        stmt.setInt(4, id1);
        stmt.executeUpdate();
        stmt.close();
        return true;
    }

    public User getUser(String email) throws SQLException {
        Connection con = DriverManager.getConnection(url, login, password);
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM users_table WHERE email= ?  ");
        stmt.setString(1, email);
        User user = null;
        try (ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                int id = rs.getInt("user_id");
                email = rs.getString("email");
                String name = rs.getString("firstname");
                String surname = rs.getString("surname");
                String patronymic = rs.getString("patronymic");
                String pass = rs.getString("pass");
                String phone = rs.getString("phone");
                user = new User(id, email, name, surname, patronymic, pass, phone);
            }
        }
        stmt.close();
        return user;
    }

    public ArrayList<User> getListReqToFriends(int id) throws SQLException {
        Connection con = DriverManager.getConnection(url, login, password);
        PreparedStatement stmt = con.prepareStatement("SELECT users_table.user_id, users_table.firstname, " +
                "users_table.surname, users_table.email, users_table.patronymic, users_table.phone, users_table.pass" +
                " FROM friends_req JOIN users_table ON friends_req.id_2=users_table.user_id WHERE friends_req.id_1=?");
        stmt.setInt(1, id);
        ArrayList<User> listReqToFriends = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                id = rs.getInt("user_id");
                String email = rs.getString("email");
                String name = rs.getString("firstname");
                String surname = rs.getString("surname");
                String patronymic = rs.getString("patronymic");
                String pass = rs.getString("pass");
                String phone = rs.getString("phone");
                listReqToFriends.add(new User(id, email, name, surname, patronymic, pass, phone));
            }
        }
        stmt.close();
        return listReqToFriends;
    }

    public String getSessionId(int idUser) throws SQLException {
        Connection con = DriverManager.getConnection(url, login, password);
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM users_table WHERE user_id = ?  ");
        stmt.setInt(1, idUser);
        String sessionId = "5";
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                sessionId = rs.getString("session");
            }
        }
        stmt.close();
        return sessionId;
    }
}


