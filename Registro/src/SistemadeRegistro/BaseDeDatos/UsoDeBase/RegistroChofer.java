package SistemadeRegistro.BaseDeDatos.UsoDeBase;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class RegistroChofer implements ActionListener {
    private String[] campos;

    public RegistroChofer(String[] campos) {
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

        // Validar que el teléfono sea un número válido
        try {
            long telefono = Long.parseLong(campos[2]);
            if (campos[2].length() != 10) { // Verifica que tenga exactamente 10 dígitos
                JOptionPane.showMessageDialog(null, "El teléfono debe contener exactamente 10 dígitos.");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El teléfono debe ser un número válido.");
            return;
        }

        // Conexión a la base de datos
        try (Connection conexion = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db")) {
            String sql = "INSERT INTO \"Chofer-Informacion\" (Nombre, Email, Telefono, CURP, Aprobado) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                for (int i = 0; i < campos.length; i++) {
                    stmt.setString(i + 1, campos[i]); // Ajusta el índice del parámetro
                }

                int filasInsertadas = stmt.executeUpdate();
                if (filasInsertadas > 0) {
                    System.out.println("✅ Registro agregado exitosamente");
                } else {
                    System.out.println("⚠️ No se pudo agregar el registro");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al agregar registro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

