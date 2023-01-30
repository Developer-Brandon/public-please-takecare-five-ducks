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

 if (thumbnailImageUrl === '') {
  alert("썸네일 주소는 필수 입력 사항입니다.")
  return
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
  totalNumberOfEpisode = 0
 } else {
  totalNumberOfEpisode = $('.movie-total-number-of-episode').val()
 }

 if ($('.movie-reg-dt').val() === '') {
  movieRegDt = 20000101
 } else {
  if($('.movie-reg-dt').val().length !== 8) {
   alert("8글자 형식으로 입력해주셔야합니다\n(Example)19911220")
   return
  } else {
   movieRegDt = $('.movie-reg-dt').val()
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

  let insertedMovieInfoForm = {
   madeNatureNo: Number(contentsMadeNatureNo)
   , title: $('.title-input').val()
   , directorName: $('.director-name-input').val()
   , link: $('.import-link').val()
   , totalNumberOfEpisode: Number(totalNumberOfEpisode)
   , webThumbnailUrl: thumbnailImageUrl
   , movieRegDt: Number(movieRegDt)
  }

  $.ajax({
   url: "./info",
   method: "POST",
   data: JSON.stringify(insertedMovieInfoForm),
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

 /** 썸네일을 찾는(구글로부터) 버튼을 클릭했을때에 호출되는 메소드입니다. */
 $(".find-thumbnail-button").click(function () {
  let insertedTitle = $('.title-input').val()

  if (insertedTitle === '') {
   window.alert("영화 제목을 입력해주세요")
   return
  }

  if ($('.thumbnail-preview-list').children().hasClass('.thumbnail')) {
   $('.thumbnail-preview-list').empty();
  }

  $.ajax({
   url: "./search/image/thumbnail",
   data: {movieName: insertedTitle},
   method: "GET",
   // contentType: "application/json",
   dataType: "json",
   // processData: false,
   success: function (data) {

    // 만약 rawImageThumbnailVOArrayList의 길이가 0개 초과라면?
    if (data.rawImageThumbnailVOArrayList.length > 0) {

     // 반복문을 실행합니다.
     $.each(data.rawImageThumbnailVOArrayList, function (key, value) {

      // img 돔 태그를 동적으로 생성합니다.
      // id값은 thumbnail1,2,3... 이런식으로, .thumbnail-preview-list안에 부여합니다.
      $('<img class="thumbnail">').attr("id", "thumbnail" + key).appendTo($('.thumbnail-preview-list'));

      // 생성된 돔태그 안에, src 태그 값을 넣습니다.
      $('#thumbnail' + key)
       .attr('src', value.imageUrl)

      // 클릭 이벤트르르 바인딩해서, 클릭했을 시에 해당 이미지 url을 변수값에 넣습니다.
       .bind('click', function(){

        thumbnailImageUrl = value.imageUrl

        // 해당썸네일의 border를 지정합니다.(선택했다는 표시)
        $('#thumbnail' + key).css('border', '5px ridge red')
        $('#thumbnail' + key).css('opacity', '1')

        $('#thumbnail' + key).siblings().css('border', '')
        $('#thumbnail' + key).siblings().css('opacity', '0.5')

        // 썸네일 url을 input에 value로 꽂아줍니다.
        $('.thumbnail-input').attr('value', thumbnailImageUrl)
       });

      $('.thumbnail-section').css('margin-bottom', '15px')
     });
    } else {
     alert('검색 결과가 없습니다!')
     return
    }
   },
   error: function (error) {
    alert("failed! ", error.toString())
    return
   }
  })
 })
});
