package SistemadeRegistro.RegistrodeChofer;

import javax.swing.*;

import SistemadeRegistro.AgendarCita.AgendarCitaGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroChoferGUI extends JFrame {
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JTextField txtTelefono;
    private JTextField txtCurp;
    private JButton btnRegistrar;

    public RegistroChoferGUI() {
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        setTitle("Registro de Nuevo Chofer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Nombre Completo:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panel.add(txtCorreo);

        panel.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panel.add(txtTelefono);

        panel.add(new JLabel("CURP:"));
        txtCurp = new JTextField();
        panel.add(txtCurp);

        btnRegistrar = new JButton("Registrar");
        panel.add(new JLabel()); // espacio vacío
        panel.add(btnRegistrar);

        add(panel);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarChofer();
            }
        });

        setVisible(true);
    }

   private void registrarChofer() {
    String nombre = txtNombre.getText().trim();
    String correo = txtCorreo.getText().trim();
    String telefonoTxt = txtTelefono.getText().trim();
    String curp = txtCurp.getText().trim();

    if (nombre.isEmpty() || correo.isEmpty() || telefonoTxt.isEmpty() || curp.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
        return;
    }

    long telefono;

try {
    telefono = Long.parseLong(telefonoTxt);
} catch (NumberFormatException ex) {
    JOptionPane.showMessageDialog(this, "Número de teléfono inválido. Solo números, sin espacios ni símbolos.");
    return;
}

    // Crear objeto chofer y pasar a agendar cita
    Chofer nuevoChofer = new Chofer(nombre, correo, (int) telefono, curp);
    
    JOptionPane.showMessageDialog(this, "Registro exitoso.\nAhora agenda tu cita para entrevista.");

    dispose(); // cerrar esta ventana
    new AgendarCitaGUI(nuevoChofer); // abrir la ventana para agendar cita
}

}
