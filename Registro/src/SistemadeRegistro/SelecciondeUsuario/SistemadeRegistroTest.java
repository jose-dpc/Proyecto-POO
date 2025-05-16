package SistemadeRegistro.SelecciondeUsuario;

import static org.junit.Assert.*;

import javax.swing.SwingUtilities;

import org.junit.Test;

public class SistemadeRegistroTest {

    @Test
    public void testSelecciondeUsuarioGUIVisible() throws Exception {
        // Usar invokeAndWait para asegurar que la GUI se construya en el hilo correcto
        SwingUtilities.invokeAndWait(() -> {
            SelecciondeUsuarioGUI gui = new SelecciondeUsuarioGUI();
            assertNotNull("La ventana no debe ser null", gui);
            assertTrue("La ventana SelecciondeUsuarioGUI debería estar visible", gui.isVisible());
        });
    }
}
