package com.cglia.register;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		UserBean ub = new UserBean();
		ub.setName(req.getParameter("name"));
		ub.setEmail(req.getParameter("email"));
		ub.setPassword(req.getParameter("password"));
		ub.setAge(Integer.parseInt(req.getParameter("age")));
		ub.setMobileNo(req.getParameter("mobileNo"));

		int k = new RegisterDAO().register(ub);
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");

		if (k > 0) {
			pw.println("<h2 style=color:blue;text-align:center;>RegisterSuccessfully...</h2><br>");

			RequestDispatcher rd = req.getRequestDispatcher("login.html");
			rd.include(req, res);
		}
	}

}

