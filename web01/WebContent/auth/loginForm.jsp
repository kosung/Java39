<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 폼</title>
<style type="text/css">
#form {
	width: 250px; 
	height: 150px; 
	border: 2px gray solid;
	margin: 100px auto 0px auto;
}
</style>
</head>
<body style='width: 100%;'>
<div id='form'>
<form action='login.do' method='post'>
아이디 <input type='text' name='id' value='${id}'><br>
암호 <input type='password' name='pwd'><br>
<input type='checkbox' name='save'>아이디 저장<br>
<input type='submit' value='로그인'> 
</form>
</div>

</body>
</html>







