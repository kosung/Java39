<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주문관리시스템</title>
</head>
<body>
<p>${customer.name}님 환영합니다! <a href='auth/logout.do'>로그아웃</a></p>
<a href='hr/searchEmp.do'>직원관리</a><br>
<a href='hr/mvc/Employee.html'>직원관리(by MVC)</a><br>
우편번호관리<br>
고객관리<br>
제품관리<br>
주문관리<br>
</body>
</html>