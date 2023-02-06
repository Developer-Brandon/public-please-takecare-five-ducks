<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setCharacterEncoding("UTF-8");

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    String redirectUrl = "/login";

    if(!email.isEmpty() && !password.isEmpty()) {

        session.setAttribute("email", email);
        session.setAttribute("password", password);

        redirectUrl = "/main";
    }

    response.sendRedirect(redirectUrl);
%>
