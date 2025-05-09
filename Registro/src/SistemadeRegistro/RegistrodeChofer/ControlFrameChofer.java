package SistemadeRegistro.RegistrodeChofer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlFrameChofer extends JFrame {
    private Chofer choferActual; // Chofer actualmente autenticado

    public ControlFrameChofer() {
        inicializarInterfaz(); // método donde configuras tu GUI de registro
    }
    
    public ControlFrameChofer(Chofer chofer) {
        this.choferActual = chofer;
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        setTitle("Panel de Chofer - Transporte UDLAP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 botones, 10px separación

        // Crear botones
        JButton btnRegistrarVehiculo = new JButton("Registrar Vehículo");
        btnRegistrarVehiculo.setFont(new Font("Arial", Font.PLAIN, 14));
        btnRegistrarVehiculo.setBackground(new Color(243, 156, 18)); // Naranja
        btnRegistrarVehiculo.setForeground(Color.WHITE);
        JButton btnCrearRuta = new JButton("Crear Ruta Nueva");
        btnCrearRuta.setFont(new Font("Arial", Font.PLAIN, 14));
        btnCrearRuta.setBackground(new Color(243, 156, 18)); // Naranja
        btnCrearRuta.setForeground(Color.WHITE);
        JButton btnUnirseRuta = new JButton("Unirse a Ruta Existente");
        btnUnirseRuta.setFont(new Font("Arial", Font.PLAIN, 14));
        btnUnirseRuta.setBackground(new Color(243, 156, 18)); // Naranja
        btnUnirseRuta.setForeground(Color.WHITE);
        JButton btnEliminarRuta = new JButton("Eliminar Ruta");
        btnEliminarRuta.setFont(new Font("Arial", Font.PLAIN, 14));
        btnEliminarRuta.setBackground(new Color(243, 156, 18)); // Naranja
        btnEliminarRuta.setForeground(Color.WHITE);
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setFont(new Font("Arial", Font.PLAIN, 14));
        btnCerrarSesion.setBackground(new Color(243, 156, 18)); // Naranja
        btnCerrarSesion.setForeground(Color.WHITE);

        // Agregar listeners a botones
        btnRegistrarVehiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (choferAutorizado()) {
                    abrirVentanaRegistroVehiculo();
                }
            }
        });

        btnCrearRuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (choferAutorizado()) {
                    abrirVentanaCrearRuta();
                }
            }
        });

        btnUnirseRuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (choferAutorizado()) {
                    abrirVentanaUnirseRuta();
                }
            }
        });

        btnEliminarRuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (choferAutorizado()) {
                    abrirVentanaEliminarRuta();
                }
            }
        });

        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });

        // Agregar los botones al panel
        panel.add(btnRegistrarVehiculo);
        panel.add(btnCrearRuta);
        panel.add(btnUnirseRuta);
        panel.add(btnEliminarRuta);
        panel.add(btnCerrarSesion);

        add(panel);
        setVisible(true);
    }

    private boolean choferAutorizado() {
        if (!choferActual.getEstado().equalsIgnoreCase("Aprobado")) {
            JOptionPane.showMessageDialog(this, "No puedes continuar. Debes asistir a tu entrevista y ser aprobado primero.");
            return false;
        }
        return true;
    }

    private void abrirVentanaRegistroVehiculo() {
        // Aquí se abriría la ventana real de registro de vehículo
        JOptionPane.showMessageDialog(this, "Aquí se abriría la ventana de registro de vehículo.");
    }

    private void abrirVentanaCrearRuta() {
        // Aquí se abriría la ventana real de crear una nueva ruta
        JOptionPane.showMessageDialog(this, "Aquí se abriría la ventana para crear una nueva ruta.");
    }

    private void abrirVentanaUnirseRuta() {
        // Aquí se abriría la ventana real para unirse a una ruta existente
        JOptionPane.showMessageDialog(this, "Aquí se abriría la ventana para unirse a una ruta existente.");
    }

    private void abrirVentanaEliminarRuta() {
        // Aquí se abriría la ventana real para eliminar una ruta
        JOptionPane.showMessageDialog(this, "Aquí se abriría la ventana para eliminar una ruta.");
    }

    private void cerrarSesion() {
        JOptionPane.showMessageDialog(this, "Sesión cerrada. Regresando al menú principal.");
        dispose();
        // Aquí deberías redirigir a la pantalla de login o menú principal
    }
}
