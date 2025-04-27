package hotel.databaseOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Database connection handler.
 * @author Faysal Ahmed
 */
public class DataBaseConnection {

    // JDBC URL for the database
    static String url = "jdbc:mysql://localhost:3306/hotel?useUnicode=true" +
            "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&" +
            "serverTimezone=UTC";

    // Logger for handling log messages
    private static final Logger LOGGER = Logger.getLogger(DataBaseConnection.class.getName());

    /**
     * Connects to the database using environment variables for credentials.
     *
     * @return Connection to the database or null if connection fails.
     */
    public static Connection connectTODB() {
        String dbUser = System.getenv("DB_USER");
        String dbPass = System.getenv("DB_PASS");

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Return the database connection
            return DriverManager.getConnection(url, dbUser, dbPass);
        } catch (SQLException | ClassNotFoundException e) {
            // Log the exception at the SEVERE level for critical failures
            LOGGER.log(Level.SEVERE, "Database connection error", e);
            return null;
        }
    }
}
