let profilePicValue
let emailValue
let passwordValue
let userNameValue

function init() {

 $('.validation').hide();
}

function validationForm() {

 // todo: 프로필 사진 검사 로직 넣기

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

 if($('.username-input').val() === '') {
  alert('유저이름은 필수 입력 값입니다.')
  return
 } else {
  userNameValue = $('.password-input').val()
 }
}

$(function () {

 init()

 // 회원가입 버튼
 $('.sign-up-button').click(function() {
  validationForm()

  // todo: 추후 회원가입 api 개발 후 연동 예정
 })
})
