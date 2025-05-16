package SistemadeRegistro.RegistrodeEstudiantes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlFrameEstudiante extends JFrame {
    private Estudiante estudianteActual;

    public ControlFrameEstudiante(Estudiante estudiante) {
        this.estudianteActual = estudiante;
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        setTitle("Panel de Estudiante - Transporte UDLAP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnIniciarViaje = new JButton("Iniciar Viaje");
        btnIniciarViaje.setFont(new Font("Arial", Font.PLAIN, 14));
        btnIniciarViaje.setBackground(new Color(243, 156, 18));
        btnIniciarViaje.setForeground(Color.WHITE);

        JButton btnCalificarViaje = new JButton("Calificar Viaje");
        btnCalificarViaje.setFont(new Font("Arial", Font.PLAIN, 14));
        btnCalificarViaje.setBackground(new Color(243, 156, 18));
        btnCalificarViaje.setForeground(Color.WHITE);

        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setFont(new Font("Arial", Font.PLAIN, 14));
        btnCerrarSesion.setBackground(new Color(243, 156, 18));
        btnCerrarSesion.setForeground(Color.WHITE);

        btnIniciarViaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarViaje();
            }
        });

        btnCalificarViaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calificarViaje();
            }
        });

        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });

        panel.add(btnIniciarViaje);
        panel.add(btnCalificarViaje);
        panel.add(btnCerrarSesion);

        add(panel);
        setVisible(true);
    }

    private void iniciarViaje() {
        JOptionPane.showMessageDialog(this, "Aquí se mostrarian los viajes disponibles en ese momento.");
    }

    private void calificarViaje() {
        JOptionPane.showMessageDialog(this, "Aquí se abriría una lista de sus viajes anteriores para calificarlos.");
    }

    private void cerrarSesion() {
        JOptionPane.showMessageDialog(this, "Sesión cerrada. Regresando al menú principal.");
        dispose();
        new LoginEstudianteGUI(null); // Abre nuevamente la ventana de login
    }
}
