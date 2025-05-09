
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
    private Chofer chofer;

    public VehiculoGUI(JFrame parentFrame, Chofer chofer) {
        this.vehiculo = new Vehiculo();
        this.chofer = chofer;

        setTitle("Registro de Vehículos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 500);
        setLayout(new GridLayout(9, 2, 5, 5));
        setLocationRelativeTo(null);

        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setForeground(new Color(243, 156, 18));
        add(lblMarca);
        marcaField = new JTextField(10);
        add(marcaField);

        JLabel lblModelo = new JLabel("Modelo:");
        lblModelo.setForeground(new Color(243, 156, 18));
        add(lblModelo);
        modeloField = new JTextField(10);
        add(modeloField);

        JLabel lblPlacas = new JLabel("Placas:");
        lblPlacas.setForeground(new Color(243, 156, 18));
        add(lblPlacas);
        placaField = new JTextField(10);
        add(placaField);

        JLabel lblColor = new JLabel("Color:");
        lblColor.setForeground(new Color(243, 156, 18));
        add(lblColor);
        colorField = new JTextField(10);
        add(colorField);

        JLabel lblAño = new JLabel("Año:");
        lblAño.setForeground(new Color(243, 156, 18));
        add(lblAño);
        añoField = new JTextField(10);
        add(añoField);

        JLabel lblKm = new JLabel("Kilometraje:");
        lblKm.setForeground(new Color(243, 156, 18));
        add(lblKm);
        kmField = new JTextField(10);
        add(kmField);

        JLabel lblRendimiento = new JLabel("Rendimiento (km/L):");
        lblRendimiento.setForeground(new Color(243, 156, 18));
        add(lblRendimiento);
        rendimientoField = new JTextField(10);
        add(rendimientoField);

        JLabel lblPoliza = new JLabel("Poliza de seguro");
        lblPoliza.setForeground(new Color(243, 156, 18));
        add(lblPoliza);
        polizaField = new JTextField(10);
        add(polizaField);

        JButton registrarBtn = new JButton("Registrar Vehiculo");
        registrarBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        registrarBtn.setBackground(new Color(46, 204, 113));
        registrarBtn.setForeground(Color.WHITE);
        add(registrarBtn);

        // celda vacía para mantener el diseño
        add(new JLabel());

        registrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean exito = registrarVehiculo();
                if (exito) {
                    dispose();
                    new ControlFrameChofer(chofer);
                }
            }
        });

        setVisible(true);
    }

    private boolean registrarVehiculo() {
        try {
            String marca = marcaField.getText().trim();
            String modelo = modeloField.getText().trim();
            String color = colorField.getText().trim();
            String poliza = polizaField.getText().trim();
            String placas = placaField.getText().trim();

            if (marca.isEmpty() || modelo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingresar valores para marca y modelo.", "Campos requeridos", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            int año = añoField.getText().isEmpty() ? vehiculo.getAño() : Integer.parseInt(añoField.getText());
            int km = kmField.getText().isEmpty() ? vehiculo.getKm() : Integer.parseInt(kmField.getText());
            int rendimiento = rendimientoField.getText().isEmpty() ? vehiculo.getRendimiento() : Integer.parseInt(rendimientoField.getText());

            vehiculo.setMarca(marca);
            vehiculo.setAño(año);
            vehiculo.setModelo(modelo);
            vehiculo.setKm(km);
            vehiculo.setColor(color);
            vehiculo.setRendimiento(rendimiento);
            vehiculo.setPoliza(poliza);
            vehiculo.setPlacas(placas);

            if (vehiculo.checkKm()) {
                String[] opciones = {"1 a 6 meses", "6 a 12 meses", "Más de 12 meses"};
                String respuesta = (String) JOptionPane.showInputDialog(
                        this,
                        "¿Cuándo fue la última revisión de mantenimiento?",
                        "Mantenimiento Preventivo",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                );

                if (respuesta == null) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar una opción para continuar.", "Mantenimiento requerido", JOptionPane.WARNING_MESSAGE);
                    return false;
                }

                switch (respuesta) {
                    case "Más de 12 meses":
                        JOptionPane.showMessageDialog(this, "¡Se sugiere mantenimiento URGENTE!");
                        break;
                    case "6 a 12 meses":
                        JOptionPane.showMessageDialog(this, "Se recomienda hacer mantenimiento pronto.");
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "No es necesario mantenimiento por ahora.");
                        break;
                }
            }

            String mensaje = "Vehículo registrado:\n" +
                    "Marca: " + marca + "\n" +
                    "Modelo: " + modelo + "\n" +
                    "Color: " + color + "\n" +
                    "Año: " + año + "\n" +
                    "Placas: " + placas + "\n" +
                    "Kilometraje: " + km + "\n" +
                    "Rendimiento: " + rendimiento + "\n" +
                    "Póliza: " + poliza;

            int confirm = JOptionPane.showConfirmDialog(this, mensaje + "\n\n¿Deseas continuar?", "Vehículo registrado", JOptionPane.OK_CANCEL_OPTION);
            return confirm == JOptionPane.OK_OPTION;

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Ingrese valores numéricos válidos en los campos Año, Kilometraje y Rendimiento.", "Formato inválido", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
