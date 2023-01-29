// todo: drama 랑 헷갈려서 잘못 표기했었음

let thumbnailImageUrl = ''
let broadcastStateEnum = ''
let contentsMadeNatureNo = ''
let dramaRegDt = ''

function enterInputValue() {
 $(".find-thumbnail-button").click();
}

/** 방영상태를 클릭했을때에 호출되는 메소드입니다. */
function selectBroadcastState(broadcastStateEnumValue) {

 broadcastStateEnum = broadcastStateEnumValue

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

/** 애니 제작 국가 선택 시 호출하는 메소드 */
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

 if (broadcastStateEnum === '') {
  alert("방영상태는 필수 입력사항입니다.")
  return
 }

 if ($('.anime-reg-dt').val() === '') {
  dramaRegDt = 20000101
 } else {
  dramaRegDt = $('.anime-reg-dt').val()
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
 // 등록하기
 $(".register-text").click(function () {

  validationFormInfo()

  let insertedAnimeInfoForm = {
   madeNatureNo: Number(contentsMadeNatureNo)
   , title: $('.title-input').val()
   , author: $('.author-input').val()
   , link: $('.import-link').val()
   , webThumbnailUrl: thumbnailImageUrl
   , finalizedYnEnum: broadcastStateEnum
   , animeBroadcastCnt: Number(broadCastCnt)
   , dramaRegDt: Number(dramaRegDt)
  }

  $.ajax({
   url: "./info",
   method: "POST",
   data: JSON.stringify(insertedAnimeInfoForm),
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

 // 수정하기
 $(".modify-text").click(function () {

  validationFormInfo()

  let updateDramaInfoForm = {
   animeNo: Number($('#anime-no').val())
   , madeNatureNo: Number(contentsMadeNatureNo)
   , title: $('.title-input').val()
   , author: $('.author-input').val()
   , link: $('.import-link').val()
   , webThumbnailUrl: thumbnailImageUrl
   , broadcastStateEnum: broadcastStateEnum
   , animeBroadcastCnt: Number(broadCastCnt)
   , dramaRegDt: Number(dramaRegDt)
  }

  $.ajax({
   url: "../../anime/info",
   method: "PUT",
   data: JSON.stringify(updateDramaInfoForm),
   contentType: "application/json",
   dataType: 'json',
   processData: false,
   success: function () {
    location.href = '../../anime/main'
   },
   error: function (error) {
    alert("failed! ", error)
    return
   }
  })
 })

 // 삭제하기
 $(".delete-text").click(function () {

  let deleteAnimeInfoForm = {
   animeNo: Number($('#anime-no').val())
  }

   $.ajax({
   url: "../../anime/info",
   method: "DELETE",
   data: JSON.stringify(deleteAnimeInfoForm),
   contentType: "application/json",
   dataType: 'json',
   processData: false,
   success: function () {
    location.href = '../../anime/main'
   },
   error: function (error) {
    alert("failed! ", error)
    return
   }
  })
 })
});
