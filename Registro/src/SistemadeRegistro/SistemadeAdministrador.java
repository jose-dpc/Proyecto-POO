package SistemadeRegistro;
import javax.swing.*;

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
        setTitle("Sistema de Administración - Transporte UDLAP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panel.setBackground(Color.white);

        JLabel titleLabel = new JLabel("Seleccione una opción de administración", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel);
        
        btnUsuarios = createStyledButton("Administrar Usuarios", new Color(243, 156, 18));
        btnRutas = createStyledButton("Administrar Rutas", new Color(243, 156, 18));
        btnChoferes = createStyledButton("Administrar Choferes", new Color(243, 156, 18));
        btnSolicitudes = createStyledButton("Revisar Solicitudes", new Color(243, 156, 18));
        btnReportes = createStyledButton("Revisar Reportes", new Color(243, 156, 18));

        panel.add(btnUsuarios);
        panel.add(btnRutas);
        panel.add(btnChoferes);
        panel.add(btnSolicitudes);
        panel.add(btnReportes);

        add(panel);

        btnUsuarios.addActionListener(e -> new VentanaUsuarios().setVisible(true));
        btnRutas.addActionListener(e -> new VentanaRutas().setVisible(true));
        btnChoferes.addActionListener(e -> new VentanaChoferes().setVisible(true));
        btnSolicitudes.addActionListener(e -> new VentanaSolicitudes().setVisible(true));
        btnReportes.addActionListener(e -> new VentanaReportes().setVisible(true));
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SistemadeAdministrador::new);
    }
}

// Subventanas sin cambios de estilo (como pediste)

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
