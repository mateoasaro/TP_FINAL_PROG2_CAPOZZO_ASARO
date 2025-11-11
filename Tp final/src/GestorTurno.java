import java.util.ArrayList;

public class GestorTurno {
    private ArrayList<Turno> turnos;

    public GestorTurno(ArrayList<Turno> turnos) {
        this.turnos = turnos;
    }

    public void agregarTurno(Turno turno){
        turnos.add(turno)
    }
}
