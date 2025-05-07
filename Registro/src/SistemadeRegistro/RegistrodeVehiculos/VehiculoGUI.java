
package SistemadeRegistro.RegistrodeVehiculos;
import SistemadeRegistro.RegistrodeChofer.ControlFrameChofer;
import SistemadeRegistro.SelecciondeRuta.SelecciondeRutaGUI;
import SistemadeRegistro.RegistrodeChofer.Chofer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VehiculoGUI  extends JFrame{
    private Vehiculo vehiculo;
    private JTextField marcaField, añoField, modeloField, kmField, rendimientoField, placaField, colorField, polizaField;
    private JTextArea resultadoArea;
    private Chofer chofer;

    public VehiculoGUI(JFrame parentFrame, Chofer chofer) {
        this.vehiculo = new Vehiculo();
        this.chofer = chofer;
        
        // Creación de la ventana
        setTitle ("Registro de Vehículos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new GridLayout(9, 2, 5, 5));
        setLocationRelativeTo(null);
        
        // Las entradas
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

        add(new JLabel("Poliza de Seguro:"));
        polizaField = new JTextField(10);
        add(polizaField);
        
        // Botón del GUI
        JButton registrarBtn = new JButton("Registrar Vehiculo");
        add(registrarBtn);
        
        resultadoArea = new JTextArea(10,40);
        resultadoArea.setEditable(false);
        resultadoArea.setText("Ingrese sus valores y presione el botón");
        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        add(scrollPane);
        
        registrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean exito = registrarVehiculo();
                if (exito) {
                    dispose();  // Cierra esta ventana
                    new ControlFrameChofer(chofer);
                }
            }
        });
        
        
        setVisible(true);
    }
    
    private boolean registrarVehiculo() {
        try {
            // Asegurar que ninguno de los campos este vacio
            String marca = marcaField.getText().trim();
            String modelo = modeloField.getText().trim();
            String color = colorField.getText().trim();
            String poliza = polizaField.getText().trim();
            String placas = placaField.getText().trim();

            if (marca.isEmpty() || modelo.isEmpty()){
                resultadoArea.setText("Por favor ingresar valores para marca y modelo");
                return false;
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
                    resultadoArea.setText(mensaje);
                    return false;
                }
            } else {
                mensaje += "No es necesario hacer mantenimiento.";
            }

            resultadoArea.setText(mensaje);
            int confirm = JOptionPane.showConfirmDialog(null, "Vehículo registrado exitosamente.\n¿Deseas continuar?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);
            return confirm == JOptionPane.OK_OPTION;

        } catch (NumberFormatException ex) {
            resultadoArea.setText("Error: Ingrese valores numéricos en Modelo y Kilometraje.");
            return false;
        } catch (IllegalArgumentException ex) {
            resultadoArea.setText(ex.getMessage());
            return false;
        }
    }

}
