package com.bhavi1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Webproject1 extends HttpServlet{
	private PrintWriter pw;
	private Connection con;
	private String url="jdbc:mysql://localhost:3306/web1";
	private String uname="root";
	private String pwd="root";
	private final String INSERT_QUERY="insert into details values(?,?,?,?)";
	private PreparedStatement pstmt;
	String name;
	String pass;
	String email;
	long mobile;
	private int result;
	@Override
	protected void service(HttpServletRequest req,HttpServletResponse res) {
		pw=res.getWriter();
		name=req.getParameter("name").toUpperCase();

		pass=req.getParameter("pass");
		email=req.getParameter("email");
		mobile=Long.parseLong(req.getParameter("mobile"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,uname,pwd);
			pstmt=con.prepareStatement(INSERT_QUERY);
			pstmt.setString(1,name);
			pstmt.setString(2,pass);
			pstmt.setLong(3,mobile);
			pstmt.setString(4,email);
			result=pstmt.executeUpdate();
			if(result!=0) {
				pw.print("Success");
			}else {
				pw.print("Fail");
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

}