<%@ page 
	language="java" 
	contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList,bit.java39.vo.Employee" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
[
<c:set var="isFirst" value="true"/>
<c:forEach var="emp" items="${requestScope.employeeList}">
<c:choose>
<c:when test="${isFirst == 'false'}">,</c:when>
<c:otherwise><c:set var="isFirst" value="false"/></c:otherwise>
</c:choose>
{
	"no": ${emp.no},
	"name": "${emp.name}",
	"job": "${emp.job}",
	"salary": ${emp.salary},
	"deptName": "${emp.departmentName}",
	"deptLoc": "${emp.departmentLocation}",
	"mgrName": "${emp.managerName}"
}
</c:forEach>
]












