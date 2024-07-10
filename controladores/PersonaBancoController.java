package controladores;

import modelo.Pago;
import modelo.Prestamo;
import modelo.TransaccionFinanciera;
import modelo.entidades.Banco;
import modelo.entidades.Entidad;
import modelo.entidades.Persona;
import modelo.enums.TipoEntidad;
import modelo.modelo2.Inversion;
import modelo.modelo2.ProcesoBancario;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PersonaBancoController {
    private static final List<Entidad> listaEntidades = ProcesoBancarioController.getListaEntidades();

    public static void administrarPersonasBancos() {
        int opcion = 0;

        while (opcion != 3) {
            mostrarPersonasBancos();
            System.out.println("Opciones:");
            System.out.println("1. Registrar Persona/Banco");
            System.out.println("2. Eliminar");
            System.out.println("3. Regresar al Menú principal");
            System.out.print("\nIngrese una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarPersonaBanco();
                    break;
                case 2:
                    eliminarPersonaBanco();
                    break;
                case 3:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Número no válido");
                    break;
            }
        }
    }

    private static void mostrarPersonasBancos() {
        System.out.printf("%-10s %-15s %-20s %-25s %-25s %-15s %n", "Código", "Fecha registro", "Identificación", "Nombre", "Email", "Otros datos");
        for (Entidad entidad : listaEntidades) {
            System.out.println(entidad.mostrarInformacion());
        }
    }

    private static int leerEntero() {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            sc.next();
        }
        int numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }

    private static void registrarPersonaBanco() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Registrar Persona o Banco ingrese: p/b: ");
        String ingreso = sc.nextLine();
        String cedula, nombre, telefono, email;

        if (ingreso.equals("p")) {
            System.out.print("Ingrese su cédula: ");
            cedula = sc.nextLine();
            System.out.print("Ingrese su nombre: ");
            nombre = sc.nextLine();
            System.out.print("Ingrese su teléfono: ");
            telefono = sc.nextLine();
            System.out.print("Ingrese su email: ");
            email = sc.nextLine();
            Persona persona = new Persona(nombre, email, cedula, fechaFormateada(), telefono);
            listaEntidades.add(persona);
        } else if (ingreso.equals("b")) {
            System.out.print("Ingrese el RUC del banco: ");
            cedula = sc.nextLine();
            System.out.print("Ingrese el nombre del banco: ");
            nombre = sc.nextLine();
            System.out.print("Ingrese el teléfono del banco: ");
            telefono = sc.nextLine();
            System.out.print("Ingrese el email del banco: ");
            email = sc.nextLine();
            System.out.print("Ingrese el nombre del oficial de crédito: ");
            String nombreOficialCredito = sc.nextLine();
            System.out.print("Ingrese el teléfono del oficial de crédito: ");
            String telefonoOficialCredito = sc.nextLine();
            Banco banco = new Banco(nombre, email, cedula, fechaFormateada(), nombreOficialCredito, telefonoOficialCredito);
            listaEntidades.add(banco);
        } else {
            System.out.println("Ingreso una opción no válida.");
        }
    }

    private static void eliminarPersonaBanco() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese un número de cédula/RUC: ");
        String cedula = sc.nextLine();
        boolean confirmacionGeneral = false;
        Iterator<Entidad> iterator = listaEntidades.iterator();

        while (iterator.hasNext()) {
            Entidad entidad = iterator.next();
            if (entidad.getNumeroIdentidad().equals(cedula)) {
                System.out.print("¿Desea eliminar el registro de " + entidad.getNombre() + "? yes/no: ");
                String confirmacion = sc.nextLine();
                if (confirmacion.equalsIgnoreCase("yes")) {
                    iterator.remove();
                    confirmacionGeneral = true;
                    System.out.println("Registro eliminado.");
                } else {
                    System.out.println("Registro no eliminado.");
                }
                break;
            }
        }




    }

    private static String fechaFormateada() {
        // Lógica para obtener la fecha actual formateada
        return "07/10/2024"; // Fecha actual
    }
}
