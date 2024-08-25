<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<body>
    <div id="menu">
        <ul>
            <li><a href="#" class="app-name">널스케쥴링</a></li>
            <li><a href="#">근무표 조회</a>
                <ul>
                    <li><a href="#">나의 근무일정</a></li>
                    <li><a href="#">부서 근무일정</a></li>
                </ul>
            </li>
            <li><a href="#">근무 신청</a>
                <ul>
                    <li><a href="#">희망 근무 신청</a></li>
                    <li><a href="#">근무 변경 신청</a></li>
                </ul>
            </li>
            <li><a href="#">게시판</a>
                <ul>
                    <li><a href="#">공지사항</a></li>
                    <li><a href="#">질의응답</a></li>
                </ul>
            </li>
            <li><a href="#">마이페이지</a>
                <ul>
                    <li><a href="#">나의 정보변경</a></li>
                    <li><a href="#">탈퇴</a></li>
                </ul>
            </li>
        </ul>
        <div class="user-info">
            <ul>
                <a href="#" id="user-name">${sessionScope.userName}</a>
                <a href="#" id="user-grade">${sessionScope.userGrade}</a>
                <button type="button" id="logout-btn">로그아웃</button>
            </ul>
        </div>
    </div>
</body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</html>

