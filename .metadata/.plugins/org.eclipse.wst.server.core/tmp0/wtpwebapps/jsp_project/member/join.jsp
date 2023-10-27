<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원가입</h1>
<br>
<div>
<form action="/mem/register" method="post">
	아이디 : <input type="text" name="id"><br>
	비밀번호 : <input type="text" name="pwd"><br>
	이메일 : <input type="text" name="email"><br>
	나이 : <input type="text" name="age"><br>
	<button type="submit">가입</button>
</form>
</div>
</body>
</html>