import org.json.JSONArray;

import java.util.ArrayList;

public class GestorTurno {
    private ArrayList<Turno> turnos;
    public static String nombreArchivo="turnos.json";

    public GestorTurno() {
        this.turnos = new ArrayList<>();
    }

    public void cancelarTurno(int idTurno){
        for (Turno t:turnos){
            if(t.getId()==idTurno){
                t.cancelarTurno();
            }
        }
    }
    public void pacienteAtendido(int idTurno){
        for (Turno t:turnos){
            if(t.getId()==idTurno){
                t.turnoFinalizado();
            }
        }
    }

    public void verTurnosXpaciente(int dniPacienteBuscado){
        int contador=1;
        System.out.println("Turnos del paciente:");
        for (Turno t:turnos){
            if (t.getPaciente().getDni()==dniPacienteBuscado){
                System.out.println("Turno "+contador+": "+t.toString());
            }
        }
    }
   public void verTurnosXprofesional(int dniProfesionalBuscado){
        int contador=1;
        System.out.println("Turnos del profesional:");
        for (Turno t:turnos){
            if (t.getPaciente().getDni()==dniProfesionalBuscado){
                System.out.println("Turno "+contador+": "+t.toString());
            }
        }
    }

    public void agregarTurno(Turno turno){
        turnos.add(turno);
    }

public void eliminarTurno(Turno turno){
        turnos.remove(turno);
}

public void guardarEnArchivo(){
    JSONArray turnosJson=new JSONArray();
    for (Turno t:turnos){
        turnosJson.put(t.toJson());
    }
    JSONutiles.uploadJSON(turnosJson,nombreArchivo);
}

public void verTurnos(){
        for (Turno t:turnos){
            System.out.println(t.toString());
        }
}
}
