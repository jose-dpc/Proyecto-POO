package SistemadeRegistro.CostodeViaje;

import org.junit.*;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class CalcPrecioGUITest {
    private CalcPrecioGUI gui;

    @Before
    public void setUp() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            gui = new CalcPrecioGUI(null);
        });
    }

    @Test
    public void testCalculoConDatosValidos() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            Component[] components = gui.getContentPane().getComponents();
            JTextField distanciaField = null;
            JTextField rendimientoField = null;
            JButton calcularButton = null;

            // Buscar los componentes necesarios
            for (Component comp : components) {
                if (comp instanceof JTextField) {
                    if (distanciaField == null) {
                        distanciaField = (JTextField) comp;
                    } else {
                        rendimientoField = (JTextField) comp;
                    }
                } else if (comp instanceof JButton) {
                    calcularButton = (JButton) comp;
                }
            }

            // Ingresar datos válidos
            if (distanciaField != null) distanciaField.setText("100");
            if (rendimientoField != null) rendimientoField.setText("10");

            // Simular clic
            if (calcularButton != null) {
                calcularButton.doClick();
            }
        });
    }

    @After
    public void tearDown() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> gui.dispose());
    }
}
