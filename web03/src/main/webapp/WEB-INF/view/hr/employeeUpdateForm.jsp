<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>직원정보 변경폼(EL)</title>
</head>
<body>
<h1>직원정보 변경(EL)</h1>
<form action='updateEmp.do' method='post'>
직원번호 <input type='text' readonly name='no' value='${employee.no}'><br>
이름 <input type='text' name='name' value='${employee.name}'><br>
직무 <input type='text' name='job' value='${employee.job}'><br>
관리자 <input type='text' name='managerNo' value='${employee.managerNo}'><br>
입사일 <input type='text' name='hireDate' value='<fmt:formatDate value="${employee.hireDate}" pattern="yyyy-MM-dd"/>'><br>
급여 <input type='text' name='salary' value='${employee.salary}'><br>
상여금 <input type='text' name='commission' value='${employee.commission}'><br>
부서번호 <input type='text' name='departmentNo' value='${employee.departmentNo}'><br>
<input type='submit' value='변경'>
<a href='searchEmp.do'>[목록]</a>
</form>
</body>
</html>