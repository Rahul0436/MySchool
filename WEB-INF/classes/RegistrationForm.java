package com.hk.servlets;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletException;

public class RegistrationForm extends GenericServlet {
    public void service(ServletRequest req, ServletResponse resp) throws IOException, ServletException {
        String un = req.getParameter("un");
        String pwd = req.getParameter("pwd");
        String mn = req.getParameter("mn");
        String em = req.getParameter("em");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl1", "dell1", "dell1");

            
            String selectQuery = "SELECT * FROM DELL WHERE USERNAME = ? OR EMAIL = ?";
            pstmt = conn.prepareStatement(selectQuery);
            pstmt.setString(1, un);
            pstmt.setString(2, em);
            rs = pstmt.executeQuery();

            if (rs.next()) {

		
		String response = "<html>"
 		+ "<head><title>Registration Form</title>"
        +"<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css' integrity='sha512-5Hs3dF2AEPkpNAR7UiOHba+lRSJNeM2ECkwxUIxC1Q/FLycGTbNapWXB4tP889k5T5Ju8fs4b1P5z/iB4nMfSQ==' crossorigin='anonymous' referrerpolicy='no-referrer' />"
        + "<link rel='stylesheet' type='text/css' href='index.css'>"
        +"</head>"
 		+ "<body >" 
		+ "<div style='height: 650px; width: 1350px; border: 2px solid black; text-align: center; padding: 20px; display:flex;align-items:center;justify-content:center; flex-direction:column; font-family: sans-serif; background-color:rgb(17, 17, 31);'>"
 	
        + "<div id='logo1'>"
        +"<i class='fa-solid fa-school' id='school' ></i>"
        +"<span ><p>My</p >School</span>"
        +"</div>"

		+ "<p style='font-size:20px; padding-top:15px; color:white; text-align:center;'>Dear <span style='color:#3f51b5;'>" + un + "</span>  </p>" 
        + "<p style='font-size:20px; padding:10px; color:white; text-align:center;'>An account with this username or email already exists</p>"
		+ "<p style='font-size:20px; padding:10px;color:white; text-align:center;'>Please Login to your account</p>" 
		+ "</div>" 
		+ "</body>" 
		+ "</html>";
		 out.println(response);
			

            }


		 else {
             
                String insertQuery = "INSERT INTO DELL(USERNAME, PASSWORD, MOBILENO, EMAIL) VALUES(?, ?, ?, ?)";
                pstmt = conn.prepareStatement(insertQuery);
                pstmt.setString(1, un);
                pstmt.setString(2, pwd);
                pstmt.setString(3, mn);
                pstmt.setString(4, em);
                int rowsAffected = pstmt.executeUpdate();

                String response = "<html>"
                        + "<head><title>Registration Form</title>"
                        +"<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css' integrity='sha512-5Hs3dF2AEPkpNAR7UiOHba+lRSJNeM2ECkwxUIxC1Q/FLycGTbNapWXB4tP889k5T5Ju8fs4b1P5z/iB4nMfSQ==' crossorigin='anonymous' referrerpolicy='no-referrer' />"
                        + "<link rel='stylesheet' type='text/css' href='index.css'>"
                        +"</head>"
                        + "<body >"
                        + "<div  id='result'>"

                        + "<div id='logo1'>"
                        +"<i class='fa-solid fa-school' id='school' ></i>"
                        +"<span ><p>My</p >School</span>"
                        +"</div>"

                        + "<p id='user' >Dear <span >" + un + "</span> </p>"
                        + "<p style='font-size:20px; padding:10px; color:white; text-align:center;'>Thank you for registration </p>"
                        + "<p style='font-size:20px; padding:10px; color:white; text-align:center;'>For exploring the content then please login to My <span style='color:#3f51b5;'> School </span></p>"
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




