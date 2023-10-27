<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>수정화면</h1>
<form action="/brd/edit" method="post" enctype="multipart/form-data">
<table border="1">
<tr>
	<th>BNO</th>
	<td><input type="text" name="bno" value="${bvo.bno }" readonly="readonly"></td>
</tr>
<tr>
	<th>TITLE</th>
	<td><input type="text" name="title" value="${bvo.title }"></td>
</tr>
<tr>
	<th>WRITER</th>
	<td>${bvo.title }</td>
</tr>
<tr>
	<th>REG_DATE</th>
	<td>${bvo.regdate }</td>
</tr>
<tr>
	<th>CONTENT</th>
	<td><textarea rows="3" cols="30" name="content">${bvo.content }</textarea></td>
</tr>
<tr>
	<th>IMAGE_FILE</th>
	<td>
		<input type="hidden" name="image_file" value="${bvo.image_File }">
		<input type="file" name="new_file" accept="image/png, image/jpg, image/gif">
	</td>
</tr>
</table>
<button type="submit">수정</button>
</form>
</body>
</html>