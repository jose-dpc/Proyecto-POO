    package SistemadeRegistro.CostodeViaje;
    import javax.swing.*;

import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;


    public class CalcPrecioGUI  extends JFrame{
        private CalcPrecio calc;
        private JTextField distanciaField, rendimientoField;

        public CalcPrecioGUI(JFrame parentFrame){
            
            calc = new CalcPrecio(10, 10);

            JFrame frame = new JFrame("Calculadora de Tarifas");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500, 350);
            setLayout(new GridLayout(4, 2, 5, 5));

            add(new JLabel("Distancia a recorrer (Km):"));
            distanciaField = new JTextField(5);
            add(distanciaField);

            add(new JLabel("Rendimiento de su vehiculo (Km/L):"));
            rendimientoField = new JTextField(5);
            add(rendimientoField);

            JButton calcularTarifa = new JButton("Calcular tarifa del viaje");
            add(calcularTarifa);


            
            calcularTarifa.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        
                        double distancia = Double.parseDouble(distanciaField.getText());
                        int rendimiento = Integer.parseInt(rendimientoField.getText());

                        
                        calc.setDistancia(distancia);
                        calc.setRendimiento(rendimiento);

                        
                        double precioTotal = calc.costoViaje();

                        JOptionPane.showMessageDialog(null, "El precio del viaje es de: " + precioTotal);
        
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Por favor, ingrese valores numéricos válidos.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    JOptionPane.showMessageDialog(null, "Ruta Regristrada" );
                System.exit(0);
                }
            });

            frame.setVisible(true);
        }
    }




