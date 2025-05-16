package Calificacion;

import org.junit.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class SelecciondeCalificacionGUITest {

    private JFrame frame;

    @Before
    public void setUp() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            new SelecciondeCalificacionGUI(null);
        });

        SwingUtilities.invokeAndWait(() -> {
            for (Frame f : Frame.getFrames()) {
                if (f.isVisible() && f.getTitle().equals("Sistema de Calificación - Transporte UDLAP")) {
                    frame = (JFrame) f;
                    break;
                }
            }
        });

        assertNotNull("No se encontró el frame principal", frame);
    }

    @Test
    public void testComponentesVisibles() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            boolean botonChofer = false;
            boolean botonEstudiantes = false;
            boolean etiquetaCorrecta = false;

            for (Component c : frame.getContentPane().getComponents()) {
                if (c instanceof JPanel) {
                    JPanel panel = (JPanel) c;
                    for (Component inner : panel.getComponents()) {
                        if (inner instanceof JLabel) {
                            JLabel label = (JLabel) inner;
                            if (label.getText().contains("Seleccione a quien le quiere dar una calificación")) {
                                etiquetaCorrecta = true;
                            }
                        } else if (inner instanceof JPanel) {
                            JPanel botones = (JPanel) inner;
                            for (Component boton : botones.getComponents()) {
                                if (boton instanceof JButton) {
                                    JButton b = (JButton) boton;
                                    if (b.getText().equals("Chofer")) botonChofer = true;
                                    if (b.getText().equals("Estudiantes")) botonEstudiantes = true;
                                }
                            }
                        }
                    }
                }
            }

            assertTrue("Falta el botón Chofer", botonChofer);
            assertTrue("Falta el botón Estudiantes", botonEstudiantes);
            assertTrue("Falta el texto de la etiqueta", etiquetaCorrecta);
        });
    }

    @Test
    public void testSimulacionBotones() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            JButton botonChofer = null;
            JButton botonEstudiantes = null;

            for (Component c : frame.getContentPane().getComponents()) {
                if (c instanceof JPanel) {
                    JPanel panel = (JPanel) c;
                    for (Component inner : panel.getComponents()) {
                        if (inner instanceof JPanel) {
                            JPanel botones = (JPanel) inner;
                            for (Component boton : botones.getComponents()) {
                                if (boton instanceof JButton) {
                                    JButton b = (JButton) boton;
                                    if (b.getText().equals("Chofer")) botonChofer = b;
                                    if (b.getText().equals("Estudiantes")) botonEstudiantes = b;
                                }
                            }
                        }
                    }
                }
            }

            assertNotNull("Botón Chofer no encontrado", botonChofer);
            assertNotNull("Botón Estudiantes no encontrado", botonEstudiantes);

            // Simula clics en ambos botones
            for (ActionListener al : botonChofer.getActionListeners()) {
                al.actionPerformed(null);
            }

            for (ActionListener al : botonEstudiantes.getActionListeners()) {
                al.actionPerformed(null);
            }
        });
    }

    @After
    public void tearDown() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            if (frame != null) {
                frame.dispose();
            }
        });
    }
}
