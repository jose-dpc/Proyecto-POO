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
        JLabel lblNombre = new JLabel("Nombre Completo:");
        lblNombre.setForeground(new Color(243, 156, 18)); // Cambia el color de la letra a naranja
        panel.add(lblNombre);
        txtNombre = new JTextField();
        panel.add(txtNombre);
        
        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setForeground(new Color(243, 156, 18)); // Cambia el color de la letra a naranja
        panel.add(lblCorreo);
        txtCorreo = new JTextField();
        panel.add(txtCorreo);

        JLabel lblTelefono = new JLabel("Telefono:");
        lblTelefono.setForeground(new Color(243, 156, 18)); // Cambia el color de la letra a naranja
        panel.add(lblTelefono);
        txtTelefono = new JTextField();
        panel.add(txtTelefono);

        JLabel lblCURP = new JLabel("CURP:");
        lblCURP.setForeground(new Color(243, 156, 18)); // Cambia el color de la letra a naranja
        panel.add(lblCURP);
        txtCurp = new JTextField();
        panel.add(txtCurp);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnRegistrar.setBackground(new Color(46, 204, 113)); // verde
        btnRegistrar.setForeground(Color.WHITE);
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
