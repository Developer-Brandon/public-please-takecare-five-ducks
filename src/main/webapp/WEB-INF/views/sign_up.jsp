<%@ page import="java.net.URLDecoder" %>
<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="page_header.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
    <script src="${pageContext.request.contextPath}/resources/js/login.js"></script>
</head>
<body>
<div class="body__inner">
    <div class="login-section">
        <div class="login-section__inner">
            <!-- 입력창 시작 -->
            <div class="input-section">
                <div class="input-section__inner">

                    <!-- logo and wording -->
                    <div class="logo-and-wording">
                        <img class="main-logo" src="${pageContext.request.contextPath}/resources/images/rubber-duck.png" alt="메인로고와 워딩입니다">
                        <h1 class="main-logo-wording">오덕을<br>부탁해</h1>
                    </div>

                    <!-- email -->
                    <div class="email-section">
                        <p class="email">Email</p>
                        <input type="text" class="email-input">
                    </div>
                    <!-- password -->
                    <div class="password-section">
                        <p class="password">Password</p>
                        <input type="text" class="password-input">
                    </div>
                    <!-- login button -->
                    <button class="login-button">로&nbsp;&nbsp;그&nbsp;&nbsp;인</button>
                    <!-- auto login check button -->
                    <div class="auto-login-section">
                        <div class="auto-login-section__inner">
                            <span class="uncheck-auto-login">◻️</span>
                            <span class="check-auto-login">◼️</span>
                            <span class="auto-login-wording">자동로그인</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="join-section">
            <!-- 회원가입하기 -->
            <p class="join-wording">회원가입하기</p>
        </div>
    </div>
</div>
</body>
</html>
