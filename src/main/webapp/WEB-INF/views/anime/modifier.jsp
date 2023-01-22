<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="../page_header.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/anime/modifier.css">
    <script src="${pageContext.request.contextPath}/resources/js/anime/modifier.js"></script>
    <script type="text/javascript">

     <%-- 수정페이지에만 삽입되면, 수정페이지 최초 진입 시 데이터를 불러오는 로직입니다. --%>
     $(function () {

      let animeNo = '${animeNo}';

      console.log(animeNo)

      $.ajax({
       url: `../../anime/info?animeNo=${animeNo}`,
       method: "GET",
       contentType: "application/json",
       dataType: 'json',
       processData: false,
       success: function (data) {

        console.log("넘어온 data: " + data)
        console.log("넘어온 animeNo: " + animeNo)

        $('#anime-no').attr('value', animeNo)

        $('.thumbnail-input').attr('value', data.webThumbnailUrl)
        $('.title-input').attr('value', data.animeTitle)
        $('.author-input').attr('value', data.animeAuthor)

        if(data.finalizedYnEnum === 'y') {
         $('.finalized-text-y').click()
        } else {
         $('.finalized-text-n').click()
        }

        $('.board-cast-cnt-input').attr('value', data.animeBroadcastCnt)

        let animeRegDtToNumber = Number(data.animeRegDt.replace(/-/g, ""))

        $('.anime-reg-dt').attr('value', animeRegDtToNumber)

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
            <p class="title">✍🏼애니 수정, 삭제하기</p>
        </div>
        <div class="top__right">
            <input id="anime-no" style="display:none"></input>
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
                <p class="title">제목<span class="required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <input type="text" class="content title-input">
                <%--                       onkeypress="if(window.event.keyCode===13) enterInputValue()">--%>
                <%--                <button class="find-thumbnail-button">썸네일 찾기</button>--%>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">작가<span class="required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <input type="text" class="content author-input">
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">방영상태<span class="required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <ul>
                    <c:forEach var="finalizedYnEnum" items="${animeFinalizedList}">
                        <li onclick="selectFinalizedState('${finalizedYnEnum}')">
                            <c:choose>
                                <c:when test="${finalizedYnEnum == 'y'}">
                                    <p class="content finalized-text finalized-text-y" style="color:#FF0000;">완결</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="content finalized-text finalized-text-n" style="color:#2400FF;">
                                        방영중</p>
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">방영회수</p>
            </div>
            <div class="item__right">
                <input class="content board-cast-cnt-input" type='number'>
                <span class="board-cast-cnt-text">&nbsp;회</span>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">최초방영일자</p>
            </div>
            <div class="item__right">
                <input class="content anime-reg-dt" type='number' placeholder="19800922">
            </div>
        </div>
        <div class="item">

            <div class="item__left">
                <p class="title">제작국가</p>
            </div>
            <div class="item__right">
                <ul>
                    <c:forEach var="contentsMadeNatureInfoVO" items="${contentsMadeNatureInfoList}">
                        <li onclick="selectMadeNature('${contentsMadeNatureInfoVO.madeNatureNo}', '${contentsMadeNatureInfoList.size()}')">
                            <p class="content made-nature-no-text made-nature-no-text${contentsMadeNatureInfoVO.madeNatureNo}">
                                    ${contentsMadeNatureInfoVO.koreanName}
                            </p>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">importLink</p>
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
