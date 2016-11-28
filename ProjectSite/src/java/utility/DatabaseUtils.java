/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import general.UserData;
import java.sql.*;

/**
 *
 * @author Karl
 */
public class DatabaseUtils {

    private static final int USER = 1;
    private static final int EMPLOYEE = 2;

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
                    UserData user = new UserData(user_id, last_name, first_name, address, city, state, zip_code, telephone, email, account_number, credit_card, rating, account_type,user_password);
                    return user;
                }

            }

        }

        return null;
    }

}
