/* Name: Alex Spiegl
Course: CNT 4714 – Spring 2023 – Project Four
Assignment title: A Three-Tier Distributed Web-Based Application
Date: April 24, 2023
*/

package project4;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;
import java.util.Properties;
import com.mysql.cj.jdbc.MysqlDataSource;

public class rootServlet extends HttpServlet {

	private Connection connection;	
	private Statement statement;
	
	public void dbconnection() throws ServletException {
		Properties properties = new Properties();
		FileInputStream filein = null;
		MysqlDataSource dataSource = null;
		try {
			filein = new FileInputStream("root.PROPERTIES");
			properties.load(filein);
			dataSource = new MysqlDataSource();
			dataSource.setUrl(properties.getProperty("MYSQL_DB_URL"));
			dataSource.setUrl(properties.getProperty("MYSQL_DB_USERNAME"));
			dataSource.setUrl(properties.getProperty("MYSQL_DB_PASSWORD"));
			connection = dataSource.getConnection();
		}
		catch ( SQLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
	public void destroy() {
		try {
			statement.close();
			connection.close();
		}catch(SQLException e) {
			System.out.println("Error closing the db connection: " + e.getMessage());
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request,response);
	}
	
	private int simpleBusinessLogic(Connection connection, Statement statement, String sqlStatement) {
		int numRowsAffected = 0;
		String sBLCommand = "update suppliers set status = status + 5 where snum in (select distinct snum from shipments where quantity >= 100);";
		try {
			//execute original user update command
			numRowsAffected = statement.executeUpdate(sqlStatement);
			//execute the simple version of the business logic
			numRowsAffected = statement.executeUpdate(sBLCommand);
		} catch (SQLException e) {
			System.out.println("Error opening the db connection: " + e.getMessage());
		} //end try-catch
		return numRowsAffected;
	}
	
	public void init() throws ServletException {
		try {
			ServletConfig config = getServletConfig();
			String dbDriver = config.getInitParameter( "databaseDriver");
			String dbURL = config.getInitParameter( "databaseName");
			String username = config.getInitParameter( "username");
			String password = config.getInitParameter( "password");
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(dbURL, username, password);
		}
		catch ( Exception exception ) {
			exception.printStackTrace();
			throw new UnavailableException( exception.getMessage() );
		}
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String sqlStatement = request.getParameter("sqlStatement");
		String message = " ";
		
		try {
			statement = connection.createStatement();
			sqlStatement = sqlStatement.trim();
			String sqlType = sqlStatement.substring(0, 6);
			int mysqlReturnVal = 0;
			if(sqlType.equalsIgnoreCase("select")) {
				ResultSet resultSet = statement.executeQuery(sqlStatement);
			} else { 
				if (sqlStatement.contains("shipments")){ 
					message = "<p style='background-color:chartreuse; border:3px; display: inline-block;" 
							+ "border-style:solid; border-color:black; text-align:center'>"
							+ "<b>The statement executed succesfully.<br>"
							+ mysqlReturnVal + " row(s) affected.<br><br>"
							+ "Business Logic Detected! - Updating Supplier Status <br><br>"
							+ "Business Logic updated " + mysqlReturnVal + " supplier status marks.</b><br></p>";
					
				} 
			else { 
				mysqlReturnVal = statement.executeUpdate(sqlStatement); 
				if (mysqlReturnVal != 0) { 
					message = "<p style='background-color:chartreuse; border:3px; display: inline-block;" 
							+ "border-style:solid; border-color:black; text-align:center'>"
							+ "<b>The statement executed succesfully.<br><br>"
							+ "Business Logic Not Triggered!</b><br></p>";
				} else {
					message = "<p style='background-color:chartreuse; border:3px; display: inline-block;" 
							+ "border-style:solid; border-color:black; text-align:center'>"
							+ "<b>The statement executed succesfully.<br>"
							+ mysqlReturnVal + " row(s) affected.</b><br></p>";
				}
			}
			statement.close();
			} 
		}
		catch(SQLException e ) {
			message = "<tr bgcolor=#ff0000><td><font color=#ffffff><b>Error executing the SQL statement:</b><br>" + e.getMessage() + "</tr></td></font>";
		}
		HttpSession session = request.getSession();
		session.setAttribute("message", message);
		session.setAttribute("sqlStatement", sqlStatement);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/rootHome.jsp");
		dispatcher.forward(request, response);
	}
}