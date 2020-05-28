<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입</h1>

	<form action="/join" method="post">
		아이디 :<input type="text" name="memberId"><br> 비밀번호 :<input
			type="password" name="password"><br> 이름: <input
			type=:text " name="memberName"><br> 나이:<input
			type="text" name="age"><br> <input type="submit"
			value="회원가입"> <input type="reset" value="취소">


	</form>



</body>
</html>