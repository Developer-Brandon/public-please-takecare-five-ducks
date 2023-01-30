function goAnimeRegisterPage() {
 location.href = './register'
}

function goAnimeModifierPage(animeNo) {
 location.href = './modifier/' + animeNo
}

function goAnimeDetailPage(animeNo, link) {

 // todo: 추후 로그인의 개념이 잡히면 userNo를 교체할 예정입니다 .

 let data = {userNo: 100, animeNo: animeNo};

 $.ajax({
  url: "./info/view",
  data: JSON.stringify(data),
  method: "POST",
  contentType: "application/json",
  dataType: "json",
  success: function (data) {
   window.open(link)
  },
  error: function (error) {
   alert("failed! ", error.toString())
  }
 })
}

function enterSearchInputValue() {

 // todo: 아무래도 JSP다 보니까, 페이지가 갱신되면서 input에 있던 검색어가 날아갑니다.
 // 추후 쿠키개념을 도입하여 보완할 예정입니다.
 location.href = './main?currentPage=1&title=' + $('.search-input').val()
}

$(function () {

 // search input 옆에 제목 텍스트와 아이콘을 누르면 발생하는 팝업창
 $('.search-text').click(function () {
  alert("제목 이외에도 다른 것으로 검색할 수 있게 기능추가 예정입니다")
 })
})
