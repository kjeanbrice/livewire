/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.DatabaseUtils;
import static utility.DatabaseUtils.makePostGroup;
import utility.GenUtils;

/**
 *
 * @author Karl
 */
@WebServlet(name = "ProcessGroupTransaction", urlPatterns = {"/ProcessGroupTransaction"})
public class ProcessGroupTransaction extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            Connection connection = GenUtils.setUpConnection("root", "toor", "jdbc:mysql://localhost:3306/cse305_part4");
            String transaction_type = request.getParameter("transaction");
            if (transaction_type == null || transaction_type.length() == 0) {
                out.println("TRANSACTION TYPE IS EMPTY");
                connection.close();
                return;

            }

            transaction_type = transaction_type.trim();
            if (transaction_type.equals("ADD_POST")) {
                String user_id = request.getParameter("userID");
                String group_id = request.getParameter("groupID");
                String content = request.getParameter("content");

                if (user_id == null || group_id == null || content == null) {
                    out.println("POST: INVALID ARGUMENTS FOR THE TRANSACTION");
                    connection.close();
                    return;
                }

                int return_val = DatabaseUtils.makePostGroup(connection, Integer.parseInt(group_id), Integer.parseInt(user_id), content);
                if (return_val > 0) {
                    out.println("POST: SQL QUERY FAILED");
                } else {
                    out.println("POST: SQL SUCCESS");
                }
                connection.close();

            } else if (transaction_type.equals("ADD_COMMENT")) {
                String user_id = request.getParameter("userID");
                String post_id = request.getParameter("postID");
                String content = request.getParameter("content");
                if (user_id == null || post_id == null || content == null) {
                    out.println("COMMENT: INVALID ARGUMENTS FOR THE TRANSACTION");
                    connection.close();
                    return;
                }

                int return_val = DatabaseUtils.addCommentToPost(connection, Integer.parseInt(user_id), Integer.parseInt(post_id), content);
                if (return_val > 0) {
                    out.println("COMMENT: SQL QUERY FAILED");
                } else {
                    out.println("COMMENT: SQL SUCCESS");
                }
                connection.close();
            } else if(transaction_type.equals("LIKE_POST") || transaction_type.equals("DISLIKE_POST") || transaction_type.equals("DELETE_POST")){

                String user_id = request.getParameter("userID");
                String post_id = request.getParameter("postID");

                if (user_id == null || post_id == null) {
                    out.println(transaction_type + "INVALID ARGUMENTS FOR THE TRANSACTION");
                    connection.close();
                    return;
                }

                int return_val = 0;
                if (transaction_type.equals("LIKE_POST")) {
                    return_val = DatabaseUtils.like_post(connection, Integer.parseInt(post_id), Integer.parseInt(user_id));
                }
                
                if(transaction_type.equals("DISLIKE_POST")){
                    return_val = DatabaseUtils.unlike_post(connection, Integer.parseInt(post_id), Integer.parseInt(user_id));
                }
                
                if(transaction_type.equals("DELETE_POST")){
                    return_val = DatabaseUtils.remove_post_from_group(connection, Integer.parseInt(post_id));
                }
                
                if (return_val > 0) {
                    out.println(transaction_type + ": SQL QUERY FAILED");
                } else {
                    out.println(transaction_type + ": SQL SUCCESS");
                }
                connection.close();
            } else if(transaction_type.equals("LIKE_COMMENT") || transaction_type.equals("DISLIKE_COMMENT") || transaction_type.equals("DELETE_COMMENT")) {

                String user_id = request.getParameter("userID");
                String comment_id = request.getParameter("commentID");

                if (user_id == null || comment_id == null) {
                    out.println("LIKE_COMMENT: INVALID ARGUMENTS FOR THE TRANSACTION");
                    connection.close();
                    return;
                }
                
                int return_val = 0;
               
                if(transaction_type.equals("LIKE_COMMENT")){
                    return_val = DatabaseUtils.like_comment(connection, Integer.parseInt(comment_id), Integer.parseInt(user_id));
                }
                 
                if(transaction_type.equals("DISLIKE_COMMENT")){
                    return_val = DatabaseUtils.unlike_comment(connection, Integer.parseInt(comment_id), Integer.parseInt(user_id));
                }
                
                if(transaction_type.equals("DELETE_COMMENT")){
                    return_val = DatabaseUtils.remove_comment_from_group(connection, Integer.parseInt(comment_id));
                }
                
                if (return_val > 0) {
                    out.println("LIKE_COMMENT: SQL QUERY FAILED");
                } else {
                    out.println("LIKE_COMMENT: SQL SUCCESS");
                }
                connection.close();
            }
            
                

        } catch (ClassNotFoundException | NumberFormatException | SQLException ex) {
            String temp = ex.getMessage();
            out.println(ex.getMessage());
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
