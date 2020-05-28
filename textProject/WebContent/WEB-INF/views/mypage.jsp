<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>My Page</title>
</head>
<body>
<h1> 마이 페이지</h1>
<hr>
<form action="/updateMember" method="post">

		
		아이디 :<input type="text" name="memberId" value="${sessionScope.member.memberId }"readonly><br>
		 비밀번호 :<input type="password" name="memberPw" value="${sessionScope.member.memberPw }"><br> 
		 이름: <input type=:text " name="memberName" vale="${sessionScope.member.memberName }"><br> 
		 나이:<input type="text" value="${sessionScope.member.age }" readonly><br> 
		가입일 : <input type="text" value="${sessionScope.member.enrollDate }" readonly><br>
			
			<input type="submit" value="정보수정">
			 <input type="reset" value="취소">
	
</form>
</body>
</html>