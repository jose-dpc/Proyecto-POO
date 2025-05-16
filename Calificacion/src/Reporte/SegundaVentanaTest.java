package Reporte;

import org.junit.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class SegundaVentanaTest {

    private JFrame frameInterno;

    @Before
    public void setUp() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            new SegundaVentana(null);
        });

        // Esperar y buscar el frame interno con título "REPORTE"
        SwingUtilities.invokeAndWait(() -> {
            for (Frame f : Frame.getFrames()) {
                if (f.isVisible() && f.getTitle().equals("REPORTE")) {
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
            boolean areaEncontrada = false;
            boolean botonEncontrado = false;

            for (Component c : frameInterno.getContentPane().getComponents()) {
                if (c instanceof JScrollPane) {
                    JScrollPane scroll = (JScrollPane) c;
                    if (scroll.getViewport().getView() instanceof JTextArea) {
                        areaEncontrada = true;
                    }
                } else if (c instanceof JButton) {
                    botonEncontrado = true;
                }
            }

            assertTrue("Debe existir un área de texto", areaEncontrada);
            assertTrue("Debe existir un botón", botonEncontrado);
        });
    }

    @Test
    public void testEnvioDeReporte() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            JTextArea area = null;
            JButton boton = null;

            for (Component c : frameInterno.getContentPane().getComponents()) {
                if (c instanceof JScrollPane) {
                    JScrollPane scroll = (JScrollPane) c;
                    if (scroll.getViewport().getView() instanceof JTextArea) {
                        area = (JTextArea) scroll.getViewport().getView();
                    }
                } else if (c instanceof JButton) {
                    boton = (JButton) c;
                }
            }

            assertNotNull(area);
            assertNotNull(boton);

            area.setText("Esto es una queja de prueba.");
            for (ActionListener al : boton.getActionListeners()) {
                al.actionPerformed(null);
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
