<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
    </head>
    <body>
        <form method="post" action="/registration">
            <input type="text" name="email" size=30 placeholder="Введите ваш email">
            <br>
            <input type="text" name="name" size=30 placeholder="Введите ваше имя">
            <br>
            <input type="text" name="surname" size=30 placeholder="Введите вашу фамилию">
            <br>
            <input type="text" name="patronymic" size=30 placeholder="Введите ваше отчество">
            <br>
            <input type="password" name="pass1" size=30 placeholder="Введите ваш пароль">
            <br>
            <input type="password" name="pass2" size=30 placeholder="Введите ваш пароль повторно">
            <br>
            <input type="submit" value="зарегистрировать">

        </form>

          <c:if test="${not empty msg1}">
             <hr>
         <h2 style="color: red"> ${msg1}</h2>
            </c:if>
             <c:if test="${not empty msg2}">
                         <hr>
                     <h2 style="color: red"> ${msg2}</h2>
                        </c:if>
 <c:if test="${not empty msg3}">
                         <hr>
                     <h2 style="color: red"> ${msg3}</h2>
                        </c:if>
    </body>
</html>