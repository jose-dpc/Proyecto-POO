package SistemadeRegistro.RegistrodeVehiculos;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VehiculoTest {

    private Vehiculo vehiculo;

    @Before
    public void setUp() {
        vehiculo = new Vehiculo();  // Se inicializa con valores por defecto
    }

    @Test
    public void testConstructorDefault() {
        assertEquals("Toyota", vehiculo.getMarca());
        assertEquals("Corolla", vehiculo.getModelo());
        assertEquals(2020, vehiculo.getAño());
        assertEquals(20000, vehiculo.getKm());
    }

    @Test
    public void testSetYGetColor() {
        vehiculo.setColor("Rojo");
        assertEquals("Rojo", vehiculo.getColor());
    }

    @Test
    public void testSetYGetPlacas() {
        vehiculo.setPlacas("ABC-123");
        assertEquals("ABC-123", vehiculo.getPlacas());
    }

    @Test
    public void testSetYGetPoliza() {
        vehiculo.setPoliza("POL123456");
        assertEquals("POL123456", vehiculo.getPoliza());
    }

    @Test
    public void testSetYGetRendimientoValido() {
        vehiculo.setRendimiento(18);
        assertEquals(18, vehiculo.getRendimiento());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetRendimientoInvalido() {
        vehiculo.setRendimiento(30);  // Mayor a 25
    }

    @Test
    public void testSetYGetKmValido() {
        vehiculo.setKm(5000);
        assertEquals(5000, vehiculo.getKm());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetKmNegativo() {
        vehiculo.setKm(-100);  // Inválido
    }

    @Test
    public void testSetYGetAñoValido() {
        vehiculo.setAño(2022);
        assertEquals(2022, vehiculo.getAño());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetAñoInvalido() {
        vehiculo.setAño(2010);  // Fuera del rango permitido
    }

    @Test
    public void testCheckKmConKilometrajeNormal() {
        vehiculo.setAño(2021);
        vehiculo.setKm(40000);  // 4 años * 20000 = 80000 esperado -> no necesita revisión
        assertFalse(vehiculo.checkKm());
    }

    @Test
    public void testCheckKmExcedeLimite() {
        vehiculo.setAño(2020);
        vehiculo.setKm(100000); // 5 años * 20000 = 100000 -> no supera, así que false
        assertFalse(vehiculo.checkKm());

        vehiculo.setKm(120000); // Excede -> true
        assertTrue(vehiculo.checkKm());
    }

    @Test
    public void testCheckKmVehiculoNuevo() {
        vehiculo.setAño(2025);  // Año actual
        vehiculo.setKm(1000);
        assertFalse(vehiculo.checkKm());  // No aplica revisión
    }
}
