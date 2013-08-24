<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>직원정보 등록폼(JSP)</title>
</head>
<body>
<h1>직원정보 등록폼(JSP)</h1>
<form action='addEmp' method='post'>
직원번호 <input type='text' name='empno'><br>
이름 <input type='text' name='ename'><br>
직무 <input type='text' name='job'><br>
관리자 <input type='text' name='mgr'><br>
입사일 <input type='text' name='hiredate'><br>
급여 <input type='text' name='sal'><br>
상여금 <input type='text' name='comm'><br>
부서번호 <input type='text' name='deptno'><br>
<input type='submit' value='등록'>
<input type='reset' value='취소'>
</form>
</body>
</html>
