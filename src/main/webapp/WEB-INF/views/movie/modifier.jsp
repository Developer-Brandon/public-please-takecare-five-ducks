<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="../page_header.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/movie/modifier.css">
    <script src="${pageContext.request.contextPath}/resources/js/movie/modifier.js"></script>
    <script type="text/javascript">

     <%-- 수정페이지에만 삽입되면, 수정페이지 최초 진입 시 데이터를 불러오는 로직입니다. --%>
     $(function () {

      let movieNo = '${movieNo}';

      console.log(movieNo)

      $.ajax({
       url: `../../movie/info?movieNo=${movieNo}`,
       method: "GET",
       contentType: "application/json",
       dataType: 'json',
       processData: false,
       success: function (data) {

        $('#movie-no').attr('value', movieNo)

        $('.thumbnail-input').attr('value', data.webThumbnailUrl)

        $('.title-input').attr('value', data.title)

        $('.director-name-input').attr('value', data.directorName)

        $('.movie-total-number-of-episode').attr('value', data.totalNumberOfEpisode)

        let movieRegDtToNumber = Number(data.movieRegDt.replace(/-/g, ""))

        $('.movie-reg-dt').attr('value', movieRegDtToNumber)

        $('.made-nature-no-text' + data.madeNatureNo).click()

        $('.import-link').attr('value', data.link)
       },
       error: function (error) {
        alert("failed! ", error.toString())
       }
      })
     });
    </script>
</head>
<body class="custom-body">
<!-- 메뉴 시작 -->
<%@ include file="../menu.jsp" %>
<!-- 메뉴 끝 -->

<%-- 메인 시작 --%>
<div class="body__inner">
    <%-- 상단의 제목 시작 --%>
    <div class="top">
        <div class="top__left">
            <p class="title">✍🏼영화 수정, 삭제하기</p>
        </div>
        <div class="top__right">
            <input id="movie-no" style="display:none">
        </div>
    </div>
    <%-- 썸네일 자동찾기 섹션 --%>
    <div class="thumbnail-section">
        <div class="thumbnail-section__inner">
            <%-- 썸네일이 있다면 이곳의 dom으로... --%>
            <div class="thumbnail-preview-list"></div>
        </div>
    </div>

    <%-- 각각 요소들의 섹션 --%>
    <div class="item-section">
        <div class="item">
            <div class="item__left">
                <p class="thumbnail">썸네일 주소</p>
            </div>
            <div class="item__right">
                <input type="text" class="content thumbnail-input" readonly>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">제목<span class="pdcfd-required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <input type="text" class="content title-input"
                       onkeypress="if(window.event.keyCode===13) enterInputValue()">
                <%--                <button class="find-thumbnail-button">썸네일 찾기</button>--%>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">제작국가<span class="pdcfd-required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <ul>
                    <c:forEach var="contentsMadeNatureInfoVO" items="${contentsMadeNatureInfoList}">
                        <li onclick="selectMadeNature('${contentsMadeNatureInfoVO.madeNatureNo}', '${contentsMadeNatureInfoList.size()}')">
                            <p class="content
                                made-nature-no-text
                                made-nature-no-text${contentsMadeNatureInfoVO.madeNatureNo}">
                                    ${contentsMadeNatureInfoVO.koreanName}
                            </p>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">감독이름<span class="pdcfd-required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <input type="text" class="content director-name-input">
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">총 회차<span class="pdcfd-required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <input type="text" class="content movie-total-number-of-episode" maxlength="1">회
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">방영일자</p>
            </div>
            <div class="item__right">
                <input class="content movie-reg-dt" type='number' placeholder="19800922">
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">importLink<span class="pdcfd-required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <input type="text" class="content import-link">
            </div>
        </div>
    </div>

    <%-- 하단의 섹션 --%>
    <div class="modifier-bottom">
        <div class="modifier-bottom__inner">
            <div class="modifier-bottom__inner__left">
                <p class="delete-text">삭제하기</p>
            </div>
            <div class="modifier-bottom__inner__right">
                <p class="modify-text">수정하기</p>
            </div>
        </div>
    </div>
</div>
<%-- 메인 끝 --%>

<!-- footer 시작 -->
<%@ include file="../footer.jsp" %>
<!-- footer 끝 -->
</body>
</html>
