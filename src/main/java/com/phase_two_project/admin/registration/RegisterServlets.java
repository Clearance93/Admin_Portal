package com.phase_two_project.admin.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")

public class RegisterServlets extends HttpServlet {
	 private final  static String query = "insert into admin_reg(fname,lname,age,email,password,cpassword) values(?,?,?,?,?,?)";
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 PrintWriter pw = res.getWriter();
		 res.setContentType("text/html");
		 pw.println("<h2 link='stylesheet' href='css/bootstrap.css></h2>");
		 
		 String fname = req.getParameter("fname");
		 String lname = req.getParameter("lname");
		 String age = req.getParameter("age");
		 String email = req.getParameter("email");
		 String password = req.getParameter("password");
		 String cpassword = req.getParameter("cpassword");
		 
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
		 } catch(Exception e) {
			 e.printStackTrace(); 
		 }
		 try (Connection con = DriverManager.getConnection("jdbc:mysql:///usermngr","root","1!Clearmorumudi");
				 PreparedStatement ps = con.prepareStatement(query);) {
			 ps.setString(1,  fname);
			 ps.setString(2,  lname);
			 ps.setString(3, age);
			 ps.setString(4, email);
			 ps.setString(5, password);
			 ps.setString(6, cpassword);
			 int count = ps.executeUpdate();
			 
			 pw.println("<div class='card' style='margin:auto; width:300px; margin-top:100px;>");
			 if(count == 1) {
				 pw.println("<h2 class='bg-info text-light text-center;'>Record registered successfully</h2>");
			 } else {
				 pw.println("<h2 class='bg-dark text-danger text-center;> Record not regisered successfully </h2>");
			 }
			 
		 } catch(SQLException se) {
			 pw.println("<h2>" + se.getMessage() + "</h2>");
			 se.printStackTrace();
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
		 pw.println("<a href='index.html'>Home</a>");
		 
		 pw.println("</div>");
		 pw.close(); 
	}
	 
	 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}
}
