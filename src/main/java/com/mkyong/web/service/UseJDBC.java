package com.mkyong.web.service;


import java.sql.*;

public class UseJDBC {
    public static void reqJDBC() throws ClassNotFoundException, SQLException {
// Загружаем нужный нам JDBC драйвер
        Class.forName("org.postgresql.Driver");

        try (Connection con =
                     DriverManager.getConnection("jdbc:postgresql://localhost:5432/users", "1", "1")) {
// Создаем statement
            try (Statement stmnt = con.createStatement()) {
// Выполняем запрос к БД
                try (ResultSet rs = stmnt.executeQuery("SELECT * FROM public.users_table")) {
// Перемщаем курсор по результам
                    while (rs.next()) {
// Извлекаем конкретные значения из ResultSet
                        int id = rs.getInt("user_id");
                        String name = rs.getString("name");
                        String surname = rs.getString("surname");
                        String patronimic = rs.getString("patronimic");
                        String pass = rs.getString("pass");
                        String email = rs.getString("email");
                        String phone = rs.getString("phone");

                        System.out.printf("id: %s, name: %s, surname: %s, patronimic: %s, pass  %s, email %s, phone" +
                                        "%s\n",
                                id, name, surname, patronimic, pass,email,phone);
                    }
                }
            }
        }
    }
}