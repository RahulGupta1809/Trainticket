package com.cglia.bookticket;

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

@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		try {

			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from ticketbook where email=?");

			ps.setString(1, email);
			ResultSet resultSet = ps.executeQuery();
			// Generate the HTML response
			pw.println("<!DOCTYPE html>");
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<title>Ticket Confirmation</title>");
			pw.println("<style>");
			pw.println("table {");
			pw.println("  border-collapse: collapse;");
			pw.println("  width: 100%;");
			pw.println("}");
			pw.println("th, td {");
			pw.println("  text-align: left;");
			pw.println("  padding: 8px;");
			pw.println("}");
			pw.println("th {");
			pw.println("  background-color: #4287f5;");
			pw.println("  color: white;");
			pw.println("}");
			pw.println("</style>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println(
					"<h2 style='color: blue; text-align: center;'>Ticket Booked Successfully... Happy Journey!</h2>");
			pw.println("<br>");
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

			while (resultSet.next()) {
				pw.println("<tr>");
				pw.println("<td>" + resultSet.getString(1) + "</td>");
				pw.println("<td>" + resultSet.getLong(2) + "</td>");
				pw.println("<td>" + resultSet.getString(3) + "</td>");
				pw.println("<td>" + resultSet.getString(4) + "</td>");
				pw.println("<td>" + resultSet.getInt(5) + "</td>");
				pw.println("<td>" + resultSet.getString(6) + "</td>");
				pw.println("<td>" + resultSet.getString(7) + "</td>");
				pw.println("<td>" + resultSet.getString(8) + "</td>");
				pw.println("<td>" + resultSet.getString(9) + "</td>");
				pw.println("</tr>");
			}

			pw.println("</table>");
			pw.println("<br>");
			pw.println("<a href='Book Ticket.html'>Back</a>");
			pw.println("</body>");
			pw.println("</html>");
		} catch (Exception ex) {
		}

	}

}
