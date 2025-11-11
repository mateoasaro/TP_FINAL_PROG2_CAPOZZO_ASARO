public class Sistema {

    private Scanner scanner;
    private static final String CONTRASENA_ADMIN = "admin123";

    private ManejoListas<Consultorio> consultorios;
    private ManejoListas<Profesional> listaProfesionales;
    private ManejoListas<Paciente> listaPacientes;
    private GestorTurno gestorTurnos;

    public Sistema() {
        this.consultorios = new ManejoListas<Consultorio>();
        this.listaProfesionales = new ManejoListas<Profesional>();
        this.listaPacientes = new ManejoListas<Paciente>();
        this.gestorTurnos = gestorTurnos;
    }

    public Paciente buscarPaciente(int dni) {
        for (Paciente p : listaPacientes.getElementos()) {
            if (p.getDni() == dni) {
                return p;
            }
        }
        return null;
    }

    public Consultorio buscarConsultorioPorId(int id) {
        for (Consultorio c : consultorios.getElementos()) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    //iniciar()

    //menu principal()

    public void menuPrincipal() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n-Bienvenido al sistema! ingrese la opcion que desee: ");
            System.out.println("1. Ingresar como cliente");
            System.out.println("2. Ingresar como administrador");
            System.out.println("3. Salir del sistema");
            try {
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        menuCliente();
                        break;
                    case 2:
                        menuAdmin();
                        break;
                    case 3:
                        if (gestorTurnos != null) {
                            gestorTurnos.guardarTurnos();
                        }
                        System.out.println("Sesion cerrada exitosamente");
                        salir = true;
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número válido");
                scanner.nextLine();
            }
        }
    }
}



    // menu admin()

// metodos para el cliente
     public void menuCliente() {
         boolean salir = false;
         while (!salir) {
             System.out.println("Bienvenido, por favor ingrese su DNI: ");
             try {
                 int dni = scanner.nextInt();
                 paciente = buscarPaciente(dni);

                 if (paciente != null) {
                     System.out.println("\n¡Bienvenido/a de nuevo, " + paciente.getNombre() + "! 😃");
                 } else {

                     System.out.println("\n--- Para registrarse, ingrese los datos solicitados ---");
                     System.out.print("Ingrese su Nombre: ");
                     String nombre = scanner.nextLine();
                     System.out.print("Ingrese su Apellido: ");
                     String apellido = scanner.nextLine();
                     System.out.println("Ingrese su numero de telefono: (solo numeros) ");
                     int telefono = scanner.nextInt();
                     System.out.print("Ingrese su Obra Social: ");
                     String obraSocial = scanner.nextLine();

                     paciente = new Paciente(nombre, apellido, dni, telefono, obraSocial);
                     agregarPaciente(paciente);
                 }
             }
         }
     }

         private void menuOpcionesPaciente(Paciente paciente) {
             boolean volver = false;
             while (!volver) {
                 System.out.println("\n  Bienvenido " + paciente.getNombre() + " ingrese la opcion que desea realizar: ");
                 System.out.println("1. Agendar un tuevo turno");
                 System.out.println("2. Ver mi historial de turnos");
                 System.out.println("3. Cancelar un turno");
                 System.out.println("4. Volver al menú ");

                 try {
                     int opcion = scanner.nextInt();
                     scanner.nextLine();

                     switch (opcion) {
                         case 1:
                             solicitarTurnoPaciente(paciente);
                             break;
                         case 2:
                             mostrarHistorialTurnos(paciente); // falta hacer el metodo
                             break;
                         case 3:
                             cancelarTurnoPaciente(paciente); // falta hacer el metodo
                             break;
                         case 4:
                             volver = true;
                             break;
                     }
                 } catch (InputMismatchException e) {
                     System.out.println(" Ingrese un número válido.");
                     scanner.nextLine();
                 }
             }
         }

public void solicitarTurnoPaciente(Paciente paciente) {
    System.out.println("Paciente: " + paciente.getNombre() + " " + paciente.getApellido());

    Profesional profesionalElegido = elegirProfesional;
    if (profesionalElegido == null) return;

    Consultorio consultorioElegido = profesionalElegido.getConsultorioAsignado();
    if (consultorioElegido == null) {
        System.out.println(" El profesional elegido no tiene un consultorio asignado.");
        return;
    }

    LocalDateTime inicioTurno = seleccionarHorario(profesionalElegido, consultorioElegido);
    if (inicioTurno == null) return;

    Turno nuevoTurno = new Turno(paciente, profesionalElegido, consultorioElegido, inicioTurno, EstadoTurno.Pendiente);
    agregarTurno(nuevoTurno);

    System.out.println("\n su turno se agendo con existo, datos del turno:");
    System.out.println(nuevoTurno);
    System.out.println("Estado: " + EstadoTurno.Pendiente);

    }

    public void elegirProfesional(){
    int contadorProfesionales = 1;
        System.out.println("Ingrese la especialidad en la que desee atenderse");

for (int i = 0; i < listaProfesionales.size; i++){
    if (listaProfesionales.getEspecialidad(i) == especialidad elegida){
        System.out.println("Profesional N"+contadorProfesionales);
        contadorProfesionales++;
}
    }


    //mostrarDisponibilidad()

}
