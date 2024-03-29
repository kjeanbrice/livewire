/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import general.AdvertisementData;
import general.UserData;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
@WebServlet(name = "ProcessManagerTransactions", urlPatterns = {"/ProcessManagerTransactions"})
public class ProcessManagerTransactions extends HttpServlet {

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
System.out.println(transaction_type);
            transaction_type = transaction_type.trim();
            if (transaction_type.equals("GET_COMPANY_ITEMS")) {
                String company = request.getParameter("company");
                ArrayList<String> items = DatabaseUtils.getItemsByCompany(connection, company);
                String returnString = "{\"items\":[ ";
                for(int i = 0; i < items.size();i++) {
                    returnString += "\"" + items.get(i) +  "\"";
                   returnString += ",";
                }
                returnString = returnString.substring(0,returnString.length()-1);
                returnString+="]}";
                response.setContentType("application/json");
                out.print(returnString);
                out.flush();
                GenUtils.closeConnection(request);
            }
            
            else if(transaction_type.equals("GET_WHO_BOUGHT")){
              String item_name = request.getParameter("item_name");
                ArrayList<String> names = DatabaseUtils.customersBoughtItem(connection, item_name);
                String returnString = "{\"names\":[ ";
                for(int i = 0; i < names.size();i++) {
                    returnString += "\"" + names.get(i) +  "\"";
                   returnString += ",";
                }
                returnString = returnString.substring(0,returnString.length()-1);
                returnString+="]}";
                response.setContentType("application/json");
                out.print(returnString);
                out.flush();
                GenUtils.closeConnection(request);
            } else if(transaction_type.equals("TRANSACTION_BY_EMAIL")) {
                String email = request.getParameter("email");
                String returnString = DatabaseUtils.transactionByUsername(connection, email);

                response.setContentType("application/json");
                out.print(returnString);
                out.flush();
                GenUtils.closeConnection(request);
            }
            else if(transaction_type.equals("TRANSACTION_BY_ITEM")) {
                String item_name = request.getParameter("item_name");
                String returnString = DatabaseUtils.transactionByUserItem(connection, item_name);

                response.setContentType("application/json");
                out.print(returnString);
                out.flush();
                GenUtils.closeConnection(request);
            }
            else if (transaction_type.equals("CUSTOMER_GROUPS")) {
              ArrayList<String> items = DatabaseUtils.getCustomerGroups(connection,request.getParameter("email"));
                String returnString = "{\"groups\":[ ";
                for(int i = 0; i < items.size();i++) {
                   returnString += "\"" + items.get(i) +  "\"";
                   returnString += ",";
                }
                returnString = returnString.substring(0,returnString.length()-1);
                returnString+="]}";
                response.setContentType("application/json");
                out.print(returnString);
                out.flush();
                GenUtils.closeConnection(request);

            } else if (transaction_type.equals("CUSTOMER_GROUPS_ALL")) {
              ArrayList<String> items = DatabaseUtils.getAllGroups(connection);
                String returnString = "{\"groups\":[ ";
                for(int i = 0; i < items.size();i++) {
                   returnString += "\"" + items.get(i) +  "\"";
                   returnString += ",";
                }
                returnString = returnString.substring(0,returnString.length()-1);
                returnString+="]}";
                response.setContentType("application/json");
                out.print(returnString);
                out.flush();
                GenUtils.closeConnection(request);

            }
            
            else if (transaction_type.equals("CUSTOMER_GROUPSWITH_ID")) {
              ArrayList<String> items = DatabaseUtils.getCustomerGroupsWithId(connection,request.getParameter("email"));
                String returnString = "{\"groups\":[ ";
                for(int i = 0; i < items.size();i++) {
                   returnString += "\"" + items.get(i) +  "\"";
                   returnString += ",";
                }
                returnString = returnString.substring(0,returnString.length()-1);
                returnString+="]}";
                response.setContentType("application/json");
                out.print(returnString);
                out.flush();
                GenUtils.closeConnection(request);

            }

            else if (transaction_type.equals("BEST_SELLER")) {
              ArrayList<String> items = DatabaseUtils.getBestSellers(connection);
                String returnString = "{\"groups\":[ ";
                for(int i = 0; i < items.size();i++) {
                   returnString += "\"" + items.get(i) +  "\"";
                   returnString += ",";
                }
                returnString = returnString.substring(0,returnString.length()-1);
                returnString+="]}";
                response.setContentType("application/json");
                out.print(returnString);
                out.flush();
                GenUtils.closeConnection(request);

            } else if(transaction_type.equals("GENERATE_TRANSACTION")) {

            Calendar currenttime = Calendar.getInstance();
            Date sqldate = new Date((currenttime.getTime()).getTime());
                DatabaseUtils.generateTransaction(connection  ,
                        Integer.parseInt(request.getParameter("ad_id")) ,
                        DatabaseUtils.findUserIdByEmail(connection, request.getParameter("seller_id")) ,
                        DatabaseUtils.findUserIdByEmail(connection, request.getParameter("consumer_id")) , 
                        sqldate , 
                       Integer.parseInt(request.getParameter("number_of_units")) , 
                       DatabaseUtils.findUserAccountByEmail(connection, request.getParameter("consumer_id")));
            } else if(transaction_type.equals("GET_USERS")) {

                ArrayList<UserData> users = DatabaseUtils.getAllUsers(connection);
                   String returnString = "{\"users\":[ ";
                for(int i = 0; i < users.size();i++) {
                   returnString += ((UserData)(users.get(i))).generateJSON();
                   returnString += ",";
                }
                returnString = returnString.substring(0,returnString.length()-1);
                returnString+="]}";
                System.out.println(returnString);
                response.setContentType("application/json");
                out.print(returnString);
                out.flush();
                
            } else if(transaction_type.equals("DELETE_USER")) {
               int user_id = Integer.parseInt(request.getParameter("user_id"));
               DatabaseUtils.deleteUser(connection, user_id);
                
            }else if(transaction_type.equals("DELETE_ADVERTISEMENT")) {
               int user_id = Integer.parseInt(request.getParameter("ad_id"));
               DatabaseUtils.delete_advertisement(connection, user_id);
                
            } 
            
            else if(transaction_type.equals("UPDATE_USER")) {
                        System.out.println("updating");
System.out.println(request.getQueryString());
                int update_user_id = Integer.parseInt(request.getParameter("user_id"));
                   String last_name = (String)request.getParameter("last_name");
                  
                   String first_name = (String)request.getParameter("first_name");
        String email = (String)request.getParameter("email");
        String password = (String)request.getParameter("password");
        String address = (String)request.getParameter("address");
               DatabaseUtils.updateUser(connection, update_user_id, email, password, address, first_name, last_name);
                
            }
             else if(transaction_type.equals("BUY")) {
                String product = request.getParameter("product");
                String supplier = request.getParameter("supplier");
                String email = request.getParameter("email");
                System.out.println(request.getQueryString());
               DatabaseUtils.buyGivenProductAndSupplier(connection, product, supplier, email);
                
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
