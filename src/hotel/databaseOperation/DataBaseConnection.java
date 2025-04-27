package hotel.databaseOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Faysal Ahmed
 */
class DataBaseConnectionTest {

    @Test
    void testConnectTODB() {
        Connection conn = DataBaseConnection.connectTODB();
        assertNotNull(conn, "Database connection should not be null if the database is running.");
        try {
            assertFalse(conn.isClosed(), "Connection should be open");
            conn.close(); // cleanup
        } catch (Exception e) {
            fail("Exception during connection test: " + e.getMessage());
        }
    }
}

public class DataBaseConnection {

    static String url = "jdbc:mysql://localhost:3306/hotel?useUnicode=true" +
            "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&" +
            "serverTimezone=UTC";

    public static Connection connectTODB() {
        String dbUser = System.getenv("DB_USER");
        String dbPass = System.getenv("DB_PASS");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, dbUser, dbPass);
        } catch (Exception e) {
            System.err.println("Connection error");
            e.printStackTrace();
            return null;
        }
    }
}
