package com.cglia.profile;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cglia.dao.DBConnection;

@SuppressWarnings("serial")
@WebServlet("/viewprofile")

public class ViewProfileServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("email");
		try {
			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement("select * from users where email=?");

			ps.setString(1, email);

			ResultSet ub = ps.executeQuery();

			if (ub.next()) {
				pw.println("<html>");
				pw.println("<head>");
				pw.println("<style>");
				pw.println("body {");
				pw.println("  background-image: url('background.jpg');");
				pw.println("  background-size: cover;");
				pw.println("  font-family: Arial, sans-serif;");
				pw.println("  color: #fff;");
				pw.println("}");
				pw.println("h2 {");
				pw.println("  text-align: center;");
				pw.println("}");
				pw.println("table {");
				pw.println("  width: 50%;");
				pw.println("  margin: 20px auto;");
				pw.println("  border: 1px solid #ccc;");
				pw.println("  border-collapse: collapse;");
				pw.println("  background-color: #333;");
				pw.println("}");
				pw.println("th, td {");
				pw.println("  padding: 12px;");
				pw.println("  text-align: center;");
				pw.println("  border-bottom: 1px solid #ccc;");
				pw.println("}");
				pw.println("th {");
				pw.println("  background-color: #f57c00;");
				pw.println("}");
				pw.println("</style>");
				pw.println("</head>");
				pw.println("<body>");
				pw.println("<h2>Profile</h2>");
				pw.println("<table>");
				pw.println("<tr>");
				pw.println("<th>Name</th>");
				pw.println("<th>Email</th>");
				pw.println("<th>Password</th>");
				pw.println("<th>Age</th>");
				pw.println("<th>MobileNo</th>");
				pw.println("</tr>");

				pw.println("<tr>");
				pw.println("<td>" + ub.getString(1) + "</td>");
				pw.println("<td>" + ub.getString(2) + "</td>");
				pw.println("<td>" + ub.getString(3) + "</td>");
				pw.println("<td>" + ub.getInt(4) + "</td>");
				pw.println("<td>" + ub.getString(5) + "</td>");
				pw.println("</tr>");

				pw.println("</table>");
				pw.println("<br>");
				pw.println("<a href='Home.html'>Back</a>");
				pw.println("</body>");
				pw.println("</html>");

			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
