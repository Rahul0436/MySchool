package com.hk.servlets;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletException;

public class LoginForm extends GenericServlet {
    public void service(ServletRequest req, ServletResponse resp) throws IOException, ServletException {
        String un = req.getParameter("un");
        String pwd = req.getParameter("pwd");
      

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl1", "dell1", "dell1");



		// User login form data is matched with database 
                	String loginQuery = "SELECT * FROM DELL WHERE USERNAME = ? AND PASSWORD = ?";
               		 pstmt = conn.prepareStatement(loginQuery);
               		 pstmt.setString(1, un);
               		 pstmt.setString(2, pwd);
               		 rs = pstmt.executeQuery();
               

                if (rs.next()) {

			
			
                    // Valid login, redirect to home page
                    String response = "<html>"
                            + "<head><title>Login Success</title>"
                            +"<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css' integrity='sha512-5Hs3dF2AEPkpNAR7UiOHba+lRSJNeM2ECkwxUIxC1Q/FLycGTbNapWXB4tP889k5T5Ju8fs4b1P5z/iB4nMfSQ==' crossorigin='anonymous' referrerpolicy='no-referrer' />"
                            +"<link rel='stylesheet' type='text/css' href='index.css'>"
                            +"</head>"
                            +"<body >"
                            +"<div id='result'  >"
                            
                            +"<div id='logo1'>"
                            +"<i class='fa-solid fa-school' id='school' ></i>"
                            +"<span ><p>My</p >School</span>"
                            +"</div>"

                            + "<p id='user' >Dear <span >" + un + "</span> </p>"
                            + "<p style='font-size:20px; color:white;padding:10px; text-align:center;'> Welcome back to My <span style='color:#3f51b5;'> School </span></p>"
                            + "<meta http-equiv='refresh' content='4;url=home.html' />"
                            + "</div>"
                            + "</body>"
                            + "</html>";
                    out.println(response);

                } 
		else {

                    // Invalid login credentials then it will show please register 

                    String response = "<html>"
                            + "<head><title>Login Failed</title>"
                            +"<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css' integrity='sha512-5Hs3dF2AEPkpNAR7UiOHba+lRSJNeM2ECkwxUIxC1Q/FLycGTbNapWXB4tP889k5T5Ju8fs4b1P5z/iB4nMfSQ==' crossorigin='anonymous' referrerpolicy='no-referrer' />"
                            + "<link rel='stylesheet' type='text/css' href='index.css'>"
                            +"</head>"
                            + "<body >"
                            + "<div id='result' >"
                          
                            + "<div id='logo1'>"
                            +"<i class='fa-solid fa-school' id='school' ></i>"
                            +"<span ><p>My</p >School</span>"
                            +"</div>"
                            
                            + "<p id='user'>Dear <span >" + un + "</span> </p>"
                            +"<p  style='font-size:20px; color:white; padding:10px; text-align:center;'>You have entered invalid <span style='color:red; '>username</span> or <span style='color:red;'>password</span></p>"
                            + "<p style=' font-size:20px; color:red;padding:10px; text-align:center;'>Please login with valid username and password</p>"
                            + "</div>"
                            + "</body>"
                            + "</html>";
                    out.println(response);
                }		


} catch (SQLException e) {
            out.println("<html><body><h2>Database Error: " + e.getMessage() + "</h2></body></html>");
        } catch (ClassNotFoundException e) {
            out.println("<html><body><h2>Error: JDBC Driver not found!</h2></body></html>");
        } catch (NumberFormatException e) {
            out.println("<html><body><h2>Error: Invalid mobile number format!</h2></body></html>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                out.println("<html><body><h2>Error closing resources: " + e.getMessage() + "</h2></body></html>");
            }
            out.flush();
            out.close();
        }
    }
}


	