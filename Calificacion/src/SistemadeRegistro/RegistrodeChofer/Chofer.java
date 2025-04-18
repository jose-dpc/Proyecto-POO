package SistemadeRegistro.RegistrodeChofer;
public class Chofer {
    private String correo;
    private String contrasena;
    private String nombre;

    // Constructor por defecto
    public Chofer() {
        this.correo = "Desconocido";
        this.contrasena = "sin_contrasena";
        this.nombre = "Desconocido";
    }

    // Constructor con parámetros
    public Chofer(String correo, String contrasena, String nombre) {
        setId(correo);
        setContrasena(contrasena);
        setNombre(nombre);
    }

    // Métodos getter
    public String getId() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    // Método toString para representar al estudiante como texto
    public String toString() {
        return String.format("ID: %s, Nombre: %s", correo, nombre);
    }

    // Métodos setter con validación
    public void setId(String correo) {
        if (correo != null && !correo.isEmpty()) {
            this.correo = correo;
        } else {
            System.out.println("ID no válido. Asignando ID por defecto.");
            this.correo = "Desconocido";
        }
    }

    public void setContrasena(String contrasena) {
        if (contrasena != null && contrasena.length() >= 8) {
            this.contrasena = contrasena;
        } else {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        }
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            this.nombre = nombre;
        } else {
            System.out.println("Nombre inválido. Asignando nombre por defecto.");
            this.nombre = "Desconocido";
        }
    }

    // Método que indica si la contraseña es segura (al menos 8 caracteres)
    public boolean contrasenaSegura() {
        return contrasena.length() >= 8;
    }
}