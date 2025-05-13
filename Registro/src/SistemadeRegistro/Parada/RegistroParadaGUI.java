package SistemadeRegistro.Parada;

import SistemadeRegistro.BaseDeDatos.UsoDeBase.RegistroParada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroParadaGUI extends JFrame {

    private JTextField txtNParada, txtNRuta, txtNombreParada, txtUbicacion;

    public RegistroParadaGUI() {
        setTitle("Registro de Parada");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        // Panel izquierdo con etiquetas y botón
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new GridLayout(5, 1, 10, 10)); // 5 filas, 1 columna
        panelIzquierdo.setBackground(Color.LIGHT_GRAY);
        panelIzquierdo.setPreferredSize(new Dimension(180, getHeight()));

        // Agregar etiquetas
        panelIzquierdo.add(new JLabel("N Parada:", SwingConstants.RIGHT));
        panelIzquierdo.add(new JLabel("N Ruta:", SwingConstants.RIGHT));
        panelIzquierdo.add(new JLabel("Nombre Parada:", SwingConstants.RIGHT));
        panelIzquierdo.add(new JLabel("Ubicacion:", SwingConstants.RIGHT));

        // Botón para registrar
        JButton btnRegistrar = new JButton("Registrar Parada");
        panelIzquierdo.add(btnRegistrar);

        add(panelIzquierdo, BorderLayout.WEST);

        // Panel derecho con campos de entrada
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new GridLayout(5, 1, 10, 10)); // 5 filas, 1 columna

        txtNParada = new JTextField();
        txtNRuta = new JTextField();
        txtNombreParada = new JTextField();
        txtUbicacion = new JTextField();

        panelDerecho.add(txtNParada);
        panelDerecho.add(txtNRuta);
        panelDerecho.add(txtNombreParada);
        panelDerecho.add(txtUbicacion);

        // Etiqueta de instrucciones
        JLabel lblMensaje = new JLabel("Ingrese los datos y presione el botón", SwingConstants.CENTER);
        panelDerecho.add(lblMensaje);

        add(panelDerecho, BorderLayout.CENTER);

        // Acción del botón
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] datos = obtenerDatos();
                if (validarCampos(datos)) {
                    RegistroParada registro = new RegistroParada(datos);
                    registro.actionPerformed(e); // Ejecuta la lógica de inserción
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos correctamente.",
                            "Datos Incompletos", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String[] obtenerDatos() {
        String[] datos = new String[4];
        datos[0] = txtNParada.getText().trim();
        datos[1] = txtNRuta.getText().trim();
        datos[2] = txtNombreParada.getText().trim();
        datos[3] = txtUbicacion.getText().trim();
        return datos;
    }

    private boolean validarCampos(String[] datos) {
        try {
            // Validar que todos los campos estén llenos
            for (String campo : datos) {
                if (campo == null || campo.isEmpty()) {
                    return false;
                }
            }

            // Validar que "N Parada" y "N Ruta" sean números válidos
            int nParada = Integer.parseInt(datos[0]);
            int nRuta = Integer.parseInt(datos[1]);
            if (nParada <= 0 || nRuta <= 0) {
                throw new NumberFormatException("Los valores deben ser positivos.");
            }

            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Los valores de 'N Parada' y 'N Ruta' deben ser números positivos.",
                    "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static void main(String[] args) {
        new RegistroParadaGUI();
    }
}