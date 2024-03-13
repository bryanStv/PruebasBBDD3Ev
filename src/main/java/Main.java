import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static  java.sql.Connection con;
    public static void main(String[] args) throws SQLException {
        String host = "jdbc:sqlite:src/main/resources/network2";
        con = java.sql.DriverManager.getConnection(host);
        menu();
    }

    private static void menu() throws SQLException {
        banner();
        Scanner tc = new Scanner(System.in);
        System.out.print("Iniciar sesión(1)\nRegistrarse(2)\nSalir(0)\nQué opción eliges: ");
        int option = tc.nextInt();
        //String user = "";
        switch(option){
            case 0:
                System.out.println("Hasta pronto");
                System.exit(0);
            case 1:
                System.out.print("Nombre de usuario: ");
                String user = tc.next();
                iniciarSesion(user);
                break;
            case 2:
                registrarse();
                break;
        }
    }

    private static void iniciarSesion(String user) throws SQLException{
        if(!usuarioExiste(user)){
            System.out.printf("El usuario %s no existe, por favor, introduce bien tus datos",user);
        }else{
            Scanner tc = new Scanner(System.in);
            System.out.print("Introduce la contraseña: ");
            String pass = tc.nextLine();

        }
    }

    private static void registrarse() throws SQLException {
        Scanner tc = new Scanner(System.in);
        String user,pass;
        System.out.print("Dime el nombre de usuario que quieres: ");
        user = tc.nextLine();
        if(usuarioExiste(user)){
            System.out.println("Usuario "+user+" ,elige otro nombre de usuario");
            registrarse();
        }else{
            System.out.print("Elige una contraseña: ");
            pass = tc.nextLine();
            crearUsuario(user,pass);
            System.out.println("Usuario creado con éxito");
        }
    }

    private static void crearUsuario(String user,String pass) throws SQLException{
        Statement st = con.createStatement();
        String sql = String.format("INSERT INTO usuario(nombre,pass) VALUES('%s','%s');",user,pass);
        st.executeUpdate(sql);
    }

    private static boolean usuarioExiste(String user) throws SQLException{
        Statement st = con.createStatement();
        ResultSet existe = st.executeQuery(String.format("SELECT CASE WHEN EXISTS (SELECT 2 FROM usuario WHERE nombre = '%s') THEN 1 ELSE 0 END AS existe;",user));
        if(existe.getInt(1)==1){
            return true;
        }else{
            return false;
        }
    }
    private static void banner(){
        System.out.println("████████╗██╗    ██╗██╗████████╗████████╗███████╗██████╗     ██╗  ██╗    ██████╗ \n" +
                "╚══██╔══╝██║    ██║██║╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗    ██║  ██║   ██╔═████╗\n" +
                "   ██║   ██║ █╗ ██║██║   ██║      ██║   █████╗  ██████╔╝    ███████║   ██║██╔██║\n" +
                "   ██║   ██║███╗██║██║   ██║      ██║   ██╔══╝  ██╔══██╗    ╚════██║   ████╔╝██║\n" +
                "   ██║   ╚███╔███╔╝██║   ██║      ██║   ███████╗██║  ██║         ██║██╗╚██████╔╝\n" +
                "   ╚═╝    ╚══╝╚══╝ ╚═╝   ╚═╝      ╚═╝   ╚══════╝╚═╝  ╚═╝         ╚═╝╚═╝ ╚═════╝ \n" +
                "                                                                                ");
    }
}
