<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="../page_header.jsp" %>
    <!-- -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/anime/index.css">
</head>
<body>
<!-- 메뉴 시작 -->
<%@ include file="../menu.jsp" %>
<!-- 메뉴 끝 -->

<div class="body__inner">

    <%-- 상단의 제목 시작 --%>
    <div class="top">
        <div class="top__left">
            <p class="title">애니 모음</p>
        </div>
        <div class="top__right">
            <p class="register-text">등록하기</p>
        </div>
    </div>

    <%-- pagination list 시작 --%>
    <div class="pagination-section">
        <ul class="pagination">
            <c:forEach var="animeType" items="${animeVOList}">
            <li class="item">
                    <a class="anime-no"><c:out value="${animeType.animeNo}"/></a>
                    <a class="anime-title"><c:out value="${animeType.animeTitle}"/></a>
                    <c:choose>
                        <c:when test="${animeType.finalizedYnEnum == 'y'}">
                            <a class="anime-finalized-yn">완결</a>
                        </c:when>
                        <c:otherwise>
                            <a class="anime-finalized-yn">방영중</a>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${animeType.finalizedYnEnum == 'y'}">
                            <a class="anime-cnt">총 <c:out value="${animeType.animeBroadcastCnt}"/>화</a>
                        </c:when>
                        <c:otherwise>
                            <a class="anime-cnt"><c:out value="${animeType.animeBroadcastCnt}"/>화</a>
                        </c:otherwise>
                    </c:choose>
                    <a class="anime-reg-dt"><c:out value="${animeType.animeRegDt}"/></a>
            </li>
            </c:forEach>
        </ul>
    </div

    <%-- search section 시작 --%>
    <%--        todo: search-section 돔이 없던데 왜그런건지 알아보기 --%>
    <div class="search-section">
        <div class="wrap-search-input">
            <p class="search-text">제목</p>
            <input type="text" class="search-input"/>
        </div>
    </div>

    <%-- pagination section 시작 --%>
</div>

<!-- footer 시작 -->
<%@ include file="../footer.jsp" %>
<!-- footer 끝 -->
</body>
</html>
