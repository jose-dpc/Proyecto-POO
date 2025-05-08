package SistemadeRegistro.BaseDeDatos.UsoDeBase;

import java.awt.*;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import javax.swing.*;
public class RegistroChoferApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Registro de Chofer");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 2, 10, 10));

        // Campos de texto
        JTextField txtNombre = new JTextField();
        JTextField txtCorreo = new JTextField();
        JTextField txtTelefono = new JTextField();
        JTextField txtCurp = new JTextField();

        // Etiquetas y campos
        frame.add(new JLabel("Nombre:"));
        frame.add(txtNombre);
        frame.add(new JLabel("Correo:"));
        frame.add(txtCorreo);
        frame.add(new JLabel("Teléfono:"));
        frame.add(txtTelefono);
        frame.add(new JLabel("CURP:"));
        frame.add(txtCurp);

        // Botón de registro
        JButton btnRegistrar = new JButton("Registrar");
        frame.add(new JLabel()); // Espacio vacío
        frame.add(btnRegistrar);

        // Acción del botón
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener datos ingresados por el usuario
                String[] datos = new String[5];
                datos[0] = txtNombre.getText().trim();
                datos[1] = txtCorreo.getText().trim();
                datos[2] = txtTelefono.getText().trim();
                datos[3] = txtCurp.getText().trim();
                datos[4] = "10"; // Valor predeterminado

                // Crear instancia de RegistroChofer y ejecutar la lógica
                RegistroChofer registro = new RegistroChofer(datos);
                registro.actionPerformed(e); // Ejecuta la lógica de inserción
            }
        });

        frame.setVisible(true);
    }
}