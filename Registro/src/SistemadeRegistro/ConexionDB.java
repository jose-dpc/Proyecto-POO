package SistemadeRegistro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:sqlite:Registro/vehiculos.db";

    public static void insertarVehiculo(String marca, String modelo, String placas, String color,
                                        int anio, int kilometraje, int rendimiento, String poliza) {
        String sql = "INSERT INTO Vehiculos(marca, modelo, placas, color, año, kilometraje, rendimiento, poliza) " +
                     "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, marca);
            pstmt.setString(2, modelo);
            pstmt.setString(3, placas);
            pstmt.setString(4, color);
            pstmt.setInt(5, anio);
            pstmt.setInt(6, kilometraje);
            pstmt.setInt(7, rendimiento);
            pstmt.setString(8, poliza);

            pstmt.executeUpdate();
            System.out.println("Vehículo registrado exitosamente.");

        } catch (SQLException e) {
            System.out.println("Error al insertar vehículo: " + e.getMessage());
        }
    }
}
