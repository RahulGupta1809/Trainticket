package com.cglia.login;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/logout")
public class LogoutServlet  extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		 PrintWriter pw=res.getWriter();  
         
		 res.setContentType("text/html");  
		 pw.print("<h2 style=color:F86F03;text-align:center;>You are successfully logged out!</h2><br>");
         
	          
	          HttpSession session=req.getSession();
	          session.invalidate();
	        req.getRequestDispatcher("login.html").include(req, res); 
	           
		
		
	}

}


