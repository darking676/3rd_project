<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<%
	String memId = request.getParameter("memId");

	StringBuffer str = new StringBuffer();
	str.append("<?xml version='1.0' encoding='UTF-8'?>");
	str.append("<root>");
	
	if( !(memId.equals("") || memId.equals("")) ) {
		str.append("true");
	} else {
		str.append("false");
	}
	str.append("<id>"+memId+"</id>");
	str.append("</root>");
	
	response.setContentType("text/xml; charset=UTF-8");
	response.getWriter().write(str.toString());
%>
</body>
</html>