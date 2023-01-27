let bookTypeNumber = ''
let serialStateEnum = ''
let contentsMadeNatureNo = ''
let bookRegDt = ''

function enterInputValue() {
 // $(".find-thumbnail-button").click();
}

/** 방영상태를 클릭했을때에 호출되는 메소드입니다. */
function selectSerialState(serialState) {

 serialStateEnum = serialState

 if (serialStateEnum === 'being') {
  $('.being').addClass('font-weight-bold')
  $('.finished').removeClass('font-weight-bold')
  $('.vacation').removeClass('font-weight-bold')
 } else if(serialStateEnum === 'finished') {
  $('.finished').addClass('font-weight-bold')
  $('.being').removeClass('font-weight-bold')
  $('.vacation').removeClass('font-weight-bold')
 } else if(serialStateEnum === 'vacation') {
  $('.vacation').addClass('font-weight-bold')
  $('.finished').removeClass('font-weight-bold')
  $('.being').removeClass('font-weight-bold')
 }
}

/** 만화책 제작 국가 선택 시 호출하는 메소드 */
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

 if ($('.comic-book-reg-dt').val() === '') {
  bookRegDt = 20000101
 } else {
  bookRegDt = $('.comic-book-reg-dt').val()
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

  let updateComicBookInfoForm = {
   bookNo: Number($('#comic-book-no').val())
   , madeNatureNo: Number(contentsMadeNatureNo)
   , title: $('.title-input').val()
   , author: $('.author-input').val()
   , link: $('.import-link').val()
   , comicBookSerialStateEnum: serialStateEnum
   , webThumbnailUrl: $('.thumbnail-input').val()
   , comicBookRegDt: Number(bookRegDt)
  }

  $.ajax({
   url: "../../comic/info",
   method: "PUT",
   data: JSON.stringify(updateComicBookInfoForm),
   contentType: "application/json",
   dataType: 'json',
   processData: false,
   success: function () {
    location.href = '../../comic/main'
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
   bookNo: Number($('#comic-book-no').val())
  }

  $.ajax({
   url: "../../comic/info",
   method: "DELETE",
   data: JSON.stringify(deleteBookInfoForm),
   contentType: "application/json",
   dataType: 'json',
   processData: false,
   success: function () {
    location.href = '../../comic/main'
   },
   error: function (error) {
    alert("failed! ", error)
    return
   }
  })
 })
});
