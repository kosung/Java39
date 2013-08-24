<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>부서정보 등록폼</title>
</head>
<body>
<h1>부서정보 등록폼</h1>
<form action='addDept.do' method='post'>
부서번호 <input type='text' name='deptno'><br>
부서명 <input type='text' name='dname'><br>
지역 <input type='text' name='loc'><br>
<input type='submit' value='등록'>
<input type='reset' value='취소'>
</form>
</body>
</html>
