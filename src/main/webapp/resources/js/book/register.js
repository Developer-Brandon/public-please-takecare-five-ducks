let bookTypeNo = ''
let contentsMadeNatureNo = ''
let bookRegDt = ''

/** 책종류를 클릭했을때에 호출되는 메소드입니다. */
function selectBookType(bookTypeEnglish, bookTypeNo) {

 this.bookTypeNo = bookTypeNo

 $('.'+bookTypeEnglish).addClass('font-weight-bold')
 $('.'+bookTypeEnglish).parents().siblings().children().removeClass('font-weight-bold')
 // $('.'+bookTypeEnglish).parents().children().addClass('font-weight-regular')

}

/** 책 제작 국가 선택 시 호출하는 메소드 */
function selectMadeNature(madeNatureNo, size) {

 contentsMadeNatureNo = madeNatureNo

 for (i = 1; i <= size; i++) {
  if (i !== Number(madeNatureNo)) {
   $('.made-nature-no-text' + i).removeClass('font-weight-bold')
  } else {
   $('.made-nature-no-text' + i).addClass('font-weight-bold')
  }
 }
}

function validationFormInfo() {

 if ($('.title-input').val() === '') {
  alert("제목은 필수 입력 사항입니다.")
  return
 } else {
  if ($('.title-input').val().length === 1) {
   alert("1글자 초과로 입력해주세요.")
   return
  }
 }

 if ($('.author-input').val() === '') {
  alert("컨텐츠 제작자는 필수 입력 사항입니다.")
  return
 } else {
  if ($('.author-input').val().length === 1) {
   alert("1글자 초과로 입력해주세요.")
   return
  }
 }

 if ($('.book-reg-dt').val() === '') {
  bookRegDt = 20000101
 } else {
  if($('.book-reg-dt').val().length !== 8) {
   alert("8글자 형식으로 입력해주셔야합니다\n(Example)19911220")
   return
  } else {
   bookRegDt = $('.book-reg-dt').val()
  }
 }

 if (contentsMadeNatureNo === '') {
  alert("제작국가는 필수 입력사항입니다.")
  return
 }

 if ($('.import-link').val() === '') {
  alert("importLink는 필수 입력사항입니다.")
  return
 }
}

/** Jquery 로딩이 끝난 후를 보장합니다 */
$(function () {

 // form 태그 안에서, submit type의 input 태그를 사용하지 않으면
 // 아래와 같이 따로 구현해주어야 합니다.
 $(".register-text").click(function () {

  validationFormInfo()

  let insertedBookInfoForm = {
   madeNatureNo: Number(contentsMadeNatureNo)
   , title: $('.title-input').val()
   , bookTypeNo: Number(bookTypeNo)
   , author: $('author-input').val()
   , link: $('.import-link').val()
   , bookRegDt: Number(bookRegDt)
  }

  $.ajax({
   url: "./info",
   method: "POST",
   data: JSON.stringify(insertedBookInfoForm),
   contentType: "application/json",
   dataType: 'json',
   processData: false,
   success: function () {
    location.href = './main'
   },
   error: function (error) {
    alert("failed! ", error.toString())
    return
   }
  })
 })
});
