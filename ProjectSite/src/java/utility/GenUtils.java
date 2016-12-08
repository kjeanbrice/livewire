package utility;

import general.MessageData;
import java.sql.*;
import general.UserData;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GenUtils {

    public static final String CONNECTION_NAME = "CONNECTION_ATTRIBUTE";

    public static void setConnection(ServletRequest request, Connection connection) {
        request.setAttribute(CONNECTION_NAME, connection);
    }

    public static Connection getConnection(ServletRequest request) {
        Connection connection = (Connection) request.getAttribute(CONNECTION_NAME);
        return connection;
    }

    public static void closeConnection(ServletRequest request) throws SQLException {
        Connection connection = (Connection) request.getAttribute(CONNECTION_NAME);
        if (connection != null) {
            connection.close();
        }
        request.removeAttribute(CONNECTION_NAME);
    }

    public static Connection setUpConnection(String user_,String password_,String server_address_) throws ClassNotFoundException, SQLException {

        /*The username of your database*/
        String user = user_;

        /*The password of your database*/
        String password = password_;

        String server_address = server_address_;

        System.out.println("Connecting to driver...");
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Connection Successful");

        Connection connection = DriverManager.getConnection(server_address, user, password);
        return connection;
    }

    public static void storeUserData(HttpSession session, UserData user_data) {
        session.setAttribute("SIGNED_IN_USER", user_data);
    }

    public static UserData getUserData(HttpSession session) {
        UserData user_data = (UserData) session.getAttribute("SIGNED_IN_USER");
        return user_data;
    }
    
}
