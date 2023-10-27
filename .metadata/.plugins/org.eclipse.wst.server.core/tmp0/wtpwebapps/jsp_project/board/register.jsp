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
<div>
<form action="/brd/insert" method="post" enctype="multipart/form-data">
	TITLE : <input type="text" name="title"><br>
	WRITER : <input type="text" name="writer" value="${ses.id }" readonly="readonly"><br>
	CONTENT : <textarea rows="3" cols="30" name="content"></textarea><br>
	IMAGE_FILE : <input type="file" name="image_file"
			accept="image/png, image/jpg, image/jpeg, image/gif"><br>
<button type="submit">등록하기</button>			
</form>
</div>
</body>
</html>