package SistemadeRegistro;
import javax.swing.SwingUtilities;

import SistemadeRegistro.SelecciondeUsuario.SeleccióndeUsuarioGUI;

public class SistemadeRegistro {
    
    public static void main(String[] args) {
        // Crear y mostrar la ventana principal
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SeleccióndeUsuarioGUI().setVisible(true);
            }
        });
    }
}


