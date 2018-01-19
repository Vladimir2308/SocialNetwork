<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
  <head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/core/css/style.css" type="text/css"/>
    <meta charset="utf-8">
    <title>T-Net</title>
    <style>
      .nav{
        width: 80px;
      }
    </style>
    <script type="text/JavaScript"
      src="${pageContext.request.contextPath}/resources/core/js/jquery-1.9.1.min.js">
    </script>
    <script type="text/javascript">
      function doDelAjax(inputText) {
	    $.ajax({
		  url : 'friendDel',
		  type: 'GET',
		  dataType: 'json',
		  data : ({
		    text: inputText
		  }),
		  success: function (data) {
          }
	    });
      }
    </script>
  </head>
  <body>
    <div class="header">
      <h1>T-Net</h1>
    </div>
    <div class="container">
      <div class="content">
        <center>
          <br> <br> <br> <b>Список друзей  </b><br> <br>
          <table class="table1" border="1">
            <tr>
              <td class="heading">id</td>
              <td class="heading">Имя</td>
              <td class="heading">Фамилия</td>
              <td class="heading">Email</td>
              <td class="heading">Инфо</td>
              <td class="heading">Удалить </td>
            </tr>
            <c:forEach var="friend" items="${listFriends}">
              <tr>
                <td>${friend.id}</td>
                <td>${friend.name}</td>
                <td>${friend.surname}</td>
                <td>${friend.email}</td>
                <td><a href="see${friend.id}">Инфо</a> </td>
                <td>
                  <input class="nav" type="button" value="Del" onclick="doDelAjax(${friend.id});$(this).closest('tr').remove();">
                </td>
              </tr>
            </c:forEach>
            <tr>
              <td colspan="7"><a href="search">Поиск друзей</a></td>
            </tr>
          </table>
        </center>
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
