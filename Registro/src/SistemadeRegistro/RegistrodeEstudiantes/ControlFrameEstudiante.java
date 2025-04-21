package SistemadeRegistro.RegistrodeEstudiantes;
import java.awt.*;
import javax.swing.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        idField = createField(frame, "ID: ");
        contrasenaField = createField(frame, "Contraseña: ");
        nombreField = createField(frame, "Nombre: ");

        // Etiqueta para mostrar el estado del estudiante
        displayLabel = new JLabel("Registro del Estudiante", SwingConstants.CENTER);
        frame.add(displayLabel);

        // Crear botón para verificar si la contraseña es segura
        JButton verificarButton = new JButton("Verificar Contraseña");
        frame.add(verificarButton);

        // Agregar acción al botón
        verificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarEstudiante();
                if (displayLabel.getText().equals("Registro del Estudiante")) {
                    System.exit(0);
                }
            }
        });

        // Hacer visible la ventana
        frame.setVisible(true);
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
            displayLabel.setText("Registro del Estudiante"); 
        } catch (IllegalArgumentException ex) {
            // Mostrar el mensaje de error en la interfaz
            displayLabel.setText("Error: " + ex.getMessage());
        } catch (Exception ex) {
            // Manejar otros posibles errores
            displayLabel.setText("Error inesperado: " + ex.getMessage());
        }
    }

}
