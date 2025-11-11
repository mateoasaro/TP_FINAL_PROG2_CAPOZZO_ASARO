import org.json.JSONObject;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Horario {
    private DayOfWeek dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Horario(DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin) {
        if (horaFin.isBefore(horaInicio)) {
            throw new IllegalArgumentException("La hora de fin no puede ser anterior a la hora de inicio.");
        }
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }


    public JSONObject toJson(){
        JSONObject horario=new JSONObject();
        horario.put("dia",dia);
        horario.put("horaInicio",horaInicio);
        horario.put("horaFin",horaFin);

        return horario;
    }

    //CONSTRUCTOR USANDO STRINGS (ejemplo:"09:00")
    public Horario(DayOfWeek dia, String horaInicio, String horaFin) {
        this(dia, LocalTime.parse(horaInicio), LocalTime.parse(horaFin));
    }

    public DayOfWeek getDia() {
        return dia;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }


    // devuelve la duración total del horario.

    public Duration duracion() {
        return Duration.between(horaInicio, horaFin);
    }

    // Indica si una fecha y hora dadas están dentro del horario.

    public boolean contiene(LocalDateTime hora) {
        return hora.getDayOfWeek() == dia &&
                !hora.toLocalTime().isBefore(horaInicio) &&
                !hora.toLocalTime().isAfter(horaFin);
    }

    @Override
    public String toString() {
        return "Horario{" +
                "dia=" + dia +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                '}';
    }
}
