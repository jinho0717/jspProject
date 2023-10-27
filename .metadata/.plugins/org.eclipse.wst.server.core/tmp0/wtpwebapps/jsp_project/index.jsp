<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${ses.id eq null }">
<form action="/mem/login">
	아이디 : <input type="text" name="id"><br>
	비밀번호 : <input type="text" name="pwd"><br>
	<button type="submit">로그인</button>
</form>
</c:if>
<c:if test="${ses. id eq null }">
<a href="/mem/join"><button>회원가입</button></a>
</c:if>
<div>
<c:if test="${ses.id ne null }">
${ses.id }님 로그인 하셨습니다 <br>
계정 생성일 : ${ses.regdate }<br>
마지막 접속 : ${ses.lastLogin }<br>
<a href="/mem/logout"><button>로그아웃</button></a>
<a href="/brd/register"><button>게시판 글쓰기</button></a>
<a href="/brd/pageList"><button>리스트 전체 보기</button></a>
<a href="/mem/list"><button>회원보기</button></a>
<a href="/mem/modify"><button>회원정보수정</button></a>
<a href="/brd/mylist?writer=${ses.id}"><button>내가 쓴 글 보기</button></a>
</c:if>
</div>


<script type="text/javascript">
const msg_login = `<c:out value="${msg_login}" />`;
if (msg_login ==='0') {
	alert("로그인 정보가 일치 하지 않습니다.");
}
</script>
</body>
</html>