<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="../page_header.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/comic/modifier.css">
    <script src="${pageContext.request.contextPath}/resources/js/comic/modifier.js"></script>
    <script type="text/javascript">

     <%-- 수정페이지에만 삽입되면, 수정페이지 최초 진입 시 데이터를 불러오는 로직입니다. --%>
     $(function () {

      let comicBookNo = '${comicBookNo}';

      console.log(comicBookNo)

      $.ajax({
       url: `../../comic/info?bookNo=${comicBookNo}`,
       method: "GET",
       contentType: "application/json",
       dataType: 'json',
       processData: false,
       success: function (data) {

        console.log("넘어온 data: " + data)
        console.log("넘어온 comicBookNo: " + comicBookNo)

        $('#comic-book-no').attr('value', comicBookNo)

        $('.thumbnail-input').attr('value', data.webThumbnailUrl)
        $('.title-input').attr('value', data.comicBookTitle)

        if (data.comicBookSerialStateEnum === 'finished') {
         $('.finished').click()
        } else if (data.comicBookSerialStateEnum === 'being'){
         $('.being').click()
        } else if (data.comicBookSerialStateEnum === 'vacation') {
         $('.vacation').click()
        }

        $('.author-input').attr('value', data.comicBookAuthor)

        let comicBookRegDtToNumber = Number(data.comicBookRegDt.replace(/-/g, ""))

        $('.comic-book-reg-dt').attr('value', comicBookRegDtToNumber)

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
            <p class="title">✍🏼만화책 수정, 삭제하기</p>
        </div>
        <div class="top__right">
            <input id="comic-book-no" style="display:none">
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
    <%--    <form onsubmit="return false;">--%>
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
                <input type="text" class="content title-input"
                       onkeypress="if(window.event.keyCode===13) enterInputValue()">
<%--                <button class="find-thumbnail-button">썸네일 찾기</button>--%>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">연재상태<span class="required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <ul>
                    <c:forEach var="comicBookSerialStateVO" items="${comicBookSerialStateList}">
                        <li onclick="selectSerialState('${comicBookSerialStateVO.comicBookSerialStateEnum}')">
                            <c:choose>
                                <c:when test="${comicBookSerialStateVO.comicBookSerialStateEnum == 'being'}">
                                    <p class="content being" style="color:#000AFF;">연재중</p>
                                </c:when>
                                <c:when test="${comicBookSerialStateVO.comicBookSerialStateEnum == 'finished'}">
                                    <p class="content finished" style="color:#FF0000;">연재완료</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="content vacation" style="color:#04CF00;">휴재중</p>
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </c:forEach>
                </ul>
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
                <p class="title">출간일자</p>
            </div>
            <div class="item__right">
                <input class="content comic-book-reg-dt" type='number' placeholder="19800922">
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">제작국가<span class="required-symbol">*</span></p>
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
                <p class="title">importLink<span class="required-symbol">*</span></p>
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
