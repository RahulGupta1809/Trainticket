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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cglia.dao.DBConnection;

@SuppressWarnings("serial")
@WebServlet("/viewprofile")
public class ViewProfileServlet extends HttpServlet {

    private static final Logger logger =
            LoggerFactory.getLogger(ViewProfileServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        logger.info("ViewProfileServlet request started");

        PrintWriter pw = res.getWriter();
        HttpSession session = req.getSession(false);

        if (session == null) {
            logger.warn("Session expired or not found");
            res.sendRedirect("login.html");
            return;
        }

        String email = (String) session.getAttribute("email");
        logger.debug("Email fetched from session: {}", email);

        if (email == null) {
            logger.warn("Email not found in session");
            res.sendRedirect("login.html");
            return;
        }

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select * from users where email=?")) {

            logger.info("Database connection established");

            ps.setString(1, email);
            logger.debug("Executing query for email: {}", email);

            ResultSet ub = ps.executeQuery();

            if (ub.next()) {
                logger.info("User profile found for email: {}", email);

                pw.println("<html>");
                pw.println("<head>");
                pw.println("<style>");
                pw.println("body { background-image: url('background.jpg'); background-size: cover; font-family: Arial; color: #fff; }");
                pw.println("h2 { text-align: center; }");
                pw.println("table { width: 50%; margin: 20px auto; border-collapse: collapse; background-color: #333; }");
                pw.println("th, td { padding: 12px; text-align: center; border-bottom: 1px solid #ccc; }");
                pw.println("th { background-color: #f57c00; }");
                pw.println("</style>");
                pw.println("</head>");
                pw.println("<body>");
                pw.println("<h2>Profile</h2>");
                pw.println("<table>");
                pw.println("<tr><th>Name</th><th>Email</th><th>Password</th><th>Age</th><th>MobileNo</th></tr>");
                pw.println("<tr>");
                pw.println("<td>" + ub.getString(1) + "</td>");
                pw.println("<td>" + ub.getString(2) + "</td>");
                pw.println("<td>" + ub.getString(3) + "</td>");
                pw.println("<td>" + ub.getInt(4) + "</td>");
                pw.println("<td>" + ub.getString(5) + "</td>");
                pw.println("</tr>");
                pw.println("</table>");
                pw.println("<br><a href='Home.html'>Back</a>");
                pw.println("</body></html>");

            } else {
                logger.warn("No user found for email: {}", email);
                pw.println("<h3>No profile found</h3>");
            }

        } catch (Exception e) {
            logger.error("Exception occurred while fetching user profile", e);
            throw new ServletException(e);
        }

        logger.info("ViewProfileServlet request completed");
    }
}
