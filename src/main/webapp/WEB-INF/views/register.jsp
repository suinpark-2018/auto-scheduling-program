<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/register.css">
<body>
    <div class="wrapper">
    <div class="header"><p>회원가입</p></div>
        <form action="/register/info" method="post">
            <table class="input-group">
                <tr>
                    <td>아이디</td>
                    <td>
                        <input type="text" id="id" name="id" oninput="checkIdFormat(this.value)" placeholder="아이디를 입력해주세요.">
                        <p id="id-msg"></p>
                        <p id="id-error-msg"></p>
                    </td>
                    <td>
                        <button type="button" id="checkDuplicateBtn">중복확인</button>
                    </td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td>
                        <input type="password" id="pwd1" name="pwd" oninput="checkPwdLength()" placeholder="비밀번호를 입력해주세요.(영문 대문자 또는 특수문자 포함)">
                        <p id="pwd-length-error-msg"></p>
                        <input type="password" id="pwd2" name="pwd" oninput="checkPwdMatch()" placeholder="비밀번호를 다시 입력해주세요.">
                        <p id="match-error-msg"></p>
                    </td>
                </tr>
                <tr>
                    <td>이름</td>
                    <td>
                        <input type="text" name="name" oninput="checkNameFormat(this.value)" placeholder="이름을 입력해주세요.">
                        <p id="name-error-msg"></p>
                    </td>
                </tr>
                <tr>
                    <td>성별</td>
                    <td>
                        <label><input type="radio" name="gender" value="M">남자</label>
                        <label><input type="radio" name="gender" value="F">여자</label>
                    </td>
                </tr>
                <tr>
                    <td>생년월일</td>
                    <td><input type="date" name="birth"></td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td><input type="text" name="address"></td>
                </tr>
                <tr>
                    <td>E-mail</td>
                    <td>
                        <input type="email" name="email" oninput="checkEmailFormat(this.value)">
                        <p id="email-error-msg"></p>
                    </td>
                </tr>
                <tr>
                    <td>휴대폰번호</td>
                    <td>
                        <input type="text" name="phone_num" oninput="restrictToPhoneNumbers(this.value)">
                        <p id="mobile-number-error-msg"></p>
                    </td>
                </tr>
                <tr>
                    <td>소속 회사</td>
                    <td>
                        <label><input type="radio" name="corp_code" value="CO01">아산병원</label>
                        <label><input type="radio" name="corp_code" value="CO02">삼성병원</label>
                    </td>
                </tr>
                <tr>
                    <td>소속 부서</td>
                    <td>
                        <label><input type="radio" name="depart_code" value="DE01">내과</label>
                        <label><input type="radio" name="depart_code" value="DE02">외과</label>
                        <label><input type="radio" name="depart_code" value="DE03">응급실</label>
                        <label><input type="radio" name="depart_code" value="DE04">중환자실</label>
                    </td>
                </tr>
                <tr>
                    <td>입사년도</td>
                    <td><input type="date" name="hire_date"></td>
                </tr>
                <tr>
                    <td>직급</td>
                    <td>
                        <label><input type="radio" name="grade" value="staff">Staff</label>
                        <label><input type="radio" name="grade" value="charge">Charge</label>
                        <label><input type="radio" name="grade" value="head">Head</label>
                    </td>
                </tr>
                <tr>
                    <td>현 근무 상태</td>
                    <td>
                        <label><input type="radio" name="work_state_code" value="WS0001">재직</label>
                        <label><input type="radio" name="work_state_code" value="WS0101">연차휴가</label>
                        <label><input type="radio" name="work_state_code" value="WS0102">청원휴가</label>
                        <label><input type="radio" name="work_state_code" value="WS0103">출산휴가</label>
                        <label><input type="radio" name="work_state_code" value="WS0104">가족돌봄휴가</label><br>
                        <label><input type="radio" name="work_state_code" value="WS0201">병가</label>
                        <label><input type="radio" name="work_state_code" value="WS0202">육아휴직</label>
                        <label><input type="radio" name="work_state_code" value="WS0203">질병휴직</label>
                        <label><input type="radio" name="work_state_code" value="WS0204">자기개발휴직</label>
                    </td>
                </tr>
                <tr>
                    <td>임신여부</td>
                    <td>
                        <label><input type="radio" name="preg_state" value="Y">예</label>
                        <label><input type="radio" name="preg_state" value="N">아니오</label>
                    </td>
                </tr>
            </table>
            <input type="hidden" name="is_active" value="Y">
            <input type="hidden" name="work_years" value="0">

            <div class="button-group">
                <button type="button" onclick="location.href='/'">Cancel</button>
                <button type="submit" id="sendUserInfoBtn">Submit</button>
            </div>
        </form>
    </div>
</body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/register.js"></script>
</html>
