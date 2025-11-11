import org.json.JSONArray;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.util.ArrayList;

public class Consultorio {
    private int id;
    private static int contador=0;
    private String nombre;
    private ArrayList<Horario> horarios;
    private Profesional profesional;

    public Consultorio(String nombre,Profesional profesionales) {
        this.id = contador++;
        this.nombre = nombre;
        this.horarios = new ArrayList<>();
        this.profesional = profesional;
    }

    public JSONObject toJson(){
        JSONObject consultorio = new JSONObject();
        consultorio.put("id",id);
        consultorio.put("nombre",nombre);
        JSONArray horariosJson =new JSONArray();
        for (Horario h:horarios){
            horariosJson.put(h.toJson());
        }
        consultorio.put("horarios",horariosJson);
        consultorio.put("nombre",nombre);

        return consultorio;
    }

    public void agregarHorario(DayOfWeek dia, String horaInicio, String horaFin){
        Horario nuevo=new Horario(dia, horaInicio, horaFin);

        horarios.add(nuevo);
    }

    public ArrayList<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<Horario> horarios) {
        this.horarios = horarios;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
