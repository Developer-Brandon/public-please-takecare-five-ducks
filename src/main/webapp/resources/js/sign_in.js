let checkAuthLoginOrNot = false;
let emailValue;
let passwordValue;

function init() {

 // TODO: 추후 쿠키에 따라서 initializing 처리할 예정입니다.
 // 만약 쿠키에 저장되어있는 자동로그인 체크 값이 true면, 체크 되어있는 상태로, 체크 값이 false면 체크 되어있지 않은 상태로 값을 설정합니다.
 // 만약 자동 로그인 처리가 되어있다면 즉시 로그인 할 수 있도록 처리해야합니다.
 if(checkAuthLoginOrNot) {
  $('.uncheck-auto-login').hide()
  $('.check-auto-login').show()
  checkAuthLoginOrNot = true
 } else {
  $('.uncheck-auto-login').show()
  $('.check-auto-login').hide()
  checkAuthLoginOrNot = false
 }

 $('.validation').hide();
}

function validationForm() {
 if($('.email-input').val() === '') {
   alert('이메일은 필수 입력 값입니다.')
  return
 } else {
  emailValue = $('.email-input').val()
 }

 if($('.password-input').val() === '') {
  alert('비밀번호는 필수 입력 값입니다.')
  return
 } else {
  passwordValue = $('.password-input').val()
 }
}

$(function () {

 init()

 // 자동로그인 element 클릭 시
 $('.auto-login-section').click(function () {

  if(checkAuthLoginOrNot) {
   $('.uncheck-auto-login').css({"display": 'inline-block'})
   $('.check-auto-login').css({"display": 'none'})
   checkAuthLoginOrNot = false
  } else {
   $('.uncheck-auto-login').css({"display": 'none'})
   $('.check-auto-login').css({"display": 'inline-block'})
   checkAuthLoginOrNot = true
  }
 })

 // 로그인 버튼
 $('.login-button').click(function() {
  validationForm()

  // TODO: session 관련 처리 후 로그인 체크 예정입니다.
 })

 // 회원가입 이벤트
 $('.join-wording').click(function() {
  location.href = './sign-up'
 })
})
