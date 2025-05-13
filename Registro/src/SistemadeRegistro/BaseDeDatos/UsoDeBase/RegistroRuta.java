package SistemadeRegistro.BaseDeDatos.UsoDeBase;

import java.sql.*;
import javax.swing.*;

public class RegistroRuta {
    private String[] campos;

    public RegistroRuta(String[] campos) {
        this.campos = campos;
    }

    public void registrarRuta() {
        // Validar que todos los campos estén llenos
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
                return;
            }
        }

        // Validar que los campos numéricos sean válidos
        try {
            int nParadas = Integer.parseInt(campos[1]);
            int idCreador = Integer.parseInt(campos[2]);
            int aprobada = Integer.parseInt(campos[4]);
            if (nParadas <= 0 || idCreador <= 0 || (aprobada != 1 && aprobada != 0 && aprobada != 10)) {
                throw new NumberFormatException("Los valores numéricos deben ser válidos.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error: Verifique que los valores numéricos sean válidos.");
            return;
        }

        // Conexión a la base de datos
        try (Connection conexion = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db")) {
            // Verificar si la ruta ya existe
            String verificarSQL = "SELECT COUNT(*) FROM Ruta WHERE \"N Ruta\" = ?";
            try (PreparedStatement verificarStmt = conexion.prepareStatement(verificarSQL)) {
                verificarStmt.setString(1, campos[0]);
                ResultSet rs = verificarStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(null, "El número de ruta ya está registrado.");
                    return;
                }
            }

            // Inserción de la nueva ruta
            String sql = "INSERT INTO Ruta (\"N Ruta\", \"N Paradas\", \"Id-Creador\", \"Nombre Ruta\", \"Aprobada si=1, no=0, no revisada=10\") VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                for (int i = 0; i < campos.length; i++) {
                    stmt.setString(i + 1, campos[i]);
                }
                int filasInsertadas = stmt.executeUpdate();
                if (filasInsertadas > 0) {
                    JOptionPane.showMessageDialog(null, "✅ Ruta registrada exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "⚠️ No se pudo registrar la ruta.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al registrar ruta: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
