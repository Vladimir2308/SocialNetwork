<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
  <head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/core/css/style.css" type="text/css"/>
    <meta charset="utf-8">
    <title>T-Net</title>
    <script type="text/JavaScript"
      src="${pageContext.request.contextPath}/resources/core/js/jquery-1.9.1.min.js">
    </script>
  </head>
  <body>
    <div class="header">
      <h1>T-Net</h1>
    </div>
    <div class="container">
      <div class="content">
        <div class=userInfo>
          Информация о друге:
          <br>
          id = ${friend.id}
          <br>
          Имя=  ${friend.name}
          <br>
          Фамилия = ${friend.surname}
          <br>
          Отчество = ${friend.patronymic}
          <br>
          email = ${friend.email}
          <br>
          Телефон = ${friend.phone}
        </div>
      </div>
      <div class="foto">
        <img  src="resources/core/images/defaultAvatar.jpg" />
      </div>
      <div class="extra">
        <p><a href="main">Главная страница</a></p>
        <p><a href="friends">Друзья</a></p>
        <p><a href="search">Поиск Людей</a></p>
        <p><a href="#">Загрузить фото</a></p>
        <p><a href="logout">Выйти</a></p>
      </div>
    </div>
  </body>
</html>
