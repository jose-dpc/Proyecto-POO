package SistemadeRegistro.RegistrodeChofer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoferLogReg extends JFrame {

    public ChoferLogReg() {
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        setTitle("Registro / Login de Chofer - Transporte UDLAP");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel lblTitulo = new JLabel("¿Qué deseas hacer?", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblTitulo);

        JButton btnRegistrar = new JButton("Registrar Nuevo Chofer");
        btnRegistrar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnRegistrar.setBackground(new Color(46, 204, 113)); // verde
        btnRegistrar.setForeground(Color.WHITE);
        JButton btnIniciarSesion = new JButton("Iniciar Sesion Chofer");
        btnIniciarSesion.setFont(new Font("Arial", Font.PLAIN, 14));
        btnIniciarSesion.setBackground(new Color(243, 156, 18)); // naranja
        btnIniciarSesion.setForeground(Color.WHITE);
        
        panel.add(btnRegistrar);
        panel.add(btnIniciarSesion);

        add(panel);

        // Acciones de botones
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegistroChoferGUI();
            }
        });

        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginChoferGUI();
            }
        });

        setVisible(true);
    }
}
