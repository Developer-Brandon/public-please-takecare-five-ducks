let thumbnailImageUrl = ''
let finalizedStateEnum = ''
let contentsMadeNatureNo = ''
let broadCastCnt = ''
let animeRegDt = ''

/** 방영상태를 클릭했을때에 호출되는 메소드입니다. */
function selectFinalizedState(finalizedYnEnum) {

 finalizedStateEnum = finalizedYnEnum

 if (finalizedYnEnum === 'y') {
  $('.finalized-text-y').addClass('font-weight-bold')
  $('.finalized-text-n').removeClass('font-weight-bold')
 } else {
  $('.finalized-text-n').addClass('font-weight-bold');
  $('.finalized-text-y').removeClass('font-weight-bold')
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

 if (finalizedStateEnum === '') {
  alert("방영상태는 필수 입력사항입니다.")
  return
 }

 if ($('.board-cast-cnt-input').val() === '') {
  broadCastCnt = 0
 } else {
  broadCastCnt = $('.board-cast-cnt-input').val()
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
   , finalizedYnEnum: finalizedStateEnum
   , animeBroadcastCnt: Number(broadCastCnt)
   , animeRegDt: Number(animeRegDt)
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

  let updateAnimeInfoForm = {
   animeNo: Number($('#anime-no').val())
   , madeNatureNo: Number(contentsMadeNatureNo)
   , title: $('.title-input').val()
   , author: $('.author-input').val()
   , link: $('.import-link').val()
   , webThumbnailUrl: thumbnailImageUrl
   , finalizedYnEnum: finalizedStateEnum
   , animeBroadcastCnt: Number(broadCastCnt)
   , animeRegDt: Number(animeRegDt)
  }

  $.ajax({
   url: "../../anime/info",
   method: "PUT",
   data: JSON.stringify(updateAnimeInfoForm),
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
