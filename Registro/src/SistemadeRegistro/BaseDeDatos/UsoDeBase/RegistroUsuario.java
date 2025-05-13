package SistemadeRegistro.BaseDeDatos.UsoDeBase;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class RegistroUsuario implements ActionListener {
    private String[] campos;

    public RegistroUsuario(String[] campos) {
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
            String verificarSQL = "SELECT COUNT(*) FROM \"Usuario-Informacion\" WHERE ID = ?";
            try (PreparedStatement verificarStmt = conexion.prepareStatement(verificarSQL)) {
                verificarStmt.setString(1, campos[0]);
                ResultSet rs = verificarStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(null, "El ID ya está registrado.");
                    return;
                }
            }

            // Inserción del nuevo usuario
            String sql = "INSERT INTO \"Usuario-Informacion\" (ID, Nombre, Contrasena) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                for (int i = 0; i < campos.length; i++) {
                    stmt.setString(i + 1, campos[i]);
                }
                int filasInsertadas = stmt.executeUpdate();
                if (filasInsertadas > 0) {
                    JOptionPane.showMessageDialog(null, "✅ Usuario registrado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "⚠️ No se pudo registrar el usuario.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al registrar usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
