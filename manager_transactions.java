public static void addEmployee(Connection connection, PreparedStatement prepared_statement,int employeeId, int social, String lastName, String firstName, String address, String city, String state, int zipcode, int telephone, java.sql.Date date, Double hourly) throws SQLException{
	prepared_statement = connection.prepareStatement("INSERT INTO employee_data(social,lastName,firstName,city,state,zipcode,telephone,startDate,hourly,address,employee_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
	prepared_statement.setInt(1,employeeId);
	prepared_statement.setInt(2,social);
	prepared_statement.setString(3,lastName);
	prepared_statement.setString(4,firstName);
	prepared_statement.setString(5,address);
	prepared_statement.setString(6,city);
	prepared_statement.setString(7,state);
	prepared_statement.setInt(8,zipcode);
	prepared_statement.setInt(9,telephone);
	prepared_statement.setDate(10,date);
	prepared_statement.setDouble(10,hourly);
	prepared_statement.executeUpdate();
}

public static void editEmployee(Connection connection, PreparedStatement prepared_statement,int employeeId, String fieldToEdit, java.sql.Date fieldContents) throws SQLException{
	prepared_statement = connection.prepareStatement("UPDATE employee_data SET "+fieldToEdit+"=? WHERE employee_id=?");
	prepared_statement.setDate(1,fieldContents);
	prepared_statement.setInt(2,employeeId);
	prepared_statement.executeUpdate();
}

public static void editEmployee(Connection connection, PreparedStatement prepared_statement,int employeeId, String fieldToEdit, String fieldContents) throws SQLException{
	prepared_statement = connection.prepareStatement("UPDATE employee_data SET "+fieldToEdit+"=? WHERE employee_id=?");
	prepared_statement.setString(1,fieldContents);
	prepared_statement.setInt(2,employeeId);
	prepared_statement.executeUpdate();
}
public static void editEmployee(Connection connection, PreparedStatement prepared_statement,int employeeId, String fieldToEdit, int fieldContents) throws SQLException{
	prepared_statement = connection.prepareStatement("UPDATE employee_data SET "+fieldToEdit+"=? WHERE employee_id=?");
	prepared_statement.setInt(1,fieldContents);
	prepared_statement.setInt(2,employeeId);
	prepared_statement.executeUpdate();
}

public static void editEmployee(Connection connection, PreparedStatement prepared_statement,int employeeId, String fieldToEdit, double fieldContents) throws SQLException{
	prepared_statement = connection.prepareStatement("UPDATE employee_data SET "+fieldToEdit+"=? WHERE employee_id=?");
	prepared_statement.setDate(1,fieldContents);
	prepared_statement.setDouble(2,employeeId);
	prepared_statement.executeUpdate();
}

public static void getMonthSales(Connection connection, PreparedStatement prepared_statement,int transactionMonth) throws SQLException{
	prepared_statement = connection.prepareStatement("SELECT * FROM sale_data WHERE MONTH(transaction_date)=?");
	prepared_statement.setInt(1,transactionMonth);
	prepared_statement.executeUpdate();
}

public static void getAdvertisedItems(Connection connection, PreparedStatement prepared_statement) throws SQLException{
	prepared_statement = connection.prepareStatement("SELECT item_name FROM advertisement_data");
	prepared_statement.executeUpdate();
}

public static void getTransactions(Connection connection, PreparedStatement prepared_statement, String itemname) throws SQLException{
	prepared_statement = connection.prepareStatement("SELECT s.*, a.item_name FROM sale_data s, advertisement_data a WHERE s.ad_id=a.advertisement_id AND a.item_name=?");
	prepared_statement.setString(1,itemName);
	prepared_statement.executeUpdate();
}

public static void getTransactions(Connection connection, PreparedStatement prepared_statement, String firtName, String lastName) throws SQLException{
	prepared_statement = connection.prepareStatement("SELECT s.*, u.first_name, u.last_name FROM sale_data s, advertisement_data a, user_data u WHERE s.ad_id=a.advertisement_id AND s.consumer_id=u.user_id AND u.first_name=? AND u.last_name=?");
	prepared_statement.setString(1,firstName);
	prepared_statement.setString(2,lastName);
	prepared_statement.executeUpdate();
}

public static void getRevenueByItemType(Connection connection, PreparedStatement prepared_statement, String itemtype) throws SQLException{
	prepared_statement = connection.prepareStatement("SELECT a.type_of_ad, a.unit_price, s.* FROM sale_data s, advertisement_data a WHERE s.ad_id=a.advertisement_id AND a.type_of_ad=?");
	prepared_statement.setString(1,itemtype);
	prepared_statement.executeUpdate();
}

public static void getRevenueByItemName(Connection connection, PreparedStatement prepared_statement, String itemname) throws SQLException{
	prepared_statement = connection.prepareStatement("SELECT a.item_name, a.unit_price, s.* FROM sale_data s, advertisement_data a WHERE s.ad_id=a.advertisement_id AND a.item_name=?");
	prepared_statement.setString(1,itemname);
	prepared_statement.executeUpdate();
}

public static void getRevenueByCustomer(Connection connection, PreparedStatement prepared_statement, int customerId) throws SQLException{
	prepared_statement = connection.prepareStatement("SELECT s.*, a.unit_price FROM sale_data s, advertisement_data a WHERE s.ad_id=a.advertisement_id AND s.consumer_id=?");
	prepared_statement.setString(1,customerId);
	prepared_statement.executeUpdate();
}

public static void getBestSellingRepresentative(Connection connection, PreparedStatement prepared_statement) throws SQLException{
	prepared_statement = connection.prepareStatement("SELECT e.employee_id AS employeeId, SUM(s.number_of_units * a.unit_price) AS totalRevenue FROM sale_data s, advertisement_data a, employee_data e WHERE s.ad_id=a.advertisement_id AND e.employee_id=a.employee_id");
	prepared_statement.setString(1,customerId);
	prepared_statement.executeUpdate();
}
public static void getBestBuyingConsumer(Connection connection, PreparedStatement prepared_statement) throws SQLException{
	prepared_statement = connection.prepareStatement("SELECT u.user_id AS userId, SUM(s.number_of_units * a.unit_price) AS totalRevenue FROM sale_data s, advertisement_data a, user_data u WHERE s.ad_id=a.advertisement_id AND u.user_id=s.consumer_id");
	prepared_statement.setString(1,customerId);
	prepared_statement.executeUpdate();
}

public static void getMostActiveItems(Connection connection, PreparedStatement prepared_statement) throws SQLException{
	prepared_statement = connection.prepareStatement("SELECT item_name,COUNT(*) AS MostActiveItem FROM advertisement_data GROUP BY item_name ORDER BY MostActiveItem DESC LIMIT 1");
	prepared_statement.executeUpdate();
}

public static void getCustomerPurchasedItem(Connection connection, PreparedStatement prepared_statement, String itemname) throws SQLException{
	prepared_statement = connection.prepareStatement("SELECT a.item_name, u.first_name, u.last_name FROM sale_data s, advertisement_data a, user_data u WHERE s.ad_id=a.advertisement_id AND s.consumer_id=u.user_id AND a.item_name=");
	prepared_statement.setString(1,itemname);
	prepared_statement.executeUpdate();
}

public static void getCompanyItems(Connection connection, PreparedStatement prepared_statement, String company) throws SQLException{
	prepared_statement = connection.prepareStatement("SELECT a.item_name, a.company FROM advertisement_data a WHERE a.company=?);
	prepared_statement.setString(1,company);
	prepared_statement.executeUpdate();
}