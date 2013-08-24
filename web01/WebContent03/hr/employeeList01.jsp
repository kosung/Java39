<%@page import="bit.java39.vo.Employee"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><body>
<h1>직원정보(JSP)</h1>
<p><a href='addEmp'>[신규]</a></p>
<table>
<tr>
	<th>사원번호</th>
	<th>사원명</th>
	<th>직무</th>
	<th>급여</th>
	<th>부서명</th>
	<th>지역</th>
	<th>상관명</th>
</tr>
<% // scriptlet = script + let => _jspService() 메서드에 그대로 복사된다.
ArrayList<Employee> list = 
	(ArrayList<Employee>)request.getAttribute("employeeList");

for (Employee emp : list) {
%>
<tr>
	<td><a href='retrieveEmp?empno=<%out.print(emp.getNo());%>'><%out.print(emp.getNo());%></a></td>
	<td><%out.print(emp.getName());%></td>
	<td><%out.print(emp.getJob());%></td>
	<td><%out.print(emp.getSalary());%></td>
	<td><%out.print(emp.getDepartmentName());%></td>
	<td><%out.print(emp.getDepartmentLocation());%></td>
	<td><%out.print(emp.getManagerName());%></td>
</tr>

<%} %>
</table>
</body></html>














