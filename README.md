## 로컬에서 돌려보시길 원하시는 경우 아래의 글들을 읽어주세요.

### 1. MySQL이 설치되어 있지 않으신 경우는 아래의 노션 페이지에서 설치법을 살펴봐 주세요.

> https://pond-play-786.notion.site/MySQL-d7b37429823b41a9bc89805fa627dc92

### 2. 그 후 계정 생성을 위해 아래의 명령어를 MySQL에서 실행시켜 주세요.

> create user 'testUser'@'localhost' identified by '1111';

> grant all privileges on *.* to 'testUser'@'localhost';

### 3. 그 후 스키마를 생성해주세요.

> create schema if not exists plz_tc_fd;

### 4. 아래와 같은 사진이 나오면 빌드에 성공한 것입니다.

> <img src="https://dk-projects-images.s3.ap-northeast-2.amazonaws.com/%EC%9D%B4%EB%AF%B8%EC%A7%80+391.png"  width="500" height="250">

### 5. 회원가입과 로그인쪽 기능을 개발 중이여서 아무 문자를 입력하시고 로그인 버튼을 누르셔서 구경해주셔도 됩니다.


### 6. 아무 문자를 입력하시고 로그인 버튼을 누르면 아래와 같은 화면으로 이동하시게 됩니다.

> <img src="https://dk-projects-images.s3.ap-northeast-2.amazonaws.com/%EC%98%A4%EB%8D%95%EC%9D%84_%EB%B6%80%ED%83%81%ED%95%B4_%EB%A1%9C%EA%B7%B8%EC%9D%B8_%ED%9B%84.png" width="500" height="250">
