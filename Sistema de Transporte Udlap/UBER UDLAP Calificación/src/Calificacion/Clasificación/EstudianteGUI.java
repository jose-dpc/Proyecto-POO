package Calificacion.Clasificación;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EstudianteGUI extends JFrame {
    private JLabel displayLabel;
    public EstudianteGUI(JFrame parentFrame) {
        setTitle("Calificación Estudiante");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 10));


        displayLabel = new JLabel("Seleccione el número de estrellas en base a la calificación:", SwingConstants.CENTER);
        add(displayLabel, BorderLayout.NORTH);

        JButton imageButton1 = new JButton();
        JButton imageButton2 = new JButton();

        crearNuevoJButtonSi(imageButton1);
        crearNuevoJButtonNo(imageButton2);

        panelBotones.add(imageButton1);
        panelBotones.add(imageButton2);
        
        


        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        add(panelPrincipal);
        setVisible(true);
    }

    private void crearNuevoJButtonSi(JButton w) {

        ImageIcon originalIcon = new ImageIcon("C:\\Users\\josed\\OneDrive\\Desktop\\Personal Docs\\UDLAP\\POO\\Proyecto Final\\Sistema de Transporte Udlap\\UBER UDLAP Calificación\\src\\Calificacion\\pulgararriba.png"); // Cambia la ruta a tu imagen

        if (originalIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Tamaño 100x100
            ImageIcon resizedIcon = new ImageIcon(resizedImage);

            w.setIcon(resizedIcon);
            w.setHorizontalTextPosition(SwingConstants.CENTER);
            w.setVerticalTextPosition(SwingConstants.BOTTOM);

        } else {
            System.out.println("Error: No se pudo cargar la imagen.");
            w.setText("Imagen no encontrada");
        }


        w.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Gracias por tu opinion");
                dispose();
            }
        });
    }
    private void crearNuevoJButtonNo(JButton w) {

        ImageIcon originalIcon = new ImageIcon("C:\\Users\\josed\\OneDrive\\Desktop\\Personal Docs\\UDLAP\\POO\\Proyecto Final\\Sistema de Transporte Udlap\\UBER UDLAP Calificación\\src\\Calificacion\\pulgarabajo.png"); // Cambia la ruta a tu imagen

        if (originalIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(100, 150, Image.SCALE_SMOOTH); // Tamaño 100x100
            ImageIcon resizedIcon = new ImageIcon(resizedImage);

            w.setIcon(resizedIcon);
            w.setHorizontalTextPosition(SwingConstants.CENTER);
            w.setVerticalTextPosition(SwingConstants.BOTTOM);

        } else {
            System.out.println("Error: No se pudo cargar la imagen.");
            w.setText("Imagen no encontrada");
        }


        w.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Gracias por tu opinion");
                dispose();
            }
        });
    }
    public static void main(String[] args) {
        // Ejecutar la aplicación
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
            }
        });
    }
}
