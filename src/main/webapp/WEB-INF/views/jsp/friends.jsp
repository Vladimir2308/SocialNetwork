<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
 <head>
  <meta charset="utf-8">
  <title>T-Net</title>
  <style>
body, html {
font: 14px Arial, Helvetica, sans-serif;
margin:0px;
padding:0px;
height: 100%;
}

   h1 {

    margin: 0;
    color: #111;
   }
   h2 {
    margin-top: 0;
   }

   .header {
   width: 90%;
    background: #0080c0;
   background-image: linear-gradient(to top right, blue, white 70%, blue);
    padding: 10px 0px 10px 30%;
    height: 5%;
   }

   .container{
  display: table;
   width:800px;
   height: 90%;
   background: #f0ffff;
   margin:0 auto;
   text-align: left;
   border: 0px solid black;
   }
   .content {
   box-sizing: border-box;
       padding: 20px;
    height: 98%;
   float:right;
   width:560px;
   border: 0px solid red;

   }

   .foto{
   float:left;
   width:200px;
    border: 1px solid #434ab1;
    padding:10px;
   }
   .extra{
      float:left;
      clear:left;
      width:200px;
      border: 1px solid black;
      padding:10px;

   }
   .footer {
       background: #333;
       padding: 0px 0px 0px 10%;
       color: #fff;
      clear:both;
      width:95%;
      margin:0;

      }
      a {
        display: table-cell;
        width: 180px;
        height: 35px;
        box-sizing: border-box;
        margin: 10px;
        text-align: center;
        color: #FFF;
        text-decoration: none;
        background: linear-gradient(#89C13E, #599C02);
        background-color: 6FAD1D;
        border-radius: 30px;
        border: 1px solid #579A00;
        font: bold 18px/35px Arial;
        box-shadow:
          inset 0 2px 2px #CCE464,
          inset 1px 0 3px #B2DC2D,
          inset -1px 0 3px #B2DC2D,
          inset 0 -2px 2px #89D301,
          0 0 3px rgba(0,0,0,0.24);
        ;
        position: relative;
        text-shadow: 0 1px #45880D;
      }
      a:before {
        content: '';
        position: absolute;
        top: -10px;
        left: -10px;
        width: 100%;
        height: 100%;
        padding: 10px;



      }
      a:hover {
        background: linear-gradient(#97D444, #65AD07);
        box-shadow:
          inset 0 1px 0 #E2F498,
          inset 0 2px 0 #CEEC87,
          inset 0 3px 0 #C3E67C,
          inset 0 5px 0 #BAE475,
          inset 1px 0 3px #B2DC2D,
          inset -1px 0 3px #B2DC2D,
          inset 0 -2px 2px #89D301,
          0 0 3px rgba(0,0,0,0.24);
        ;
      }
      a:active {
        background: linear-gradient(#83BB37, #599C02);
        box-shadow:
          inset 0 3px 7px #3C7B00;
        ;
        padding: 1px 0 0 1px;
      }
      td {
       font-size: 15px;
       color: black;
       width: 100px;
       height: 22px;
       text-align: center;
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

			var result = "deleted";
			$("#result_text").text(result);
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
              <br /> <br /> <br /> <b>User
               List |  </b><br /> <br />



              <table border="1">
               <tr>
                <td class="heading">User Id</td>
                <td class="heading">Name</td>
                <td class="heading">Surname</td>
                <td class="heading">Patronymic</td>
                <td class="heading">Email</td>
                <td class="heading">Del from friends</td>

               </tr>
               <c:forEach var="friend" items="${listFriends}">
                <tr>
                 <td>${friend.id}</td>
                 <td>${friend.name}</td>
                 <td>${friend.surname}</td>
                 <td>${friend.patronymic}</td>
                 <td>${friend.email}</td>


                 <td>
                     	<input class="nav" id=${friend.id} type="button" value="Del" onclick="doDelAjax(${friend.id})">
                     	<p id="result_text"></p> </td>

                </tr>
               </c:forEach>
               <tr><td colspan="7"><a href="search">Поиск друзей</a></td></tr>

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
