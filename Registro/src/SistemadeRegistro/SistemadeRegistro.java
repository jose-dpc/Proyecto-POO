package SistemadeRegistro;
import javax.swing.SwingUtilities;

import SistemadeRegistro.SelecciondeUsuario.SelecciondeUsuarioGUI;

public class SistemadeRegistro {
    
    public static void main(String[] args) {
        // Crear y mostrar la ventana principal
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SelecciondeUsuarioGUI().setVisible(true);
            }
        });
    }
}


