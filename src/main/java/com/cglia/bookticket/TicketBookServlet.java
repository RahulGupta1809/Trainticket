package com.cglia.bookticket;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cglia.dao.DBConnection;

@SuppressWarnings("serial")
@WebServlet("/ticketBook")
public class TicketBookServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("email");
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		try {
			String name = req.getParameter("name");
			long trainnumber = Long.parseLong(req.getParameter("trainnumber"));
			String startingstation = req.getParameter("startingstation");
			String endingstation = req.getParameter("endingstation");
			String ticketdate = req.getParameter("date");
			String gender = req.getParameter("gender");
			String mobileNo = req.getParameter("mobileNo");
			int age = Integer.parseInt(req.getParameter("age"));

			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"insert into ticketbook(name,trainnumber,startingstation,endingstation,age,date,gender,mobileNo,email) values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setLong(2, trainnumber);
			ps.setString(3, startingstation);
			ps.setString(4, endingstation);
			ps.setInt(5, age);
			ps.setString(6, ticketdate);
			ps.setString(7, gender);
			ps.setString(8, mobileNo);
			ps.setString(9, email);

			int k = ps.executeUpdate();

			if (k > 0) {
				session.setAttribute("email", email);
				pw.println("<html>");
				pw.println("<head>");
				pw.println("<style>");
				pw.println("body {");
				pw.println("  background-color: #f2f2f2;");
				pw.println("  font-family: Arial, sans-serif;");
				pw.println("}");
				pw.println("h2 {");
				pw.println("  text-align: center;");
				pw.println("  color: #804A00;");
				pw.println("}");
				pw.println("table {");
				pw.println("  width: 50%;");
				pw.println("  margin: 20px auto;");
				pw.println("  border: 1px solid white;");
				pw.println("  border-collapse: collapse;");
				pw.println("  background-color: #FFA500;");
				pw.println("}");
				pw.println("th, td {");
				pw.println("  padding: 12px;");
				pw.println("  text-align: center;");
				pw.println("  border-bottom: 1px solid white;");
				pw.println("}");
				pw.println("th {");
				pw.println("  background-color: white;");
				pw.println("  color: #000;");
				pw.println("}");
				pw.println("a {");
				pw.println("  display: inline-block;");
				pw.println("  margin-top: 10px;");
				pw.println("  color: #4287f5;");
				pw.println("  text-decoration: none;");
				pw.println("}");
				pw.println("</style>");
				pw.println("</head>");
				pw.println("<body>");
				pw.println("<h2>Ticket Booked Successfully ... Happy Journey</h2>");
				pw.println("<table>");
				pw.println("<tr>");
				pw.println("<th>Name</th>");
				pw.println("<th>Train Number</th>");
				pw.println("<th>Starting Station</th>");
				pw.println("<th>Ending Station</th>");
				pw.println("<th>Age</th>");
				pw.println("<th>Ticket Date</th>");
				pw.println("<th>Gender</th>");
				pw.println("<th>Mobile Number</th>");
				pw.println("<th>Email</th>");
				pw.println("</tr>");

				pw.println("<tr>");
				pw.println("<td>" + name + "</td>");
				pw.println("<td>" + trainnumber + "</td>");
				pw.println("<td>" + startingstation + "</td>");
				pw.println("<td>" + endingstation + "</td>");
				pw.println("<td>" + age + "</td>");
				pw.println("<td>" + ticketdate + "</td>");
				pw.println("<td>" + gender + "</td>");
				pw.println("<td>" + mobileNo + "</td>");
				pw.println("<td>" + email + "</td>");
				pw.println("</tr>");

				pw.println("</table>");
				pw.println("<br>");
				pw.println("<a href='Book Ticket.html'>Back</a>");
				pw.println("</body>");
				pw.println("</html>");

			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}