import java.time.LocalDateTime;

public class Turno {
    private int id;
    private static int contador = 0;
    private Paciente paciente;
    private Profesional profesional;
    private Consultorio consultorio;
    private LocalDateTime inicio;
    private EstadoTurno estadoTurno:

    public Turno(int id,String nombrePaciente, String apellidoPaciente, int dniP, int telefonoP, String obraSocial, Profesional profesional, Consultorio consultorio, LocalDateTime inicio, EstadoTurno estadoTurno) {
        this.id = contador++;
        this.paciente = paciente;
        this.profesional = profesional;
        this.consultorio = consultorio;
        this.inicio = inicio;
        this.estadoTurno = estadoTurno;
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

    public void confirmarTurno(EstadoTurno estadoTurno) {
        if (estadoTurno == EstadoTurno.Cancelado) {
            //crear excepcion con ese nombre   throw new ImposibleConfirmarEx("No se puede confirmar un turno cancelado.");
        }
        setEstadoTurno(EstadoTurno.Confirmado);
    }
}

public void cancelarTurno(EstadoTurno estadoTurno) {
    setEstadoTurno(EstadoTurno.Cancelado);
}

public void reprogramar(LocalDateTime nuevaFecha) {
    setInicio(nuevaFecha);
}

@Override
public String toString() {
    return "Turno{" +
            "id=" + id +
            ", paciente=" + paciente +
            ", profesional=" + profesional +
            ", consultorio=" + consultorio +
            ", inicio=" + inicio +
            ", estadoTurno=" + estadoTurno +
            '}';
}
}
