package BasesDeDatos;
import javax.swing.*;

public class AgendaDirecciones {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("📘 Agenda de Direcciones (SQLite)");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(600, 500);

        // Crear panel desplazable (campos de texto)
        PanelDesplazable panelDatos = new PanelDesplazable();
        JScrollPane scrollPane = new JScrollPane(panelDatos);
        ventana.add(scrollPane, "Center");

        // Crear panel de control (botones)
        PanelControl panelBotones = new PanelControl(panelDatos.obtenerCampos());
        ventana.add(panelBotones, "South");

        ventana.setVisible(true);
    }
}