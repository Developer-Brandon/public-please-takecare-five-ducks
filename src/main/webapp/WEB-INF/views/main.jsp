<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <title>Main</title>
</head>
<%! String greeting = "Book Market Mall";
    String tagline = "Welcome to Book Market!";
%>
<body>
    <%-- bootstrap은 기본 margin이 있어서 style로 margin을 0으로 지정 --%>

    <%@ include file="menu.jsp"%>

    <div class="jumbotron">
        <div class="container">
            <h1 class="display-3"> <%= greeting %> </h1>
        </div>
    </div>

    <div class="container">
        <div class="text-center">
            <h3><%= tagline %></h3>
        </div>
    </div>

    <%@ include file="footer.jsp"%>
</body>
</html>
