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
        <c:forEach var="animeType" items="${animeVOList}">
            <ul class="pagination">
                <li class="item anime-no"><c:out value="${animeType.animeNo}"/></li>
                <li class="item anime-title"><c:out value="${animeType.animeTitle}"/></li>
                <c:choose>
                    <c:when test="${animeType.finalizedYnEnum == 'y'}">
                        <li class="item anime-finalized-yn">완결</li>
                    </c:when>
                    <c:otherwise>
                        <li class="item anime-finalized-yn">방영중</li>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${animeType.finalizedYnEnum == 'y'}">
                        <li class="item anime-cnt">총 <c:out value="${animeType.animeBroadcastCnt}"/>화</li>
                    </c:when>
                    <c:otherwise>
                        <li class="item anime-cnt"><c:out value="${animeType.animeBroadcastCnt}"/>화</li>
                    </c:otherwise>
                </c:choose>
                <li class="item anime-reg-dt"><c:out value="${animeType.animeRegDt}"/></li>
            </ul>
        </c:forEach>
    </div>

    <%-- pagination section 시작 --%>
    <div class="search-section">
        <div class="search-section__inner">

        </div>
    </div>
</div>

<!-- footer 시작 -->
<%@ include file="../footer.jsp" %>
<!-- footer 끝 -->
</body>
</html>
