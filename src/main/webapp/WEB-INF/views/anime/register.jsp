<html>
<!-- jsp reload 하는 법 -->
<!-- https://velog.io/@coreminw/Intellij%EC%97%90%EC%84%9C-JSP-%EC%88%98%EC%A0%95%EC%8B%9C-%EC%9E%90%EB%8F%99-%EB%A6%AC%EB%A1%9C%EB%93%9C-%EB%90%98%EB%8F%84%EB%A1%9D-%EC%84%A4%EC%A0%95 -->
<!-- reload시 주의점: css의 reloading을 고려하여 ctrl + shift + R로 새로고침을 실행해야합니다.-->
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="../page_header.jsp" %>
    <!-- -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/anime/main.css">
</head>
<body>
<!-- 메뉴 시작 -->
<%@ include file="../menu.jsp" %>
<!-- 메뉴 끝 -->

<c:forEach var="animeType" items="${animeVOList}">
    <ul>
        <li><c:out value="${animeType.animeNo}"/></li>
        <li><c:out value="${animeType.animeTitle}"/></li>
        <li><c:out value="${animeType.animeAuthor}"/></li>
    </ul>
</c:forEach>

<!-- footer 시작 -->
<%@ include file="../footer.jsp" %>
<!-- footer 끝 -->
</body>
</html>
