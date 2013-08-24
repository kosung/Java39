<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>부서 상세정보</title>
</head>
<body>
<h1>부서 상세정보</h1>
<table>
<tr><th>부서번호</th> 
<td>${department.no}</td></tr>
<tr><th>부서명</th>
<td>${department.name}</td></tr>
<tr><th>지역</th>
<td>${department.location}</td></tr>
</table>
<a href='updateDept?deptno=${department.no}'>[변경]</a>
<a href='deleteDept?deptno=${department.no}'>[삭제]</a>
<a href='searchDept'>[목록]</a>
</body>
</html>