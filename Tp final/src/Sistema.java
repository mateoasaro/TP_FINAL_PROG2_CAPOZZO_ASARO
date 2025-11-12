import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

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
        this.gestorTurnos = new GestorTurno();
        this.scanner = new Scanner(System.in);
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

                int opcion = scanner.nextInt();
            scanner.nextLine();

                switch (opcion) {
                    case 1:
                        menuCliente();
                        break;
                    case 2:
                        menuAdmin();
                        break;
                    case 3:
                        if (gestorTurnos != null) {
                            gestorTurnos.guardarEnArchivo();
                        }
                        System.out.println("Sesion cerrada exitosamente");
                        salir = true;
                        break;

                }

        }
    }




    private void menuAdmin() {
        System.out.println("\n---ACCESO DE ADMINISTRADOR---");
        System.out.print("Ingrese la contraseña: ");
        String password = scanner.nextLine();

        if (!password.equals(CONTRASENA_ADMIN)) {
            System.out.println("Acceso denegado. Contraseña incorrecta.");
            return;
        }

        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- MENÚ ADMINISTRADOR ---");
            System.out.println("1. Agregar Nuevo Consultorio");
            System.out.println("2. Mostrar  profesionales registrados");
            System.out.println("3. Ver Todos los Pacientes Registrados");
            System.out.println("4. Confirmar Turnos Pendientes (Asignar Horario)");
            System.out.println("5. Ver Todos los Historiales de Turnos");
            System.out.println("6. Volver al Menú Principal");
            System.out.print("Seleccione una opción de gestión: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea

                switch (opcion) {
                    case 1:
                        agregarConsultorio();
                        break;
                    case 2:
                        listaProfesionales.listar();
                        break;
                    case 3:
                        listaPacientes.listar();
                        break;
                    case 4:
                        confirmarTurnos();
                        break;
                    case 5:
                        verTodosLosHistoriales();
                        break;
                    case 6:
                        volver = true;
                        // Asegurar que se guarden los cambios antes de salir del admin
                        if (gestorTurnos != null) {
                            gestorTurnos.guardarEnArchivo();
                        }
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine();
            }
        }
    }

    public void verTodosLosHistoriales(){
        gestorTurnos.verTurnos();
    }

    public void confirmarTurnos(){
        int turnoElegido = 0;
        gestorTurnos.verTurnos();

            System.out.println("Ingrese el id del turno a confirmar");
            turnoElegido = scanner.nextInt();
                gestorTurnos.confirmarTurno(turnoElegido);
            }

    public Profesional crearProfesional() {
        System.out.println("\n---AGREGAR NUEVO PROFESIONAL PARA EL CONSULTORIO---");


            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Apellido: ");
            String apellido = scanner.nextLine();
            System.out.print("DNI: ");
            int dni = scanner.nextInt();
            System.out.print("Teléfono: ");
            int telefono = scanner.nextInt();
            System.out.print("Matrícula: ");
            int matricula = scanner.nextInt();
            scanner.nextLine();


            Especialidad espElegida = seleccionarEspecialidad();
            if (espElegida == null) {
                System.out.println("Operación de agregado de profesional cancelada.");

            }

            Profesional nuevoProfesional = new Profesional(nombre, apellido, dni, telefono, espElegida, matricula);

            listaProfesionales.agregar(nuevoProfesional);

            System.out.println("Profesional " + apellido + " (" + espElegida + ") agregado.");
            return nuevoProfesional;

    }

    public void agregarConsultorio(){
            System.out.println("\n---AGREGAR NUEVO CONSULTORIO ---");

            try {
                System.out.print("Ingrese el nombre del consultorio ");
                String nombre = scanner.nextLine();
Profesional nuevo = crearProfesional();
                Consultorio nuevoConsultorio = new Consultorio(nombre,nuevo);

                System.out.println("agregando horarios por defecto (Lunes a Viernes 08:00-20:00)");

                DayOfWeek[] diasLaborables = {
                        DayOfWeek.MONDAY,
                        DayOfWeek.TUESDAY,
                        DayOfWeek.WEDNESDAY,
                        DayOfWeek.THURSDAY,
                        DayOfWeek.FRIDAY
                };
                LocalTime horaApertura = LocalTime.of(8, 0);
                LocalTime horaCierre = LocalTime.of(20, 0);
                for (DayOfWeek dia : diasLaborables) {
                    Horario horarioDia = new Horario(dia, horaApertura, horaCierre);
                    nuevoConsultorio.getHorarios().add(horarioDia);
                }

                consultorios.agregar(nuevoConsultorio);
                System.out.println(" Consultorio '" + nombre + "' agregado y configurado con horario de lunes a viernes de 08:00 a 20:00 .");


            } catch (InputMismatchException e) {
                System.out.println("Error: El ID debe ser un número válido.");
                scanner.nextLine();
            }

    }
// metodos para el cliente
     public void menuCliente() {
         boolean salir = false;
         while (!salir) {
             System.out.println("Bienvenido, por favor ingrese su DNI: ");

                 int dni = scanner.nextInt();
             scanner.nextLine();
                 Paciente paciente = buscarPaciente(dni);

                 if (paciente != null) {
                     System.out.println("\n¡Bienvenido/a de nuevo, " + paciente.getNombre() + "!");
                 } else {

                     System.out.println("\n--- Para registrarse, ingrese los datos solicitados ---");
                     System.out.print("Ingrese su Nombre: ");
                     String nombre = scanner.nextLine();
                     System.out.print("Ingrese su Apellido: ");
                     String apellido = scanner.nextLine();
                     System.out.println("Ingrese su numero de telefono: (solo numeros) ");
                     int telefono = scanner.nextInt();
                     scanner.nextLine();
                     System.out.print("Ingrese su Obra Social: ");
                     String obraSocial = scanner.nextLine();

                     paciente = new Paciente(nombre, apellido, dni, telefono, obraSocial);
                     listaPacientes.agregar(paciente);
                 }
    menuOpcionesPaciente(paciente);
                 salir=true;
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
                            gestorTurnos.verTurnosXpaciente(paciente.getDni());
                             break;
                         case 3:
                             cancelarTurnoPaciente(paciente);
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

         public Consultorio consultorioElegido(int matricula){
        for (Consultorio c:consultorios.getElementos()){
            if (c.getProfesional() != null) {
                if (c.getProfesional().getMatricula() == matricula) {
                    return c;
                }
            }
        }
        return null;
         }

public void solicitarTurnoPaciente(Paciente paciente) {
    System.out.println("Paciente: " + paciente.getNombre() + " " + paciente.getApellido());

    Profesional profesionalElegido = elegirProfesional();
    if (profesionalElegido == null) return;

    Consultorio consultorio = consultorioElegido(profesionalElegido.getMatricula());
    if (consultorio == null) {
        System.out.println(" El profesional elegido no tiene un consultorio asignado.");
        return;
    }

    LocalDateTime inicioTurno = seleccionarHorario();
    if (inicioTurno == null) return;

    Turno nuevoTurno = new Turno(paciente, profesionalElegido, consultorio, inicioTurno, EstadoTurno.Pendiente);
    gestorTurnos.agregarTurno(nuevoTurno);

    System.out.println("\n su turno se agendo con existo, datos del turno:");
    System.out.println(nuevoTurno);
    System.out.println("Estado: " + EstadoTurno.Pendiente);

    }

    public Especialidad seleccionarEspecialidad() {
        Especialidad espElegida = null;
        int opcion;
        Especialidad[] especialidades = Especialidad.values();

        while (espElegida == null) {
            System.out.println("\n--- 1. Seleccione la Especialidad ---");

            for (int i = 0; i < especialidades.length; i++) {

                System.out.println((i + 1) + ". " + especialidades[i]);
            }

            System.out.print("Ingrese el número de la especialidad (0 para cancelar): ");


                opcion = scanner.nextInt();
                scanner.nextLine();

                if (opcion == 0) {
                    return null;
                }

                if (opcion > 0 && opcion <= especialidades.length) {

                    espElegida = especialidades[opcion - 1];
                } else {
                    System.out.println(" Opción inválida. Ingrese un número de la lista.");
                }

            }

        return espElegida;
    }


    public Profesional elegirProfesional() {
        Profesional profesionalElegido = null;
        Especialidad espElegida = seleccionarEspecialidad();
        int opcionProfesional;
        int contadorProfesionales = 0;
        for (int i = 0; i < listaProfesionales.getElementos().size(); i++) {
            if (listaProfesionales.getElementos().get(i).getEspecialidad() == espElegida) {
                System.out.println("Profesional N: " + i + listaProfesionales.getElementos().get(i).toString());
                contadorProfesionales = i;

            }

        }
        System.out.println("ingrese el numero de matricula del profesional a elegir (0 para cancelar)");
        opcionProfesional = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < listaProfesionales.getElementos().size(); i++) {
            if (listaProfesionales.getElementos().get(i).getEspecialidad() == espElegida) {
                if (opcionProfesional == listaProfesionales.getElementos().get(i).getMatricula()) {
                    return listaProfesionales.getElementos().get(i);
                }
            } else {
                System.out.println("Opcion invalida");
            }
        }
        return null;
    }
    public LocalDateTime seleccionarHorario() {
        String fechaStr;
        String horaStr;
        LocalDateTime fechaHora = null;

        while (fechaHora == null) {
            System.out.println("\n--- 3. Ingrese Fecha y Hora para el Turno ---");
            System.out.print("Ingrese la FECHA para el turno (ej: AAAA-MM-DD): ");
            fechaStr = scanner.nextLine();
            System.out.print("Ingrese la HORA para el turno (ej: HH:MM): ");
            horaStr = scanner.nextLine();

                fechaHora = LocalDateTime.parse(fechaStr + "T" + horaStr + ":00");


        }
        return fechaHora;
    }

    public void cancelarTurnoPaciente(Paciente paciente){
        int turnoElegido = 0;
        gestorTurnos.verTurnosXpaciente(paciente.getDni());
        try {
            System.out.println("Ingrese el id del turno a cancelar");
            turnoElegido = scanner.nextInt();
            if (gestorTurnos.buscarTurnoXid(turnoElegido).getPaciente().getDni() != paciente.getDni()) {
                throw new imposibleCancelarTurnoEx("No se puede cancelar ese turno porque corresponde a otro paciente");
            }else {
                gestorTurnos.cancelarTurno(turnoElegido);
            }
        }catch (imposibleCancelarTurnoEx e){
            System.out.println("No se puede cancelar ese turno porque corresponde a otro paciente, ingrese otro id:");
            turnoElegido = scanner.nextInt();
            }
    }

    public void agregarProfesional(Profesional neuvo){
        listaProfesionales.agregar(neuvo);
    }
    public void agregarPaciente(Paciente neuvo){
        listaPacientes.agregar(neuvo);
    }
    public void agregarConsultorioXparametro(Consultorio neuvo){
        consultorios.agregar(neuvo);
    }

    }
    //mostrarDisponibilidad()


