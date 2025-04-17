package SistemadeRegistro.SelecciondeUsuario;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import SistemadeRegistro.RegistrodeChofer.ControlFrameChofer;
import SistemadeRegistro.RegistrodeEstudiantes.ControlFrameEstudiante;


public class SeleccióndeUsuarioGUI extends JFrame {
    private JLabel displayLabel;

    public SeleccióndeUsuarioGUI() {
        setTitle("Sistema de Registro - Transporte UDLAP");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 2, 5, 5));

        displayLabel = new JLabel("Seleccione si es Chofer o Estudiante", SwingConstants.CENTER);
        add(displayLabel);
        displayLabel = new JLabel("", SwingConstants.CENTER);
        add(displayLabel);

        JButton ChoferButton = new JButton("Chofer");
        add(ChoferButton);

        JButton EstudianteButton = new JButton("Estudiante");
        add(EstudianteButton);

        
        EstudianteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                ControlFrameEstudiante EstudianteGUI = new ControlFrameEstudiante(SeleccióndeUsuarioGUI.this);
                EstudianteGUI.setVisible(true);
            }
        });
        ChoferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                ControlFrameChofer EstudianteGUI = new ControlFrameChofer(SeleccióndeUsuarioGUI.this);
                EstudianteGUI.setVisible(true);
            }
        });

        setVisible(true);
    }


}