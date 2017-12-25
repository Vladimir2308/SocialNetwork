<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
    </head>
    <body>
        <form method="post" action="/regist">
            <input type="text" name="email" placeholder="Введите ваш email">
            <br>
            <input type="text" name="name" placeholder="Введите ваше имя">
            <br>
            <input type="text" name="surname" placeholder="Введите вашу фамилию">
            <br>
            <input type="text" name="patronymic" placeholder="Введите ваше отчество">
            <br>
            <input type="text" name="pass1" placeholder="Введите ваш пароль">
            <br>
            <input type="text" name="pass2" placeholder="Введите ваш пароль повторно">
            <br>
            <input type="submit" value="зарегистрировать">

        </form>
          <c:if test="${not empty name}">
                 <hr>
                 <h2>Привет, ${name}!</h2>
                   <h2>Привет, ${test}!</h2>
                 </c:if>

    </body>
</html>