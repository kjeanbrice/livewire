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
            String last_name = rs1.getString("last_name");
            String first_name = rs1.getString("first_name");
            String address = rs1.getString("address");
            String city = rs1.getString("city");
            String state = rs1.getString("state");
            int zip_code = rs1.getInt("zip_code");
            long telephone = rs1.getLong("telephone");
            String user_email = rs1.getString("email");
            long account_number = rs1.getLong("account_number");
            long credit_card = rs1.getLong("credit_card");
            int rating = rs1.getInt("rating");

            UserData user = new UserData(userID, last_name, first_name, address, city, state, zip_code, telephone, user_email, account_number, credit_card, rating, 0, "temp");
            return user;
        }
        return null;
    }

    public static GroupData populateGroup(Connection connection, int groupID) throws SQLException {
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

                UserData group_owner = DatabaseUtils.getUser(connection, user_id);

                groupdata.setGroupID(groupID);
                groupdata.setUser(group_owner);

                groupdata.setCreationDate(creation_date);
                groupdata.setGroupName(group_name);

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
                            "SELECT P.post_id, P.user_id, P.content, P.dateOfPost,(Select COUNT(Distinct L.user_id) FROM liked_posts L WHERE L.post_id = P.post_id) as like_count"
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
                        new_post.setPostLikes(rs4.getInt("like_count"));
                        postdata.add(new_post);

                        ResultSet rs5 = null;
                        PreparedStatement pstm5 = connection.prepareStatement("SELECT C.comment_id, C.user_id, C.comment_content, C.creation_date,(Select COUNT(Distinct L.user_id) FROM liked_comments L WHERE L.comment_id = C.comment_id) as like_count"
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
                            new_comment.setCommentLikes(rs5.getInt("like_count"));
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
                }else{
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

    /*Allows the owner of a group to create a new post*/
    public static int makePostGroup(Connection connection,
            int group_id, int user_id, String content) throws SQLException {

        PreparedStatement prepared_statement;

        ResultSet result_set = null;
        prepared_statement = connection.prepareStatement("SELECT page_id"
                + " FROM page_data"
                + " WHERE page_data.associated_group  = ?");
        prepared_statement.setInt(1, group_id);
        result_set = prepared_statement.executeQuery();

        if (result_set.next()) {
            prepared_statement = connection.prepareStatement("INSERT INTO "
                    + "post_data(user_id,page_id,content)"
                    + " VALUES (?,?,?)");
            prepared_statement.setInt(1, user_id);
            prepared_statement.setInt(2, result_set.getInt("page_id"));
            prepared_statement.setString(3, content);
            return prepared_statement.executeUpdate();
        }

        return 0;
    }

    /*Allows a user to add a comment to a post*/
    public static int addCommentToPost(Connection connection,
            int user_id, int post_id, String content)
            throws SQLException {

        PreparedStatement prepared_statement = null;
        ResultSet resultSet = null;
        prepared_statement = connection.prepareStatement(
                "INSERT INTO comment_data(user_id,post_id,comment_content) "
                + "VALUES (?,?,?)");
        prepared_statement.setInt(1, user_id);
        prepared_statement.setInt(2, post_id);
        prepared_statement.setString(3, content);
        prepared_statement.executeUpdate();

        prepared_statement = connection.prepareStatement("SELECT P.comment_count "
                + "FROM post_data P "
                + "WHERE P.post_id = ?");
        prepared_statement.setInt(1, post_id);

        resultSet = prepared_statement.executeQuery();
        if (resultSet.next()) {
            prepared_statement = connection.prepareStatement("UPDATE post_data "
                    + "SET post_data.comment_count = ? "
                    + "WHERE post_data.post_id  = ?");
            prepared_statement.setInt(1, resultSet.getInt("comment_count") + 1);
            prepared_statement.setInt(2, post_id);
            int return_val = prepared_statement.executeUpdate();
            return return_val;
        }
        return 0;
    }

    public static int like_comment(Connection connection,int comment_id, int user_id) throws SQLException {
        
        PreparedStatement prepared_statement;
        prepared_statement = connection.prepareStatement("INSERT INTO liked_comments(comment_id,user_id) VALUES (?,?)");
        prepared_statement.setInt(1, comment_id);
        prepared_statement.setInt(2, user_id);
        int return_val = prepared_statement.executeUpdate();
        return return_val;
    }
    
    
    public static int like_post(Connection connection,
            int post_id, int user_id) throws SQLException {
        
        PreparedStatement prepared_statement;
        int return_val = 0;
        prepared_statement = connection.prepareStatement("INSERT INTO liked_posts(post_id,user_id)"
                + " VALUES (?,?)");
        prepared_statement.setInt(1, post_id);
        prepared_statement.setInt(2, user_id);
        return_val = prepared_statement.executeUpdate();
        return return_val;
    }
    
     public static int unlike_post(Connection connection,
            int post_id, int user_id)
            throws SQLException {
        PreparedStatement prepared_statement;
        int return_val = 0;
        prepared_statement = connection.prepareStatement("DELETE FROM "
                + "liked_posts "
                + "WHERE liked_posts.post_id = ? "
                + "and liked_posts.user_id = ?");
        prepared_statement.setInt(1, post_id);
        prepared_statement.setInt(2, user_id);
        return_val = prepared_statement.executeUpdate();
        return return_val;
    }
    
     public static int unlike_comment(Connection connection,
            int comment_id, int user_id)
            throws SQLException {
        int return_val = 0;
        PreparedStatement prepared_statement;
        prepared_statement = connection.prepareStatement("DELETE FROM "
                + "liked_comments WHERE liked_comments.comment_id = ? "
                + "and liked_comments.user_id = ?");
        prepared_statement.setInt(1, comment_id);
        prepared_statement.setInt(2, user_id);
        return_val = prepared_statement.executeUpdate();
        return return_val;
    }
     
     
       public static int remove_post_from_group(Connection connection,
             int post_id) throws SQLException {
        
        PreparedStatement prepared_statement;
        int return_val = 0;
        prepared_statement = connection.prepareStatement("DELETE "
                + "FROM post_data "
                + "WHERE post_data.post_id = ?");
        prepared_statement.setInt(1, post_id);
        return_val = prepared_statement.executeUpdate();
        return return_val;
    }

    
    public static int remove_comment_from_group(Connection connection,
            int comment_id) throws SQLException {

        int return_val = 0;
        PreparedStatement prepared_statement;
        prepared_statement = connection.prepareStatement("DELETE FROM "
                + "comment_data"
                + " WHERE comment_data.comment_id = ?");
        prepared_statement.setInt(1, comment_id);
        return_val = prepared_statement.executeUpdate();
        return return_val;
    }
    
    
      public static int editComment(Connection connection,
            int comment_id,String content)
            throws SQLException {
        int return_val = 0;
        PreparedStatement prepared_statement;
        prepared_statement = connection.prepareStatement("UPDATE comment_data "
                + "SET comment_data.comment_content = ? "
                + "WHERE comment_data.comment_id  = ? ");

        prepared_statement.setString(1, content);
        prepared_statement.setInt(2, comment_id);
        return_val = prepared_statement.executeUpdate();
        return return_val;
    }
      
    public static int editPost(Connection connection,
            int post_id, String content)
            throws SQLException {
        int return_val = 0;
        PreparedStatement prepared_statement;
        prepared_statement = connection.prepareStatement("UPDATE post_data "
                + "SET post_data.content = ? "
                + "WHERE post_data.post_id  = ?");
        prepared_statement.setInt(2, post_id);
        prepared_statement.setString(1, content);
        return_val = prepared_statement.executeUpdate();
        return return_val;
    }

}
