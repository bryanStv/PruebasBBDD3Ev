import java.sql.SQLException;
public class Main {
    private static  java.sql.Connection con;
    public static void main(String[] args) throws SQLException {
        String host = "jdbc:sqlite:src/main/resources/network";
        con = java.sql.DriverManager.getConnection( host );
    }
}