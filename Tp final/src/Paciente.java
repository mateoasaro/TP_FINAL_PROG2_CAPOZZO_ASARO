import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Paciente extends Persona{
    private String obraSocial;
   private ManejoListas<Turno>historialTurnos;

    public Paciente(String nombre, String apellido, int dni, int telefono, String obraSocial) {
        super(nombre, apellido, dni, telefono);
        this.obraSocial = obraSocial;
         historialTurnos = new ManejoListas<>();
    }

    public JSONObject toJson(){
        JSONObject paciente = new JSONObject();
        JSONObject persona = super.toJson();
        paciente.put("persona",persona);
        paciente.put("obraSocial",obraSocial);
        JSONArray historialTurnos = new JSONArray();
        for (Turno t: getHistorialTurnos().getElementos()){
            historialTurnos.put(t);
        }
       paciente.put("historialTurnos",historialTurnos);

return paciente;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public ManejoListas<Turno> getHistorialTurnos() {
        return historialTurnos;
    }

    public void setHistorialTurnos(ManejoListas<Turno> historialTurnos) {
        this.historialTurnos = historialTurnos;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "obraSocial='" + obraSocial + '\'' +
                ", historialTurnos=" + historialTurnos +
                '}';
    }


}
