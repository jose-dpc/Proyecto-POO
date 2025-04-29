package SistemadeRegistro.RegistrodeChofer;

public class Chofer {
    private String nombre;
    private int telefono;
    private String correo;
    private String curp;
    private String estado; // "Pendiente", "Aprobado", "Rechazado"

    public Chofer(String nombre, String correo, int telefono, String curp) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.curp = curp;
        this.estado = "Pendiente"; // Al registrarse, siempre "Pendiente"
    }

    // Getters y Setters
    public String getNombre() { 
        return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getTelefono() { 
        return telefono; }
    public void setTelefono(int telefono) { this.telefono = telefono; }

    public String getCorreo() { 
        return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getCurp() { 
        return curp; }
    public void setCurp(String curp) { this.curp = curp; }

    public String getEstado() { 
        return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
