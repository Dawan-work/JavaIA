package jdbc.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DbConnector {
    private static Connection connection = null;
    private static String dbURL = "jdbc:mariadb://localhost:3307/javaiajdbc";
    private static String dbUser = "root";
    private static String dbPass = "";

    private DbConnector() {
    }

    public static Connection getInstance() throws ClassNotFoundException, SQLException {
        if(connection == null) {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL,dbUser,dbPass);
            connection.setAutoCommit(false); // Prevent action before commit call
        }
        return connection;
    }
}
