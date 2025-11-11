import org.json.JSONObject;

import java.util.ArrayList;

public class Profesional extends Persona{
    private Especialidad especialidad;
    private String matricula;

    public Profesional(String nombre, String apellido, int dni, int telefono, Especialidad especialidad, String matricula) {
        super(nombre, apellido, dni, telefono);
        this.especialidad = especialidad;
        this.matricula = matricula;
    }

    public JSONObject toJson(){
        JSONObject profesional= new JSONObject();
      JSONObject persona = super.toJson();
      profesional.put("persona",persona);
        profesional.put("especialidad",especialidad);
        profesional.put("matricula",matricula);

        return profesional;
    }

    @Override
    public String toString() {
        return "Profesional [nombre=" + getNombre() + ", apellido=" + getApellido() + ", especialidad=" + especialidad + ", matricula=" + matricula + "]";
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
