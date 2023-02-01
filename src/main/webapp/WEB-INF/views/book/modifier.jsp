<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="../page_header.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/book/modifier.css">
    <script src="${pageContext.request.contextPath}/resources/js/book/modifier.js"></script>
    <script type="text/javascript">

     <%-- 수정페이지에만 삽입되면, 수정페이지 최초 진입 시 데이터를 불러오는 로직입니다. --%>
     $(function () {

      let bookNo = '${bookNo}';

      console.log(bookNo)

      $.ajax({
       url: `../../book/info?bookNo=${bookNo}`,
       method: "GET",
       contentType: "application/json",
       dataType: 'json',
       processData: false,
       success: function (data) {

        console.log("넘어온 data: " + data)
        console.log("넘어온 bookNo: " + bookNo)

        $('#book-no').attr('value', bookNo)

        $('.title-input').attr('value', data.bookTitle)

        if(data.bookTypeEnglish === 'SCRIPT') {
         $('.SCRIPT').click()
        } else if(data.bookTypeEnglish === 'BOOK_SUMMARY') {
         $('.BOOK_SUMMARY').click()
        } else if(data.bookTypeEnglish === 'BOOK_PDF') {
         $('.BOOK_PDF').click()
        } else if(data.bookTypeEnglish === 'STUDY_SUMMARY') {
         $('.STUDY_SUMMARY').click()
        } else if(data.bookTypeEnglish === 'LECTURE_SUMMARY') {
         $('.LECTURE_SUMMARY').click()
        } else if(data.bookTypeEnglish === 'NOVEL') {
         $('.NOVEL').click()
        }

        $('.author-input').attr('value', data.bookAuthor)

        $('.made-nature-no-text' + data.madeNatureNo).click()

        let bookRegDtToNumber = Number(data.bookRegDt.replace(/-/g, ""))

        $('.book-reg-dt').attr('value', bookRegDtToNumber)

        $('.import-link').attr('value', data.link)
       },
       error: function (error) {
        alert("failed! ", error.toString())
       }
      })

     });
    </script>
</head>
<body class="ptcfd-body ptcfd-block-drag">
<!-- 메뉴 시작 -->
<%@ include file="../menu.jsp" %>
<!-- 메뉴 끝 -->

<%-- 메인 시작 --%>
<div class="ptcfd-body__inner">
    <%-- 상단의 제목 시작 --%>
    <div class="top">
        <div class="top__left">
            <p class="title">✍🏼책 수정, 삭제하기</p>
        </div>
        <div class="top__right">
            <input id="book-no" style="display:none">
        </div>
    </div>

    <%-- 각각 요소들의 섹션 --%>
    <div class="item-section">
        <div class="item">
            <div class="item__left">
                <p class="title">제목<span class="pdcfd-required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <input type="text" class="content title-input">
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">책 종류<span class="pdcfd-required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <ul>
                    <c:forEach var="bookType" items="${bookTypeList}">
                        <li onclick="selectBookType('${bookType.bookTypeEnglish}','${bookType.bookTypeNo}')">
                            <c:choose>
                                <c:when test="${bookType.bookTypeEnglish == 'SCRIPT'}">
                                    <p class="content book-type-text ${bookType.bookTypeEnglish}" style="color:${bookType.hexCode};">${bookType.bookTypeKorean}</p>
                                </c:when>
                                <c:when test="${bookType.bookTypeEnglish == 'BOOK_SUMMARY'}">
                                    <p class="content book-type-text ${bookType.bookTypeEnglish}" style="color:${bookType.hexCode};">${bookType.bookTypeKorean}</p>
                                </c:when>
                                <c:when test="${bookType.bookTypeEnglish == 'BOOK_PDF'}">
                                    <p class="content book-type-text ${bookType.bookTypeEnglish}" style="color:${bookType.hexCode};">${bookType.bookTypeKorean}</p>
                                </c:when>
                                <c:when test="${bookType.bookTypeEnglish == 'STUDY_SUMMARY'}">
                                    <p class="content book-type-text ${bookType.bookTypeEnglish}" style="color:${bookType.hexCode};">${bookType.bookTypeKorean}</p>
                                </c:when>
                                <c:when test="${bookType.bookTypeEnglish == 'LECTURE_SUMMARY'}">
                                    <p class="content book-type-text ${bookType.bookTypeEnglish}" style="color:${bookType.hexCode};">${bookType.bookTypeKorean}</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="content book-type-text NOVEL" style="color:${bookType.hexCode};">
                                            ${bookType.bookTypeKorean}
                                    </p>
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">컨텐츠제작자<span class="pdcfd-required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <input type="text" class="content author-input">
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">컨텐츠제작일자</p>
            </div>
            <div class="item__right">
                <input class="content book-reg-dt" type='number' placeholder="19800922">
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
