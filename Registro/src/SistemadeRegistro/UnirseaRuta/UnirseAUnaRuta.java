package SistemadeRegistro.UnirseaRuta;

import SistemadeRegistro.RegistrodeChofer.ControlFrameChofer;
import SistemadeRegistro.RegistrodeChofer.Chofer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnirseAUnaRuta extends JFrame {

    private JTextField txtNumeroRuta, txtNumeroParada, txtDistancia;

    public UnirseAUnaRuta(JFrame parentFrame, Chofer chofer) {
        setTitle("Unirse a una Ruta");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panel.setBackground(Color.WHITE);

        JLabel lblRuta = new JLabel("Número de Ruta:");
        lblRuta.setForeground(new Color(243, 156, 18));
        txtNumeroRuta = new JTextField();

        JLabel lblParada = new JLabel("Número de Parada:");
        lblParada.setForeground(new Color(243, 156, 18));
        txtNumeroParada = new JTextField();

        JLabel lblDistancia = new JLabel("Distancia:");
        lblDistancia.setForeground(new Color(243, 156, 18));
        txtDistancia = new JTextField();

        JButton btnUnirse = new JButton("Unirse a la Ruta");
        btnUnirse.setBackground(new Color(46, 204, 113));
        btnUnirse.setForeground(Color.WHITE);

        JButton btnVolver = new JButton("Regresar al Panel del Chofer");
        btnVolver.setBackground(new Color(243, 156, 18));
        btnVolver.setForeground(Color.WHITE);

        panel.add(lblRuta);
        panel.add(txtNumeroRuta);
        panel.add(lblParada);
        panel.add(txtNumeroParada);
        panel.add(lblDistancia);
        panel.add(txtDistancia);
        panel.add(new JLabel()); // Espacio
        panel.add(new JLabel());
        panel.add(btnUnirse);
        panel.add(btnVolver);

        add(panel);

        btnUnirse.addActionListener(e -> {
            String ruta = txtNumeroRuta.getText();
            String parada = txtNumeroParada.getText();
            String distancia = txtDistancia.getText();

            if (!ruta.isEmpty() && !parada.isEmpty() && !distancia.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Te has unido a la ruta:\nNúmero de Ruta: " + ruta +
                                "\nNúmero de Parada: " + parada +
                                "\nDistancia: " + distancia + " km",
                        "Unión Exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Por favor, complete todos los campos.",
                        "Datos Incompletos",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

    btnVolver.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose(); // cerrar esta ventana
            new ControlFrameChofer(chofer); // volver al panel del chofer con sesión activa
        }
    });

        setVisible(true);
    }
}
