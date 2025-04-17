package SistemadeRegistro.UnirseaRuta;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnirseAUnaRuta extends JFrame {
    
    private JTextField txtNumeroRuta, txtNumeroParada, txtDistancia;

    public UnirseAUnaRuta(JFrame parentFrame) {
        setTitle("Unirse a una Ruta");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        // Panel izquierdo con etiquetas y botón
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new GridLayout(4, 1, 10, 10)); // 4 filas, 1 columna
        panelIzquierdo.setBackground(Color.LIGHT_GRAY);
        panelIzquierdo.setPreferredSize(new Dimension(180, getHeight()));

        // Agregar etiquetas
        panelIzquierdo.add(new JLabel("Número de Ruta:", SwingConstants.RIGHT));
        panelIzquierdo.add(new JLabel("Número de Parada:", SwingConstants.RIGHT));
        panelIzquierdo.add(new JLabel("Distancia:", SwingConstants.RIGHT));

        // Botón para unirse a la ruta
        JButton btnUnirse = new JButton("Unirse a la Ruta");
        panelIzquierdo.add(btnUnirse);

        add(panelIzquierdo, BorderLayout.WEST);

        // Panel derecho con campos de entrada
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new GridLayout(4, 1, 10, 10)); // 4 filas, 1 columna

        txtNumeroRuta = new JTextField();
        txtNumeroParada = new JTextField();
        txtDistancia = new JTextField();

        panelDerecho.add(txtNumeroRuta);
        panelDerecho.add(txtNumeroParada);
        panelDerecho.add(txtDistancia);

        // Etiqueta de instrucciones
        JLabel lblMensaje = new JLabel("Ingrese los datos y presione el botón", SwingConstants.CENTER);
        panelDerecho.add(lblMensaje);

        add(panelDerecho, BorderLayout.CENTER);

        // Acción del botón
        btnUnirse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroRuta = txtNumeroRuta.getText();
                String numeroParada = txtNumeroParada.getText();
                String distancia = txtDistancia.getText();

                if (!numeroRuta.isEmpty() && !numeroParada.isEmpty() && !distancia.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Te has unido a la ruta:\n"
                            + "Número de Ruta: " + numeroRuta + "\n"
                            + "Número de Parada: " + numeroParada + "\n"
                            + "Distancia: " + distancia + " km", 
                            "Unión Exitosa", JOptionPane.INFORMATION_MESSAGE);
                            System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", 
                            "Datos Incompletos", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);
        setVisible(true);
    }


} 
