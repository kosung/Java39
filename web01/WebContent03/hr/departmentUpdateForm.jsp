<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>부서정보 변경폼</title>
</head>
<body>
<h1>부서정보 변경</h1>
<form action='updateDept' method='post'>
부서번호 <input type='text' readonly name='deptno' value='${department.no}'><br>
부서명 <input type='text' name='dname' value='${department.name}'><br>
지역 <input type='text' name='loc' value='${department.location}'><br>
<input type='submit' value='변경'>
<a href='searchDept'>[목록]</a>
</form>
</body>
</html>