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
        setContrasena(contrasena); // Aquí lanza error si es insegura
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
            throw new IllegalArgumentException("ID no puede estar vacío.");
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
            throw new IllegalArgumentException("Nombre no puede estar vacío.");
        }
    }

    // Ya no se necesita este método si usas el setter como validación directa
    public boolean contrasenaSegura() {
        return contrasena.length() >= 8;
    }
}
