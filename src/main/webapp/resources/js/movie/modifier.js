let thumbnailImageUrl = ''
let contentsMadeNatureNo = ''
let totalNumberOfEpisode = ''
let movieRegDt = ''

function enterInputValue() {
 $(".find-thumbnail-button").click();
}

/** 영화 제작 국가 선택 시 호출하는 메소드 */
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

 if ($('.director-name-input').val() === '') {
  alert("작가이름은 필수 입력 사항입니다.")
  return
 } else {
  if ($('.director-name-input').val().length === 1) {
   alert("1글자 초과로 입력해주세요.")
   return
  }
 }

 if ($('.movie-total-number-of-episode').val() === '') {
  alert("회차는 필수 입력 사항입니다.")
  return
 } else {
  totalNumberOfEpisode = $('.movie-total-number-of-episode').val()
 }

 if ($('.movie-reg-dt').val() === '') {
  movieRegDt = 20000101
 } else {
  movieRegDt = $('.movie-reg-dt').val()
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

  let updateMovieInfoForm = {
   movieNo: Number($('#movie-no').val())
   , madeNatureNo: Number(contentsMadeNatureNo)
   , title: $('.title-input').val()
   , directorName: $('.director-name-input').val()
   , link: $('.import-link').val()
   , totalNumberOfEpisode: totalNumberOfEpisode
   , webThumbnailUrl: thumbnailImageUrl
   , movieRegDt: Number(movieRegDt)
  }

  $.ajax({
   url: "../../movie/info",
   method: "PUT",
   data: JSON.stringify(updateMovieInfoForm),
   contentType: "application/json",
   dataType: 'json',
   processData: false,
   success: function () {
    location.href = '../../movie/main'
   },
   error: function (error) {
    alert("failed! ", error)
    return
   }
  })
 })

 // 삭제하기
 $(".delete-text").click(function () {

  let deleteMovieInfoForm = {
   movieNo: Number($('#movie-no').val())
  }

  $.ajax({
   url: "../../movie/info",
   method: "DELETE",
   data: JSON.stringify(deleteMovieInfoForm),
   contentType: "application/json",
   dataType: 'json',
   processData: false,
   success: function () {
    location.href = '../../movie/main'
   },
   error: function (error) {
    alert("failed! ", error)
    return
   }
  })
 })
});
