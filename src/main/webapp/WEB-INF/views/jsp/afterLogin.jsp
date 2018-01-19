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
    function doAjaxAdd(inputText) {
	$.ajax({
		url : 'friendAdd',
		type: 'POST',
		dataType: 'json',
		data : ({
			text: inputText
		}),
		success: function (data) {
			var result = data;
			$("#result_text").text(result);
		}
	  });
    }
    function doAjaxFault(inputText) {
	$.ajax({
		url : 'friendReqFault',
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
       <div class=userInfo>
         Текущий пользователь:
         <br>
         <br>id = ${user.id}
         <br>
         Имя=  ${user.name}
         <br>
         Фамилия = ${user.surname}
         <br>
         Отчество = ${user.patronymic}
         <br>
         email = ${user.email}
         <br>
         Телефон = ${user.phone}
       </div>
       <c:if test="${not empty listRequestFriends}">
         <center>
           <table class="table1"  border="1">
             <br>
             <tr><td colspan="7">Запросы на добавление в друзья:</td></tr>
             <tr>
               <td class="heading"> id</td>
               <td class="heading">Имя</td>
               <td class="heading">Фамилия</td>
               <td class="heading">Отчество</td>
               <td class="heading">Выбор</td>
             </tr>
             <c:forEach var="userFromReq" items="${listRequestFriends}">
               <tr>
                 <td>${userFromReq.id}</td>
                 <td>${userFromReq.name}</td>
                 <td>${userFromReq.surname}</td>
                 <td>${userFromReq.patronymic}</td>
                 <td><input class="nav" id=${user.id} type="button"
                   value="Add" onclick="doAjaxAdd(${userFromReq.id});$(this).closest('tr').remove();">
                   <input class="nav" id=${user.id} type="button"
                   value="Del" onclick="doAjaxFault(${userFromReq.id});$(this).closest('tr').remove();">
                 </td>
               </tr>
             </c:forEach>
           </table>
         </center>
       </c:if>
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
