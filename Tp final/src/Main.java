import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

// 1. Inicializar el sistema
        Sistema sistema = new Sistema();

        Profesional p1 = new Profesional("Laura", "Gomez", 30111222, 1140001000, Especialidad.Cardiologia, 1001);
        Profesional p2 = new Profesional("Ricardo", "Perez", 31333444, 1140002000, Especialidad.Pediatria, 1002);
        Profesional p3 = new Profesional("Ana", "Lopez", 32555666, 1140003000,  Especialidad.Dermatologia,1003);
        Profesional p4 = new Profesional("Jorge", "Ruiz", 33777888, 1140004000, Especialidad.Traumatologia, 1004);
        sistema.agregarProfesional(p1);
        sistema.agregarProfesional(p2);
        sistema.agregarProfesional(p3);
        sistema.agregarProfesional(p4);



        DayOfWeek[] diasLaborables = {
                DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY
        };
        LocalTime horaApertura = LocalTime.of(8, 0);
        LocalTime horaCierre = LocalTime.of(20, 0);
        ArrayList<Horario> horariosConsultorio=new ArrayList<>();
        for (DayOfWeek dia : diasLaborables) {
            Horario horarioDia = new Horario(dia, horaApertura, horaCierre);
            horariosConsultorio.add(horarioDia);
        }
        Consultorio c1 = new Consultorio("Consultorio Cardiología", p1);
        c1.setHorarios(horariosConsultorio);
        sistema.agregarConsultorioXparametro(c1);

        Consultorio c2 = new Consultorio("Consultorio Pediatria", p2);
        c2.setHorarios(horariosConsultorio);
        sistema.agregarConsultorioXparametro(c2);

        Consultorio c3 = new Consultorio("Consultorio Dermatologia", p3);
        c3.setHorarios(horariosConsultorio);
        sistema.agregarConsultorioXparametro(c3);

        Consultorio c4 = new Consultorio("Consultorio Traumatologia", p4);
        c4.setHorarios(horariosConsultorio);
        sistema.agregarConsultorioXparametro(c4);


        Paciente pac1 = new Paciente("Mateo", "Asaro", 40123456, 1150001000, "OSDE");
        Paciente pac2 = new Paciente("Homero", "Capozzo", 41654321, 1150002000, "Swiss Medical");

        sistema.agregarPaciente(pac1);
        sistema.agregarPaciente(pac2);


        sistema.menuPrincipal();
    }
    }
