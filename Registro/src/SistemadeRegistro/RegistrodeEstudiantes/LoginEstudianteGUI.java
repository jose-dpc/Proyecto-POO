package SistemadeRegistro.RegistrodeEstudiantes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginEstudianteGUI extends JFrame {
    private JTextField nombreField, idField;
    private JPasswordField contrasenaField;
    private JButton btnRegistrar;

    public LoginEstudianteGUI(JFrame parentFrame) {
        setTitle("Login de Estudiante");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(new Color(243, 156, 18));
        nombreField = new JTextField();

        JLabel lblID = new JLabel("ID:");
        lblID.setForeground(new Color(243, 156, 18));
        idField = new JTextField();

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setForeground(new Color(243, 156, 18));
        contrasenaField = new JPasswordField();

        btnRegistrar = new JButton("Iniciar Sesión");
        btnRegistrar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnRegistrar.setBackground(new Color(46, 204, 113));
        btnRegistrar.setForeground(Color.WHITE);

        panel.add(lblNombre);
        panel.add(nombreField);
        panel.add(lblID);
        panel.add(idField);
        panel.add(lblContrasena);
        panel.add(contrasenaField);
        panel.add(new JLabel());
        panel.add(btnRegistrar);

        add(panel);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarEstudiante();
            }
        });

        setVisible(true);
    }

    private void verificarEstudiante() {
        try {
            String nombre = nombreField.getText().trim();
            String id = idField.getText().trim();
            String contrasena = new String(contrasenaField.getPassword()).trim();

            if (id.isEmpty() || contrasena.isEmpty() || nombre.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos son obligatorios.");
            }

            Estudiante estudiante = new Estudiante(id, contrasena, nombre);

            if (!estudiante.contrasenaSegura()) {
                throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
            }

            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
            dispose();
            new ControlFrameEstudiante(estudiante); // Abrir panel principal del estudiante

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
