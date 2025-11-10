import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    static Connection connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/digital_library?useSSL=false&allowPublicKeyRetrieval=true";
            String user = "root";
            String pass = "1234";  // ✅ Replace
            
            return DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            System.out.println("DB Error: " + e.getMessage());
            return null;
        }
    }
}
