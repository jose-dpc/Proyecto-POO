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
        dateHandler = new DateHandler(1, 1, 2025);
        frame = new JFrame("Registro de Citas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 10, 10));
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel lblNombre = new JLabel("Nombre Completo:");
        lblNombre.setFont(new Font("SansSerif", Font.BOLD, 14));
        txtNombre = new JTextField();
        txtNombre.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panelCampos.add(lblNombre);
        panelCampos.add(txtNombre);

        JLabel lblDia = new JLabel("Día:");
        lblDia.setFont(new Font("SansSerif", Font.BOLD, 14));
        txtDia = new JTextField(Integer.toString(dateHandler.getDia()));
        txtDia.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panelCampos.add(lblDia);
        panelCampos.add(txtDia);

        JLabel lblMes = new JLabel("Mes:");
        lblMes.setFont(new Font("SansSerif", Font.BOLD, 14));
        txtMes = new JTextField(Integer.toString(dateHandler.getMes()));
        txtMes.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panelCampos.add(lblMes);
        panelCampos.add(txtMes);

        JLabel lblAnio = new JLabel("Año:");
        lblAnio.setFont(new Font("SansSerif", Font.BOLD, 14));
        txtAnio = new JTextField(Integer.toString(dateHandler.getAnio()));
        txtAnio.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panelCampos.add(lblAnio);
        panelCampos.add(txtAnio);

        lblFecha = new JLabel("Fecha: " + dateHandler.getFecha(), SwingConstants.CENTER);
        lblFecha.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblFecha.setForeground(new Color(34, 139, 34));

        lblError = new JLabel("", SwingConstants.CENTER);
        lblError.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblError.setForeground(Color.RED);

        JButton btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSiguiente.setBackground(new Color(255, 140, 0));
        btnSiguiente.setForeground(Color.WHITE);
        btnSiguiente.setPreferredSize(new Dimension(150, 40));

        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "✔ Cita Agendada", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
                VehiculoGUI siguienteVentana = new VehiculoGUI(Cita.this);
                siguienteVentana.setVisible(true);
            }
        });

        panelBotones.add(btnSiguiente);

        txtDia.addActionListener(new DateUpdater());
        txtMes.addActionListener(new DateUpdater());
        txtAnio.addActionListener(new DateUpdater());

        frame.add(lblFecha, BorderLayout.NORTH);
        frame.add(panelCampos, BorderLayout.CENTER);
        frame.add(lblError, BorderLayout.WEST);
        frame.add(panelBotones, BorderLayout.SOUTH);

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
                lblError.setText("");
            } catch (Exception ex) {
                lblError.setText("Error: Fecha inválida");
            }
        }
    }
}


