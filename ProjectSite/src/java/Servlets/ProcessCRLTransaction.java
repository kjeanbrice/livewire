/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import general.AdvertisementData;
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
@WebServlet(name = "ProcessCRLTransaction", urlPatterns = {"/ProcessCRLTransaction"})
public class ProcessCRLTransaction extends HttpServlet {

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
            if (transaction_type.equals("CREATE_ADVERTISEMENT")) {
                                System.out.println("asdf");

                String employee_id = request.getParameter("employee_id");
                String date_of_ad = request.getParameter("date_of_ad");
                String type = request.getParameter("type");
                String company = request.getParameter("company");
                String item = request.getParameter("item");
                String content = request.getParameter("content");
                String unit_price = request.getParameter("unit_price");
                String num_available = request.getParameter("num_available");
                DatabaseUtils.createAdvertisement( connection,Integer.parseInt(employee_id) ,date_of_ad ,type,  company ,  item ,  content , Float.parseFloat(unit_price) , Integer.parseInt(num_available));

            } else if (transaction_type.equals("GET_ADS")) {
                ArrayList<AdvertisementData> ads = DatabaseUtils.getAds(connection);
                String returnString = "{\"ads\":[ ";
                for(int i = 0; i < ads.size();i++) {
                   returnString += ((AdvertisementData)(ads.get(i))).generateJSON();
                   returnString += ",";
                }
                returnString = returnString.substring(0,returnString.length()-1);
                returnString+="]}";
                response.setContentType("application/json");
                out.print(returnString);
                out.flush();
                GenUtils.closeConnection(request);
            } else if(transaction_type.equals("ITEM_SUGGESTION")){
                ArrayList<String> items = DatabaseUtils.getSuggestedItems(connection,request.getParameter("email"));
                String returnString = "{\"suggestions\":[ ";
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
            } else if(transaction_type.equals("CUSTOMER_LIST")) {
                ArrayList<String> emails = DatabaseUtils.getMailingList(connection);
                String returnString = "{\"emails\":[ ";
                for(int i = 0; i < emails.size();i++) {
                   returnString += "\"" + emails.get(i) +  "\"";
                   returnString += ",";
                }
                returnString = returnString.substring(0,returnString.length()-1);
                returnString+="]}";
                response.setContentType("application/json");
                out.print(returnString);
                out.flush();
                GenUtils.closeConnection(request);
            } else if (transaction_type.equals("CUSTOMER_GROUPS")) {
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

            } else if (transaction_type.equals("CUSTOMER_GROUPSWITH_ID")) {
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
                                System.out.println(request.getParameter("number_of_units"));

            Calendar currenttime = Calendar.getInstance();
            Date sqldate = new Date((currenttime.getTime()).getTime());
                DatabaseUtils.generateTransaction(connection  ,
                        Integer.parseInt(request.getParameter("ad_id")) ,
                        Integer.parseInt(request.getParameter("seller_id")) ,
                        Integer.parseInt(request.getParameter("consumer_id")) , 
                        sqldate , 
                       Integer.parseInt(request.getParameter("number_of_units")) , 
                       Integer.parseInt(request.getParameter("account_number")));
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
