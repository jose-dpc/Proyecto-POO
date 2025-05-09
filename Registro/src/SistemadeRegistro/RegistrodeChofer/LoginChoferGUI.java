package SistemadeRegistro.RegistrodeChofer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

         JLabel lblCURP = new JLabel("CURP:");
        lblCURP.setForeground(new Color(243, 156, 18)); // Cambia el color de la letra a naranja
        panel.add(lblCURP);
        txtCurp = new JTextField();
        panel.add(txtCurp);

        btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setFont(new Font("Arial", Font.PLAIN, 14));
        btnIniciarSesion.setBackground(new Color(46, 204, 113)); // verde
        btnIniciarSesion.setForeground(Color.WHITE);
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

        // Aquí en el futuro deberíamos buscar en la base de datos el chofer por CURP.
        // Por ahora simplemente avanzamos.

        JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.\n(Validación pendiente)");

        dispose();
        new ControlFrameChofer(); // De momento vamos directo, luego validaremos el estado.
    }
}
