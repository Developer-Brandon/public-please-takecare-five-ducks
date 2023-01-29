let thumbnailImageUrl = ''
let broadCastStateEnum = ''
let contentsMadeNatureNo = ''
let broadCastCnt = ''
let dramaRegDt = ''

function enterInputValue() {
 $(".find-thumbnail-button").click();
}

/** 방영상태를 클릭했을때에 호출되는 메소드입니다. */
function selectBroadcastState(broadCastStateEnumValue) {

 broadCastStateEnum = broadCastStateEnumValue

 if (broadCastStateEnum === 'end') {
  $('.end').addClass('font-weight-bold')
  $('.yet').removeClass('font-weight-bold')
  $('.early_end').removeClass('font-weight-bold')
 } else if(broadCastStateEnum === 'yet') {
  $('.end').removeClass('font-weight-bold')
  $('.yet').addClass('font-weight-bold')
  $('.early_end').removeClass('font-weight-bold')
 } else {
  $('.end').removeClass('font-weight-bold')
  $('.yet').removeClass('font-weight-bold')
  $('.early_end').addClass('font-weight-bold')
 }
}

/** 드라마 제작 국가 선택 시 호출하는 메소드 */
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

 if (broadCastStateEnum === '') {
  alert("방영상태는 필수 입력사항입니다.")
  return
 }

 if ($('.drama-reg-dt').val() === '') {
  dramaRegDt = 20000101
 } else {
  dramaRegDt = $('.drama-reg-dt').val()
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
 // 수정하기
 $(".modify-text").click(function () {

  validationFormInfo()

  let updateDramaInfoForm = {
   dramaNo: Number($('#drama-no').val())
   , madeNatureNo: Number(contentsMadeNatureNo)
   , title: $('.title-input').val()
   , author: $('.author-input').val()
   , link: $('.import-link').val()
   , webThumbnailUrl: thumbnailImageUrl
   , broadcastStateEnum: broadCastStateEnum
   , dramaRegDt: Number(dramaRegDt)
  }

  $.ajax({
   url: "../../drama/info",
   method: "PUT",
   data: JSON.stringify(updateDramaInfoForm),
   contentType: "application/json",
   dataType: 'json',
   processData: false,
   success: function () {
    location.href = '../../drama/main'
   },
   error: function (error) {
    alert("failed! ", error)
    return
   }
  })
 })

 // 삭제하기
 $(".delete-text").click(function () {

  let deleteDramaInfoForm = {
   dramaNo: Number($('#drama-no').val())
  }

   $.ajax({
   url: "../../drama/info",
   method: "DELETE",
   data: JSON.stringify(deleteDramaInfoForm),
   contentType: "application/json",
   dataType: 'json',
   processData: false,
   success: function () {
    location.href = '../../drama/main'
   },
   error: function (error) {
    alert("failed! ", error)
    return
   }
  })
 })
});
