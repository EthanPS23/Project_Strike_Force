package servicerestjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    /* Used to connect to the database and server hosted on a local raspberry pi
	* Author: Ethan Shipley
	* Course CMPP 264
	* Date: April 7 2019
	*/
	public static Connection getConnection()
    {
        Connection conn = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.163.37.7:3306/travelexperts", "harv", "password");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
