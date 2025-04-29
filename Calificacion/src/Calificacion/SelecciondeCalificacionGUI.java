package Calificacion;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Calificacion.Clasificación.ChoferGUI;
import Calificacion.Clasificación.EstudianteGUI;


public class SelecciondeCalificacionGUI extends JFrame {
    private JLabel displayLabel;

    public SelecciondeCalificacionGUI(JFrame parentFrame) {
        setTitle("Sistema de Calificación - Transporte UDLAP");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 10));
        
        

        displayLabel = new JLabel("Seleccione a quien le quiere dar una calificación:", SwingConstants.CENTER);
        panelPrincipal.add(displayLabel, BorderLayout.NORTH);


        JButton ChoferButton = new JButton("Chofer");
        panelBotones.add(ChoferButton);


        JButton EstudianteButton = new JButton("Estudiantes");
        panelBotones.add(EstudianteButton);

        
        EstudianteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                EstudianteGUI EstudianteGUI = new EstudianteGUI(SelecciondeCalificacionGUI.this);
                EstudianteGUI.setVisible(true);
            }
        });
        ChoferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                ChoferGUI choferGUI = new ChoferGUI(SelecciondeCalificacionGUI.this);
                choferGUI.setVisible(true);
            }
        });
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        add(panelPrincipal);
        
        setVisible(true);
    }

    
}