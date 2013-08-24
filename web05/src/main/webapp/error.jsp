<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>알림!</title>

</head>
<body>
시스템이 바쁩니다. 잠시 후 다시 시도해 주세요.<br>
만약, 계속 이 문구가 출력된다면 관리자(111-1111)에 문의해 주세요.<br>
<jsp:useBean id="error" type="java.lang.Exception" scope="request"></jsp:useBean>
<%-- 
jsp:useBean 태그
1) id : 객체를 찾을 때 키
2) scope : 객체를 찾을 보관소 지정(ServletContext, HttpSession, ServletRequest, PageContext)
3) class : 리턴되는 객체의 클래스 및 인스턴스 변수의 타입
			만약, 객체를 찾을 수 없다면 새로 빈 객체를 생성한다.
4) type : 인스턴스 변수의 타입.
			- 만약, 객체를 못 찾는다면 그냥 null이다.
		    - 리턴되는 객체의 클래스가 뭔지 상관없이 상위 인터페이스나 
		    상위 클래스의 인스턴스 변수로 받을 때.
		    - 리턴되는 객체를 인터페이스 변수로 받고자 할 때 
--%>
<pre>
<%
//Exception error = (Exception)request.getAttribute("error");
error.printStackTrace(new PrintWriter(out));
%>
</pre>
</body>
</html>

