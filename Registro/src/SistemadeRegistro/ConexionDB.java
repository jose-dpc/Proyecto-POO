package SistemadeRegistro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionDB {
    // Ruta relativa a tu base de datos (desde la raíz del proyecto)
    private static final String URL = "jdbc:sqlite:Registro/Vehiculos.db";

    public static Connection conectar() throws SQLException {
        // Registrar el driver de SQLite (solo necesario en algunas versiones)
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver SQLite no encontrado", e);
        }
        return DriverManager.getConnection(URL);
    }

    public static void insertarVehiculo(String marca, String modelo, String placas, String color,
                                      int año, int kilometraje, int rendimiento, String poliza) 
                                      throws SQLException {
        // Sentencia SQL con parámetros
        String sql = "INSERT INTO Vehiculos(marca, modelo, placas, color, año, kilometraje, rendimiento, poliza) "
                   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Establecer los parámetros
            pstmt.setString(1, marca);
            pstmt.setString(2, modelo);
            pstmt.setString(3, placas);
            pstmt.setString(4, color);
            pstmt.setInt(5, año);
            pstmt.setInt(6, kilometraje);
            pstmt.setInt(7, rendimiento);
            pstmt.setString(8, poliza);

            // Ejecutar la inserción
            pstmt.executeUpdate();
        }
    }
}