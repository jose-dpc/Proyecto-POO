package SistemadeRegistro.CostodeViaje;
public class CalcPrecio {
    private double distancia;
    private int rendimiento; 
    private double total;
    private static final int costoLitro = 25;


    public CalcPrecio(double distancia, int rendimiento) {
        setDistancia(distancia);  // Usamos los setters para validar los valores
        setRendimiento(rendimiento);
    }

    public double precioFijo(double distancia){
        //establecemos un costo base de 20 pesos/km
        return distancia * 20;
    }

    public double tarifaRendimiento(){
        return (costoLitro)*(this.distancia/ (double) this.rendimiento);
    }

    public double costoViaje(){
        return precioFijo(this.distancia) + tarifaRendimiento();
    }

    //funciones getters
    public double getTotal(){
        total= costoViaje();
        return total;
    }

    public double getDistancia(){
        return distancia;
    }

    public int getRendimiento(){
        return rendimiento;
    }

    //funciones setters
    public void setDistancia(double d){
        if(d<=0){
            throw new IllegalArgumentException("La distancia debe ser mayor a cero");
        }
        else{
            this.distancia = d;
        }
        
    }

    public void setRendimiento(int r){
        //se establece un limite de 25 km/l porque mas que esto es muy poco comun a menos que el carro sea electrico
        if (r>0 && r<25) {
            this.rendimiento = r;
        }
        else{
            throw new IllegalArgumentException("El rendimiento debe ser mayor a cero y menor a 25");
        }
       
    }

    
    
}
