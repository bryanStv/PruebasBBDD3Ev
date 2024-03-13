import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class Main2 {
    private static  java.sql.Connection con;
    public static void main(String[] args) throws SQLException {
        String host = "jdbc:sqlite:src/main/resources/network";
        con = java.sql.DriverManager.getConnection( host );
        //crearTabla();
        //crearUsuarios();
        leerUsuarios();
    }

    private static void leerUsuarios() throws SQLException{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM empleados");
        while(rs.next()){
            System.out.printf("Num: %d, Nombre: %s, Departamento: %d, Edad: %d, Sueldo: %f\n",rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getDouble(5));
        }
    }
    private static void crearTabla() throws SQLException {
        Statement st = con.createStatement();
        String sql = "CREATE TABLE empleados(" +
                "num INTEGER PRIMARY KEY," +
                "nombre VARCHAR(255)," +
                "departamento INTEGER," +
                "edad INTEGER," +
                "sueldo REAL)";
        st.executeUpdate(sql);
        st.close();
    }

    private static void crearUsuarios() throws SQLException{
        Statement st = con.createStatement();
        String sql = "INSERT INTO empleados(num,nombre,departamento,edad,sueldo) VALUES(5,'Arturo',10,32,1000.0)";
        st.executeUpdate(sql);
        sql = "INSERT INTO empleados(num,nombre,departamento,edad,sueldo) VALUES(6,'Juan',20,28,1200.0)";
        st.executeUpdate(sql);
        sql = "INSERT INTO empleados(num,nombre,departamento,edad,sueldo) VALUES(7,'Martín',10,26,1400.0)";
        st.executeUpdate(sql);
    }
}