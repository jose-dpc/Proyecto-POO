package SistemadeRegistro.AgendarCita;


import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

import SistemadeRegistro.RegistrodeVehiculos.VehiculoGUI;
import SistemadeRegistro.RegistrodeChofer.Chofer;

public class AgendarCitaGUI extends JFrame {
    private JTextField txtCorreo;
    private JComboBox<Integer> comboDia;
    private JComboBox<String> comboMes;
    private JComboBox<Integer> comboAño;
    private JComboBox<String> comboHora;
    private JButton btnConfirmar;
    private Chofer chofer;

    public AgendarCitaGUI(Chofer chofer) {
        this.chofer = chofer;
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        setTitle("Agendar Cita de Entrevista");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));

        // Matrícula
        panel.add(new JLabel("Correo: "));
        txtCorreo = new JTextField();
        panel.add(txtCorreo);

        // Día
        panel.add(new JLabel("Día:"));
        comboDia = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            comboDia.addItem(i);
        }
        panel.add(comboDia);

        // Mes
        panel.add(new JLabel("Mes:"));
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
                          "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        comboMes = new JComboBox<>(meses);
        panel.add(comboMes);

        // Año
        panel.add(new JLabel("Año:"));
        comboAño = new JComboBox<>();
        for (int i = 2025; i <= 2026; i++) {
            comboAño.addItem(i);
        }
        panel.add(comboAño);

        // Hora
        panel.add(new JLabel("Hora:"));
        String[] horas = {"09:00", "10:00", "11:00", "12:00", "13:00", 
                          "14:00", "15:00", "16:00", "17:00"};
        comboHora = new JComboBox<>(horas);
        panel.add(comboHora);

        // Botón Confirmar
        btnConfirmar = new JButton("Confirmar Cita");
        panel.add(new JLabel()); // Espaciador
        panel.add(btnConfirmar);

        add(panel);

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarCita();
                
            }
        });

        setVisible(true);
    }

    private void confirmarCita() {
        String correo = txtCorreo.getText().trim();
        int dia = (Integer) comboDia.getSelectedItem();
        int mes = comboMes.getSelectedIndex() + 1; // porque enero es 0
        int anio = (Integer) comboAño.getSelectedItem();
        String horaSeleccionada = (String) comboHora.getSelectedItem();

        if (correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese su correo.");
            return;
        }

        LocalDate fecha;
        LocalTime hora;
        try {
            fecha = LocalDate.of(anio, mes, dia);
            hora = LocalTime.parse(horaSeleccionada + ":00");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Fecha o hora inválida.");
            return;
        }

        if (!DateHandler.esFechaValida(fecha)) {
            JOptionPane.showMessageDialog(this, "No puedes agendar una cita en el pasado.");
            return;
        }

        if (!DateHandler.esDiaLaboral(fecha)) {
            JOptionPane.showMessageDialog(this, "Solo puedes agendar citas en días laborales (lunes a viernes).");
            return;
        }

        if (!DateHandler.esHoraValida(hora)) {
            JOptionPane.showMessageDialog(this, "La hora debe estar entre 9:00 AM y 5:00 PM.");
            return;
        }

        // Si todo es válido
        JOptionPane.showMessageDialog(this, "¡Cita agendada exitosamente!\nMatrícula: " + correo +
                "\nFecha: " + fecha + "\nHora: " + hora);

        dispose(); // Cierra la ventana después de agendar

        new VehiculoGUI();
    }
}
