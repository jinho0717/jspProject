<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원정보</h1>
<table border="1">
<tr>
	<th>ID</th>
	<th>PW</th>
	<th>E-MAIL</th>
	<th>AGE</th>
	<th>REG_DATE</th>
	<th>LAST_LOGIN</th>
</tr>
<c:forEach items="${list }" var="mvo">
	<tr>
	<td>${mvo.id }</td>
		<td>${mvo.pwd }</td>
		<td>${mvo.email }</td>
		<td>${mvo.age }</td>
		<td>${mvo.regdate }</td>
		<td>${mvo.lastLogin }</td>
	</tr>
</c:forEach>
</table>
<a href="/index.jsp"><button>인덱스 페이지</button></a>
</body>
</html>