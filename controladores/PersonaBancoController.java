package controladores;

import modelo.Pago;
import modelo.Prestamo;
import modelo.TransaccionFinanciera;
import modelo.entidades.Banco;
import modelo.entidades.Entidad;
import modelo.entidades.Persona;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PersonaBancoController {
    private static final List<TransaccionFinanciera> listaTransaccionFinanciera = TransaccionFinancieraController.getListaTransaccionFinanciera();

    /**
     * Administra las opciones relacionadas con personas y bancos, permitiendo registrar y eliminar
     * personas/bancos y regresar al menú principal.
     */
    public static void administrarPersonasBancos() {
        int opcion = 0;
        Scanner sc = new Scanner(System.in);

        while (true) {
            mostrarPersonasBancos();
            System.out.println("Opciones:");
            System.out.println("1. Registrar Persona/Banco");
            System.out.println("2. Eliminar");
            System.out.println("3. Regresar al Menú principal");
            System.out.print("\nIngrese una opción: ");
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1:
                    registrarPersonaBanco(sc);
                    break;
                case 2:
                    eliminarPersonaBanco(sc);
                    break;
                case 3:
                    System.out.println("Regresando al menú principal...");
                    return;
                default:
                    System.out.println("Número no válido");
                    break;
            }
        }
    }

    /**
     * Muestra la lista de personas y bancos registrados.
     */
    private static void mostrarPersonasBancos() {
        System.out.printf("%-10s %-15s %-20s %-25s %-25s %-15s %n", "Código", "Fecha registro", "Identificación", "Nombre", "Email", "Otros datos");

        List<Entidad> listaEntidades = ProcesoBancarioController.getListaEntidades();

        for (Entidad entidad : listaEntidades) {
            String otrosDatos = "";
            if (entidad instanceof Persona) {
                otrosDatos = "Teléfono: " + ((Persona) entidad).getTelefono();
            } else if (entidad instanceof Banco) {
                otrosDatos = "Oficial de Crédito: " + ((Banco) entidad).getNombreOficialCredito() + " Teléfono Oficial: " + ((Banco) entidad).getTelefonoOficialCredito();
            }
            System.out.printf("%-10s %-15s %-20s %-25s %-25s %-15s %n",
                    entidad.getCodigo(),
                    entidad.getFechaRegistro() != null ? entidad.getFechaRegistro() : "null",
                    entidad.getNumeroIdentidad(),
                    entidad.getNombre(),
                    entidad.getEmail(),
                    otrosDatos);
        }
    }

    /**
     * Lee un número entero desde el teclado, asegurándose de que la entrada sea válida.
     *
     * @param sc el objeto Scanner para leer la entrada del usuario.
     * @return el número entero leído.
     */
    private static int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            sc.next();
        }
        int numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }

    /**
     * Permite registrar una nueva persona o banco, solicitando los datos necesarios al usuario.
     *
     * @param sc el objeto Scanner para leer la entrada del usuario.
     */
    private static void registrarPersonaBanco(Scanner sc) {
        System.out.print("Registrar Persona o Banco ingrese: p/b: ");
        String ingreso = sc.nextLine();
        String cedula, nombre, telefono, email;

        if (ingreso.equalsIgnoreCase("p")) {
            System.out.print("Ingrese su cédula: ");
            cedula = sc.nextLine();
            System.out.print("Ingrese su nombre: ");
            nombre = sc.nextLine();
            System.out.print("Ingrese su teléfono: ");
            telefono = sc.nextLine();
            System.out.print("Ingrese su email: ");
            email = sc.nextLine();
            String fechaRegistro = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Persona persona = new Persona(nombre, email, cedula, fechaRegistro, telefono);
            ProcesoBancarioController.agregarEntidad(persona);
            System.out.println("Persona registrada correctamente.");
        } else if (ingreso.equalsIgnoreCase("b")) {
            System.out.print("Ingrese el RUC del banco: ");
            cedula = sc.nextLine();
            System.out.print("Ingrese el nombre del banco: ");
            nombre = sc.nextLine();
            System.out.print("Ingrese el email del banco: ");
            email = sc.nextLine();
            System.out.print("Ingrese el nombre del oficial de crédito: ");
            String nombreOficialCredito = sc.nextLine();
            System.out.print("Ingrese el teléfono del oficial de crédito: ");
            String telefonoOficialCredito = sc.nextLine();
            String fechaRegistro = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Banco banco = new Banco(nombre, email, cedula, fechaRegistro, nombreOficialCredito, telefonoOficialCredito);
            ProcesoBancarioController.agregarEntidad(banco);
            System.out.println("Banco registrado correctamente.");
        } else {
            System.out.println("Ingreso una opción no válida.");
        }
    }

    /**
     * Elimina una persona o banco de la lista de entidades, y también elimina sus transacciones asociadas.
     *
     * @param sc el objeto Scanner para leer la entrada del usuario.
     */
    private static void eliminarPersonaBanco(Scanner sc) {
        System.out.print("Ingrese un número de cédula/RUC: ");
        String cedula = sc.nextLine();
        boolean confirmacionGeneral = false;

        // Eliminar de la lista de entidades
        List<Entidad> listaEntidades = ProcesoBancarioController.getListaEntidades();
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

        if (confirmacionGeneral) {
            eliminarTransaccionesAsociadas(cedula);
        } else {
            System.out.println("No se encontró una entidad con el número de cédula/RUC proporcionado.");
        }
    }

    /**
     * Elimina las transacciones financieras asociadas a una persona o banco.
     *
     * @param cedula el número de cédula/RUC de la persona o banco cuyas transacciones se desean eliminar.
     */
    private static void eliminarTransaccionesAsociadas(String cedula) {
        Iterator<TransaccionFinanciera> iterator = listaTransaccionFinanciera.iterator();

        while (iterator.hasNext()) {
            TransaccionFinanciera transaccion = iterator.next();
            if (transaccion instanceof Prestamo) {
                Prestamo prestamo = (Prestamo) transaccion;
                if (prestamo.getDeudor().getNumeroIdentidad().equals(cedula)) {
                    iterator.remove();
                }
            } else if (transaccion instanceof Pago) {
                Pago pago = (Pago) transaccion;
                if (pago.getAcredor().getNumeroIdentidad().equals(cedula)) {
                    iterator.remove();
                }
            }
        }
    }
}
