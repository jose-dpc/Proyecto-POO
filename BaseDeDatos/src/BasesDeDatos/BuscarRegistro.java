package BasesDeDatos;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class BuscarRegistro implements ActionListener {
    private JTextField[] campos;

    public BuscarRegistro(JTextField[] campos) {
        this.campos = campos;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        String nombre = campos[0].getText();
        String apellido = campos[1].getText();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar nombre y apellido para buscar.");
            return;
        }

        try (Connection conexion = DriverManager.getConnection("jdbc:sqlite:direcciones.db")) {
            String sql = "SELECT * FROM addresses WHERE firstname = ? AND lastname = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                stmt.setString(2, apellido);
                ResultSet resultado = stmt.executeQuery();

                if (resultado.next()) {
                    for (int i = 2; i < campos.length; i++) {
                        campos[i].setText(resultado.getString(i + 1)); // índice inicia en 3
                    }
                    JOptionPane.showMessageDialog(null, "Registro encontrado.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró ningún registro con ese nombre y apellido.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar registro: " + e.getMessage());
        }
    }
}