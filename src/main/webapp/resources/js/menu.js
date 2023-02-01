function goMainPage(rootUrl) {
 location.href = rootUrl + '/'
}

$(function () {

 // 로그아웃 글자 클릭 시 로그아웃 request 예정입니다.
 $('.logout').click(function() {
  console.log('안녕하세요')
 })
})
