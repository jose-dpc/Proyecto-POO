package SistemadeRegistro.AgendarCita;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import SistemadeRegistro.RegistrodeVehiculos.VehiculoGUI;;


public class Cita extends JFrame {
    private JFrame frame;
    private JTextField txtDia, txtMes, txtAnio, txtNombre;
    private JLabel lblFecha, lblError;
    private DateHandler dateHandler;

    public Cita(JFrame parentFrame) {
        dateHandler = new DateHandler(1, 1, 2024);
        frame = new JFrame("Registro de Citas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 5, 5));
        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 5, 5));

        panelCampos.add(new JLabel("Nombre Completo:"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(new JLabel("Día:"));
        txtDia = new JTextField(Integer.toString(dateHandler.getDia()));
        panelCampos.add(txtDia);

        panelCampos.add(new JLabel("Mes:"));
        txtMes = new JTextField(Integer.toString(dateHandler.getMes()));
        panelCampos.add(txtMes);

        panelCampos.add(new JLabel("Año:"));
        txtAnio = new JTextField(Integer.toString(dateHandler.getAnio()));
        panelCampos.add(txtAnio);

        lblFecha = new JLabel("Fecha: " + dateHandler.getFecha(), SwingConstants.CENTER);
        lblError = new JLabel("", SwingConstants.CENTER);
        lblError.setForeground(Color.RED);


        JButton btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "✔ Cita Agendada", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);

                VehiculoGUI EstudianteGUI = new VehiculoGUI(Cita.this);
                EstudianteGUI.setVisible(true);
            }
        });

        panelBotones.add(btnSiguiente);

        txtDia.addActionListener(new DateUpdater());
        txtMes.addActionListener(new DateUpdater());
        txtAnio.addActionListener(new DateUpdater());

        frame.add(panelCampos, BorderLayout.CENTER);
        frame.add(lblFecha, BorderLayout.NORTH);
        frame.add(panelBotones, BorderLayout.SOUTH);
        frame.add(lblError, BorderLayout.WEST);

        frame.setVisible(true);
    }

    private void actualizarFecha() {
        txtDia.setText(Integer.toString(dateHandler.getDia()));
        txtMes.setText(Integer.toString(dateHandler.getMes()));
        txtAnio.setText(Integer.toString(dateHandler.getAnio()));
        lblFecha.setText("Fecha: " + dateHandler.getFecha());
    }


    private class DateUpdater implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int dia = Integer.parseInt(txtDia.getText());
                int mes = Integer.parseInt(txtMes.getText());
                int anio = Integer.parseInt(txtAnio.getText());
                dateHandler.setFecha(dia, mes, anio);
                actualizarFecha();
            } catch (Exception ex) {
                lblError.setText("Error: Fecha inválida");
            }
        }
    }
}


