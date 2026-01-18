import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PolocczenieZBaza {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/tarot";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Połączono z bazą offline!");
        } catch (SQLException e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }
}