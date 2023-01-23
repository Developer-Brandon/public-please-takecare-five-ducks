<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="../page_header.jsp" %>
    <!-- -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/util/component/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/anime/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css">
    <script type="text/javascript">
     function goAnimeRegisterPage() {
      location.href = './register'
     }

     function goAnimeModifierPage(animeNo) {
      location.href = './modifier/' + animeNo
     }

     function goAnimeDetailPage(link) {
      window.open(link)
     }

     $(function () {
      $('.search-text').click(function() {
       alert("제목 이외에도 다른 것으로 검색할 수 있게 기능추가 예정입니다")
      })
     })
    </script>
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
            <p class="register-text" onclick="goAnimeRegisterPage()">등록하기</p>
        </div>
    </div>

    <%-- list section 시작 --%>
    <div class="list-section">
        <ul>
            <c:forEach var="animeVO" items="${animeListResultVO.animeVOList}">
                <li class="item" onclick="goAnimeDetailPage('${animeVO.link}')">
                    <a class="anime-no"><c:out value="${animeVO.animeNo}"/></a>
                    <a class="anime-title">
                        <c:out value="${animeVO.animeTitle}"/>
                        <img style="width:13px;height:13px;border-radius:5px;"
                             src="${animeVO.webThumbnailUrl}"
                             alt="웹 썸네일 URL 입니다">
                        <span style="display:inline-block;"
                           onclick="goAnimeModifierPage('${animeVO.animeNo}')">&nbsp;✍🏻
                        </span>
                    </a>
                    <c:choose>
                        <c:when test="${animeVO.finalizedYnEnum == 'y'}">
                            <a class="anime-finalized-yn" style="color:#FF0000;">완결</a>
                        </c:when>
                        <c:otherwise>
                            <a class="anime-finalized-yn" style="color:#2400FF;">방영중</a>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${animeVO.finalizedYnEnum == 'y'}">
                            <a class="anime-cnt">총 <c:out value="${animeVO.animeBroadcastCnt}"/> 화</a>
                        </c:when>
                        <c:otherwise>
                            <a class="anime-cnt"><c:out value="${animeVO.animeBroadcastCnt}"/> 화</a>
                        </c:otherwise>
                    </c:choose>
                    <a class="anime-reg-dt"><c:out value="${animeVO.animeRegDt}"/></a>
                </li>
            </c:forEach>
        </ul>
    </div>

    <%-- search section 시작 --%>
    <div class="search-section">
        <div class="wrap-search-input">
            <p class="search-text">👆🏼제목</p>
            <input type="text" class="search-input"/>
        </div>
    </div>

    <%-- pagination section 시작 --%>
    <div class="pagination-section">
        <div class="pagination-section__inner">
            <c:if test="${animeListResultVO.pageHandler.totalCnt != null && animeListResultVO.pageHandler.totalCnt != 0}">
                <c:if test="${animeListResultVO.pageHandler.showPrev}">
                    <a class="first-left-side"
                        href="<c:url value="/anime/main${animeListResultVO.pageHandler.sc.getQueryString(animeListResultVO.pageHandler.beginPage)}"/>">
                        <%-- 추후 아이콘으로 사용하고 싶으면 아래 주석 풀고 사용하기 --%>
                        <%--                        <img src="${pageContext.request.contextPath}/resources/images/left-arrow.png"--%>
                        <%--                             alt="페이지의 앞으로 이동하는 아이콘입니다."--%>
                        <%--                             class="left-arrow">--%>
                        맨 앞으로
                    </a>
                </c:if>
                <ul>
                    <c:forEach var="i"
                               begin="${animeListResultVO.pageHandler.beginPage}"
                               end="${animeListResultVO.pageHandler.endPage}">
                        <li class="item ${i == animeListResultVO.pageHandler.sc.page? "active" : ""}">
                            <a href="<c:url value="/anime/main${animeListResultVO.pageHandler.sc.getQueryString(i)}"/>">
                                    ${i}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
                <c:if test="${animeListResultVO.pageHandler.showNext}">
                <a class="last-right-side"
                   href="<c:url value="/anime/main${animeListResultVO.pageHandler.sc.getQueryString(animeListResultVO.pageHandler.endPage)}"/>">
                        <%-- 추후 아이콘으로 사용하고 싶으면 아래 주석 풀고 사용하기 --%>
                        <%--                    <img src="${pageContext.request.contextPath}/resources/images/right-arrow.png"--%>
                        <%--                         alt="페이지의 앞으로 이동하는 아이콘입니다."--%>
                        <%--                         class="right-arrow">--%>
                        맨 뒤로
                </a>
                </c:if>
            </c:if>
        </div>
    </div>
    <%-- pagination section 끝 --%>
</div>

<!-- footer 시작 -->
<%@ include file="../footer.jsp" %>
<!-- footer 끝 -->
</body>
</html>
