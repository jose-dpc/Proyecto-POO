package SistemadeRegistro.SelecciondeUsuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import SistemadeRegistro.RegistrodeChofer.ChoferLogReg;
import SistemadeRegistro.RegistrodeEstudiantes.LoginEstudianteGUI;


public class SelecciondeUsuarioGUI extends JFrame {
    private JLabel displayLabel;

    public SelecciondeUsuarioGUI() {
        setTitle("Sistema de Registro - Transporte UDLAP");
        setSize(500, 170);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 2, 5, 5)); // 2 filas, 2 columnas, separación de 5px
        
        displayLabel = new JLabel("Seleccione si es Chofer o Estudiante", SwingConstants.CENTER);
        add(displayLabel);
        displayLabel = new JLabel("", SwingConstants.CENTER);
        add(displayLabel);
        
        // Botón Chofer
        JButton choferButton = new JButton("Chofer");
        choferButton.setFont(new Font("Arial", Font.PLAIN, 14));
        choferButton.setBackground(new Color(46, 204, 113)); // Verde
        choferButton.setForeground(Color.WHITE);
        add(choferButton);

        // Botón Estudiante
        JButton estudianteButton = new JButton("Estudiante");
        estudianteButton.setFont(new Font("Arial", Font.PLAIN, 14));
        estudianteButton.setBackground(new Color(243, 156, 18)); // Naranja
        estudianteButton.setForeground(Color.WHITE);
        add(estudianteButton);

        // Acción botones
        estudianteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LoginEstudianteGUI estudianteGUI = new LoginEstudianteGUI(SelecciondeUsuarioGUI.this);
                estudianteGUI.setVisible(true);
            }
        });

        choferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Cierra esta ventana
                ChoferLogReg choferGUI = new ChoferLogReg(); // Abrir registro de chofer
                choferGUI.setVisible(true);
            }
        });

        setVisible(true);
    }
    }
