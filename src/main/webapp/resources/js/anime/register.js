let thumbnailImageUrl
let finalizedStateEnum
let contentsMadeNatureNo
let broadCastCnt
let animeRegDt

/** 썸네일을 찾는(구글로부터) 버튼을 클릭했을때에 호출되는 메소드입니다. */
function selectImageThumbnailFromGoogle() {
 let insertedTitle = $('.title-input').val()

 if (insertedTitle === '') {
  window.alert("애니 제목을 입력해주세요")
  return
 }

 if ($('.thumbnail-preview-list').children().hasClass('.thumbnail')) {
  $('.thumbnail-preview-list').empty();
 }

 $.ajax({
  url: "./search/image/thumbnail",
  data: {animeName: insertedTitle},
  method: "GET",
  dataType: "json"
 })
 .done(function (data) {

  // 만약 rawImageThumbnailVOArrayList의 길이가 0개 초과라면?
  if (data.rawImageThumbnailVOArrayList.length > 0) {

   // 반복문을 실행합니다.
   $.each(data.rawImageThumbnailVOArrayList, function (key, value) {

    // img 돔 태그를 동적으로 생성합니다.
    // id값은 thumbnail1,2,3... 이런식으로, .thumbnail-preview-list안에 부여합니다.
    $('<img class="thumbnail">').attr("id", "thumbnail" + key).appendTo($('.thumbnail-preview-list'));

    // 생성된 돔태그 안에, src 태그 값을 넣습니다.
    $('#thumbnail' + key).attr('src', value.imageUrl)
    $('.thumbnail-section').css('margin-bottom', '15px')
   });
  } else {
   alert('검색 결과가 없습니다!')
  }
 })
 .fail(function (xhr, status, errorThrown) {
  alert(status + "error" + "\n" + errorThrown)
 })
}

/** 썸네일을 선택했을때에 호출되는 메소드입니다. */
function selectImageThumbnail(imageUrl) {
 thumbnailImageUrl = imageUrl
}

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

}

/** Jquery 로딩이 끝난 후를 보장합니다 */
$(function () {

 // form 태그 안에서, submit type의 input 태그를 사용하지 않으면
 // 아래와 같이 따로 구현해주어야 합니다.
 $(".register-text").click(function () {

  if ($('.register-text').val() === '') {
   console.log("썸네일 주소 관련 태그는 추후 개발 예정입니다.")
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

  let insertedAnimeInfoForm = {
   madeNatureNo: contentsMadeNatureNo
   , title: $('.title-input').val()
   , author: $('.author-input').val()
   , link: $('.import-link').val()
   , filePath: thumbnailImageUrl
   , fileName: ''
   , finalizedYnEnum: finalizedStateEnum
   , animeBroadcastCnt: broadCastCnt
   , animeRegDt: animeRegDt
  }

  $.ajax({
   method: "POST",
   data: insertedAnimeInfoForm,
   dataType: 'json',
   processData: false,
   contentType: false,
   success: function () {
    console.log("completed!");
   },
   error: function () {
    alert("failed! ")
   }
  });
 });

});
