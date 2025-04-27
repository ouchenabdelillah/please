package hotel.databaseOperation;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Faysal Ahmed
 */
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
