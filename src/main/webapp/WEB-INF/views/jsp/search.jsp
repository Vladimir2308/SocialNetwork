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
    <script type="text/javascript">
      function doAjax(inputText) {
        $.ajax({
		  url : 'friendRequest',
		  type: 'GET',
		  dataType: 'json',
	      contentType: 'application/json',
	      mimeType: 'application/json',
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
        <h4 ><font size="3"> Список всех зарегистрированных пользователей:  </h4>
        <center>
          <table class="table1" cellspacing="0" border="1">
            <tr>
              <td class="heading">id</td>
              <td class="heading">Имя</td>
              <td class="heading">Фамилия</td>
              <td class="heading">Отчество</td>
              <td class="heading">добавить в друзья</td>
            </tr>
            <c:forEach var="user" items="${userList}">
              <tr>
                <td><div >${user.id}</div></td>
                <td><div class=info>${user.name}</div></td>
                <td><div class=info>${user.surname}</div></td>
                <td><div class=info>${user.patronymic}</div></td>
                <td>
                  <input class="${user.id}" type="button" value="OK" onclick="doAjax(${user.id});$(this).remove();">
                </td>
              </tr>
            </c:forEach>
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
