package SistemadeRegistro.CostodeViaje;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.junit.Before;
import org.junit.Test;

public class CalcPrecioGUITest {

    private CalcPrecioGUI gui;

    @Before
    public void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> gui = new CalcPrecioGUI(null));
        Thread.sleep(300); // Pequeña espera para asegurar carga
    }

    private JTextField findTextFieldByLabel(Container container, String labelText) {
        Component[] components = container.getComponents();
        for (int i = 0; i < components.length - 1; i++) {
            if (components[i] instanceof JLabel && ((JLabel) components[i]).getText().contains(labelText)) {
                if (components[i + 1] instanceof JTextField) {
                    return (JTextField) components[i + 1];
                }
            }
        }
        return null;
    }

    private JButton findButtonByText(Container container, String text) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals(text)) {
                return (JButton) comp;
            } else if (comp instanceof Container) {
                JButton found = findButtonByText((Container) comp, text);
                if (found != null) return found;
            }
        }
        return null;
    }

    @Test
    public void testComponentesExisten() {
        JTextField distanciaField = findTextFieldByLabel(gui.getContentPane(), "Distancia");
        JTextField rendimientoField = findTextFieldByLabel(gui.getContentPane(), "Rendimiento");
        JButton calcularButton = findButtonByText(gui.getContentPane(), "Calcular tarifa del viaje");

        assertNotNull("Campo de distancia no encontrado", distanciaField);
        assertNotNull("Campo de rendimiento no encontrado", rendimientoField);
        assertNotNull("Botón calcular no encontrado", calcularButton);
    }

    @Test
    public void testCalculoExitoso() throws Exception {
        JTextField distanciaField = findTextFieldByLabel(gui.getContentPane(), "Distancia");
        JTextField rendimientoField = findTextFieldByLabel(gui.getContentPane(), "Rendimiento");
        JButton calcularButton = findButtonByText(gui.getContentPane(), "Calcular tarifa del viaje");

        assertNotNull(distanciaField);
        assertNotNull(rendimientoField);
        assertNotNull(calcularButton);

        SwingUtilities.invokeAndWait(() -> {
            distanciaField.setText("100.0");
            rendimientoField.setText("15");
            calcularButton.doClick();
        });

        assertEquals("100.0", distanciaField.getText());
        assertEquals("15", rendimientoField.getText());
    }
}
