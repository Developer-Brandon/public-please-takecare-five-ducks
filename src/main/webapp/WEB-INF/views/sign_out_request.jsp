<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    session.removeAttribute("email");
    session.removeAttribute("password");
    response.sendRedirect("./");
%>
