<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<h1>직원 상세정보(EL)</h1>
<table>
<tr><th>사원번호</th> 
<%-- 객체가 보관된 객체를 지정하지 않으면 다음 순서로 찾는다.
pageScope -> requestScope -> sessionScope -> applicationScope --%>
<td>${employee.no}</td></tr>
<tr><th>이름</th>
<td>${employee.name}</td></tr>
<tr><th>직무</th>
<td>${employee.job}</td></tr>
<tr><th>관리자</th>
<td>${employee.managerNo}</td></tr>
<tr><th>입사일</th>
<td>${employee.hireDate}</td></tr>
<tr><th>급여</th>
<td>${employee.salary}</td></tr>
<tr><th>상여금</th>
<td>${employee.commission}</td></tr>
<tr><th>부서번호</th>
<td>${employee.departmentNo}</td></tr>
</table>
<a href='updateEmp?empno=${employee.no}'>[변경]</a>
<a href='deleteEmp?empno=${employee.no}'>[삭제]</a>
<a href='searchEmp'>[목록]</a>

</body>
</html>