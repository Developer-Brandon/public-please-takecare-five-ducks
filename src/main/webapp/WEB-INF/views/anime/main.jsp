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
                    <a class="anime-title"><c:out value="${animeVO.animeTitle}"/><span style="display:inline-block;"
                                                                                       onclick="goAnimeModifierPage('${animeVO.animeNo}')">&nbsp;✍🏻</span></a>
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
            <img src="${pageContext.request.contextPath}/resources/images/left-arrow.png"
                 alt=""
                 class="left-arrow">
            <ul>
                <li class="item">1</li>
                <li class="item">2</li>
                <li class="item">3</li>
                <li class="item">4</li>
                <li class="item">5</li>
            </ul>
            <img src="${pageContext.request.contextPath}/resources/images/right-arrow.png"
                 alt=""
                 class="right-arrow">
        </div>
    </div>
    <%-- pagination section 끝 --%>

    <%--        <!-- pagination start -->--%>
    <%--        <c:if test="${pageHandler.totalCnt != null && pageHandler.totalCnt != 0}">--%>
    <%--            <div class="pull-right">--%>
    <%--                <ul class="pagination">--%>
    <%--                    <c:if test="${pageHandler.showPrev}">--%>
    <%--                        <li class="paginate_button previous">--%>
    <%--                            <a href="<c:url value="/notice/content/list${pageHandler.sc.getQueryString(pageHandler.beginPage - 1)}"/>">이전</a>--%>
    <%--                        </li>--%>
    <%--                    </c:if>--%>

    <%--                    <c:forEach var="i"--%>
    <%--                               begin="${pageHandler.beginPage}"--%>
    <%--                               end="${pageHandler.endPage}">--%>
    <%--                        <li class="paginate_button ${i==pageHandler.sc.page? "active" : ""}">--%>
    <%--                            <a href="<c:url value="/notice/content/list${pageHandler.sc.getQueryString(i)}"/>">${i}</a>--%>
    <%--                        </li>--%>
    <%--                    </c:forEach>--%>

    <%--                    <c:if test="${pageHandler.showNext}">--%>
    <%--                        <li class="paginate_button next">--%>
    <%--                            <a href="<c:url value="/notice/content/list${pageHandler.sc.getQueryString(pageHandler.endPage + 1)}"/>">다음</a>--%>
    <%--                        </li>--%>
    <%--                    </c:if>--%>
    <%--                </ul>--%>
    <%--            </div>--%>
    <%--        </c:if>--%>
    <%--        <!-- pagination end -->--%>
</div>

<!-- footer 시작 -->
<%@ include file="../footer.jsp" %>
<!-- footer 끝 -->
</body>
</html>
