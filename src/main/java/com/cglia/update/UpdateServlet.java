package com.cglia.update;

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.cglia.dao.DBConnection;

@SuppressWarnings("serial")
@WebServlet("/update")

public class UpdateServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("email");
		int age = Integer.parseInt(req.getParameter("age"));
		String number = req.getParameter("mobileNo");
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("Update users set age=?, mobileNo=? where email=? ");
			ps.setInt(1, age);
			ps.setString(2, number);
			ps.setString(3, email);
			int k = ps.executeUpdate();

			if (k > 0) {
				pw.println("<h2 style=color:red;text-align:center;> Profile Update Successfully...</h2><br>");

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
