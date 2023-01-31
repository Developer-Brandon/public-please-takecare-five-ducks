<%@ page import="java.net.URLDecoder" %>
<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="page_header.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sign_up.css">
    <script src="${pageContext.request.contextPath}/resources/js/sign_up.js"></script>
</head>
<body>
<div class="body__inner ptcfd-block-drag">
    <div class="login-section">
        <div class="login-section__inner">
            <!-- 입력창 시작 -->
            <div class="input-section">
                <div class="input-section__inner">

                    <!-- logo and wording -->
                    <div class="logo-and-wording">
                        <img class="main-logo" src="${pageContext.request.contextPath}/resources/images/rubber-duck.png"
                             alt="메인로고와 워딩입니다">
                        <h1 class="main-logo-wording">오덕을<br>부탁해</h1>
                    </div>

                    <!-- profile img -->
                    <div class="profile-section">
                        <div class="default">
                            <p class="default-wording">프로필 사진<br>추가하기</p>
                        </div>
                        <!-- <img src="" alt="" class="real"> -->
                    </div>

                    <!-- email -->
                    <div class="email-section">
                        <p class="email">이메일<span class="pdcfd-required-symbol">*</span></p>
                        <input type="text" class="email-input ptcfd-main-input">
                    </div>

                    <!-- password -->
                    <div class="password-section">
                        <p class="password">비밀번호<span class="pdcfd-required-symbol">*</span></p>
                        <input type="text" class="password-input ptcfd-main-input">
                    </div>

                    <!-- user type -->
                    <%--                    <div class="user-type-section">--%>
                    <%--                        <p class="user-type">유저타입</p>--%>
                    <%--                        <c:choose>--%>
                    <%--                            <c:when test="${userTypeVOList.size() != 0}">--%>
                    <%--                                <ul>--%>
                    <%--                                    <c:forEach var="userTypeVO" items="${userTypeVOList}">--%>
                    <%--                                        <c:if test="${userTypeVO.userType.toString() == 'GUEST'}">--%>
                    <%--                                            <li>손님</li>--%>
                    <%--                                        </c:if>--%>
                    <%--                                        <c:if test="${userTypeVO.userType.toString() == 'ADMIN'}">--%>
                    <%--                                            <li>관리자</li>--%>
                    <%--                                        </c:if>--%>
                    <%--                                    </c:forEach>--%>
                    <%--                                </ul>--%>
                    <%--                            </c:when>--%>
                    <%--                            <c:otherwise>--%>
                    <%--                                <p class="user-type-call-warning">유저타입을 불러오는데에 실패하였습니다</p>--%>
                    <%--                            </c:otherwise>--%>
                    <%--                        </c:choose>--%>
                    <%--                    </div>--%>

                    <!-- username -->
                    <div class="username-section">
                        <p class="username">유저이름<span class="pdcfd-required-symbol">*</span></p>
                        <input type="text" class="username-input ptcfd-main-input">
                    </div>

                    <!-- 안내말 -->
                    <ul class="warning-announce-when-sign-up">
                        <li class="validation email-essential">이메일 , 비밀번호, 유저이름은(는) 필수입력값 입니다.</li>
                        <li class="validation email-essential">이메일은 필수 입력 값입니다</li>
                        <li class="validation email-not-exist">존재하지 않는 이메일입니다</li>
                        <li class="validation password-exist">비밀번호는 필수 입력 값입니다</li>
                        <li class="validation password-validation">영문8글자 이상/숫자 2개 이상 섞어주세요</li>
                        <li class="validation password-do-not-match">비밀번호가 일치하지 않습니다</li>
                        <li class="validation user-name-validation">유저이름은 필수 입력 값입니다</li>
                        <li class="validation user-name-exist">이미 존재하는 유저 이름 입니다</li>
                    </ul>
                    <!-- sign-up button -->
                    <button class="sign-up-button">회&nbsp;원&nbsp;가&nbsp;입</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
