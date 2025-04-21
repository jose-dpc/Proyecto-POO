package SistemadeRegistro.RegistrodeChofer;
import java.awt.*;
import javax.swing.*;

import SistemadeRegistro.AgendarCita.Cita;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlFrameChofer extends JFrame{
    private Chofer chofer;
    private JTextField idField, contrasenaField, nombreField;
    private JLabel displayLabel;

    public ControlFrameChofer(JFrame parentFrame) {
        setTitle("Registro de Chofer");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana
        setLayout(new GridLayout(5, 2, 10, 10));
        getContentPane().setBackground(new Color(240, 255, 240)); // Verde claro

        Font fuente = new Font("Arial", Font.PLAIN, 14);

        JLabel idLabel = new JLabel("Correo:");
        idLabel.setFont(fuente);
        add(idLabel);
        idField = new JTextField();
        idField.setFont(fuente);
        add(idField);

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setFont(fuente);
        add(contrasenaLabel);
        contrasenaField = new JTextField();
        contrasenaField.setFont(fuente);
        add(contrasenaField);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setFont(fuente);
        add(nombreLabel);
        nombreField = new JTextField();
        nombreField.setFont(fuente);
        add(nombreField);

        displayLabel = new JLabel("Registro del Chofer", SwingConstants.CENTER);
        displayLabel.setFont(new Font("Arial", Font.BOLD, 10));
        add(displayLabel);

        JButton verificarButton = new JButton("Verificar y Continuar");
        verificarButton.setFont(fuente);
        verificarButton.setBackground(new Color(255, 165, 0)); // Naranja
        verificarButton.setForeground(Color.WHITE);
        add(verificarButton);

        verificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarChofer();
            }
        });

        setVisible(true);
    }

    private void verificarChofer() {
        try {
            String id = idField.getText().trim();
            String contrasena = contrasenaField.getText().trim();
            String nombre = nombreField.getText().trim();

            if (id.isEmpty()) {
                throw new IllegalArgumentException("El correo no puede estar vacío.");
            }

            if (nombre.isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío.");
            }

            chofer = new Chofer(id, contrasena, nombre);

            if (!chofer.contrasenaSegura()) {
                throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
            }

            // Mensaje de éxito
            JOptionPane.showMessageDialog(this, "Chofer registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Pasar a la siguiente ventana
            setVisible(false);
            new Cita(this).setVisible(true);

        } catch (IllegalArgumentException ex) {
            displayLabel.setText("⚠ " + ex.getMessage());
            displayLabel.setForeground(Color.RED);
        } catch (Exception ex) {
            displayLabel.setText("Error inesperado: " + ex.getMessage());
            displayLabel.setForeground(Color.RED);
        }
    }

}
