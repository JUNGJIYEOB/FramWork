<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${empty sessionScope.member }">
		<form action="/login">
			아이디 : <input type="text" name="memberId"><br> 비밀번호 : <input
				type="password" name="memberPw"><br> <input
				type="submit" value="로그인"> <input type="reset" value="취소">
			<a href="/joinFrm">회원가입</a>
		</form>

	</c:if>
	<c:if test="${not empty sessionScope.member }">
		<h1>${sessionScope.member.memberName }님</h1>
		<hr>
		<a href="/views/fileuploadTest.jsp">다중파일업로드테스트</a>
		<a href="/boardList?reqPage=1">게시판ㄱ ㄱ</a>
		<br>
		<a href="/delete">회원탈퇴</a>
		<br>
		<a href="/allMemberPage">전체회원조회</a>
		<br>
		<a href="/mypage">마이페이지</a>
		<br>
		<a href="/allMember">전체 회원 조회</a>
		<br>
		<a href="/logout">로그아웃</a>
		<br>

		<hr>
		<h2>MyBatis 동적쿼리</h2>
		<br>
		<a href="/views/dynamicQuery_if.html">1.동적쿼리 if</a>
		<br>
		<a href="/views/dynamicQuery_choose.html">2.동적쿼리 choose</a>
		<br>
		<a href="/views/dynamicQuery_trim.html">3.동적쿼리 trim</a>
		<br>
		<a href="/views/dynamicQuery_foreach.html">4.동적쿼리 foreach</a>



	</c:if>
</body>
</html>