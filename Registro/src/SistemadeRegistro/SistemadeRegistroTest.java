package SistemadeRegistro;

import static org.junit.Assert.assertTrue;

import java.awt.Window;

import javax.swing.SwingUtilities;

import org.junit.Test;

import SistemadeRegistro.SelecciondeUsuario.SelecciondeUsuarioGUI;

public class SistemadeRegistroTest {

    @Test
    public void testSelecciondeUsuarioGUIVisible() throws Exception {
        // Ejecutar el main de forma segura en el hilo de eventos
        SwingUtilities.invokeAndWait(() -> {
            SistemadeRegistro.main(new String[]{});
        });

        // Esperar un momento para que la ventana se abra (opcional)
        Thread.sleep(500);

        // Verificar si alguna ventana visible es una instancia de SelecciondeUsuarioGUI
        boolean ventanaVisible = false;
        for (Window w : Window.getWindows()) {
            if (w.isVisible() && w instanceof SelecciondeUsuarioGUI) {
                ventanaVisible = true;
                break;
            }
        }

        assertTrue("La ventana SelecciondeUsuarioGUI debería estar visible", ventanaVisible);
    }
}
