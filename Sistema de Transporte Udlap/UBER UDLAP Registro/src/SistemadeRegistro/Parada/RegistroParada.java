package SistemadeRegistro.Parada;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroParada extends JFrame {
    
    private JTextField txtDistancia, txtCantidad, txtUbicacion;

    public RegistroParada() {
        setTitle("Registro de Parada");
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
        panelIzquierdo.add(new JLabel("Distancia:", SwingConstants.RIGHT));
        panelIzquierdo.add(new JLabel("Cantidad:", SwingConstants.RIGHT));
        panelIzquierdo.add(new JLabel("Ubicación:", SwingConstants.RIGHT));

        // Botón para registrar
        JButton btnRegistrar = new JButton("Registrar Parada");
        panelIzquierdo.add(btnRegistrar);

        add(panelIzquierdo, BorderLayout.WEST);

        // Panel derecho con campos de entrada
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new GridLayout(4, 1, 10, 10)); // 4 filas, 1 columna

        txtDistancia = new JTextField();
        txtCantidad = new JTextField();
        txtUbicacion = new JTextField();

        panelDerecho.add(txtDistancia);
        panelDerecho.add(txtCantidad);
        panelDerecho.add(txtUbicacion);

        // Etiqueta de instrucciones
        JLabel lblMensaje = new JLabel("Ingrese los datos y presione el botón", SwingConstants.CENTER);
        panelDerecho.add(lblMensaje);

        add(panelDerecho, BorderLayout.CENTER);

        // Acción del botón
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String distancia = txtDistancia.getText();
                String cantidad = txtCantidad.getText();
                String ubicacion = txtUbicacion.getText();

                if (!distancia.isEmpty() && !cantidad.isEmpty() && !ubicacion.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Parada registrada:\n"
                            + "Distancia: " + distancia + " km\n"
                            + "Cantidad: " + cantidad + "\n"
                            + "Ubicación: " + ubicacion, 
                            "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
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

    public static void main(String[] args) {
        new RegistroParada();
    }
}