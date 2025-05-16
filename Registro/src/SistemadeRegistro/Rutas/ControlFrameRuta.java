package SistemadeRegistro.Rutas;

import SistemadeRegistro.RegistrodeChofer.ControlFrameChofer;
import SistemadeRegistro.CostodeViaje.CalcPrecioGUI;
import SistemadeRegistro.RegistrodeChofer.Chofer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlFrameRuta extends JFrame {
    private Ruta ruta;
    private JTextField inicioField, finField, numParadasField, distanciaField;
    private JLabel displayLabel;

    public ControlFrameRuta(JFrame parentFrame, Chofer chofer) {
        setTitle("Registro de Rutas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panel.setBackground(Color.WHITE);

        JLabel lblInicio = new JLabel("Inicio:");
        lblInicio.setForeground(new Color(243, 156, 18));
        inicioField = new JTextField();

        JLabel lblFin = new JLabel("Fin:");
        lblFin.setForeground(new Color(243, 156, 18));
        finField = new JTextField();

        JLabel lblParadas = new JLabel("Número de Paradas:");
        lblParadas.setForeground(new Color(243, 156, 18));
        numParadasField = new JTextField();

        JLabel lblDistancia = new JLabel("Distancia (km):");
        lblDistancia.setForeground(new Color(243, 156, 18));
        distanciaField = new JTextField();

        displayLabel = new JLabel("Estado de la Ruta", SwingConstants.CENTER);
        displayLabel.setForeground(Color.GRAY);

        JButton btnRegistrar = new JButton("Registrar Ruta");
        btnRegistrar.setBackground(new Color(46, 204, 113));
        btnRegistrar.setForeground(Color.WHITE);

        JButton btnVolver = new JButton("Regresar al Panel del Chofer");
        btnVolver.setBackground(new Color(243, 156, 18));
        btnVolver.setForeground(Color.WHITE);

        panel.add(lblInicio);
        panel.add(inicioField);
        panel.add(lblFin);
        panel.add(finField);
        panel.add(lblParadas);
        panel.add(numParadasField);
        panel.add(lblDistancia);
        panel.add(distanciaField);
        panel.add(displayLabel);
        panel.add(new JLabel()); // vacío
        panel.add(btnRegistrar);
        panel.add(btnVolver);

        add(panel);

        btnRegistrar.addActionListener(e -> {
            verificarRuta();
            setVisible(false);
            CalcPrecioGUI calcPrecio = new CalcPrecioGUI(ControlFrameRuta.this);
            calcPrecio.setVisible(true);
        });

     
        btnVolver.addActionListener(new ActionListener() {
         @Override
            public void actionPerformed(ActionEvent e) {
            dispose(); // cerrar esta ventana
            new ControlFrameChofer(chofer); // volver con el chofer aprobado
            }
        });
        setVisible(true);
    }

    private void verificarRuta() {
        try {
            String inicio = inicioField.getText();
            String fin = finField.getText();
            int numParadas = Integer.parseInt(numParadasField.getText());
            double distancia = Double.parseDouble(distanciaField.getText());

            if (numParadas < 0) throw new IllegalArgumentException("El número de paradas no puede ser negativo.");
            if (distancia < 0) throw new IllegalArgumentException("La distancia no puede ser negativa.");

            ruta = new Ruta(inicio, fin, numParadas, distancia);
            displayLabel.setText(ruta.toString());
        } catch (Exception ex) {
            displayLabel.setText("Error: " + ex.getMessage());
        }
    }
}
