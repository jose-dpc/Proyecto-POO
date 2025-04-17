package SistemadeRegistro.SelecciondeRuta;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


import SistemadeRegistro.Rutas.ControlFrameRuta;
import SistemadeRegistro.UnirseaRuta.UnirseAUnaRuta;;


public class SelecciondeRutaGUI extends JFrame {
    private JLabel displayLabel;

    public SelecciondeRutaGUI(JFrame parentFrame) {
        setTitle("Sistema de Registro - Transporte UDLAP");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 2, 5, 5));

        displayLabel = new JLabel("Seleccione si desea crear o unirse a una ruta", SwingConstants.CENTER);
        add(displayLabel);
        displayLabel = new JLabel("", SwingConstants.CENTER);
        add(displayLabel);

        JButton CrearButton = new JButton("Crear");
        add(CrearButton);

        JButton UnirseButton = new JButton("Unirse");
        add(UnirseButton);

        
        UnirseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                UnirseAUnaRuta UnirseAUnaRuta = new UnirseAUnaRuta(SelecciondeRutaGUI.this);
                UnirseAUnaRuta.setVisible(true);
            }
        });
        CrearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                ControlFrameRuta ControlFrameRuta = new ControlFrameRuta(SelecciondeRutaGUI.this);
                ControlFrameRuta.setVisible(true);
            }
        });

        setVisible(true);
    }

    
}