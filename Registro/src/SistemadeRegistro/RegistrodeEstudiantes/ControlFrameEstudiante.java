package SistemadeRegistro.RegistrodeEstudiantes;
import SistemadeRegistro.BaseDeDatos.UsoDeBase.RegistroUsuario;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ControlFrameEstudiante extends JFrame{
    private Estudiante estudiante;
    private JTextField idField, contrasenaField, nombreField;
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

    public ControlFrameEstudiante(JFrame parentFrame) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Registro de Estudiantes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new GridLayout(6, 2, 5, 5));

        // Crear los campos de texto para ID, contraseña y nombre
        JPanel idpanel = new JPanel(new FlowLayout());
        idpanel.add(new JLabel("ID:"));
        idField = new JTextField(10);
        idpanel.add(idField);
        frame.add(idpanel);
        JPanel passpanel = new JPanel(new FlowLayout());
        passpanel.add(new JLabel("Contraseña:"));
        contrasenaField = new JTextField(10);
        passpanel.add(contrasenaField);
        frame.add(passpanel);
        JPanel namepanel = new JPanel(new FlowLayout());
        namepanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField(10);
        namepanel.add(nombreField);
        frame.add(namepanel);

        // Etiqueta para mostrar el estado del estudiante
        displayLabel = new JLabel("Registro del Estudiante", SwingConstants.CENTER);
        frame.add(displayLabel);

        // Crear botón para verificar si la contraseña es segura
        JButton verificarButton = new JButton("Registrar Estudiante");
        verificarButton.setBackground(Color.GREEN);
        frame.add(verificarButton);

        // Agregar acción al botón
        verificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] datos = obtenerDatos();
                RegistroUsuario registro = new RegistroUsuario(datos);
                verificarEstudiante();
                System.exit(0);
            }
        });

        // Hacer visible la ventana
        frame.setVisible(true);
    }
    private String[] obtenerDatos() {
        String[] datos = new String[3];
        datos[0] = idField.getText().trim();
        datos[1] = nombreField.getText().trim();
        datos[2] = contrasenaField.getText().trim(); // Convierte la contraseña a String
        return datos;
    }
    private void verificarEstudiante() {
        try {
            // Obtener los datos ingresados por el usuario
            String id = idField.getText();
            String contrasena = contrasenaField.getText();
            String nombre = nombreField.getText();

            // Validar ID
            if (id == null || id.isEmpty()) {
                throw new IllegalArgumentException("ID no puede estar vacío.");
            }

            // Validar nombre
            if (nombre == null || nombre.isEmpty()) {
                throw new IllegalArgumentException("Nombre no puede estar vacío.");
            }

            // Crear un nuevo objeto Estudiante
            estudiante = new Estudiante(id, contrasena, nombre);

            // Validar contraseña
            if (!estudiante.contrasenaSegura()) {
                throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
            }

            // Si todo es válido, mostrar mensaje de éxito
            JOptionPane.showMessageDialog(null,"Estudiante registrado correctamente.");
        } catch (IllegalArgumentException ex) {
            // Mostrar el mensaje de error en la interfaz
            displayLabel.setText("Error: " + ex.getMessage());
        } catch (Exception ex) {
            // Manejar otros posibles errores
            displayLabel.setText("Error inesperado: " + ex.getMessage());
        }
    }

}
