package SistemadeRegistro.SelecciondeRuta;

import static org.junit.Assert.*;

import javax.swing.*;
import java.awt.*;

import org.junit.Before;
import org.junit.Test;

public class SelecciondeRutaGUITest {

    private SelecciondeRutaGUI ventana;

    @Before
    public void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            ventana = new SelecciondeRutaGUI(null) {
                @Override
                public void dispose() {
                    // Evita cerrar la ventana real en los tests
                }
            };
        });
    }

    @Test
    public void testComponentesYAcciones() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            assertNotNull("La ventana debe existir", ventana);

            JButton botonCrear = encontrarBotonPorTexto(ventana, "Crear");
            JButton botonUnirse = encontrarBotonPorTexto(ventana, "Unirse");

            assertNotNull("El botón Crear debe existir", botonCrear);
            assertNotNull("El botón Unirse debe existir", botonUnirse);

            // Verificamos que al hacer click, la ventana se oculta (simulación)
            botonCrear.doClick();
            assertFalse("Ventana debe estar oculta tras click en Crear", ventana.isVisible());

            ventana.setVisible(true); // Reabrimos para siguiente test

            botonUnirse.doClick();
            assertFalse("Ventana debe estar oculta tras click en Unirse", ventana.isVisible());
        });
    }

    private JButton encontrarBotonPorTexto(Container contenedor, String textoEsperado) {
        for (Component comp : contenedor.getComponents()) {
            if (comp instanceof JButton boton && boton.getText().equals(textoEsperado)) {
                return boton;
            } else if (comp instanceof Container hijo) {
                JButton resultado = encontrarBotonPorTexto(hijo, textoEsperado);
                if (resultado != null) return resultado;
            }
        }
        return null;
    }
}
