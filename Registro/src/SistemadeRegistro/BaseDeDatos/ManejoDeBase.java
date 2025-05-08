package SistemadeRegistro.BaseDeDatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ManejoDeBase {
    @SuppressWarnings("CallToPrintStackTrace")
    public static Connection conectar() {
        Connection conexion=null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:BaseDeDatos.db";
            conexion = DriverManager.getConnection(url);
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró el driver SQLite ");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos SQLite");
            e.printStackTrace();
        }
        return conexion;
    }
    
        public static void main(String[] args) {
            conectar();}

    
}