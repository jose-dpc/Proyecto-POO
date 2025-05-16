    package Reporte;
    import javax.swing.*;
    import java.awt.*;


    public class SegundaVentana extends JFrame {


    public SegundaVentana(JFrame parentFrame) {
        JFrame frame = new JFrame("REPORTE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JLabel label = new JLabel("A continuación, escriba su queja especificando los motivos y los nombres de los involucrados. Si eres pasajero agrega tu ID");
        JTextArea quejaArea = new JTextArea(10, 30);
        JButton enviarButton = new JButton("Enviar Reporte");
        
        enviarButton.addActionListener(e -> {
            String queja = quejaArea.getText();
            if (!queja.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Reporte enviado. ¡Gracias!", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, escribe una queja.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(label, BorderLayout.NORTH);
        frame.add(new JScrollPane(quejaArea), BorderLayout.CENTER);
        frame.add(enviarButton, BorderLayout.SOUTH);
        
        frame.setVisible(true);
    }
    }
