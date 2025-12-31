package com.cglia.login;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.cglia.dao.DBConnection;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	
		PrintWriter out = res.getWriter();
		HttpSession session=req.getSession();
			try {
				String email=req.getParameter("email");
				String password=req.getParameter("password");
				Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement("select * from users where email=? and password=?");
				ps.setString(1, email);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				 
				if(!rs.next())
				{
					out.println("<h2 style=text-align:center;color:red>Invalid Email or Password..</h2> <br>");
					RequestDispatcher redi = req.getRequestDispatcher("login.html");
					redi.include(req, res);
				}
				else
				{
//					pw.println("<h2 style=text-align:center;color:green;>Welcome - "+ub.getName()+"</h2><br>");
					session.setAttribute("email",email);
					RequestDispatcher rd = req.getRequestDispatcher("Home.html");
					rd.forward(req, res);
				}
			
				con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		

	
	}

}

