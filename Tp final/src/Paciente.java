import java.util.ArrayList;

public class Paciente extends Persona{
    private String obraSocial;
    // ESTA LISTA TIENE QUE SER DE TIPO MANEJOLISTAS - private ArrayList<Turno>historialTurnos;

    public Paciente(String nombre, String apellido, int dni, int telefono, String obraSocial) {
        super(nombre, apellido, dni, telefono);
        this.obraSocial = obraSocial;
         historialTurnos = new ArrayList();
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public ArrayList<Turno> getHistorialTurnos() {
        return historialTurnos;
    }

    public void setHistorialTurnos(ArrayList<Turno> historialTurnos) {
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
