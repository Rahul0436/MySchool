package com.hk.servlets;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

public class PasswordUpdate extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String em = req.getParameter("em");
        String pwd = req.getParameter("pwd");
        String confirmpassword = req.getParameter("cfmpwd");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

     
        if (em == null || pwd == null || confirmpassword == null) {


			String response = "<html>"
                        + "<head><title>Email Not Registered</title>"
                        + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css' integrity='sha512-5Hs3dF2AEPkpNAR7UiOHba+lRSJNeM2ECkwxUIxC1Q/FLycGTbNapWXB4tP889k5T5Ju8fs4b1P5z/iB4nMfSQ==' crossorigin='anonymous' referrerpolicy='no-referrer' />"
                        + "<link rel='stylesheet' type='text/css' href='index.css'>"
                        + "</head>"
                        + "<body>"
                        + "<div id='result' >"

                        + "<div id='logo1'>"
                        + "<i class='fa-solid fa-school' id='school' ></i>"
                        + "<span><p>My</p>School</span>"
                        + "</div>"

                       + "<p id='user' >Dear <span > user </span> </p>"
                        + "<p style='font-size:20px; color:white;padding:10px; text-align:center;'>Error: One or more parameters are missing. Please provide email and both passwords</p>"
                       
                        + "</div>"
                        + "</body>"
                        + "</html>";
			out.println(response);
            		return;
        }

        if (!pwd.equals(confirmpassword)) {
           	
			String response = "<html>"
                        + "<head><title>Email Not Registered</title>"
                        + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css' integrity='sha512-5Hs3dF2AEPkpNAR7UiOHba+lRSJNeM2ECkwxUIxC1Q/FLycGTbNapWXB4tP889k5T5Ju8fs4b1P5z/iB4nMfSQ==' crossorigin='anonymous' referrerpolicy='no-referrer' />"
                        + "<link rel='stylesheet' type='text/css' href='index.css'>"
                        + "</head>"
                        + "<body>"
                        + "<div id='result' >"
                       
                        + "<div id='logo1'>"
                        + "<i class='fa-solid fa-school' id='school' ></i>"
                        + "<span><p>My</p>School</span>"
                        + "</div>"
                        
                       + "<p id='user' >Dear <span > user </span> </p>"
                        + "<p style='font-size:20px; color:white;padding:10px; text-align:center;'>Passwords do not match. Please try again.</p>"
                       
                        + "</div>"
                        + "</body>"
                        + "</html>";
			out.println(response);
            		return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
              Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl1", "dell1", "dell1");

            String selectQuery = "SELECT * FROM DELL WHERE EMAIL = ?";
            pstmt = conn.prepareStatement(selectQuery);
            pstmt.setString(1, em);
            rs = pstmt.executeQuery();

            if (rs.next()) {
             
                String updateQuery = "UPDATE DELL SET PASSWORD = ? WHERE EMAIL = ?";
                pstmt = conn.prepareStatement(updateQuery);
                pstmt.setString(1, pwd);
                pstmt.setString(2, em);
                int updateCount = pstmt.executeUpdate();

                if (updateCount > 0) {
                 

			String response = "<html>"
                        + "<head><title>Email Not Registered</title>"
                        + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css' integrity='sha512-5Hs3dF2AEPkpNAR7UiOHba+lRSJNeM2ECkwxUIxC1Q/FLycGTbNapWXB4tP889k5T5Ju8fs4b1P5z/iB4nMfSQ==' crossorigin='anonymous' referrerpolicy='no-referrer' />"
                        + "<link rel='stylesheet' type='text/css' href='index.css'>"
                        + "</head>"
                        + "<body>"
                        + "<div style='height: 650px; width: 1350px; border: 2px solid black; text-align: center; padding: 20px; display:flex;align-items:center;justify-content:center; flex-direction:column; text-align:center; font-family: sans-serif; background-color:rgb(17, 17, 31);'>"
                        + "<div id='logo'>"
                        + "<i class='fa-solid fa-school' id='school' ></i>"
                        + "<span><p>My</p>School</span>"
                        + "</div>"
                        + "<p style='font-size:20px; padding-top:15px; color:white; text-align:center;'>Dear User,</p>"
                        + "<p style='font-size:20px; color:white;padding:10px; text-align:center;'>Password updated successfully..</p>"
                       
                        + "</div>"
                        + "</body>"
                        + "</html>";
			out.println(response);

                } else {


			String response = "<html>"
                        + "<head><title>Email Not Registered</title>"
                        + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css' integrity='sha512-5Hs3dF2AEPkpNAR7UiOHba+lRSJNeM2ECkwxUIxC1Q/FLycGTbNapWXB4tP889k5T5Ju8fs4b1P5z/iB4nMfSQ==' crossorigin='anonymous' referrerpolicy='no-referrer' />"
                        + "<link rel='stylesheet' type='text/css' href='index.css'>"
                        + "</head>"
                        + "<body>"
                        + "<div style='height: 650px; width: 1350px; border: 2px solid black; text-align: center; padding: 20px; display:flex;align-items:center;justify-content:center; flex-direction:column; text-align:center; font-family: sans-serif; background-color:rgb(17, 17, 31);'>"
                        + "<div id='logo'>"
                        + "<i class='fa-solid fa-school' id='school' ></i>"
                        + "<span><p>My</p>School</span>"
                        + "</div>"
                        + "<p style='font-size:20px; padding-top:15px; color:white; text-align:center;'>Dear User,</p>"
                        + "<p style='font-size:20px; color:red;padding:10px; text-align:center;'>Failed to update password. Please try again.</p>"
                       
                        + "</div>"
                        + "</body>"
                        + "</html>";
			out.println(response);


                }
            } else {
                
                String response = "<html>"
                        + "<head><title>Email Not Registered</title>"
                        + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css' integrity='sha512-5Hs3dF2AEPkpNAR7UiOHba+lRSJNeM2ECkwxUIxC1Q/FLycGTbNapWXB4tP889k5T5Ju8fs4b1P5z/iB4nMfSQ==' crossorigin='anonymous' referrerpolicy='no-referrer' />"
                        + "<link rel='stylesheet' type='text/css' href='index.css'>"
                        + "</head>"
                        + "<body>"
                        + "<div style='height: 650px; width: 1350px; border: 2px solid black; text-align: center; padding: 20px; display:flex;align-items:center;justify-content:center; flex-direction:column; text-align:center; font-family: sans-serif; background-color:rgb(17, 17, 31);'>"
                        + "<div id='logo'>"
                        + "<i class='fa-solid fa-school' id='school' ></i>"
                        + "<span><p>My</p>School</span>"
                        + "</div>"
                        + "<p style='font-size:20px; padding-top:15px; color:white; text-align:center;'>Dear User,</p>"
                        + "<p style='font-size:20px; color:white;padding:10px; text-align:center;'>Email is not registered. Please enter a valid email.</p>"
                       
                        + "</div>"
                        + "</body>"
                        + "</html>";
                out.println(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("An error occurred. Please try again.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
