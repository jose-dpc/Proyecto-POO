package SistemadeRegistro.BaseDeDatos.UsoDeBase;

import java.sql.*;
import javax.swing.*;

public class RegistroVehiculo {
    private String[] campos;

    public RegistroVehiculo(String[] campos) {
        this.campos = campos;
    }

    public void registrarVehiculo() {

        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
                return;
            }
        }

        try {
            int idDueño = Integer.parseInt(campos[1]);
            int año = Integer.parseInt(campos[3]);
            int kmtraje = Integer.parseInt(campos[5]);
            int rendimiento = Integer.parseInt(campos[6]);
            if (idDueño <= 0 || año <= 0 || kmtraje < 0 || rendimiento <= 0) {
                throw new NumberFormatException("Los valores numéricos deben ser positivos.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error: Verifique que los valores numéricos sean válidos.");
            return;
        }

        // Conexión a la base de datos
        try (Connection conexion = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db")) {
            // Verificar si la placa ya existe
            String verificarSQL = "SELECT COUNT(*) FROM Choche WHERE Placa = ?";
            try (PreparedStatement verificarStmt = conexion.prepareStatement(verificarSQL)) {
                verificarStmt.setString(1, campos[7]);
                ResultSet rs = verificarStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(null, "La placa ya está registrada.");
                    return;
                }
            }

            String sql = "INSERT INTO Choche (\"ID-Dueño\", \"Marca\", \"Año\", \"modelo\", \"Kmtraje\", \"Rendimiento\", \"Placa\", \"Color\", \"N poliza\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                for (int i = 0; i < campos.length; i++) {
                    stmt.setString(i + 1, campos[i]);
                }
                int filasInsertadas = stmt.executeUpdate();
                if (filasInsertadas > 0) {
                    JOptionPane.showMessageDialog(null, "✅ Vehículo registrado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "⚠️ No se pudo registrar el vehículo.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al registrar vehículo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
