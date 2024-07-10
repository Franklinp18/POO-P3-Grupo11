package controladores;

import modelo.entidades.*;
import modelo.Pago;
import modelo.TransaccionFinanciera;

import java.util.List;
import java.util.Scanner;

public class PagoController {

    public static void administrarPagos() {
        int opcion = 0;
        Scanner sc = new Scanner(System.in);

        while (opcion != 2) {
            mostrarPagos();
            System.out.println("Opciones:");
            System.out.println("1 Registrar Pago");
            System.out.println("2 Regresar al menú principal");
            System.out.print("Ingrese una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarPago();
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

    private static void mostrarPagos() {
        System.out.printf("%-15s %-20s %-15s %-30s %-20s %-15s%n", "Código", "Acreedor", "Valor", "Descripción", "Fecha de préstamo", "Cuota");
        for (TransaccionFinanciera transaccion : TransaccionFinancieraController.getListaTransaccionFinanciera()) {
            if (transaccion instanceof Pago) {
                Pago pago = (Pago) transaccion;
                System.out.println(pago.mostrarInformacion());
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

    private static void registrarPago() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el número de identidad: ");
        String nIdentidad = sc.nextLine();
        Entidad acredor = buscarEntidadPorIdentidad(nIdentidad);

        if (acredor == null) {
            System.out.println("Entidad no registrada. Ingrese los datos de la nueva entidad.");
            System.out.print("Identificación: ");
            String id = sc.nextLine();
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Es Persona o Banco (p/b): ");
            String tipo = sc.nextLine();
            if (tipo.equals("p")) {
                System.out.print("Teléfono: ");
                String telefono = sc.nextLine();
                acredor = new Persona(nombre, email, id, "FechaActual", telefono); // Aquí deberías usar la fecha actual
                PersonaController.agregarPersona((Persona) acredor);
            } else if (tipo.equals("b")) {
                System.out.print("Nombre del oficial de crédito: ");
                String nombreOficialCredito = sc.nextLine();
                System.out.print("Teléfono del oficial de crédito: ");
                String telefonoOficialCredito = sc.nextLine();
                acredor = new Banco(nombre, email, id, "FechaActual", nombreOficialCredito, telefonoOficialCredito); // Aquí deberías usar la fecha actual
                BancoController.agregarBanco((Banco) acredor);
            }
        } else {
            System.out.println("Entidad encontrada: " + acredor.getNombre());
        }

        System.out.print("Cantidad: ");
        double cantidad = sc.nextDouble();
        sc.nextLine();
        System.out.print("Interés: ");
        double interes = sc.nextDouble();
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

        Pago pago = new Pago(descripcion, cantidad, fechaInicio, fechaFin, acredor, interes, fechaPrestamo, cuota);

        while (!TransaccionFinanciera.esFechaMayor(fechaFin, fechaInicio)) {
            System.out.println("La fecha de fin no es mayor que la fecha de inicio.");
            System.out.print("Ingrese una nueva fecha final: ");
            fechaFin = sc.nextLine();
            pago.setFechaFin(fechaFin);
        }

        TransaccionFinancieraController.agregarTransaccion(pago);
        System.out.println("Pago registrado correctamente.");
    }

    private static Entidad buscarEntidadPorIdentidad(String identidad) {
        List<Persona> listaPersonas = PersonaController.getListaPersonas();
        for (Persona persona : listaPersonas) {
            if (persona.getNumeroIdentidad().equals(identidad)) {
                return persona;
            }
        }

        List<Banco> listaBancos = BancoController.getListaBancos();
        for (Banco banco : listaBancos) {
            if (banco.getNumeroIdentidad().equals(identidad)) {
                return banco;
            }
        }

        return null;
    }
}
