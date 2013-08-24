<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList,bit.java39.vo.Employee" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><body>
<h1>직원정보(JSP 태그: useBean)</h1>
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
<jsp:useBean id="employeeList" scope="request" type="ArrayList<Employee>"></jsp:useBean>
<% // scriptlet = script + let => _jspService() 메서드에 그대로 복사된다.
//ArrayList<Employee> employeeList = 
//	(ArrayList<Employee>)request.getAttribute("employeeList");

for (Employee emp : employeeList) {
%>
<tr>
	<td><a href='retrieveEmp?empno=<%=emp.getNo()%>'><%=emp.getNo()%></a></td>
	<td><%=emp.getName()%></td>
	<td><%=emp.getJob()%></td>
	<td><%=emp.getSalary()%></td>
	<td><%=emp.getDepartmentName()%></td>
	<td><%=emp.getDepartmentLocation()%></td>
	<td><%=emp.getManagerName()%></td>
</tr>

<%} %>
</table>
</body></html>














