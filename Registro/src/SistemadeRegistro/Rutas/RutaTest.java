package SistemadeRegistro.Rutas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RutaTest {

    private Ruta ruta;

    @Before
    public void setUp() {
        ruta = new Ruta();
    }

    @Test
    public void testConstructorPorDefecto() {
        assertEquals("Desconocido", ruta.getInicio());
        assertEquals("Desconocido", ruta.getFin());
        assertEquals(0, ruta.getNumParadas());
        assertEquals(0.0, ruta.getDistancia(), 0.001);
    }

    @Test
    public void testConstructorConParametrosValidos() {
        Ruta r = new Ruta("Puebla", "Cholula", 3, 12.5);
        assertEquals("Puebla", r.getInicio());
        assertEquals("Cholula", r.getFin());
        assertEquals(3, r.getNumParadas());
        assertEquals(12.5, r.getDistancia(), 0.001);
    }

    @Test
    public void testSettersConValoresValidos() {
        ruta.setInicio("UDLAP");
        ruta.setFin("Centro");
        ruta.setNumParadas(4);
        ruta.setDistancia(8.2);

        assertEquals("UDLAP", ruta.getInicio());
        assertEquals("Centro", ruta.getFin());
        assertEquals(4, ruta.getNumParadas());
        assertEquals(8.2, ruta.getDistancia(), 0.001);
    }

    @Test
    public void testSettersConValoresInvalidos() {
        ruta.setInicio(null);
        ruta.setFin("");
        ruta.setNumParadas(-2);
        ruta.setDistancia(0);

        assertEquals("Desconocido", ruta.getInicio());
        assertEquals("Desconocido", ruta.getFin());
        assertEquals(0, ruta.getNumParadas());
        assertEquals(0.0, ruta.getDistancia(), 0.001);
    }

    @Test
    public void testToString() {
        ruta.setInicio("Terminal A");
        ruta.setFin("Terminal B");
        ruta.setNumParadas(5);
        ruta.setDistancia(15.75);

        String esperado = "Ruta de Terminal A a Terminal B, con 5 paradas. Distancia: 15.75 km.";
        assertEquals(esperado, ruta.toString());
    }
}
