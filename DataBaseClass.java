package DatabasePackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseClass {

	public Connection con;
	public Statement statement;

	// method for creating statement
	public Statement getStatement() throws ClassNotFoundException, SQLException {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String connection = "jdbc:mysql://localhost:3306/product";
			String username = "root";
			String password = "password";
			Class.forName(driver);
			/*
			 * once we have above information, then we need to register the
			 * driver & for that we use Class.forName(driver) for driver reg.
			 */

			/*
			 * After the above once, we need to make a connection to DB, for
			 * that we use 'DriverManager' DriverManager is basically a class.
			 * It has getConnection method in it.
			 */
			con = DriverManager.getConnection(connection, username, password);
			// so this will create a connection to database

			statement = con.createStatement();
			/*
			 * On this connection we need to create a statement coz once we have
			 * the statement then only we can execute the sql query.
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return statement;
	}

	public void insertData(String query) throws ClassNotFoundException, SQLException {
		Statement sta = getStatement();
		sta.executeUpdate(query);
	}

	public ResultSet getData(String query) throws ClassNotFoundException, SQLException {
		ResultSet data = getStatement().executeQuery(query);
		return data;
	}

	public void updateData(String query) throws ClassNotFoundException, SQLException {
		getStatement().executeUpdate(query);

	}
}
