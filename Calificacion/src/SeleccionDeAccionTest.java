import static org.junit.Assert.*;

import javax.swing.SwingUtilities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SeleccionDeAccionTest {

    private SeleccionDeAccion gui;

    @Before
    public void setUp() throws Exception {
        // Crear la GUI de forma segura en el hilo de eventos
        SwingUtilities.invokeAndWait(() -> {
            gui = new SeleccionDeAccion();
        });
    }

    @After
    public void tearDown() throws Exception {
        if (gui != null) {
            gui.dispose(); // Cierra la ventana después del test
        }
    }

    @Test
    public void testVentanaEsVisibleYConTituloCorrecto() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            assertNotNull("La ventana no debe ser null", gui);
            assertTrue("La ventana debe estar visible", gui.isVisible());

            String tituloEsperado = "Sistema de Calificación - Transporte UDLAP";
            assertEquals("El título de la ventana es incorrecto", tituloEsperado, gui.getTitle());
        });
    }
}
