import java.util.ArrayList;

public class ManejoListas <T>{
    private ArrayList<T> elementos;


    public ManejoListas() {
        this.elementos = new ArrayList<>();
    }


    public void agregar(T elemento) {
        elementos.add(elemento);
    }

    public void eliminar(T elemento) {
        if (elementos.remove(elemento)) {
            System.out.println("Elemento eliminado: " + elemento);
        } else {
            System.out.println("El elemento no se encuentra en la lista.");
        }
    }


    public void listar() {
        if (elementos.isEmpty()) {
            System.out.println("La lista está vacía.");
        } else {
            System.out.println("Elementos en la lista:");
            for (T e : elementos) {
                System.out.println("- " + e.toString());
            }
        }
    }


    public ArrayList<T> getElementos() {
        return elementos;
    }
}
