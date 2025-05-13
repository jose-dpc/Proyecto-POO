
package SistemadeRegistro.RegistrodeVehiculos;
import SistemadeRegistro.SelecciondeRuta.SelecciondeRutaGUI;
import SistemadeRegistro.BaseDeDatos.UsoDeBase.RegistroVehiculo;
import SistemadeRegistro.RegistrodeChofer.ControlFrameChofer;
import SistemadeRegistro.RegistrodeChofer.Chofer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VehiculoGUI  extends JFrame{
    private Vehiculo vehiculo;
    private JTextField txtIDDueño, txtMarca, txtAño, txtModelo, txtKmtraje, txtRendimiento, txtPlaca, txtColor, txtPoliza;
    private JTextArea resultadoArea;
    private Chofer chofer;

    public VehiculoGUI(JFrame parentFrame, Chofer chofer) {
        this.vehiculo = new Vehiculo();
        this.chofer = chofer;
        
        // Creación de la ventana
        setTitle ("Registro de Vehículos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new GridLayout(10, 2, 10, 10));
        setLocationRelativeTo(null);
        
        add(new JLabel("ID Dueño:"));
        txtIDDueño = new JTextField();
        add(txtIDDueño);

        add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        add(txtMarca);

        add(new JLabel("Año:"));
        txtAño = new JTextField();
        add(txtAño);

        add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        add(txtModelo);

        add(new JLabel("Kilometraje:"));
        txtKmtraje = new JTextField();
        add(txtKmtraje);

        add(new JLabel("Rendimiento:"));
        txtRendimiento = new JTextField();
        add(txtRendimiento);

        add(new JLabel("Placa:"));
        txtPlaca = new JTextField();
        add(txtPlaca);

        add(new JLabel("Color:"));
        txtColor = new JTextField();
        add(txtColor);

        add(new JLabel("Poliza:"));
        txtPoliza = new JTextField();
        add(txtPoliza);
        
        // Botón del GUI
        JButton registrarBtn = new JButton("Registrar Vehículo");
        add(registrarBtn);
        
        resultadoArea = new JTextArea(5, 40);
        resultadoArea.setEditable(false);
        resultadoArea.setText("Ingrese los datos del vehículo y presione el botón.");
        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        add(scrollPane);
        
        registrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] datos = obtenerDatos();
                if (validarCampos(datos)) {
                    RegistroVehiculo registro = new RegistroVehiculo(datos);
                    registro.registrarVehiculo();
                    resultadoArea.setText("Vehículo registrado exitosamente.");
                } else {
                    resultadoArea.setText("Por favor, complete todos los campos correctamente.");
                }
                boolean exito = registrarVehiculo();
                if (exito) {
                    dispose();  // Cierra esta ventana
                    new ControlFrameChofer(chofer);
                }
            }
        });
        
        
        setVisible(true);
    }
    private String[] obtenerDatos() {
        return new String[]{
            txtIDDueño.getText().trim(),
            txtMarca.getText().trim(),
            txtAño.getText().trim(),
            txtModelo.getText().trim(),
            txtKmtraje.getText().trim(),
            txtRendimiento.getText().trim(),
            txtPlaca.getText().trim(),
            txtColor.getText().trim(),
            txtPoliza.getText().trim()
        };
    }
    private boolean validarCampos(String[] datos) {
        try {
            // Validar que todos los campos estén llenos
            for (String campo : datos) {
                if (campo == null || campo.isEmpty()) {
                    return false;
                }
            }

            
            // Validar que los campos numéricos sean válidos
            int idDueño = Integer.parseInt(datos[0]);
            int año = Integer.parseInt(datos[2]);
            int kmtraje = Integer.parseInt(datos[4]);
            int rendimiento = Integer.parseInt(datos[5]);
            if (idDueño <= 0 || año <= 0 || kmtraje < 0 || rendimiento <= 0) {
                throw new NumberFormatException("Los valores numéricos deben ser positivos.");
            }

            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error: Verifique que los valores numéricos sean válidos.",
                    "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private boolean registrarVehiculo() {
        try {
            // Asegurar que ninguno de los campos este vacio
            String marca = txtMarca.getText().trim();
            String modelo = txtModelo.getText().trim();
            String color = txtColor.getText().trim();
            String poliza = txtPoliza.getText().trim();
            String placas = txtPlaca.getText().trim();

            if (marca.isEmpty() || modelo.isEmpty()){
                resultadoArea.setText("Por favor ingresar valores para marca y modelo");
                return false;
            }

            int año =  txtAño.getText().isEmpty() ? vehiculo.getAño() : Integer.parseInt(txtAño.getText());
            int km =  txtKmtraje.getText().isEmpty() ? vehiculo.getKm() : Integer.parseInt(txtKmtraje.getText());
            int rendimiento = txtRendimiento.getText().isEmpty() ? vehiculo.getRendimiento() : Integer.parseInt(txtRendimiento.getText());
            
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
