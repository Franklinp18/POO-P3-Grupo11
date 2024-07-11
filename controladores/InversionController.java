package controladores;

import modelo.*;
import modelo.entidades.Banco;
import modelo.entidades.Entidad;
import modelo.modelo2.ProcesoBancario;
import modelo.modelo2.Inversion;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class InversionController {

    /**
     * Administra las opciones relacionadas con las inversiones, permitiendo registrar y eliminar inversiones, y regresar al menú principal.
     */
    public static void administrarInversiones() {
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        List<ProcesoBancario> listaProcesosBancarios = ProcesoBancarioController.getListaProcesosBancarios();
        List<Entidad> listaEntidades = ProcesoBancarioController.getListaEntidades();

        while (opcion != 3) {
            mostrarInversiones(listaProcesosBancarios);
            System.out.println("Opciones:");
            System.out.println("1. Registrar inversión");
            System.out.println("2. Eliminar inversión");
            System.out.println("3. Regresar al menú principal");
            System.out.print("Ingrese una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    registrarInversion(sc, listaProcesosBancarios, listaEntidades);
                    break;
                case 2:
                    eliminarInversion(sc, listaProcesosBancarios);
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

    /**
     * Muestra la lista de inversiones registradas.
     *
     * @param listaProcesosBancarios la lista de procesos bancarios, incluyendo inversiones.
     */
    private static void mostrarInversiones(List<ProcesoBancario> listaProcesosBancarios) {
        System.out.printf("%-10s %-30s %-15s %-15s %-15s %-15s%n", "Código", "Entidad Bancaria", "Fecha apertura", "Cantidad", "Interés mensual", "Fecha finalización");
        for (ProcesoBancario procesoBancario : listaProcesosBancarios) {
            if (procesoBancario instanceof Inversion) {
                Inversion inversion = (Inversion) procesoBancario;
                System.out.println(inversion.mostrarInformacion());
            }
        }
        System.out.println("////////////////////////////////////////////");
    }

    /**
     * Permite registrar una nueva inversión, solicitando los datos necesarios al usuario.
     *
     * @param sc el objeto Scanner para leer la entrada del usuario.
     * @param listaProcesosBancarios la lista de procesos bancarios, incluyendo inversiones.
     * @param listaEntidades la lista de entidades bancarias.
     */
    private static void registrarInversion(Scanner sc, List<ProcesoBancario> listaProcesosBancarios, List<Entidad> listaEntidades) {
        System.out.print("Ingrese el nombre o ruc de la entidad bancaria: ");
        String identificador = sc.nextLine();
        Banco bancoEncontrado = null;

        for (Entidad entidad : listaEntidades) {
            if (entidad instanceof Banco) {
                Banco banco = (Banco) entidad;
                if (banco.getNombre().equals(identificador) || banco.getNumeroIdentidad().equals(identificador)) {
                    bancoEncontrado = banco;
                    break;
                }
            }
        }

        if (bancoEncontrado != null) {
            System.out.print("Ingrese la fecha de apertura: ");
            String fechaApertura = sc.nextLine();
            System.out.print("Ingrese la cantidad a invertir: ");
            double cantidad = sc.nextDouble();
            sc.nextLine();
            System.out.print("Ingrese el interés aproximado ganado en el mes: ");
            double interes = sc.nextDouble();
            sc.nextLine();
            System.out.print("Ingrese la fecha de finalización: ");
            String fechaCierre = sc.nextLine();

            Inversion inversion = new Inversion(bancoEncontrado, fechaApertura, interes, fechaCierre, cantidad);
            listaProcesosBancarios.add(inversion);
            System.out.println("Inversión registrada correctamente.");
        } else {
            System.out.println("Entidad bancaria no encontrada.");
        }
    }

    /**
     * Permite eliminar una inversión existente, solicitando el código de la inversión al usuario.
     *
     * @param sc el objeto Scanner para leer la entrada del usuario.
     * @param listaProcesosBancarios la lista de procesos bancarios, incluyendo inversiones.
     */
    private static void eliminarInversion(Scanner sc, List<ProcesoBancario> listaProcesosBancarios) {
        System.out.print("Ingrese el código de la inversión a eliminar: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        boolean eliminado = false;

        Iterator<ProcesoBancario> iterator = listaProcesosBancarios.iterator();
        while (iterator.hasNext()) {
            ProcesoBancario procesoBancario = iterator.next();
            if (procesoBancario instanceof Inversion) {
                Inversion inversion = (Inversion) procesoBancario;
                if (inversion.getCodigo() == codigo) {
                    System.out.print("¿Desea continuar? yes/no: ");
                    String confirmacion = sc.nextLine();
                    if (confirmacion.equalsIgnoreCase("yes")) {
                        iterator.remove();
                        System.out.println("La inversión ha sido eliminada.");
                        eliminado = true;
                    } else {
                        System.out.println("La inversión no ha sido eliminada.");
                    }
                    break;
                }
            }
        }

        if (!eliminado) {
            System.out.println("No se encontró una inversión con el código proporcionado.");
        }
    }
}
