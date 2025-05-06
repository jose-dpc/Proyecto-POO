package SistemadeRegistro;

import javax.swing.*;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemadeAdministrador extends JFrame {
    private JButton btnUsuarios;
    private JButton btnRutas;
    private JButton btnChoferes;
    private JButton btnSolicitudes;
    private JButton btnReportes;

    public SistemadeAdministrador() {
        setTitle("Sistema de Administración");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        btnUsuarios = new JButton("Administrar Usuarios");
        btnRutas = new JButton("Administrar Rutas");
        btnChoferes = new JButton("Administrar Choferes");
        btnSolicitudes = new JButton("Revisar Solicitudes");
        btnReportes = new JButton("Revisar Reportes");

        btnUsuarios.addActionListener(e -> new VentanaUsuarios().setVisible(true));
        btnRutas.addActionListener(e -> new VentanaRutas().setVisible(true));
        btnChoferes.addActionListener(e -> new VentanaChoferes().setVisible(true));
        btnSolicitudes.addActionListener(e -> new VentanaSolicitudes().setVisible(true));
        btnReportes.addActionListener(e -> new VentanaReportes().setVisible(true));

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(btnUsuarios);
        panel.add(btnRutas);
        panel.add(btnChoferes);
        panel.add(btnSolicitudes);
        panel.add(btnReportes);

        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SistemadeAdministrador().setVisible(true));
    }
}


class VentanaUsuarios extends JFrame {
    public VentanaUsuarios() {
        setTitle("Gestión de Usuarios");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new JLabel("Ventana de Administración de Usuarios", JLabel.CENTER));
    }
}

class VentanaRutas extends JFrame {
    public VentanaRutas() {
        setTitle("Gestión de Rutas");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new JLabel("Ventana de Administración de Rutas", JLabel.CENTER));
    }
}

class VentanaChoferes extends JFrame {
    public VentanaChoferes() {
        setTitle("Gestión de Choferes");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new JLabel("Ventana de Administración de Choferes", JLabel.CENTER));
    }
}

class VentanaSolicitudes extends JFrame {
    public VentanaSolicitudes() {
        setTitle("Revisión de Solicitudes");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new JLabel("Ventana para Revisar Solicitudes", JLabel.CENTER));
    }
}

class VentanaReportes extends JFrame {
    public VentanaReportes() {
        setTitle("Revisión de Reportes");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new JLabel("Ventana para Revisar Reportes", JLabel.CENTER));
    }
}
