## [Project - Spring Framework] Automatic Shift Scheduling Program
#### [Placeholder Logo]
<img src="https://github.com/user-attachments/assets/a344cc44-d8ae-44de-bf60-5c0388844fdc" alt="logo" width="200">  

</br></br>

## 🔎 프로젝트 소개
#### 교대근무자들에게 최적의 근무일정을 제공하고, 적절한 근무일정을 효율적으로 생성하기 위한 프로그램  

일부 회사에서는 관리자 또는 시니어 직원이 전체 부서원들의 교대근무 일정을 직접 작성하고 있습니다.  
사람이 직접 고민하여 작성한 근무일정은 모든 부서원의 요구사항을 반영하지 못하여 반복적인 수정이 이루어집니다.  
이로 인해, 근무일정 관리자가 해당 작업에 할애하는 시간과 스트레스가 상당히 높은 상황입니다.

기본적인 근무일정 규칙과 부서원들의 다양한 요구사항을 반영하여 근무일정을 자동 생성하는 프로그램을 통하여 근무일정 생성 작업의 효율성 및 직원 만족도를 높이고자 합니다.

</br>

## 🏃‍♂️ 프로젝트 기간
- 전체 개발기간: 2024.07.08 - 현재 (진행 중)

</br>

## 📁 ERD
### 9 Tables
#### 📌 전체 구조
<img width="772" alt="ERD_전체구조" src="https://github.com/user-attachments/assets/bd1ceeb6-cb15-4cb0-a67f-e497cda82264">

</br>

#### 📌 상세 구조
<img alt="ERD_상세구조" src="https://github.com/user-attachments/assets/0a2b34f4-168e-45da-9f58-00d2d7880cf4">

</br>

## ⚙ 기술 스택  
<div align=center>
  <img src="https://img.shields.io/badge/java-FF7800?style=for-the-badge&logo=OpenJDK&logoColor=white">
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
  <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white">
  <img src="https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=JUnit5&logoColor=white">
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <br>
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/mybatis-EF2D5E?style=for-the-badge&logo=mybatis&logoColor=white">
    <img src="https://img.shields.io/badge/apachetomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
</div> 

</br>

## 🔮 주요 기능
* 회원가입
* 로그인/로그아웃
* 근무일정 조회
* 게시판
  - 공지사항
  - 근무일정 신청
  - 근무일정 변경
* 관리자
  - 근무일정 생성
  - 근무일정 설정

</br>

## 🏗 Project Structure
```
schd
├── README.md
├── pom.xml
├── schd.iml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── schedule
│   │   │           ├── common
│   │   │           │   ├── exception
│   │   │           │   │   └── GlobalExceptionHandler.java
│   │   │           │   ├── message
│   │   │           │   └── validation
│   │   │           │       ├── ValidationGroups.java
│   │   │           │       └── ValidationSequence.java
│   │   │           ├── config
│   │   │           │   └── SecurityConfig.java
│   │   │           ├── controller
│   │   │           │   ├── FindIdPwdController.java
│   │   │           │   ├── LoginController.java
│   │   │           │   └── SignUpController.java
│   │   │           ├── dao
│   │   │           │   ├── StaffDao.java
│   │   │           │   └── StaffDaoImpl.java
│   │   │           ├── dto
│   │   │           │   ├── BoardDto.java
│   │   │           │   ├── CommonCodeDto.java
│   │   │           │   ├── CommonCodeTypeDto.java
│   │   │           │   ├── DutyExchReqDto.java
│   │   │           │   ├── DutyExchReqHistDto.java
│   │   │           │   ├── ShiftSchdArchDto.java
│   │   │           │   ├── ShiftSchdDto.java
│   │   │           │   ├── StaffDto.java
│   │   │           │   └── StaffInfoChgHistDto.java
│   │   │           └── service
│   │   │               ├── StaffService.java
│   │   │               ├── StaffServiceImpl.java
│   │   │               └── mail
│   │   │                   ├── MailHandler.java
│   │   │                   ├── MailSender.java
│   │   │                   ├── MailService.java
│   │   │                   ├── MockMailSender.java
│   │   │                   └── TempKey.java
│   │   ├── resources
│   │   │   ├── application.properties
│   │   │   ├── mapper
│   │   │   │   ├── StaffInfoChgHistMapper.xml
│   │   │   │   └── StaffMapper.xml
│   │   │   └── mybatis-config.xml
│   │   └── webapp
│   │       ├── WEB-INF
│   │       │   ├── spring
│   │       │   │   ├── appServlet
│   │       │   │   │   └── servlet-context.xml
│   │       │   │   └── root-context.xml
│   │       │   ├── views
│   │       │   │   ├── findIdPwd.jsp
│   │       │   │   ├── identityVerification.jsp
│   │       │   │   ├── index.jsp
│   │       │   │   ├── login.jsp
│   │       │   │   ├── main.jsp
│   │       │   │   ├── modifyPwd.jsp
│   │       │   │   └── register.jsp
│   │       │   └── web.xml
│   │       └── resources
│   │           ├── css
│   │           │   ├── findIdPwd.css
│   │           │   ├── identityVerification.css
│   │           │   ├── index.css
│   │           │   ├── login.css
│   │           │   ├── main.css
│   │           │   ├── modifyPwd.css
│   │           │   └── register.css
│   │           ├── img
│   │           └── js
│   │               ├── findIdPwd.js
│   │               ├── identityVerification.js
│   │               ├── login.js
│   │               ├── main.js
│   │               └── register.js
│   └── test
│       └── java
│           └── com
│               └── schedule
│                   ├── dao
│                   │   └── StaffDaoTest.java
│                   └── service
│                       └── StaffServiceImplTest.java
```

</br>

## 📄 Commit Message Convention

#### 규칙
```
- 제목 첫글자는 대문자 사용
- 제목 마침표 미포함
- 제목과 본문은 빈 행으로 구분
- 제목 명령문으로 작성
- 제목 50자 이내로 간결하게 작성
```

#### 유형
```
Feat        :     새로운 기능 추가
Test        :     테스트 코드 완료
Docs        :     문서 추가 또는 수정 (ex. README 변경)
Chore       :     패키지 매니저(ex. gitignore 수정), 빌드 업무 수정 
Refactor    :     리팩토링, 코드 개선
Fix         :     버그 수정
Style       :     코드 스타일 변경 (포맷팅, 세미콜론 누락 등 코드 변경이 없는 경우)
Comment     :     주석 추가 및 수정
Rename      :     파일 또는 폴더명을 수정하거나 이동하는 작업만 수행한 경우
Remove      :     파일을 삭제하는 작업만 수행한 경우
Conflict    :     합병 시 발생한 충돌 수정
```
