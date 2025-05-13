package SistemadeRegistro.RegistrodeChofer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginChoferGUI extends JFrame {
    private JTextField txtCurp;
    private JButton btnIniciarSesion;

    public LoginChoferGUI() {
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        setTitle("Iniciar Sesión Chofer");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        panel.add(new JLabel("CURP:"));
        txtCurp = new JTextField();
        panel.add(txtCurp);

        btnIniciarSesion = new JButton("Iniciar Sesión");
        panel.add(new JLabel()); // espacio vacío
        panel.add(btnIniciarSesion);

        add(panel);

        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        setVisible(true);
    }

    private void iniciarSesion() {
        String curp = txtCurp.getText().trim();

        if (curp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese su CURP.");
            return;
        }

        // Llamar a la función para validar el CURP en la base de datos
        int estado = buscarEstadoChofer(curp);

        switch (estado) {
            case 1: // Aprobado
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
                dispose();
                new ControlFrameChofer(); // Abrir ControlFrameChofer
                break;
            case 0: // No aprobado
                JOptionPane.showMessageDialog(this, "Su solicitud fue rechazada. No puede iniciar sesión.");
                dispose(); // Cerrar la ventana
                break;
            case 10: // Pendiente
                JOptionPane.showMessageDialog(this, "Su solicitud está pendiente de aprobación. Por favor, espere.");
                break;
            default: // CURP no encontrado
                JOptionPane.showMessageDialog(this, "CURP no encontrado. Verifique los datos ingresados.");
                break;
        }
    }

    private int buscarEstadoChofer(String curp) {
        int estado = -1; // Valor por defecto si no se encuentra el CURP

        String url = "jdbc:sqlite:BaseDeDatos.db"; // Ruta de la base de datos
        String query = "SELECT Aprobado FROM \"Chofer-Informacion\" WHERE CURP = ?";

        try (Connection conexion = DriverManager.getConnection(url);
             PreparedStatement stmt = conexion.prepareStatement(query)) {

            stmt.setString(1, curp); // Asignar el CURP al parámetro de la consulta
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                estado = rs.getInt("Aprobado"); // Obtener el estado de aprobación
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        return estado;
    }
}
