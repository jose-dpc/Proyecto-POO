package SistemadeRegistro.Rutas;
import java.awt.*;
import javax.swing.*;

import SistemadeRegistro.CostodeViaje.CalcPrecioGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControlFrameRuta extends JFrame {
    private Ruta ruta;
    private JTextField inicioField, finField, numParadasField, distanciaField;
    private JLabel displayLabel;

    // Método para crear los campos de texto y etiquetas
    private JTextField createField(JFrame frame, String labelText) {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel(labelText));
        JTextField field = new JTextField(10);
        panel.add(field);
        frame.add(panel);
        return field;
    }

    public ControlFrameRuta(JFrame parentFrame) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Registro de Rutas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(6, 2, 5, 5));

        // Crear los campos de texto para Inicio, Fin, Número de Paradas y Distancia
        inicioField = createField(frame, "Inicio: ");
        finField = createField(frame, "Fin: ");
        numParadasField = createField(frame, "Número de Paradas: ");
        distanciaField = createField(frame, "Distancia (km): ");

        // Etiqueta para mostrar el estado de la ruta
        displayLabel = new JLabel("Estado de la Ruta", SwingConstants.CENTER);
        frame.add(displayLabel);

        // Crear botón para verificar la ruta
        JButton verificarButton = new JButton("Registrar Ruta");
        frame.add(verificarButton);

        // Agregar acción al botón
        verificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarRuta();
                setVisible(false);

                CalcPrecioGUI CalcPrecio = new CalcPrecioGUI(ControlFrameRuta.this);
                CalcPrecio.setVisible(true);
            }
        });

        // Hacer visible la ventana
        frame.setVisible(true);
    }

    private void verificarRuta() {
        try {
            // Obtener los datos ingresados por el usuario
            String inicio = inicioField.getText();
            String fin = finField.getText();
            int numParadas = Integer.parseInt(numParadasField.getText());
            double distancia = Double.parseDouble(distanciaField.getText());

            // Verificar que los números no sean negativos
            if (numParadas < 0) {
                throw new IllegalArgumentException("El número de paradas no puede ser negativo.");
            }
            if (distancia < 0) {
                throw new IllegalArgumentException("La distancia no puede ser negativa.");
            }

            // Crear un nuevo objeto Ruta
            ruta = new Ruta(inicio, fin, numParadas, distancia);

            // Mostrar la información de la ruta en la etiqueta
            displayLabel.setText(ruta.toString());
        } catch (IllegalArgumentException ex) {
            // Mostrar el mensaje de error en la interfaz
            displayLabel.setText("Error: " + ex.getMessage());
        } catch (Exception ex) {
            // Manejar otros posibles errores
            displayLabel.setText("Error inesperado: " + ex.getMessage());
        }
    }

}
