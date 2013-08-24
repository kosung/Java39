<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><body>
<h1>부서 정보(JSTL)</h1>
<p><a href='addDept.do'>[신규]</a></p>
<table>
<tr>
	<th>부서번호</th>
	<th>부서명</th>
</tr>
<c:forEach var="dept" items="${requestScope.departmentList}">
<tr>
	<td><a href='retrieveDept.do?deptno=${dept.no}'>${dept.no}</a></td>
	<td>${dept.name}</td>
</tr>
</c:forEach>
</table>
</body></html>














