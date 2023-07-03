package com.cglia.search;

import java.io.*;


import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
@WebServlet("/search")

public class SearchServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		SearchBean sb = new SearchDAO().searchtrain(req);
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");

		if (sb == null) {
			pw.print("<h2 style=text-align:center;color:red;> Invalid Train Number..  </h2> ");
			RequestDispatcher rd = req.getRequestDispatcher("Search Train.html");
			rd.include(req, res);

		} else {

			pw.print("<h2>Train Details</h2>");
			pw.println("<table style='width: 100%; border: 2px solid #ccc; border-collapse: collapse; background-color: #f2f2f2;'>");
			pw.println("<tr>");
			pw.println("<th style='border: 1px solid #ccc; background-color: #4287f5; color: #fff;'>Train Number</th>");
			pw.println("<th style='border: 1px solid #ccc; background-color: #4287f5; color: #fff;'>Train Name</th>");
			pw.println("<th style='border: 1px solid #ccc; background-color: #4287f5; color: #fff;'>Starting Station</th>");
			pw.println("<th style='border: 1px solid #ccc; background-color: #4287f5; color: #fff;'>Ending Station</th>");
			pw.println("<th style='border: 1px solid #ccc; background-color: #4287f5; color: #fff;'>Start Time</th>");
			pw.println("<th style='border: 1px solid #ccc; background-color: #4287f5; color: #fff;'>End Time</th>");
			pw.println("<th style='border: 1px solid #ccc; background-color: #4287f5; color: #fff;'>Duration</th>");
			pw.println("</tr>");

			pw.println("<tr>");
			pw.println("<td style='border: 1px solid #ccc; text-align: center;'>" + sb.getTrainnumber() + "</td>");
			pw.println("<td style='border: 1px solid #ccc; text-align: center;'>" + sb.getTrainname() + "</td>");
			pw.println("<td style='border: 1px solid #ccc; text-align: center;'>" + sb.getStartingstation() + "</td>");
			pw.println("<td style='border: 1px solid #ccc; text-align: center;'>" + sb.getEndingstation() + "</td>");
			pw.println("<td style='border: 1px solid #ccc; text-align: center;'>" + sb.getStarttime() + "</td>");
			pw.println("<td style='border: 1px solid #ccc; text-align: center;'>" + sb.getEndtime() + "</td>");
			pw.println("<td style='border: 1px solid #ccc; text-align: center;'>" + sb.getDuration() + "</td>");
			pw.println("</tr>");

			pw.println("</table>");

			

		}
	}

}
