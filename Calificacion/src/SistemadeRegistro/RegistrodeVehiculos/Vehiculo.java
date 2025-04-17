package SistemadeRegistro.RegistrodeVehiculos;

public class Vehiculo {

private String modelo;
private int km;
private int año;
private String marca;
private String poliza;
private String placas;
private String color;
private int rendimiento;

//constructor default

public Vehiculo(){
    marca = "Toyota";
    modelo = "Corolla";
    año = 2020;
    km = 20000;
}

//funciones getters
public int getRendimiento(){
    return rendimiento;
}

public String getColor(){
    return color;
}

public String getPlacas(){
    return placas;
}

public String getPoliza(){
    return poliza;
}

public String getMarca() {
    return marca;
}

public int getAño() {
    return año;
}

public String getModelo() {
    return modelo;
}

public int getKm() {
    return km;
}

//funciones setters
public void setRendimiento(int rendimiento){
    //se establece un limite de 25 km/l porque mas que esto es muy poco comun a menos que el carro sea electrico
    if (rendimiento>0 && rendimiento<25) {
        this.rendimiento = rendimiento;
    }
    else{
        throw new IllegalArgumentException("El rendimiento debe ser mayor a cero y menor a 25");
    }
}

public void setColor(String color){
   this.color = color;
}

public void setPlacas(String placas){
  this.placas = placas;
}

public void setPoliza(String poliza){
   this.poliza = poliza;
}

public void setMarca(String m) {
    this.marca = m;
}

public void setModelo(String l) {
    this.modelo = l;
}

public void setAño(int a) {
    if (a >= 2016 && a <= 2025) {
        año = a;
    } else {
        throw new IllegalArgumentException("Año fuera del rango permitido para este programa (2016 - 2025).");
    }
}

public void setKm(int k) {
    if (k >= 0) {
        km = k;
    } else {
        throw new IllegalArgumentException("El kilometraje debe ser positivo");
    }
}

//verificacion de kilometraje con respecto al modelo
public boolean checkKm() {
    int añosUso = 2025 - año;
    int kmEsperado = añosUso * 20000;

    // Evita revisar si el auto es del año actual (2025)
    if (añosUso == 0) {
        return false;
    }

    return km > kmEsperado; // True si necesita revisión
}

}

