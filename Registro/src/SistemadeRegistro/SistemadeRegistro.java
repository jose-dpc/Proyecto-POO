package SistemadeRegistro;
import SistemadeRegistro.SelecciondeUsuario.SeleccióndeUsuarioGUI;
import javax.swing.SwingUtilities;

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


