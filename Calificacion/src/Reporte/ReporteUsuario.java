package Reporte;
import javax.swing.*;
import java.awt.*;


public class ReporteUsuario extends JFrame {
    private String tipoUsuario;
    private String nombreRuta;


    public ReporteUsuario(JFrame parentFrame) {
        JFrame frame = new JFrame("Reporte de Incidente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("                 Seleccione el tipo de usuario:                ");
        JButton choferButton = new JButton("Chofer");
        JButton pasajeroButton = new JButton("Pasajero");
        JTextField rutaField = new JTextField(15);
        JButton continuarButton = new JButton("Continuar");

        choferButton.addActionListener(e -> tipoUsuario = "Chofer");
        pasajeroButton.addActionListener(e -> tipoUsuario = "Pasajero");
        
        continuarButton.addActionListener(e -> {
            nombreRuta = rutaField.getText();
            if (tipoUsuario != null && !nombreRuta.isEmpty()) {
                frame.dispose(); 
                SegundaVentana SegundaVentana = new SegundaVentana(ReporteUsuario.this);
                SegundaVentana.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(frame, "Datos faltantes", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(label);
        frame.add(choferButton);
        frame.add(pasajeroButton);
        frame.add(new JLabel(" Nombre de la Ruta: "));
        frame.add(rutaField);
        frame.add(continuarButton);
        
        frame.setVisible(true);
    }


}