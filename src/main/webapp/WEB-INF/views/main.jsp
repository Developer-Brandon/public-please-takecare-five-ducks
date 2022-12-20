<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<!-- jsp reload 하는 법 -->
<!-- https://velog.io/@coreminw/Intellij%EC%97%90%EC%84%9C-JSP-%EC%88%98%EC%A0%95%EC%8B%9C-%EC%9E%90%EB%8F%99-%EB%A6%AC%EB%A1%9C%EB%93%9C-%EB%90%98%EB%8F%84%EB%A1%9D-%EC%84%A4%EC%A0%95 -->
<%! String title = "오덕을 부탁해"; %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= title %></title>
    <!-- jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- -->
    <link rel="stylesheet" href="../util/google-reset-elements.css">

    <!-- -->
    <link rel="stylesheet" href="../util/font/reset-font.css">

    <link rel="stylesheet" href="../util/fd-util.css">

    <link rel="stylesheet" href="main.css">

    <!-- bootstrap -->
    <!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->

</head>
<body>
    <%-- bootstrap은 기본 margin이 있어서 style로 margin을 0으로 지정 --%>

    <!-- 메뉴 시작 -->
    <%@ include file="menu.jsp"%>
    <!-- 메뉴 끝 -->

    <!-- 메인 시작 -->
    <div class="jumbotron">
        <div class="container">
            <h1 class="display-3"></h1>
        </div>
    </div>

    <div class="container">
        <div class="text-center">
            <h3>hi</h3>
        </div>
    </div>
    <!-- 메인 끝 -->

    <!-- footer 시작 -->
    <%@ include file="footer.jsp"%>
    <!-- footer 끝 -->
</body>
</html>
