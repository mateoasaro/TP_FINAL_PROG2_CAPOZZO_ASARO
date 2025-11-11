import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Turno {
    private int id;
    private static int contador = 0;
    private Paciente paciente;
    private Profesional profesional;
    private Consultorio consultorio;
    private LocalDateTime fechaYhora;
    private EstadoTurno estadoTurno;

    public Turno( Paciente paciente, Profesional profesional, Consultorio consultorio, LocalDateTime fechaYhora, EstadoTurno estadoTurno) {
        this.id = contador++;
        this.paciente = paciente;
        this.profesional = profesional;
        this.consultorio = consultorio;
        this.fechaYhora = fechaYhora;
        this.estadoTurno = estadoTurno;
    }

    public void cancelarTurno(){
        setEstadoTurno(EstadoTurno.Cancelado);
}
public void turnoFinalizado(){
        setEstadoTurno(EstadoTurno.Atendido);
}

public JSONObject toJson(){
        JSONObject turnoJson = new JSONObject();
        turnoJson.put("id",id);
        turnoJson.put("paciente",paciente.toJson());
        turnoJson.put("profesional",profesional.toJson());
        turnoJson.put("consultorio",consultorio.toJson());
        turnoJson.put("inicio",fechaYhora);
        turnoJson.put("estadoTurno",estadoTurno);

        return turnoJson;
}

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", paciente=" + paciente.toString() +
                ", profesional=" + profesional.toString() +
                ", consultorio=" + consultorio.toString() +
                ", fechaYhora=" + fechaYhora +
                ", estadoTurno=" + estadoTurno +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Turno.contador = contador;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public LocalDateTime getFechaYhora() {
        return fechaYhora;
    }

    public void setFechaYhora(LocalDateTime fechaYhora) {
        this.fechaYhora = fechaYhora;
    }

    public EstadoTurno getEstadoTurno() {
        return estadoTurno;
    }

    public void setEstadoTurno(EstadoTurno estadoTurno) {
        this.estadoTurno = estadoTurno;
    }

    public void confirmarTurno(EstadoTurno estadoTurno) throws ImposibleConfirmarEx{
        if (estadoTurno == EstadoTurno.Cancelado) {
      throw new ImposibleConfirmarEx("No se puede confirmar un turno ya cancelado.");
        }
        setEstadoTurno(EstadoTurno.Confirmado);
    }

}
