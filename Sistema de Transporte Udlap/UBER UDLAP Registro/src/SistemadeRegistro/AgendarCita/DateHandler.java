package SistemadeRegistro.AgendarCita;

import java.time.LocalDate;


class DateHandler {
    private LocalDate fecha;

    public DateHandler(int dia, int mes, int anio) {
        setFecha(dia, mes, anio);
    }

    public void setFecha(int dia, int mes, int anio) {
        try {
            fecha = LocalDate.of(anio, mes, dia);
        } catch (Exception e) {
            fecha = LocalDate.now();
        }
    }

    public void siguienteDia() {
        fecha = fecha.plusDays(1);
    }

    public void diaAnterior() {
        fecha = fecha.minusDays(1);
    }

    public int getDia() {
        return fecha.getDayOfMonth();
    }

    public int getMes() {
        return fecha.getMonthValue();
    }

    public int getAnio() {
        return fecha.getYear();
    }

    public String getFecha() {
        return fecha.toString();
    }
}