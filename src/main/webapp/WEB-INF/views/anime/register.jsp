<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="../page_header.jsp" %>
    <!-- -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/util/component/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/anime/register.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/jquery-3.6.3.min.js">
    <%-- 스크립트 시작 --%>
    <script type="text/javascript">

     $(function () {
      //제이쿼리
     });

     function getContextPath() {
      let hostIndex = location.href.indexOf(location.host) + location.host.length;
      return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
     }

     function selectImageThumbnail() {
      let insertedTitle = $('.title-input').val();

      if (insertedTitle === '') {
       window.alert("애니 제목을 입력해주세요");
       return;
      }

      // // json 형식으로 데이터 set
      // let params = {animeName: insertedTitle}
      //
      // // ajax 통신
      // $.ajax({
      //  type: "POST",
      //  url: "/anime/search/image/thumbnail",
      //  data: params,
      //  success: function (res) {
      //   alert(res.code);
      //  },
      //  error: function (XMLHttpRequest, textStatus, errorThrown) {
      //   alert("통신 실패.")
      //  }
      // });

      $.get("./search/image/thumbnail?animeName=" + insertedTitle
       , function (data) {
        console.log(typeof data); // string
        console.log(data); // HTML content of the jQuery.ajax page
       });

      // $.ajax({
      //  url: "./search/image/thumbnail", // 클라이언트가 HTTP 요청을 보낼 서버의 URL 주소
      //  data: { animeName: insertedTitle },  // HTTP 요청과 함께 서버로 보낼 데이터
      //  method: "GET",   // HTTP 요청 메소드(GET, POST 등)
      //  dataType: "json" // 서버에서 보내줄 데이터의 타입
      // })
      // // HTTP 요청이 성공하면 요청한 데이터가 done() 메소드로 전달됨.
      // .done(function(json) {
      //  alert(json);
      //  // $("<h1>").text(json.title).appendTo("body");
      //  // $("<div class=\"content\">").html(json.html).appendTo("body");
      // })
      // // HTTP 요청이 실패하면 오류와 상태에 관한 정보가 fail() 메소드로 전달됨.
      // .fail(function(xhr, status, errorThrown) {
      //  // $("#text").html("오류가 발생했다.<br>")
      //  // .append("오류명: " + errorThrown + "<br>")
      //  // .append("상태: " + status);
      //  alert(errorThrown)
      //  alert(status)
      // })
      // //
      // .always(function(xhr, status) {
      //  // $("#text").html("요청이 완료되었습니다!");
      //  // alert(status)
      // });
     }

    </script>
    <%-- 스크립트 끝 --%>
</head>
<body class="custom-body">
<!-- 메뉴 시작 -->
<%@ include file="../menu.jsp" %>
<!-- 메뉴 끝 -->

<%-- 메인 시작 --%>
<%--<div class="anime-banner">--%>
<%--    <div class="anime-banner__inner"></div>--%>
<%--</div>--%>
<div class="body__inner">
    <%-- 상단의 제목 시작 --%>
    <div class="top">
        <div class="top__left">
            <p class="title">✍🏼애니 등록하기</p>
        </div>
        <div class="top__right">
        </div>
    </div>

    <%-- 썸네일 자동찾기 섹션 --%>
    <div class="thumbnail-section">
        <div class="thumbnail-section__inner">
            <%-- <img src="" alt="" class="thumbnail"> --%>
            <%-- img 태그가 없을 시 기본적으로 사진 지정 --%>
            <div class="default-thumbnail"></div>
        </div>
    </div>

    <%-- 각각 요소들의 섹션 --%>
    <div class="item-section">
        <div class="item">
            <div class="item__left">
                <p class="title">제목</p>
            </div>
            <div class="item__right">
                <input type="text" class="content title-input">
                <button class="find-thumbnail-button" onclick="selectImageThumbnail()">썸네일 찾기</button>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">방영상태</p>
            </div>
            <div class="item__right">
                <p class="content finalized-text">완결</p>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">방영회수</p>
            </div>
            <div class="item__right">
                <input type="text" class="content board-cast-cnt-input">
                <span class="board-cast-cnt-text">&nbsp;회</span>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">최초방영일자</p>
            </div>
            <div class="item__right">
                <input type="text" class="content anime-reg-dt">
            </div>
        </div>
        <div class="item">

            <div class="item__left">
                <p class="title">제작국가</p>
            </div>
            <div class="item__right">
                <p class="content made-nature-no-text">일본</p>
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
    <div class="register-bottom">
        <div class="register-bottom__inner">
            <p class="register-text">등록하기</p>
        </div>
    </div>
</div>

<%-- 메인 끝 --%>

<!-- footer 시작 -->
<%@ include file="../footer.jsp" %>
<!-- footer 끝 -->
</body>
</html>
