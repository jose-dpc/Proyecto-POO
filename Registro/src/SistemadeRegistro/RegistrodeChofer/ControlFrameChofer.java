package SistemadeRegistro.RegistrodeChofer;

import SistemadeRegistro.Rutas.ControlFrameRuta;
import SistemadeRegistro.UnirseaRuta.UnirseAUnaRuta;
import SistemadeRegistro.RegistrodeVehiculos.VehiculoGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlFrameChofer extends JFrame {
    private Chofer choferActual;

    public ControlFrameChofer() {
        inicializarInterfaz();
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
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton btnRegistrarVehiculo = new JButton("Registrar Vehículo");
        configurarBoton(btnRegistrarVehiculo);

        JButton btnCrearRuta = new JButton("Crear Ruta Nueva");
        configurarBoton(btnCrearRuta);

        JButton btnUnirseRuta = new JButton("Unirse a Ruta Existente");
        configurarBoton(btnUnirseRuta);

        JButton btnEliminarRuta = new JButton("Eliminar Ruta");
        configurarBoton(btnEliminarRuta);

        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        configurarBoton(btnCerrarSesion);

        btnRegistrarVehiculo.addActionListener(e -> {
            if (choferAutorizado()) {
                abrirVentanaRegistroVehiculo();
            }
        });

        btnCrearRuta.addActionListener(e -> {
            if (choferAutorizado()) {
                new ControlFrameRuta(this, choferActual);
                dispose();
            }
        });

        btnUnirseRuta.addActionListener(e -> {
            if (choferAutorizado()) {
                new UnirseAUnaRuta(this, choferActual);
                dispose();
            }
        });

        btnEliminarRuta.addActionListener(e -> {
            if (choferAutorizado()) {
                abrirVentanaEliminarRuta();
            }
        });

        btnCerrarSesion.addActionListener(e -> {
            cerrarSesion();
        });

        panel.add(btnRegistrarVehiculo);
        panel.add(btnCrearRuta);
        panel.add(btnUnirseRuta);
        panel.add(btnEliminarRuta);
        panel.add(btnCerrarSesion);

        add(panel);
        setVisible(true);
    }

    private void configurarBoton(JButton boton) {
        boton.setFont(new Font("Arial", Font.PLAIN, 14));
        boton.setBackground(new Color(243, 156, 18));
        boton.setForeground(Color.WHITE);
    }

    private boolean choferAutorizado() {
        if (choferActual == null || !choferActual.getEstado().equalsIgnoreCase("Aprobado")) {
            JOptionPane.showMessageDialog(this,
                    "No puedes continuar. Debes asistir a tu entrevista y ser aprobado primero.",
                    "Acceso Denegado",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void abrirVentanaRegistroVehiculo() {
        new VehiculoGUI(this, choferActual);
        dispose();
    }

    private void abrirVentanaEliminarRuta() {
        JOptionPane.showMessageDialog(this, "Aquí se abriría la ventana para eliminar una ruta.");
    }

    private void cerrarSesion() {
        JOptionPane.showMessageDialog(this, "Sesión cerrada. Regresando al login de chofer.");
        dispose();
        new LoginChoferGUI(); // Asegúrate de tener esta clase
    }
}
