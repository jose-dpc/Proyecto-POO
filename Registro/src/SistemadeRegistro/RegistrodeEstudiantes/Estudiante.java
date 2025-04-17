package SistemadeRegistro.RegistrodeEstudiantes;
public class Estudiante {
    private String id;
    private String contrasena;
    private String nombre;

    // Constructor por defecto
    public Estudiante() {
        this.id = "Desconocido";
        this.contrasena = "sin_contrasena";
        this.nombre = "Desconocido";
    }

    // Constructor con parámetros
    public Estudiante(String id, String contrasena, String nombre) {
        setId(id);
        setContrasena(contrasena);
        setNombre(nombre);
    }

    // Métodos getter
    public String getId() {
        return id;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    // Método toString para representar al estudiante como texto
    public String toString() {
        return String.format("ID: %s, Nombre: %s", id, nombre);
    }

    // Métodos setter con validación
    public void setId(String id) {
        if (id != null && !id.isEmpty()) {
            this.id = id;
        } else {
            System.out.println("ID no válido. Asignando ID por defecto.");
            this.id = "Desconocido";
        }
    }

    public void setContrasena(String contrasena) {
        if (contrasena != null && contrasena.length() >= 6) {
            this.contrasena = contrasena;
        } else {
            System.out.println("Contraseña inválida. Debe tener al menos 6 caracteres.");
            this.contrasena = "sin_contrasena";
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