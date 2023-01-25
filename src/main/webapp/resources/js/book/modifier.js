let bookTypeNumber = ''
let contentsMadeNatureNo = ''
let bookRegDt = ''

/** 책종류를 클릭했을때에 호출되는 메소드입니다. */
function selectBookType(bookTypeEnglish, bookTypeNo) {

 bookTypeNumber = bookTypeNo

 $('.'+bookTypeEnglish).addClass('font-weight-bold')
 $('.'+bookTypeEnglish).parents().siblings().children().removeClass('font-weight-bold')
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

 if ($('.thumbnail-input').val() === '') {
  alert("썸네일 주소는 필수 입력 사항입니다.")
  return
 } else {
  thumbnailImageUrl = $('.thumbnail-input').val()
 }

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
  alert("작가이름은 필수 입력 사항입니다.")
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
  bookRegDt = $('.book-reg-dt').val()
 }


 if ($('.anime-reg-dt').val() === '') {
  animeRegDt = 20000101
 } else {
  animeRegDt = $('.anime-reg-dt').val()
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

 // 수정하기
 $(".modify-text").click(function () {

  validationFormInfo()

  let updateBookInfoForm = {
   bookNo: Number($('#book-no').val())
   , madeNatureNo: Number(contentsMadeNatureNo)
   , title: $('.title-input').val()
   , bookTypeNo: Number(bookTypeNumber)
   , author: $('.author-input').val()
   , link: $('.import-link').val()
   , bookRegDt: Number(bookRegDt)
  }

  $.ajax({
   url: "../../book/info",
   method: "PUT",
   data: JSON.stringify(updateBookInfoForm),
   contentType: "application/json",
   dataType: 'json',
   processData: false,
   success: function () {
    location.href = '../../book/main'
   },
   error: function (error) {
    alert("failed! ", error)
    return
   }
  })
 })

 // 삭제하기
 $(".delete-text").click(function () {

  let deleteBookInfoForm = {
   bookNo: Number($('#book-no').val())
  }

   $.ajax({
   url: "../../book/info",
   method: "DELETE",
   data: JSON.stringify(deleteBookInfoForm),
   contentType: "application/json",
   dataType: 'json',
   processData: false,
   success: function () {
    location.href = '../../book/main'
   },
   error: function (error) {
    alert("failed! ", error)
    return
   }
  })
 })
});
