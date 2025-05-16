package Reporte;

import org.junit.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class ReporteUsuarioTest {

    private JFrame frameInterno;

    @Before
    public void setUp() throws InvocationTargetException, InterruptedException {
        // Ejecuta la creación del frame en el hilo de eventos de Swing
        SwingUtilities.invokeAndWait(() -> {
            new ReporteUsuario(null);
        });

        // Buscar el frame con título "Reporte de Incidente"
        SwingUtilities.invokeAndWait(() -> {
            for (Frame f : Frame.getFrames()) {
                if (f.isVisible() && f.getTitle().equals("Reporte de Incidente")) {
                    frameInterno = (JFrame) f;
                    break;
                }
            }
        });

        assertNotNull("No se encontró el frame interno", frameInterno);
    }

    @Test
    public void testComponentesVisibles() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            boolean choferBoton = false;
            boolean pasajeroBoton = false;
            boolean continuarBoton = false;
            boolean campoRuta = false;

            for (Component c : frameInterno.getContentPane().getComponents()) {
                if (c instanceof JButton) {
                    JButton b = (JButton) c;
                    if (b.getText().equals("Chofer")) choferBoton = true;
                    if (b.getText().equals("Pasajero")) pasajeroBoton = true;
                    if (b.getText().equals("Continuar")) continuarBoton = true;
                } else if (c instanceof JTextField) {
                    campoRuta = true;
                }
            }

            assertTrue("Falta botón Chofer", choferBoton);
            assertTrue("Falta botón Pasajero", pasajeroBoton);
            assertTrue("Falta botón Continuar", continuarBoton);
            assertTrue("Falta campo de texto para ruta", campoRuta);
        });
    }

    @Test
    public void testContinuarSinDatos() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            JButton continuar = null;

            for (Component c : frameInterno.getContentPane().getComponents()) {
                if (c instanceof JButton && ((JButton) c).getText().equals("Continuar")) {
                    continuar = (JButton) c;
                    break;
                }
            }

            assertNotNull("Botón continuar no encontrado", continuar);

            // Simula clic en botón sin seleccionar tipo ni ruta
            for (ActionListener al : continuar.getActionListeners()) {
                al.actionPerformed(null);  // Esto debería lanzar un JOptionPane con "Datos faltantes"
            }
        });
    }

    @After
    public void tearDown() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            if (frameInterno != null) {
                frameInterno.dispose();
            }
        });
    }
}
