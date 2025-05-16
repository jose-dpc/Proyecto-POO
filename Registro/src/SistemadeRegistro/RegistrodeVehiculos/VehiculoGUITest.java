package SistemadeRegistro.RegistrodeVehiculos;

import SistemadeRegistro.RegistrodeChofer.Chofer;
import org.junit.*;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class VehiculoGUITest {
    private VehiculoGUI vehiculoGUI;
    private Chofer choferDummy;

    @Before
    public void setUp() throws InvocationTargetException, InterruptedException {
        // Usamos el constructor de Chofer con los datos requeridos
        choferDummy = new Chofer("Juan Pérez", "juan@mail.com", 1234567890, "CURP123456");

        SwingUtilities.invokeAndWait(() -> {
            vehiculoGUI = new VehiculoGUI(null, choferDummy);
        });
    }

    @Test
    public void testRegistroVehiculoConDatosValidos() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            Component[] components = vehiculoGUI.getContentPane().getComponents();

            for (int i = 0; i < components.length; i++) {
                Component comp = components[i];
                if (comp instanceof JTextField && i > 0 && components[i - 1] instanceof JLabel) {
                    JLabel label = (JLabel) components[i - 1];
                    JTextField tf = (JTextField) comp;
                    switch (label.getText()) {
                        case "Marca:":
                            tf.setText("Toyota");
                            break;
                        case "Modelo:":
                            tf.setText("Corolla");
                            break;
                        case "Placas:":
                            tf.setText("XYZ1234");
                            break;
                        case "Color:":
                            tf.setText("Negro");
                            break;
                        case "Año:":
                            tf.setText("2019");
                            break;
                        case "Kilometraje:":
                            tf.setText("60000");
                            break;
                        case "Rendimiento (km/L):":
                            tf.setText("14");
                            break;
                        case "Poliza de seguro":
                            tf.setText("POL123");
                            break;
                    }
                }
            }

            // Simula el clic en el botón "Registrar Vehiculo"
            for (Component comp : components) {
                if (comp instanceof JButton) {
                    JButton btn = (JButton) comp;
                    if ("Registrar Vehiculo".equals(btn.getText())) {
                        btn.doClick();
                        break;
                    }
                }
            }
        });
    }

    @After
    public void tearDown() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> vehiculoGUI.dispose());
    }
}
