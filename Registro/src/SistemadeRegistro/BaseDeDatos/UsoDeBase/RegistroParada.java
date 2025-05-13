package SistemadeRegistro.BaseDeDatos.UsoDeBase;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class RegistroParada implements ActionListener {
    private String[] campos;

    public RegistroParada(String[] campos) {
        this.campos = campos;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        // Validar que todos los campos estén llenos
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
                return;
            }
        }

        // Conexión a la base de datos
        try (Connection conexion = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db")) {
            // Verificar si "N Parada" ya existe
            String verificarSQL = "SELECT COUNT(*) FROM Parada WHERE \"N Parada\" = ?";
            try (PreparedStatement verificarStmt = conexion.prepareStatement(verificarSQL)) {
                verificarStmt.setString(1, campos[0]);
                ResultSet rs = verificarStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(null, "El número de parada ya está registrado.");
                    return;
                }
            }

            // Verificar si "N Ruta" ya existe
            verificarSQL = "SELECT COUNT(*) FROM Parada WHERE \"N Ruta\" = ?";
            try (PreparedStatement verificarStmt = conexion.prepareStatement(verificarSQL)) {
                verificarStmt.setString(1, campos[1]);
                ResultSet rs = verificarStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(null, "El número de ruta ya está registrado.");
                    return;
                }
            }

            // Inserción de la nueva parada
            String sql = "INSERT INTO Parada (\"N Parada\", \"N Ruta\", \"Nombre Parada\", \"Ubicacion\") VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                for (int i = 0; i < campos.length; i++) {
                    stmt.setString(i + 1, campos[i]);
                }
                int filasInsertadas = stmt.executeUpdate();
                if (filasInsertadas > 0) {
                    JOptionPane.showMessageDialog(null, "✅ Parada registrada exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "⚠️ No se pudo registrar la parada.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al registrar parada: " + e.getMessage());
            e.printStackTrace();
        }
    }
}