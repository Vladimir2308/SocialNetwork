<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <!--
       <style>
          <%@include file='style.css' %>
       </style>
       -->
   <style type="text/css">
   html,body{margin:0;padding:0}
   body{font: 76% arial,sans-serif;text-align:center}
   p{margin:0 10px 10px}
   a{display:block;color: #981793;padding:10px}
   div#header h1{height:80px;line-height:80px;margin:0;
     padding-left:10px;background: #EEE;color: #79B30B}
   div#container{text-align:left}
   div#content p{line-height:1.4}
   div#navigation{background:#B9CAFF}
   div#extra{background:#FF8539}
   div#footer{background: #333;color: #FFF}
   div#footer p{margin:0;padding:5px 10px}

   div#container{width:700px;margin:0 auto}
   div#content{float:right;width:500px}
   div#navigation{float:left;width:200px}
   div#extra{float:left;clear:left;width:200px}
   div#footer{clear:both;width:100%}
   </style>
   </head>
   <body>
    <div id="container">
    <div id="header"><a href="http://localhost:8080/index">Выйти</a></div>
    <div id="wrapper">
    <div id="content">
    <p><strong>1) Content here.</strong> <text>Login is succsesful
     </text>
    <input type="text" name="name" >
  <c:if test="${not empty test}">
     <hr>
 <h2>Привет, ${test}!</h2>
    </c:if>



    <p>Some information </p>
    </div>
    </div>
    <div id="navigation">
    <p><img src="defaultAvatar.jpg"></p>
    </div>
    <div id="extra">
    <p><strong>Друзья:</p>
    </div>
    <div id="footer"><p>something</p></div>
    </div>



    </body>
</html>