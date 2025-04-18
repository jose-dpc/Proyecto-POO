package SistemadeRegistro.RegistrodeVehiculos;
import javax.swing.*;

import SistemadeRegistro.SelecciondeRuta.SelecciondeRutaGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VehiculoGUI  extends JFrame{
    private Vehiculo vehiculo;
    private JTextField marcaField, añoField, modeloField, kmField, rendimientoField, placaField, colorField, polizaField;
    private JTextArea resultadoArea;

    public VehiculoGUI(JFrame parentFrame) {
        vehiculo = new Vehiculo();
        
        // Creación de la ventana
        JFrame frame = new JFrame("Registro de Vehículos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridLayout(9, 2, 5, 5));
        
        // Las entradas
        frame.add(new JLabel("Marca:"));
        marcaField = new JTextField(10);
        frame.add(marcaField);

        frame.add(new JLabel("Modelo:"));
        modeloField = new JTextField(10);
        frame.add(modeloField);

        frame.add(new JLabel("Placas:"));
        placaField = new JTextField(10);
        frame.add(placaField);

        frame.add(new JLabel("Color:"));
        colorField = new JTextField(10);
        frame.add(colorField);

        frame.add(new JLabel("Año:"));
        añoField = new JTextField(10);
        frame.add(añoField);
        
        frame.add(new JLabel("Kilometraje:"));
        kmField = new JTextField(10);
        frame.add(kmField);

        frame.add(new JLabel("Rendimiento (km/L):"));
        rendimientoField = new JTextField(10);
        frame.add(rendimientoField);

        frame.add(new JLabel("Poliza de Seguro:"));
        polizaField = new JTextField(10);
        frame.add(polizaField);
        
        // Botón del GUI
        JButton registrarBtn = new JButton("Registrar Vehiculo");
        frame.add(registrarBtn);
        
        resultadoArea = new JTextArea(10,40);
        resultadoArea.setEditable(false);
        resultadoArea.setText("Ingrese sus valores y presione el botón");
        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        frame.add(scrollPane);
        
        // Listener para la acción
        registrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarVehiculo();
                setVisible(false);

                SelecciondeRutaGUI Cita = new SelecciondeRutaGUI(VehiculoGUI.this);
                Cita.setVisible(true);
            }
        });
        
        frame.setVisible(true);
    }
    
    private void registrarVehiculo() {
        try {
            // Asegurar que ninguno de los campos este vacio
            String marca = marcaField.getText().trim();
            String modelo = modeloField.getText().trim();
            String color = colorField.getText().trim();
            String poliza = polizaField.getText().trim();
            String placas = placaField.getText().trim();

            if (marca.isEmpty() || modelo.isEmpty()){
                resultadoArea.setText("Por favor ingresar valores para marca y modelo");
                return;
            }

            int año =  añoField.getText().isEmpty() ? vehiculo.getAño() : Integer.parseInt(añoField.getText());
            int km =  kmField.getText().isEmpty() ? vehiculo.getKm() : Integer.parseInt(kmField.getText());
            int rendimiento = rendimientoField.getText().isEmpty() ? vehiculo.getRendimiento() : Integer.parseInt(rendimientoField.getText());
            
            vehiculo.setMarca(marca);
            vehiculo.setAño(año);
            vehiculo.setModelo(modelo);
            vehiculo.setKm(km);
            vehiculo.setColor(color);
            vehiculo.setRendimiento(rendimiento);
            vehiculo.setPoliza(poliza);
            vehiculo.setPlacas(placas);

            
            String mensaje = "Vehículo registrado: " + marca + " " + modelo + " " + color + ", " + año + "\n" + "Placas: " + placas + "\n" + 
                             "Kilometraje: " + km + "\n" + "Rendimiento: "+ rendimiento + "\n" + "Poliza de Seguro: " + poliza + "\n";

            // Verificación de kilometraje
            if (vehiculo.checkKm()) {
                // Preguntar al usuario cuándo fue la última revisión
                String[] opciones = {"Entre 1 y 6 meses", "De 6 meses a un año", "Más de un año"};
                String respuesta = (String) JOptionPane.showInputDialog(
                    null, 
                    "¿Hace cuánto tiempo fue la última revisión de su vehículo?", 
                    "Revisión de Mantenimiento", 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, 
                    opciones, 
                    opciones[0]);

                if (respuesta != null) {
                    if (respuesta.equals("Más de un año")) {
                        mensaje += "¡Se sugiere mantenimiento URGENTE!";
                    } else if (respuesta.equals("De 6 meses a un año")) {
                        mensaje += "Se recomienda hacer mantenimiento.";
                    } else {
                        mensaje += "No es necesario mantenimiento por ahora.";
                    }
                } else {
                    mensaje += "Se debe contestar para determinar la necesidad de mantenimiento.";
                }
            } else {
                mensaje += "No es necesario hacer mantenimiento.";
            }

            resultadoArea.setText(mensaje);
        } catch (NumberFormatException ex) {
            resultadoArea.setText("Error: Ingrese valores numéricos en Modelo y Kilometraje.");
        } catch (IllegalArgumentException ex) {
            resultadoArea.setText(ex.getMessage());
        }
    }

}

