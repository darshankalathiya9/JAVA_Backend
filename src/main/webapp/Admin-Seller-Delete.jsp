<%@page import="Dao.SellerDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	int ID = Integer.parseInt(request.getParameter("ID"));
	SellerDao.deleteSeller(ID);
	response.sendRedirect("Admin-Seller-List.jsp");
	%>

</body>
</html>