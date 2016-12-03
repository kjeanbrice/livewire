/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import general.CommentData;
import general.GroupData;
import general.PostData;
import general.UserData;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utility.DatabaseUtils;
import utility.GenUtils;

/**
 *
 * @author Karl
 */
@WebServlet(name = "ProcessLogin", urlPatterns = {"/ProcessLogin"})
public class ProcessLogin extends HttpServlet {

    
    
    public ProcessLogin(){
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        String useremail = (String)request.getParameter("email");
        String userpassword = (String)request.getParameter("password");
            
        System.out.println("Process Login Email: " + useremail);
        System.out.println("Process Login Password: " + userpassword);
        
        UserData userdata = null;
        boolean errorFlag = false;
        String errorString = null;
        
        if(useremail == null || userpassword == null || useremail.length() == 0 || userpassword.length() == 0){
            errorFlag = true;
            errorString = "Your password and email is required to log in!";
        }
        else{
            Connection connection = GenUtils.getConnection(request);
            if(connection == null){
                try {
                    /*The username of your database*/
                    String user = "root";
                    
                    /*The password of your database*/
                    String password = "kJb_123456";
                    
                    String server_address = "jdbc:mysql://localhost:3306/cse305_part3";
                    
                    System.out.println("Connecting to driver...");
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Connection Successful");
                    
                    connection = DriverManager.getConnection(server_address, user, password);
                    GenUtils.setConnection(request, connection);
                    connection = GenUtils.getConnection(request);
                    
                } catch (ClassNotFoundException ex) {
                    errorFlag = true;
                    errorString = ex.getMessage();
                } catch (SQLException ex) {
                    errorFlag = true;
                    errorString = ex.getMessage();
                }
            }
            else{
                connection = GenUtils.getConnection(request);
            }
            
            try{
                System.out.println("Testing SQL connection");
                userdata = DatabaseUtils.findUser(connection, useremail, userpassword);
                GenUtils.closeConnection(request);
                       
                if(userdata == null){
                    errorFlag = true;
                    errorString = "Invalid password or email address";
                }
            } catch (SQLException ex) {
                errorFlag = true;
                errorString = ex.getMessage();
            }
        }
        
     
        
       
        if(errorFlag){
            System.out.println("Error occured during login process in ProcessLogin");
            request.setAttribute("LOGIN_ERROR", errorString);
            UserData temp = new UserData();
            temp.setEmail(useremail);
            temp.setPassword(userpassword);
            request.setAttribute("invaliduser",temp);
                   
            RequestDispatcher d= this.getServletContext().getRequestDispatcher("/loginpage.jsp");
            d.forward(request,response);
        }
        else{
            HttpSession session = request.getSession();
            GenUtils.storeUserData(session, userdata);
            request.setAttribute("GROUP_ID","11");
            
            /*Credentials validated - go to the user page: WORK IN PROGRESS*/
            RequestDispatcher d= this.getServletContext().getRequestDispatcher("/grouppage.jsp");
            d.forward(request,response);
        }
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
