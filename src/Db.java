import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private static final String URL  = "jdbc:postgresql://localhost:5432/examdb";
    private static final String USER = "postgres";
    private static final String PASS = "1234"; // или твой реальный пароль

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
