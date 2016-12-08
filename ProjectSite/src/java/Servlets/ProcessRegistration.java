/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.DatabaseUtils;
import utility.GenUtils;

/**
 *
 * @author Karl
 */
@WebServlet(name = "ProcessRegistration", urlPatterns = {"/ProcessRegistration"})
public class ProcessRegistration extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
public ProcessRegistration(){
        super();
    }
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        String last_name = (String)request.getParameter("last_name");
        String first_name = (String)request.getParameter("first_name");
        String email = (String)request.getParameter("email");
        String password = (String)request.getParameter("password");
        String address = (String)request.getParameter("address");
        
        Connection connection = GenUtils.getConnection(request);
            if(connection == null){
                try {
                    /*The username of your database*/
                    String user = "root";
                    
                    /*The password of your database*/
                    String pw = "password";
                    
                    String server_address = "jdbc:mysql://localhost:3306/cse305_part3?currentSchema=cse305_part3";
                    
                    System.out.println("Connecting to driver...");
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Connection Successful");
                    
                    connection = DriverManager.getConnection(server_address, user, pw);
                    GenUtils.setConnection(request, connection);
                    connection = GenUtils.getConnection(request);
                    
                } catch (ClassNotFoundException ex) {

                } catch (SQLException ex) {
                }
            }
            else{
                connection = GenUtils.getConnection(request);
            }
        DatabaseUtils.register_user( connection , first_name ,  last_name,  address,  email,  password);

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
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(ProcessRegistration.class.getName()).log(Level.SEVERE, null, ex);
    }
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
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(ProcessRegistration.class.getName()).log(Level.SEVERE, null, ex);
    }
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
