import org.json.JSONObject;

import java.time.LocalDateTime;

public class Turno {
    private int id;
    private static int contador = 0;
    private Paciente paciente;
    private Profesional profesional;
    private Consultorio consultorio;
    private LocalDateTime inicio;
    private EstadoTurno estadoTurno;

    public Turno(int id, String nombrePaciente, String apellidoPaciente, int dniP, int telefonoP, String obraSocial, String nombreProfesional, String apellidoProfesional, int dniProfesional, int telefonoProfesional, Especialidad especialidad, String matriculaProfesional, Consultorio consultorio, LocalDateTime inicio, EstadoTurno estadoTurno) {
        this.id = contador++;
        paciente = new Paciente(nombrePaciente, apellidoPaciente,dniP,telefonoP,obraSocial);
        profesional = new Profesional(nombreProfesional,apellidoProfesional,dniProfesional,telefonoProfesional,especialidad,matriculaProfesional);
        this.consultorio = consultorio;
        this.inicio = inicio;
        this.estadoTurno = estadoTurno;
    }
public void cancelarTurno(){
        setEstadoTurno(EstadoTurno.Cancelado);
}

public JSONObject toJson(){
        JSONObject turnoJson = new JSONObject();
        turnoJson.put("id",id);
        turnoJson.put("paciente",paciente.toJson());
        turnoJson.put("profesional",profesional.toJson());
        turnoJson.put("consultorio",consultorio.toJson());
        turnoJson.put("inicio",inicio);
        turnoJson.put("estadoTurno",estadoTurno);

        return turnoJson;
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

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
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
