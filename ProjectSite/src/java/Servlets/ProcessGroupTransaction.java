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
import java.sql.SQLException;
import java.util.ArrayList;
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

            Connection connection = GenUtils.setUpConnection("root", "password", "jdbc:mysql://localhost:3306/cse305_part3");
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
            } else if (transaction_type.equals("LIKE_POST") || transaction_type.equals("DISLIKE_POST") || transaction_type.equals("DELETE_POST")) {

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

                if (transaction_type.equals("DISLIKE_POST")) {
                    return_val = DatabaseUtils.unlike_post(connection, Integer.parseInt(post_id), Integer.parseInt(user_id));
                }

                if (transaction_type.equals("DELETE_POST")) {
                    return_val = DatabaseUtils.remove_post_from_group(connection, Integer.parseInt(post_id));
                }

                if (return_val > 0) {
                    out.println(transaction_type + ": SQL SUCESS");
                } else {
                    out.println(transaction_type + ": SQL SQL QUERY SUCCESS");
                }
                connection.close();
            } else if (transaction_type.equals("LIKE_COMMENT") || transaction_type.equals("DISLIKE_COMMENT") || transaction_type.equals("DELETE_COMMENT")) {

                String user_id = request.getParameter("userID");
                String comment_id = request.getParameter("commentID");

                if (user_id == null || comment_id == null) {
                    out.println("LIKE_COMMENT: INVALID ARGUMENTS FOR THE TRANSACTION");
                    connection.close();
                    return;
                }

                int return_val = 0;

                if (transaction_type.equals("LIKE_COMMENT")) {
                    return_val = DatabaseUtils.like_comment(connection, Integer.parseInt(comment_id), Integer.parseInt(user_id));
                }

                if (transaction_type.equals("DISLIKE_COMMENT")) {
                    return_val = DatabaseUtils.unlike_comment(connection, Integer.parseInt(comment_id), Integer.parseInt(user_id));
                }

                if (transaction_type.equals("DELETE_COMMENT")) {
                    return_val = DatabaseUtils.remove_comment_from_group(connection, Integer.parseInt(comment_id));
                }

                if (return_val > 0) {
                    out.println(transaction_type + ":SQL SUCCESS");
                } else {
                    out.println(transaction_type + ":SQL QUERY FAILED");
                }
                connection.close();
                //"/ProjectSite/ProcessGroupTransaction?transaction=EDIT_COMMENT&groupID=" + group_id + "&userID=" + user_id + "&content=" + edit_content 
                // + "&commentOwner=" + comment_userid + "&commentID=" + comment_id;
            } else if (transaction_type.equals("EDIT_COMMENT")) {

                String group_id = request.getParameter("groupID");
                String user_id = request.getParameter("userID");
                String comment_owner = request.getParameter("commentOwner");
                String comment_id = request.getParameter("commentID");
                String content = request.getParameter("content");

                GroupData group_data = DatabaseUtils.populateGroup(connection, Integer.parseInt(group_id));
                int return_val = 0;
                if (group_data.getUser().getUserid() == Integer.parseInt(user_id)) {
                    return_val = DatabaseUtils.editComment(connection, Integer.parseInt(comment_id), content);
                } else {
                    if (Integer.parseInt(user_id) == Integer.parseInt(comment_owner)) {
                        return_val = DatabaseUtils.editComment(connection, Integer.parseInt(comment_id), content);
                    } else {
                        out.println(transaction_type + ":SQL QUERY FAILED - NOT OWNER");
                        connection.close();
                        return;
                    }
                }

                if (return_val > 0) {
                    out.println(transaction_type + ":SQL SUCCESS");
                } else {
                    out.println(transaction_type + ":SQL QUERY FAILED");
                }
                connection.close();

            } else if (transaction_type.equals("EDIT_POST")) {
                //"/ProjectSite/ProcessGroupTransaction?transaction=EDIT_POST&groupID=" + group_id + "&userID=" + user_id + "&content=" + edit_content 
                //  + "&postOwner=" + post_userid + "&postID=" + post_id;
                String group_id = request.getParameter("groupID");
                String user_id = request.getParameter("userID");
                String post_owner = request.getParameter("postOwner");
                String post_id = request.getParameter("postID");
                String content = request.getParameter("content");

                GroupData group_data = DatabaseUtils.populateGroup(connection, Integer.parseInt(group_id));
                int return_val = 0;
                if (group_data.getUser().getUserid() == Integer.parseInt(user_id)) {
                    return_val = DatabaseUtils.editPost(connection, Integer.parseInt(post_id), content);
                } else {
                    if (Integer.parseInt(user_id) == Integer.parseInt(post_owner)) {
                        return_val = DatabaseUtils.editPost(connection, Integer.parseInt(post_id), content);
                    } else {
                        out.println(transaction_type + ":SQL QUERY FAILED - NOT OWNER");
                        connection.close();
                        return;
                    }
                }

                if (return_val > 0) {
                    out.println(transaction_type + ":SQL SUCCESS");
                } else {
                    out.println(transaction_type + ":SQL QUERY FAILED");
                }
                connection.close();
            } else if (transaction_type.equals("RENAME")) {
                // "/ProjectSite/ProcessGroupTransaction?transaction=RENAME&groupID=" + group_id + "&userID=" + user_id + "&content=" + edit_content 
                //  + "&newGroupName=" + new_group_name;
                String group_id = request.getParameter("groupID");
                String user_id = request.getParameter("userID");
                String new_group_name = request.getParameter("newGroupName");

                GroupData group_data = DatabaseUtils.populateGroup(connection, Integer.parseInt(group_id));
                int return_val = 0;
                if (group_data.getUser().getUserid() == Integer.parseInt(user_id)) {
                    return_val = DatabaseUtils.renameGroup(connection, group_data.getGroupID(), new_group_name);
                } else {
                    out.println(transaction_type + ":SQL QUERY FAILED - NOT OWNER");
                    connection.close();
                    return;
                }

                if (return_val > 0) {
                    out.println(transaction_type + ":SQL SUCCESS");
                } else {
                    out.println(transaction_type + ":SQL QUERY FAILED");
                }
                connection.close();
            } else if (transaction_type.equals("GET_MEMBERS")) {
                // "/ProjectSite/ProcessGroupTransaction?transaction=RENAME&groupID=" + group_id + "&userID=" + user_id + "&content=" + edit_content 
                //  + "&newGroupName=" + new_group_name; 
                response.setContentType("application/json");
                String group_id = request.getParameter("groupID");
                ArrayList<UserData> group_members = DatabaseUtils.getGroupMembers(connection, Integer.parseInt(group_id));
                if (group_members.isEmpty()) {
                    out.println("");
                    connection.close();
                    return;
                } else {
                    String json_output = "{\"GroupMembers\":[";
                    for (int i = 0; i < group_members.size(); i++) {
                        if (i == group_members.size() - 1) {
                            json_output += group_members.get(i).generateJSON() + "]";
                        } else {
                            json_output += group_members.get(i).generateJSON() + ",";
                        }
                    }
                    json_output += "}";
                    System.out.println(json_output);
                    out.println(json_output);
                }
                connection.close();
            } else if (transaction_type.equals("REMOVE_MEMBER")) {
                String group_id = request.getParameter("groupID");
                String user_id = request.getParameter("userID");
                String user_email = request.getParameter("emailAddress");

                int return_val = 0;
                GroupData group_data = DatabaseUtils.populateGroup(connection, Integer.parseInt(group_id));
                if (user_id.trim().equals("ADMIN_JOIN")) {
                    return_val = DatabaseUtils.removeGroupMember(connection, group_data.getGroupID(), user_email.trim());
                } else if (group_data.getUser().getUserid() == Integer.parseInt(user_id)) {
                    return_val = DatabaseUtils.removeGroupMember(connection, group_data.getGroupID(), user_email.trim());
                } else {
                    out.println(transaction_type + ":FAILURE");
                    connection.close();
                    return;
                }

                if (return_val > 0) {
                    out.println(transaction_type + ":SUCCESS");
                } else {
                    out.println(transaction_type + ":FAILURE");
                }
                connection.close();
            } else if (transaction_type.equals("SEARCH_MEMBER")) {
                response.setContentType("application/json");
                String group_id = request.getParameter("groupID");
                String user_email = request.getParameter("emailAddress");
                ArrayList<UserData> potential_members = DatabaseUtils.searchGroupMembers(connection, Integer.parseInt(group_id), user_email);
                if (potential_members.isEmpty()) {
                    out.println("");
                    connection.close();
                    return;
                } else {
                    String json_output = "{\"GroupMembers\":[";
                    for (int i = 0; i < potential_members.size(); i++) {
                        if (i == potential_members.size() - 1) {
                            json_output += potential_members.get(i).generateJSON() + "]";
                        } else {
                            json_output += potential_members.get(i).generateJSON() + ",";
                        }
                    }
                    json_output += "}";
                    System.out.println(json_output);
                    out.println(json_output);
                }
                connection.close();
            } else if (transaction_type.equals("ADD_MEMBER")) {
                String group_id = request.getParameter("groupID");
                String user_id = request.getParameter("userID");
                String user_email = request.getParameter("emailAddress");

                int return_val = 0;
                GroupData group_data = DatabaseUtils.populateGroup(connection, Integer.parseInt(group_id));
                if (user_id.trim().equals("ADMIN_JOIN")) {
                    return_val = DatabaseUtils.addGroupMember(connection, group_data.getGroupID(), user_email.trim());
                } else if (group_data.getUser().getUserid() == Integer.parseInt(user_id)) {
                    return_val = DatabaseUtils.addGroupMember(connection, group_data.getGroupID(), user_email.trim());
                } else {
                    out.println(transaction_type + ":FAILURE");
                    connection.close();
                    return;
                }

                if (return_val > 0) {
                    out.println(transaction_type + ":SUCCESS");
                } else {
                    out.println(transaction_type + ":FAILURE");
                }
                connection.close();
            } else if (transaction_type.equals("CHECK_JOINSTATUS")) {
                String group_id = request.getParameter("groupID");
                String user_id = request.getParameter("userID");

                int return_val = 0;
                GroupData group_data = DatabaseUtils.populateGroup(connection, Integer.parseInt(group_id));
                if (group_data.getUser().getUserid() == Integer.parseInt(user_id)) {
                    out.println(transaction_type + ":OWNER");
                    connection.close();
                    return;
                } else {
                    return_val = DatabaseUtils.checkJoinStatus(connection, group_data.getGroupID(), Integer.parseInt(user_id));
                }

                if (return_val > 0) {
                    out.println(transaction_type + ":JOINED");
                } else {
                    out.println(transaction_type + ":UNJOINED");
                }
                connection.close();
            } else if (transaction_type.equals("DELETE_GROUP")) {
                //"/ProjectSite/ProcessGroupTransaction?transaction=DELETE_GROUP&groupID=" + group_id + "&userID=" + user_id;
                String group_id = request.getParameter("groupID");
                String user_id = request.getParameter("userID");

                int return_val = 0;
                GroupData group_data = DatabaseUtils.populateGroup(connection, Integer.parseInt(group_id));
                if (group_data.getUser().getUserid() == Integer.parseInt(user_id)) {
                   return_val = DatabaseUtils.deleteGroup(connection,group_data.getGroupID());
                } else {
                    out.println(transaction_type + ":FAILURE");
                    connection.close();
                    return;
                }

                if (return_val > 0) {
                    out.println(transaction_type + ":SUCCESS");
                } else {
                    out.println(transaction_type + ":FAILURE");
                }
                connection.close();

            } else if (transaction_type.equals("CREATE_GROUP")) {
                //int user_id, String group_name, String group_type
                String user_id = request.getParameter("userID");
                String groupName = request.getParameter("groupName");
                String groupType = request.getParameter("groupType");
      
                if(groupName == null || groupType == null || user_id == null || groupName.trim().length() == 0 || groupType.trim().length() == 0){
                    out.println("CREATE_GROUP:FAILURE");
                    connection.close();
                    return;
                }
                
                int group_val = 0;
                group_val = DatabaseUtils.createGroup(connection, Integer.parseInt(user_id),groupName.trim(),groupType.trim());
               

                if (group_val > 0) {
                    out.println(group_val);
                } else {
                    out.println("CREATE_GROUP:FAILURE");
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
