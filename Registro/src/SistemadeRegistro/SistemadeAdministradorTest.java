package SistemadeRegistro;

import static org.junit.Assert.*;

import javax.swing.*;
import java.awt.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SistemadeAdministradorTest {
    private SistemadeAdministrador adminGUI;

    @Before
    public void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> adminGUI = new SistemadeAdministrador());
    }

    @After
    public void tearDown() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            for (Window w : Window.getWindows()) {
                if (w instanceof JFrame) {
                    ((JFrame) w).dispose();
                }
            }
        });
    }

    @Test
    public void testComponentesVisibles() {
        assertNotNull(adminGUI);
        assertTrue(adminGUI.isVisible());

        JButton[] botones = encontrarBotones(adminGUI);
        assertEquals(5, botones.length);
    }

    @Test
    public void testClickEnBotones() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JButton[] botones = encontrarBotones(adminGUI);
            for (JButton boton : botones) {
                boton.doClick(); // Simula el clic
            }
        });

        // Espera a que se abran las ventanas
        Thread.sleep(500); 

        Window[] ventanas = Window.getWindows();
        int abiertas = 0;
        for (Window w : ventanas) {
            if (w.isVisible() && w instanceof JFrame && w != adminGUI) {
                abiertas++;
            }
        }

        assertEquals("Deberían abrirse 5 ventanas secundarias", 5, abiertas);
    }

    private JButton[] encontrarBotones(JFrame frame) {
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                Component[] componentes = panel.getComponents();

                java.util.List<JButton> botones = new java.util.ArrayList<>();
                for (Component c : componentes) {
                    if (c instanceof JButton) {
                        botones.add((JButton) c);
                    }
                }
                return botones.toArray(new JButton[0]);
            }
        }
        return new JButton[0];
    }
}
