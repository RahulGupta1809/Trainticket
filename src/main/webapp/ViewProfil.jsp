<%@ page import="java.sql.*,java.util.*,java.io.*,com.cglia.dao.*"%>
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
String email=(String)session.getAttribute("email");

	Connection con = DBConnection.getConnection();

	PreparedStatement ps = con.prepareStatement("select * from users where email=?");
	ps.setString(1, email);
	ResultSet ub = ps.executeQuery();
   String name="";
   String password="";
   String number="";
   int age=0;
	if (ub.next()) { 
		 name=ub.getString(1);
		 email=ub.getString(2);
		 password=ub.getString(3);
		 age=ub.getInt(4);
		 number=ub.getString(5);
	}
	session.setAttribute("email", email);
	%>
	<table>
	<tr>
	<th>Name</th>
	<th>Email</th>
	<th>Password</th>
	<th>Age</th>
	<th>Number</th>
	</tr>
	<tr>
	<td><%=name %></td>
	<td><%=email %></td>
	<td><%=password %></td>
	<td><%=age %></td>
	<td><%=number %></td>
	</tr>
	</table>
</body>
</html>
