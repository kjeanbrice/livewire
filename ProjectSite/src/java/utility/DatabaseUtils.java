/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import general.CommentData;
import general.GroupData;
import general.PostData;
import general.UserData;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Karl
 */
public class DatabaseUtils {

    private static final int USER = 1;
    private static final int EMPLOYEE = 2;

    public static UserData getUser(Connection connection, int userID) throws SQLException {
        ResultSet rs1 = null;
        PreparedStatement pstm1 = connection.prepareStatement(
                "SELECT U.last_name, U.first_name, "
                + "U.address, U.city, U.state, "
                + "U.zip_code, U.telephone, U.email, "
                + "U.account_number, U.credit_card, "
                + "U.rating FROM user_data U "
                + "WHERE U.user_id = ?");
        pstm1.setInt(1, userID);
        rs1 = pstm1.executeQuery();
        if (rs1.next()) {
            UserData temp = new UserData();
            temp.setFirstname(rs1.getString("first_name"));
            temp.setLastname(rs1.getString("last_name"));
            return temp;
        }
        return null;
    }

    public static GroupData populateGroup(Connection connection, int groupID, int signedInUser) throws SQLException {
        GroupData groupdata = new GroupData();
        ArrayList<PostData> postdata = new ArrayList<PostData>();
        

        System.out.println("Attempting to find a group with id: " + groupID);
        ResultSet rs1 = null;
        PreparedStatement pstm1 = connection.prepareStatement(
                "SELECT G.user_id, G.group_name, G.creation_date "
                + "FROM group_data G "
                + "WHERE G.group_id  = ?");

        pstm1.setInt(1, groupID);
        rs1 = pstm1.executeQuery();

        if (rs1.next()) {
            int user_id = rs1.getInt("user_id");
            String group_name = rs1.getString("group_name");
            String creation_date = rs1.getString("creation_date");

            ResultSet rs2 = null;
            PreparedStatement pstm2 = connection.prepareStatement(
                    "SELECT U.last_name, U.first_name, "
                    + "U.address, U.city, U.state, "
                    + "U.zip_code, U.telephone, U.email, "
                    + "U.account_number, U.credit_card, "
                    + "U.rating FROM user_data U "
                    + "WHERE U.user_id = ?");
            pstm2.setInt(1, user_id);
            rs2 = pstm2.executeQuery();

            if (rs2.next()) {
                String firstName = rs2.getString("first_name");
                String lastName = rs2.getString("last_name");
                String email = rs2.getString("email");
                
                groupdata.setGroupID(groupID);
                groupdata.setOwnerFirstname(firstName);
                groupdata.setOwnerLastname(lastName);
                groupdata.setCreationDate(creation_date);
                groupdata.setGroupName(group_name);
                groupdata.setEmailAddress(email);
                
                if (user_id == signedInUser) {
                    groupdata.setGroupOwner(true);
                }
                ResultSet rs3 = null;
                PreparedStatement pstm3 = connection.prepareStatement(
                        "SELECT page_id"
                        + " FROM page_data"
                        + " WHERE page_data.associated_group  = ?");

                pstm3.setInt(1, groupdata.getGroupID());
                rs3 = pstm3.executeQuery();

                //Get Posts
                if (rs3.next()) {
                    ResultSet rs4 = null;
                    PreparedStatement pstm4 = connection.prepareStatement(
                            "SELECT P.post_id, P.user_id, P.content, P.dateOfPost"
                            + " FROM post_data P"
                            + " WHERE P.page_id  = ?");

                    int temp_pageid = rs3.getInt("page_id");
                    pstm4.setInt(1, temp_pageid);
                    rs4 = pstm4.executeQuery();

                    while (rs4.next()) {
                        int user_post_id = rs4.getInt("user_id");
                        UserData post_user = getUser(connection, user_post_id);
                        if (post_user == null) {
                            return null;
                        }

                        PostData new_post = new PostData();
                        new_post.setUser(post_user);
                        new_post.setPostID(rs4.getInt("post_id"));
                        new_post.setPostContent(rs4.getString("content"));
                        new_post.setPostDate(rs4.getString("dateOfPost"));
                        postdata.add(new_post);

                        ResultSet rs5 = null;
                        PreparedStatement pstm5 = connection.prepareStatement("SELECT C.comment_id, C.user_id, C.comment_content, C.creation_date"
                                + " FROM comment_data C"
                                + " WHERE C.post_id  = ?");

                        pstm5.setInt(1, new_post.getPostID());
                        rs5 = pstm5.executeQuery();

                        //Get comments
                        ArrayList<CommentData> commentdata = new ArrayList<CommentData>();
                        while (rs5.next()) {
                            
                            int comment_id = rs5.getInt("comment_id");
                            CommentData new_comment = new CommentData();
                            String creation_date2 = rs5.getString("creation_date");
                                
                            new_comment.setCommentID(comment_id);
                            new_comment.setCommentContent(rs5.getString("comment_content"));
                            new_comment.setCreationDate(creation_date2);
                            UserData comment_user = getUser(connection, rs5.getInt("user_id"));
                            new_comment.setUser(comment_user);
                            
                            if (comment_user == null) {
                                return null;
                            }
                            commentdata.add(new_comment);
                            
                            new_post.setCommentData(commentdata);
                        }

                    }
                    groupdata.setPostData(postdata); 
                    return groupdata;
                }

            }

        }

        return null;
    }

    public static UserData findUser(Connection connection, String email, String password) throws SQLException {
        System.out.println("Attempting to find user with email: " + email);

        String sql_statement = "SELECT L.user_email, L.user_id , L.account_type, L.user_password "
                + "FROM login L "
                + "WHERE L.user_email = ? AND L.user_password = ?";

        PreparedStatement pstmt1 = connection.prepareStatement(sql_statement);
        pstmt1.setString(1, email);
        pstmt1.setString(2, password);
        ResultSet rs1 = pstmt1.executeQuery();

        if (rs1.next()) {
            System.out.println("Matched someone in the database...");
            String user_email = rs1.getString("user_email");
            String user_password = rs1.getString("user_password");
            int account_type = rs1.getInt("account_type");
            int user_id = rs1.getInt("user_id");
            if (account_type == USER) {
                System.out.println("We found a USER!");
                String sql_statement2 = "SELECT U.last_name, U.first_name, "
                        + "U.address, U.city, U.state, "
                        + "U.zip_code, U.telephone, U.email, "
                        + "U.account_number, U.credit_card, "
                        + "U.rating FROM user_data U "
                        + "WHERE U.user_id = ?";
                PreparedStatement pstmt2 = connection.prepareStatement(sql_statement2);
                pstmt2.setInt(1, user_id);
                ResultSet rs2 = pstmt2.executeQuery();

                if (rs2.next()) {
                    String last_name = rs2.getString("last_name");
                    String first_name = rs2.getString("first_name");
                    String address = rs2.getString("address");
                    String city = rs2.getString("city");
                    String state = rs2.getString("state");
                    int zip_code = rs2.getInt("zip_code");
                    long telephone = rs2.getLong("telephone");
                    user_email = rs2.getString("email");
                    long account_number = rs2.getLong("account_number");
                    long credit_card = rs2.getLong("credit_card");
                    int rating = rs2.getInt("rating");
                    UserData user = new UserData(user_id, last_name, first_name, address, city, state, zip_code, telephone, email, account_number, credit_card, rating, account_type, user_password);
                    return user;
                }

            }

        }

        return null;
    }

}
