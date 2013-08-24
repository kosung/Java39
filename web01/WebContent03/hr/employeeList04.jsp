<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList,bit.java39.vo.Employee" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><body>
<h1>직원정보(JSTL)</h1>
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
<%
// 방법1.
/*
ArrayList<Employee> employeeList = 
	(ArrayList<Employee>) request.getAttribute("employeeList");
*/
%>
<%-- 방법2. --%>
<%-- 
<jsp:useBean id="employeeList" 
	scope="request" 
	class="java.util.ArrayList<Employee>"/>
--%>
<%-- 방법3. Unified Expression Language(EL) 사용 --%>
<c:forEach var="emp" items="${requestScope.employeeList}">
<tr>
	<td><a href='retrieveEmp?empno=${emp.no}'>${emp.no}</a></td>
	<td>${emp.name}</td>
	<td>${emp.job}</td>
	<td>${emp.salary}</td>
	<td>${emp.departmentName}</td>
	<td>${emp.departmentLocation}</td>
	<td>${emp.managerName}</td>
</tr>
</c:forEach>
</table>
</body></html>














