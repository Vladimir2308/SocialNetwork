<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
    <meta charset="utf-8">
  </head>
  <body>
    <form method="post" action="/login">
      <input type="text" name="email" placeholder="Введите email">
      <br>
      <input type="text" name="pass" placeholder="Введите ваш пароль">
      <br>
      <input type="submit" value="войти">
    </form>
    <a href="/registration">Зарегистрироваться</a>
    <c:if test="${not empty msg}">
      <hr>
      <h2 style="color: red"> ${msg}</h2>
    </c:if>
  </body>
</html>