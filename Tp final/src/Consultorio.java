import java.util.ArrayList;

public class Consultorio {
    private int id;
    private static int contador=0;
    private String nombre;
    private ArrayList<Horario> horarios;
    private ManejoListas<String/*clase profesiona*/> profesionales;

    public Consultorio(int id, String nombre, ManejoListas<String> profesionales) {
        this.id = contador++;
        this.nombre = nombre;
        this.horarios = new ArrayList<>();
        this.profesionales = profesionales;
    }

    public void agregarProfesional(){}


}
