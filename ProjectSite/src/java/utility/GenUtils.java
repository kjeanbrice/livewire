
package utility;

import java.sql.*;
import general.UserData;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GenUtils {
    
    public static final String CONNECTION_NAME = "CONNECTION_ATTRIBUTE";
    
    public static void setConnection(ServletRequest request, Connection connection){
        request.setAttribute(CONNECTION_NAME,connection);
    }
    
    public static Connection getConnection(ServletRequest request){
        Connection connection = (Connection)request.getAttribute(CONNECTION_NAME);
        return connection;
    }
    
      public static void closeConnection(ServletRequest request) throws SQLException{
        Connection connection = (Connection)request.getAttribute(CONNECTION_NAME);
        if(connection != null){
            connection.close();
        }   
        request.removeAttribute(CONNECTION_NAME);
    }
    
    
    
    
    public static void storeUserData(HttpSession session,UserData user_data){
        session.setAttribute("SignedInUser",user_data);
    }
    
    public static UserData getUserData(HttpSession session){
        UserData user_data = (UserData)session.getAttribute("SignedInUser");
        return user_data;
    }
    
    
    
    
}
