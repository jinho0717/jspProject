<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/mem/update">
		아이디 : <input type="text" name="id" value="${ses.id }" readonly="readonly"><br>
		비밀번호 : <input type="text" name="pwd" placeholder="새로운비밀번호를 입력해주세요"><br>
		이메일 : <input type="text" name="email" placeholder="이메일을 입력해주세요"><br>
		나이 : <input type="text" name="age" placeholder="나이를 입력해주세요"><br>
		<button type="submit">수정</button>
	</form>
	<br>
	<a href="/mem/remove"><button type="button">탈퇴</button></a>
</body>
</html>