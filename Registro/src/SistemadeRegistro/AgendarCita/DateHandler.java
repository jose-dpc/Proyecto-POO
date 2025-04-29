package SistemadeRegistro.AgendarCita;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;

public class DateHandler {

    public static boolean esFechaValida(LocalDate fecha) {
        return !fecha.isBefore(LocalDate.now());
    }

    public static boolean esHoraValida(LocalTime hora) {
        LocalTime inicio = LocalTime.of(9, 0);
        LocalTime fin = LocalTime.of(17, 0);
        return !hora.isBefore(inicio) && !hora.isAfter(fin);
    }

    public static boolean esDiaLaboral(LocalDate fecha) {
        DayOfWeek dia = fecha.getDayOfWeek();
        return dia != DayOfWeek.SATURDAY && dia != DayOfWeek.SUNDAY;
    }

    // obtener número de días que tiene un mes en un año específico
    public static int obtenerDiasDelMes(int anio, int mes) {
        YearMonth yearMonth = YearMonth.of(anio, mes);
        return yearMonth.lengthOfMonth(); // Devuelve 28, 29, 30 o 31 según el mes y año
    }

    //saber si un año es bisiesto
    public static boolean esAnioBisiesto(int anio) {
        return java.time.Year.isLeap(anio);
    }
}
