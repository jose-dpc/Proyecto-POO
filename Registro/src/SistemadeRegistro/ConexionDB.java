package SistemadeRegistro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:sqlite:Registro/Vehiculos.db";

    public static void insertarVehiculo(String marca, String modelo, String placas, String color,
                                        int año, int kilometraje, int rendimiento, String poliza) {
        try {
            // Registrar el controlador JDBC de SQLite
            Class.forName("org.sqlite.JDBC");

            String sql = "INSERT INTO Vehiculos(marca, modelo, placas, color, año, kilometraje, rendimiento, poliza) " +
                         "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(URL);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, marca);
                pstmt.setString(2, modelo);
                pstmt.setString(3, placas);
                pstmt.setString(4, color);
                pstmt.setInt(5, año);
                pstmt.setInt(6, kilometraje);
                pstmt.setInt(7, rendimiento);
                pstmt.setString(8, poliza);

                pstmt.executeUpdate();
                System.out.println("Vehículo insertado correctamente en la base de datos.");
            }

        } catch (ClassNotFoundException e) {
            System.err.println("No se encontró el controlador JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error de SQL: " + e.getMessage());
        }
    }
}
