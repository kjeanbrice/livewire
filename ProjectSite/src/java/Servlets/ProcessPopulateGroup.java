/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import general.GroupData;
import general.UserData;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
@WebServlet(name = "ProcessPopulateGroup", urlPatterns = {"/ProcessPopulateGroup"})
public class ProcessPopulateGroup extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String group_id = (String)request.getParameter("groupId");
        UserData user_data = GenUtils.getUserData(request.getSession());

        PrintWriter out = response.getWriter();
        boolean errorFlag = false;
        String errorString = null;

        if (group_id == null || user_data == null || group_id.length() == 0) {
            out.println("");
        } else {
            Connection connection = GenUtils.getConnection(request);
            group_id = group_id.trim();
            if (connection == null) {
                try {
                    /*The username of your database*/
                    String user = "root";

                    /*The password of your database*/
                    String password = "password";

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
            } else {
                connection = GenUtils.getConnection(request);
            }

            try {
                System.out.println("Testing SQL connection");
                GroupData group_data = DatabaseUtils.populateGroup(connection, Integer.parseInt(group_id));
                if (group_data == null) {
                    out.println("");
                } else {
                    String json_output = group_data.generateJSON();
                    out.println(json_output);
                }
                GenUtils.closeConnection(request);
            } catch (SQLException ex) {
                errorFlag = true;
                errorString = ex.getMessage();
            }
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
