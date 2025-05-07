package BasesDeDatos;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class ActualizarRegistro implements ActionListener {
    @SuppressWarnings("FieldMayBeFinal")
    private JTextField[] campos;

    public ActualizarRegistro(JTextField[] campos) {
        this.campos = campos;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        String nombre = campos[0].getText();
        String apellido = campos[1].getText();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar nombre y apellido para actualizar.");
            return;
        }

        try (Connection conexion = DriverManager.getConnection("jdbc:sqlite:direcciones.db")) {
            String sql = "UPDATE addresses SET "
                       + "address = ?, "
                       + "city = ?, "
                       + "stateorprovince = ?, "
                       + "postalcode = ?, "
                       + "country = ?, "
                       + "emailaddress = ?, "
                       + "homephone = ? "
                       + "WHERE firstname = ? AND lastname = ?";

            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                for (int i = 2; i < campos.length; i++) {
                    stmt.setString(i - 1, campos[i].getText());
                }
                stmt.setString(9, nombre);
                stmt.setString(10, apellido);

                int filasActualizadas = stmt.executeUpdate();
                if (filasActualizadas > 0) {
                    JOptionPane.showMessageDialog(null, "✅ Registro actualizado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "⚠️ No se encontró el registro para actualizar.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al actualizar registro: " + e.getMessage());
        }
    }
}