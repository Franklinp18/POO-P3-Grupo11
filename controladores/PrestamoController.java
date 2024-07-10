package controladores;

import modelo.entidades.Persona;
import modelo.Prestamo;
import modelo.TransaccionFinanciera;

import java.util.List;
import java.util.Scanner;

public class PrestamoController {

    public static void administrarPrestamos() {
        int opcion = 0;
        Scanner sc = new Scanner(System.in);

        while (opcion != 2) {
            mostrarPrestamos();
            System.out.println("Opciones:");
            System.out.println("1 Registrar Préstamo");
            System.out.println("2 Regresar al menú principal");
            System.out.print("Ingrese una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarPrestamo();
                    break;
                case 2:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Número no válido");
                    break;
            }
        }
    }

    private static void mostrarPrestamos() {
        System.out.printf("%-15s %-15s %-15s %-30s %-20s %-15s%n", "Código", "Deudor", "Valor", "Descripción", "Fecha Préstamo", "Cuota");
        for (TransaccionFinanciera transaccion : TransaccionFinancieraController.getListaTransaccionFinanciera()) {
            if (transaccion instanceof Prestamo) {
                Prestamo prestamo = (Prestamo) transaccion;
                System.out.println(prestamo.mostrarInformacion());
            }
        }
        System.out.println("////////////////////////////////////////////");
    }

    private static int leerEntero() {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            sc.next(); // Limpiar la entrada inválida
        }
        int numero = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer
        return numero;
    }

    private static void registrarPrestamo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el número de cédula: ");
        String nCedula = sc.nextLine();
        Persona deudor = buscarPersonaPorCedula(nCedula);

        if (deudor == null) {
            System.out.println("Persona no registrada. Ingrese los datos de la nueva persona.");
            System.out.print("Cédula: ");
            String cedula = sc.nextLine();
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Teléfono: ");
            String telefono = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            deudor = new Persona(nombre, email, cedula, "FechaActual", telefono); // Aquí deberías usar la fecha actual
            PersonaController.agregarPersona(deudor);
        } else {
            System.out.println("Persona encontrada: " + deudor.getNombre());
        }

        System.out.print("Cantidad: ");
        double cantidad = sc.nextDouble();
        sc.nextLine();
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();
        System.out.print("Fecha de préstamo (dd/mm/aaaa): ");
        String fechaPrestamo = sc.nextLine();
        System.out.print("Cuota: ");
        int cuota = sc.nextInt();
        sc.nextLine();
        System.out.print("Fecha de inicio de pago (dd/mm/aaaa): ");
        String fechaInicio = sc.nextLine();
        System.out.print("Fecha de finalización de pago (dd/mm/aaaa): ");
        String fechaFin = sc.nextLine();

        Prestamo prestamo = new Prestamo(descripcion, cantidad, fechaInicio, fechaFin, deudor, fechaPrestamo, cuota);

        while (!TransaccionFinanciera.esFechaMayor(fechaFin, fechaInicio)) {
            System.out.println("La fecha de fin no es mayor que la fecha de inicio.");
            System.out.print("Ingrese una nueva fecha final: ");
            fechaFin = sc.nextLine();
            prestamo.setFechaFin(fechaFin);
        }

        TransaccionFinancieraController.agregarTransaccion(prestamo);
        System.out.println("Préstamo registrado correctamente.");
    }

    private static Persona buscarPersonaPorCedula(String cedula) {
        List<Persona> listaPersonas = PersonaController.getListaPersonas();
        for (Persona persona : listaPersonas) {
            if (persona.getNumeroIdentidad().equals(cedula)) {
                return persona;
            }
        }
        return null;
    }
}
