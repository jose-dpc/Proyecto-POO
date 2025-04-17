package SistemadeRegistro.Rutas;
public class Ruta {
    private String inicio;
    private String fin;
    private int numParadas;
    private double distancia;

    // Constructor por defecto
    public Ruta() {
        this.inicio = "Desconocido";
        this.fin = "Desconocido";
        this.numParadas = 0;
        this.distancia = 0.0;
    }

    // Constructor con parámetros
    public Ruta(String inicio, String fin, int numParadas, double distancia) {
        setInicio(inicio);
        setFin(fin);
        setNumParadas(numParadas);
        setDistancia(distancia);
    }

    // Métodos getter
    public String getInicio() {
        return inicio;
    }

    public String getFin() {
        return fin;
    }

    public int getNumParadas() {
        return numParadas;
    }

    public double getDistancia() {
        return distancia;
    }

    // Método toString para representar la ruta como texto
    public String toString() {
        return String.format("Ruta de %s a %s, con %d paradas. Distancia: %.2f km.", inicio, fin, numParadas, distancia);
    }

    // Métodos setter con validación
    public void setInicio(String inicio) {
        this.inicio = (inicio != null && !inicio.isEmpty()) ? inicio : "Desconocido";
    }

    public void setFin(String fin) {
        this.fin = (fin != null && !fin.isEmpty()) ? fin : "Desconocido";
    }

    public void setNumParadas(int numParadas) {
        if (numParadas > 0) {
            this.numParadas = numParadas;
        } else {
            System.out.println("El número de paradas debe ser mayor que 0.");
            this.numParadas = 0;
        }
    }

    public void setDistancia(double distancia) {
        if (distancia > 0) {
            this.distancia = distancia;
        } else {
            System.out.println("La distancia debe ser mayor que 0.");
            this.distancia = 0.0;
        }
    }
}
