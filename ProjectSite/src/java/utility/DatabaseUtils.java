/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import general.AdvertisementData;
import general.CommentData;
import general.GroupData;
import general.MessageData;
import general.PostData;
import general.UserData;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

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

    public static ArrayList<UserData> getAllUsers(Connection connection) throws SQLException {
        ResultSet rs1 = null;
        ArrayList<UserData> all_users = new ArrayList<UserData>();
        PreparedStatement pstm1 = connection.prepareStatement(
                "SELECT U.first_name, U.last_name,U.user_id, U.email"
                + "FROM user_data U");
        rs1 = pstm1.executeQuery();
        while (rs1.next()) {

            String last_name = rs1.getString("last_name");
            String first_name = rs1.getString("first_name");
            String address = rs1.getString("address");
            String user_email = rs1.getString("email");
            int user_id = rs1.getInt("user_id");

            UserData user = new UserData();
            user.setFirstname(first_name);
            user.setLastname(last_name);
            user.setAddress(address);
            user.setEmail(user_email);
            user.setUserid(user_id);
            all_users.add(user);
        }
        return all_users;
    }

    public static ArrayList<UserData> getGroupMembers(Connection connection, int groupID) throws SQLException {
        ResultSet rs1 = null;
        ArrayList<UserData> all_users = new ArrayList<UserData>();
        PreparedStatement pstm1 = connection.prepareStatement(
                "SELECT DISTINCT U.first_name, U.last_name,U.user_id, U.email FROM user_data U, group_members G "
                + "WHERE G.group_id = ? AND U.user_id IN"
                + "(  SELECT G1.user_id    "
                + " FROM group_members G1    "
                + " WHERE G1.group_id = ?)");
        pstm1.setInt(1, groupID);
        pstm1.setInt(2, groupID);
        rs1 = pstm1.executeQuery();
        while (rs1.next()) {

            String last_name = rs1.getString("last_name");
            String first_name = rs1.getString("first_name");
            String user_email = rs1.getString("email");
            int user_id = rs1.getInt("user_id");

            UserData user = new UserData();
            user.setFirstname(first_name);
            user.setLastname(last_name);
            user.setEmail(user_email);
            user.setUserid(user_id);
            all_users.add(user);
        }
        return all_users;
    }

    public static ArrayList<UserData> searchGroupMembers(Connection connection, int groupID, String email) throws SQLException {
        ResultSet rs1 = null;
        ArrayList<UserData> all_users = new ArrayList<UserData>();
        PreparedStatement pstm1 = connection.prepareStatement(
                "SELECT DISTINCT U.first_name, U.last_name,U.user_id, U.email "
                        + "FROM user_data U, group_members G "
                        + "WHERE G.group_id = ? "
                        + "AND U.user_id NOT IN(SELECT G1.user_id    "
                        + "FROM group_members G1    "
                        + "WHERE G1.group_id = ?) "
                        + "AND U.email LIKE ?");
        pstm1.setInt(1, groupID);
        pstm1.setInt(2, groupID);
        if(email.trim().length() == 0){
            pstm1.setString(3, "%");
        }
        else{
            pstm1.setString(3, "%" + email + "%");
        }
        
        rs1 = pstm1.executeQuery();
        while (rs1.next()) {

            String last_name = rs1.getString("last_name");
            String first_name = rs1.getString("first_name");
            String user_email = rs1.getString("email");
            int user_id = rs1.getInt("user_id");

            UserData user = new UserData();
            user.setFirstname(first_name);
            user.setLastname(last_name);
            user.setEmail(user_email);
            user.setUserid(user_id);
            all_users.add(user);
        }
        return all_users;
    }
    
    public static int checkJoinStatus(Connection connection, int groupID, int userID) throws SQLException{
        ResultSet rs1 = null;
        PreparedStatement pstm1 = connection.prepareStatement("SELECT * FROM group_members G WHERE G.user_id = ? AND G.group_id = ?");
        pstm1.setInt(1,userID);
        pstm1.setInt(2,groupID);
        rs1 = pstm1.executeQuery();
        if(rs1.next()){
            return 1;
        }
        return 0;
    }
    public static int removeGroupMember(Connection connection, int groupID, String user_email) throws SQLException {
        int return_val = 0;
        PreparedStatement prepared_statement;
        prepared_statement = connection.prepareStatement("DELETE FROM group_members \n"
                + "WHERE group_members.group_id = ? AND group_members.user_id = "
                + "(SELECT U.user_id "
                + "FROM user_data U "
                + "WHERE U.email = ? )");
        prepared_statement.setInt(1, groupID);
        prepared_statement.setString(2,user_email);
        return_val = prepared_statement.executeUpdate();
        return return_val;
    }
    
        public static int addGroupMember(Connection connection, int groupID, String user_email) throws SQLException {
        int return_val = 0;
        PreparedStatement prepared_statement;
        
        Timestamp  sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
        prepared_statement = connection.prepareStatement("INSERT INTO group_members (group_id,user_id,join_date) "
                + "VALUES (?,"
                + "(Select U.user_id FROM user_data U WHERE U.email = ?),"
                + "?)");
        prepared_statement.setInt(1, groupID);
        prepared_statement.setString(2,user_email);
        prepared_statement.setTimestamp(3, sqlDate);
        
        return_val = prepared_statement.executeUpdate();
        return return_val;
    }
    
    

    public static GroupData populateGroup(Connection connection, int groupID) throws SQLException {
        GroupData groupdata = new GroupData();
        ArrayList<PostData> postdata = new ArrayList<PostData>();

        System.out.println("Attempting to find a group with id: " + groupID);
        ResultSet rs1 = null;
        PreparedStatement pstm1 = connection.prepareStatement(
                "SELECT G.user_id, G.group_name, G.creation_date, G.group_type "
                + "FROM group_data G "
                + "WHERE G.group_id  = ?");

        pstm1.setInt(1, groupID);
        rs1 = pstm1.executeQuery();

        if (rs1.next()) {
            int user_id = rs1.getInt("user_id");
            String group_name = rs1.getString("group_name");
            String creation_date = rs1.getString("creation_date");
            String group_type = rs1.getString("group_type");

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
                groupdata.setGroupType(group_type);

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
                } else {
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

     
     public static int findUserIdByEmail(Connection connection, String email) throws SQLException {
         int userId = 0;
         ResultSet result_set = null;
         PreparedStatement prepared_statement = connection.prepareStatement("SELECT user_id"
                + " FROM user_data"
                + " WHERE user_data.email  = ?");
         prepared_statement.setString(1, email);
         result_set = prepared_statement.executeQuery();
         if(result_set.next()) {
             userId = result_set.getInt("user_id");
         }
         return userId;
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

    public static int like_comment(Connection connection, int comment_id, int user_id) throws SQLException {

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
    
//        public static int sendMessage(Connection connection, String subject, String message , int sender, int receiver) throws SQLException{
//        int return_val = 0;
//        PreparedStatement prepared_statement;
//
//        Calendar currenttime = Calendar.getInstance();
//        Date sqldate = new Date((currenttime.getTime()).getTime());
//        prepared_statement = connection.prepareStatement(
//                "INSERT INTO " + 
//        "messages_data(message_date,message_subject,message_content,message_sender,message_receiver) VALUES (?,?,?,?,?)");
//        prepared_statement.setDate(1,sqldate);
//        prepared_statement.setString(2,subject);
//        prepared_statement.setString(3,message);
//        prepared_statement.setInt(4,sender);
//        prepared_statement.setInt(5,receiver);
//        return_val = prepared_statement.executeUpdate();
//        return return_val;
//    }
        
        public static int sendMessage(Connection connection, String subject, String message , int sender, String receiver) throws SQLException{
        int return_val = 0;
        PreparedStatement prepared_statement;
        int message_receiver = findUserIdByEmail(connection,receiver);
        Calendar currenttime = Calendar.getInstance();
        Date sqldate = new Date((currenttime.getTime()).getTime());
        prepared_statement = connection.prepareStatement(
                "INSERT INTO " + 
        "messages_data(message_date,message_subject,message_content,message_sender,message_receiver) VALUES (?,?,?,?,?)");
        prepared_statement.setDate(1,sqldate);
        prepared_statement.setString(2,subject);
        prepared_statement.setString(3,message);
        prepared_statement.setInt(4,sender);
        prepared_statement.setInt(5,message_receiver);
        return_val = prepared_statement.executeUpdate();
        return return_val;
        }
        public static void deleteMessage(Connection connection, int messageId) throws SQLException {
            PreparedStatement prepared_statement = connection.prepareStatement("DELETE FROM messages_data WHERE message_id=?");
        prepared_statement.setInt(1,messageId);
        prepared_statement.executeUpdate();
        }
    
    public static ArrayList<MessageData> get_messages(Connection connection, int user_id ) throws SQLException{
        ResultSet rs1 = null;
        PreparedStatement prepared_statement;
        ArrayList<MessageData> messages = new ArrayList<MessageData>();
        prepared_statement = connection.prepareStatement("SELECT * FROM messages_data WHERE message_receiver=? ");
        prepared_statement.setInt(1,user_id);
        rs1 = prepared_statement.executeQuery();
        int i =0;
        while (rs1.next()) {
            System.out.println("MESSAGES" + i);
            int messageId = rs1.getInt("message_id");
            String message_date = rs1.getString("message_date");
            String message_subject = rs1.getString("message_subject");
            String message_content = rs1.getString("message_content");
            String message_sender = getUser(connection, rs1.getInt("message_sender")).getFirstname() + " " + getUser(connection, rs1.getInt("message_sender")).getLastname();
            String message_receiver = getUser(connection, rs1.getInt("message_receiver")).getFirstname() + " " + getUser(connection, rs1.getInt("message_receiver")).getLastname();
            messages.add(new MessageData(messageId, message_date, message_subject, message_content,rs1.getInt("message_sender"), rs1.getInt("message_receiver"), message_sender,message_receiver));
        }
        if(messages.size() == 0) return null;
        else return messages;
    }   
    
        public static ArrayList<String> get_Posts(Connection connection, int user_id ) throws SQLException{
        ResultSet rs1 = null;
        PreparedStatement prepared_statement;
        ArrayList<String> posts = new ArrayList<String>();
        prepared_statement = connection.prepareStatement("SELECT * FROM post_data WHERE user_id=? ");
        prepared_statement.setInt(1,user_id);
        rs1 = prepared_statement.executeQuery();
        int i =0;
        while (rs1.next()) {
            String post = rs1.getString("content");
            posts.add(post);
        }
        if(posts.size() == 0) return null;
        else return posts;
    }   
        
   public static ArrayList<AdvertisementData> getAds(Connection connection ) throws SQLException{
        ResultSet rs1 = null;
        PreparedStatement prepared_statement;
        ArrayList<AdvertisementData> ads = new ArrayList<AdvertisementData>();
        prepared_statement = connection.prepareStatement("SELECT * FROM advertisement_data");
        rs1 = prepared_statement.executeQuery();
        while (rs1.next()) {
            AdvertisementData ad = new AdvertisementData(rs1.getInt("advertisement_id") ,rs1.getInt("employee_id"), rs1.getString("date_of_ad"), rs1.getString("type_of_ad"), rs1.getString("company"),rs1.getString("item_name"), rs1.getString("content"), rs1.getFloat("unit_price"),rs1.getInt("num_available"));
            ads.add(ad);
        }
        if(ads.size() == 0) return null;
        else return ads;
    }   
        
        
        
        public static void createAdvertisement(Connection connection,int employee_id ,String date_of_ad ,String ty, String company , String item , String content , float unitPrice , int numAvailable ) throws SQLException {
            System.out.println("creating");
            PreparedStatement prepared_statement = connection. prepareStatement("INSERT INTO advertisement_data(employee_id ,date_of_ad , type_of_ad ,company ,item_name , content , unit_price ,num_available) VALUES (?,?,?,?,?,?,?,?)");
            prepared_statement.setInt(1,employee_id);
            prepared_statement.setDate(2,new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            prepared_statement.setString(3,ty);
            prepared_statement.setString(4,company);
            prepared_statement.setString(5,item);
            prepared_statement.setString(6,content);
            prepared_statement.setFloat(7,unitPrice);
            prepared_statement.setInt(8,numAvailable);
            prepared_statement.executeUpdate();
        }

        
public static void delete_advertisement(Connection connection ,int advertisement_id ) throws SQLException{ 
    PreparedStatement prepared_statement = connection. prepareStatement("DELETE FROM advertisement_data WHERE advertisement_id=? ");
    prepared_statement.setInt(1,advertisement_id);
    prepared_statement.executeUpdate();
}

 public static ArrayList<AdvertisementData> get_advertisements(Connection connection ) throws SQLException{
        ResultSet rs1 = null;
        PreparedStatement prepared_statement;
        ArrayList<AdvertisementData> ads = new ArrayList<AdvertisementData>();
        prepared_statement = connection.prepareStatement("SELECT * FROM advertisement_data ");
        rs1 = prepared_statement.executeQuery();
        while (rs1.next()) {
            int advertisement_id = rs1.getInt("message_id");
            int employee_id = rs1.getInt("employee_id");
            String date_of_ad = rs1.getString("date_of_ad");
            String type_of_ad = rs1.getString("type_of_ad");
            String company = rs1.getString("company");
            String item_name = rs1.getString("item_name");
            String content = rs1.getString("content");
            float unit_price = rs1.getFloat("unit_price");
            int num_available = rs1.getInt("num_available");
            ads.add(new AdvertisementData(advertisement_id,employee_id,date_of_ad,type_of_ad,company,item_name,content,unit_price,num_available));
        }
        if(ads.size() == 0) return null;
        else return ads;
    }   
 
 public static void generateTransaction(Connection connection  ,int ad_id , int seller_id , int consumer_id , java.sql.Date date , int number_of_units , int account_number) throws SQLException{
     PreparedStatement prepared_statement = connection.prepareStatement("INSERT INTO sale_data( ad_id, seller_id ,consumer_id , transaction_date ,number_of_units , account_number) VALUES (?,?,?,?,?,?)");

     prepared_statement.setInt(1,ad_id);
     prepared_statement.setInt(2,seller_id);
     prepared_statement.setInt(3,consumer_id);
     prepared_statement.setDate(4,date);
     prepared_statement.setInt(5,number_of_units);
     prepared_statement.setInt(6,account_number);
     prepared_statement.executeUpdate();
 }
 
 public static ArrayList<String> getMailingList(Connection connection )
throws SQLException{
     ResultSet rs1;
     PreparedStatement prepared_statement = connection.prepareStatement("SELECT * FROM user_data WHERE email IS NOT NULL");
     rs1 =  prepared_statement.executeQuery();
     ArrayList<String> rval = new ArrayList<String>();
     while(rs1.next()) {
         rval.add(rs1.getString("email"));
     }
     return rval;
 }
 
 
public static ArrayList<String> getSuggestedItems( Connection connection , String email ) throws SQLException {
    PreparedStatement prepared_statement = connection. prepareStatement("SELECT * FROM sale_data WHERE consumer_id=?");
    int u = findUserIdByEmail(connection, email);
    System.out.println("User is" + email + u);
    prepared_statement.setInt(1,u);
    ResultSet rs1 =  prepared_statement.executeQuery();
    ArrayList<Integer> ids = new ArrayList<Integer>();
    while(rs1.next()) {
        int ad_id = rs1.getInt("ad_id");
        System.out.println("AD ID IS" + ad_id); 
        ids.add(ad_id);
    }
    ArrayList<String> suggestions = new ArrayList<String>();
    System.out.println("ok"+ids.size());
    for(int i = 0; i < ids.size(); i++) {
        prepared_statement = connection.prepareStatement("SELECT * FROM advertisement_data WHERE advertisement_id=?");
        prepared_statement.setInt(1,ids.get(i));        
        rs1 = prepared_statement.executeQuery();
        
        rs1.first();
        String company = rs1.getString("company");
        String item = rs1.getString("item_name");
        suggestions.add(item + " sold by " +company);
    }
    return suggestions;
}

public static ArrayList<String> getCustomerGroups( Connection connection ,String email ) throws SQLException{
    PreparedStatement prepared_statement = connection. prepareStatement("SELECT * FROM group_data WHERE user_id=?");
    int u = findUserIdByEmail(connection, email);
    prepared_statement.setInt(1,u);
    ResultSet rs1 =  prepared_statement.executeQuery();
    ArrayList<String> groupNames = new ArrayList<String>();
    while(rs1.next()) {
        groupNames.add(rs1.getString("group_name"));
    }
    return groupNames;
}
public static ArrayList<String> getCustomerGroupsWithId( Connection connection ,String email ) throws SQLException{
    PreparedStatement prepared_statement = connection. prepareStatement("SELECT * FROM group_data WHERE user_id=?");
    int u = findUserIdByEmail(connection, email);
    prepared_statement.setInt(1,u);
    ResultSet rs1 =  prepared_statement.executeQuery();
    ArrayList<String> groupNames = new ArrayList<String>();
    while(rs1.next()) {
        groupNames.add(rs1.getString("group_name") + "~" + rs1.getInt("group_id"));
    }
    return groupNames;
}

    public static int editComment(Connection connection,
            int comment_id, String content)
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

    public static int renameGroup(Connection connection,
            int group_id, String new_group_name)
            throws SQLException {

        int return_val;
        PreparedStatement prepared_statement;
        prepared_statement = connection.prepareStatement("UPDATE group_data "
                + "SET group_data.group_name = ? WHERE group_data.group_id  = ?");
        prepared_statement.setString(1, new_group_name);
        prepared_statement.setInt(2, group_id);
        return_val = prepared_statement.executeUpdate();

        return return_val;
    }
    
    
     public static int deleteGroup(Connection connection,int group_id) throws SQLException {
        PreparedStatement prepared_statement;
        int return_val = 0;
        prepared_statement = connection.prepareStatement("DELETE FROM "
                + "group_data "
                + "WHERE group_data.group_id = ?");
        prepared_statement.setInt(1, group_id);
        return_val = prepared_statement.executeUpdate();
        return return_val;
    }
     
     
     public static int createGroup(Connection connection,
            int user_id, String group_name, String group_type)
            throws SQLException {
        
        int group_id = -1;
        PreparedStatement prepared_statement;
        prepared_statement = connection.prepareStatement(" INSERT INTO group_data"
                + "(user_id,group_name,group_type,creation_date) "
                + "VALUES (?,?,?,?)");

        prepared_statement.setInt(1, user_id);
        prepared_statement.setString(2, group_name);
        prepared_statement.setString(3, group_type);
        prepared_statement.setTimestamp(4,
                new java.sql.Timestamp(new java.util.Date().getTime()));
        prepared_statement.executeUpdate();

        ResultSet result_set = null;
        prepared_statement = connection.prepareStatement(
                "SELECT group_id"
                + " FROM group_data"
                + " WHERE group_data.user_id  = ? and group_data.group_name = ?");
        prepared_statement.setInt(1, user_id);
        prepared_statement.setString(2, group_name);
        result_set = prepared_statement.executeQuery();

        if (result_set.next()) {
            group_id = result_set.getInt("group_id");
            prepared_statement = connection.prepareStatement(" INSERT INTO page_data"
                    + "(page_owner,associated_group,post_count) "
                    + "VALUES (?,?,?)");
            prepared_statement.setInt(1, user_id);
            prepared_statement.setInt(2, result_set.getInt("group_id"));
            prepared_statement.setInt(3, 0);
            prepared_statement.executeUpdate();
        }
        
        return group_id;
    }

    public static ArrayList<String> getBestSellers(Connection connection ) throws SQLException{
PreparedStatement prepared_statement = connection. prepareStatement("SELECT ad_id,number_of_units,COUNT(*) FROM sale_data GROUP BY ad_id,number_of_units ORDER BY 2 DESC");
ResultSet rs1= prepared_statement.executeQuery();
 ArrayList<Integer> ids = new ArrayList<Integer>();
 ArrayList<Integer> units = new ArrayList<Integer>();

    while(rs1.next()) {
        int ad_id = rs1.getInt("ad_id");
        System.out.println("AD ID IS" + ad_id); 
        ids.add(ad_id);
        units.add(rs1.getInt("number_of_units"));
    }
    ArrayList<String> suggestions = new ArrayList<String>();
    System.out.println("ok"+ids.size());
    for(int i = 0; i < ids.size(); i++) {
        prepared_statement = connection.prepareStatement("SELECT * FROM advertisement_data WHERE advertisement_id=?");
        prepared_statement.setInt(1,ids.get(i));        
        rs1 = prepared_statement.executeQuery();
        
        rs1.first();
        String company = rs1.getString("company");
        String item = rs1.getString("item_name");
        suggestions.add(item + " sold by " +company +  "which sold " + units.get(i) + " units");
    }
    return suggestions;
}

    public static void register_user(Connection connection ,String first_name , String last_name, String address, String email, String password) throws SQLException{ 
        System.out.println("FROM DBUTILS" + last_name+first_name+email+password+address);
        PreparedStatement prepared_statement =connection.prepareStatement("INSERT INTO user_data(last_name ,first_name,email, address,account_number) VALUES (?,?,?,?,?)");
        prepared_statement.setString(1,last_name);
        prepared_statement.setString(2,first_name);
        prepared_statement.setString(3,email);
        prepared_statement.setString(4,address);        
        int rand1 = (int)(Math.random()* 99999999);
        int rand2 = (int)(Math.random()* 99999999);
        prepared_statement.setString(5, String.valueOf(rand1) +String.valueOf(rand2));
        prepared_statement.execute();
        
        
         prepared_statement =connection.prepareStatement("INSERT INTO login(user_email ,user_password,user_id,account_type) VALUES (?,?,?,?)");
        prepared_statement.setString(1,email);
        prepared_statement.setString(2,password);
        prepared_statement.setInt(3,findUserIdByEmail(connection,email));
        prepared_statement.setInt(4,1);
        prepared_statement.execute();
    }
 }


