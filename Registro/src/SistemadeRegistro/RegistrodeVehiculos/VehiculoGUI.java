package SistemadeRegistro.RegistrodeVehiculos;

import SistemadeRegistro.ConexionDB;
import SistemadeRegistro.SelecciondeRuta.SelecciondeRutaGUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VehiculoGUI extends JFrame {
    private Vehiculo vehiculo;
    private JTextField marcaField, añoField, modeloField, kmField, rendimientoField, placaField, colorField, polizaField;
    private JTextArea resultadoArea;

    public VehiculoGUI(JFrame parentFrame) {
        vehiculo = new Vehiculo();
        
        // Configuración básica de la ventana
        setTitle("Registro de Vehículos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLayout(new GridLayout(9, 2, 5, 5));
        
        // Componentes de la interfaz
        add(new JLabel("Marca:"));
        marcaField = new JTextField(10);
        add(marcaField);

        add(new JLabel("Modelo:"));
        modeloField = new JTextField(10);
        add(modeloField);

        add(new JLabel("Placas:"));
        placaField = new JTextField(10);
        add(placaField);

        add(new JLabel("Color:"));
        colorField = new JTextField(10);
        add(colorField);

        add(new JLabel("Año:"));
        añoField = new JTextField(10);
        add(añoField);
        
        add(new JLabel("Kilometraje:"));
        kmField = new JTextField(10);
        add(kmField);

        add(new JLabel("Rendimiento (km/L):"));
        rendimientoField = new JTextField(10);
        add(rendimientoField);

        add(new JLabel("Póliza de Seguro:"));
        polizaField = new JTextField(10);
        add(polizaField);
        
        // Botón de registro
        JButton registrarBtn = new JButton("Registrar Vehículo");
        add(registrarBtn);
        
        // Área de resultados
        resultadoArea = new JTextArea(10, 40);
        resultadoArea.setEditable(false);
        resultadoArea.setText("Ingrese los datos del vehículo y presione 'Registrar'");
        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        add(scrollPane);
        
        // Acción del botón Registrar
        registrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validar campos obligatorios
                    if (marcaField.getText().trim().isEmpty() || modeloField.getText().trim().isEmpty()) {
                        resultadoArea.setText("Error: Marca y Modelo son campos obligatorios");
                        return;
                    }

                    // Obtener valores de los campos
                    String marca = marcaField.getText().trim();
                    String modelo = modeloField.getText().trim();
                    String placas = placaField.getText().trim();
                    String color = colorField.getText().trim();
                    int año = Integer.parseInt(añoField.getText().trim());
                    int kilometraje = Integer.parseInt(kmField.getText().trim());
                    int rendimiento = Integer.parseInt(rendimientoField.getText().trim());
                    String poliza = polizaField.getText().trim();

                    // Insertar en la base de datos
                    ConexionDB.insertarVehiculo(marca, modelo, placas, color, año, kilometraje, rendimiento, poliza);
                    
                    // Actualizar el objeto Vehiculo
                    vehiculo.setMarca(marca);
                    vehiculo.setModelo(modelo);
                    vehiculo.setPlacas(placas);
                    vehiculo.setColor(color);
                    vehiculo.setAño(año);
                    vehiculo.setKm(kilometraje);
                    vehiculo.setRendimiento(rendimiento);
                    vehiculo.setPoliza(poliza);

                    // Mostrar mensaje de éxito
                    String mensaje = "Vehículo registrado exitosamente:\n"
                                  + "Marca: " + marca + "\n"
                                  + "Modelo: " + modelo + "\n"
                                  + "Placas: " + placas + "\n"
                                  + "Color: " + color + "\n"
                                  + "Año: " + año + "\n"
                                  + "Kilometraje: " + kilometraje + "\n"
                                  + "Rendimiento: " + rendimiento + " km/L\n"
                                  + "Póliza: " + poliza + "\n";
                    
                    // Verificar mantenimiento
                    if (vehiculo.checkKm()) {
                        mensaje += "\n¡ADVERTENCIA! El kilometraje es alto para el año del vehículo. Se recomienda revisión.";
                    } else {
                        mensaje += "\nEl kilometraje está dentro del rango esperado.";
                    }

                    resultadoArea.setText(mensaje);
                    
                    // Limpiar campos después de registrar
                    limpiarCampos();

                } catch (NumberFormatException ex) {
                    resultadoArea.setText("Error: Asegúrate de ingresar valores numéricos válidos en Año, Kilometraje y Rendimiento.");
                } catch (SQLException ex) {
                    resultadoArea.setText("Error al guardar en la base de datos: " + ex.getMessage());
                } catch (IllegalArgumentException ex) {
                    resultadoArea.setText("Error: " + ex.getMessage());
                }
            }
        });
    }

    private void limpiarCampos() {
        marcaField.setText("");
        modeloField.setText("");
        placaField.setText("");
        colorField.setText("");
        añoField.setText("");
        kmField.setText("");
        rendimientoField.setText("");
        polizaField.setText("");
    }
}
