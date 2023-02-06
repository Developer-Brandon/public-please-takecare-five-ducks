<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setCharacterEncoding("UTF-8");

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    // 만약 기본적인 validation에서 막히면 다시 login page로 이동시킵니다.

    // 사례를 더 찾아보기
    // https://daspace.tistory.com/260
    // https://jinseok12.tistory.com/13

    String redirectUrl = "/login";

    if(!email.isEmpty() && !password.isEmpty()) {

        session.setAttribute("email", email);
        session.setAttribute("password", password);

        redirectUrl = "/main";
    }

    response.sendRedirect(redirectUrl);
%>
