package controladores;

import modelo.Categoria;
import modelo.Ingreso;
import modelo.TransaccionFinanciera;

import java.util.List;
import java.util.Scanner;

public class IngresoController {

    public static void administrarIngresos() {
        int opcion = 0;

        while (opcion != 4) {
            mostrarIngresos();
            System.out.println("Opciones:");
            System.out.println("1 Registrar Ingreso");
            System.out.println("2 Eliminar Ingreso");
            System.out.println("3 Finalizar Ingreso");
            System.out.println("4 Regresar al menú principal");
            System.out.print("Ingrese una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarIngreso();
                    break;
                case 2:
                    eliminarIngreso();
                    break;
                case 3:
                    finalizarIngreso();
                    break;
                case 4:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Número no válido");
                    break;
            }
        }
    }

    private static void mostrarIngresos() {
        System.out.printf("%-10s %-30s %-15s %-15s %-15s %-15s %-15s%n", "Código", "Descripción", "Valor", "Fecha inicio", "Fecha fin", "Categoría", "Repetición");
        for (TransaccionFinanciera transaccion : TransaccionFinancieraController.getListaTransaccionFinanciera()) {
            if (transaccion instanceof Ingreso) {
                Ingreso ingreso = (Ingreso) transaccion;
                System.out.println(ingreso.mostrarInformacion());
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

    private static void registrarIngreso() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese una categoría ya registrada: ");
        String categoriaNombre = sc.nextLine();
        Categoria categoriaSeleccionada = null;

        List<Categoria> listaCategorias = CategoriaController.getListaCategorias();

        for (Categoria cat : listaCategorias) {
            if (cat.getNombre().equals(categoriaNombre)) {
                categoriaSeleccionada = cat;
                break;
            }
        }

        if (categoriaSeleccionada != null) {
            System.out.print("Ingrese un valor: ");
            double valor = sc.nextDouble();
            sc.nextLine();
            System.out.print("Ingrese una descripción: ");
            String descripcion = sc.nextLine();
            System.out.print("Ingrese la fecha de inicio del ingreso: ");
            String fechaInicio = sc.nextLine();
            System.out.print("Ingrese la repetición del ingreso: ");
            String repeticion = sc.nextLine();
            System.out.print("Ingrese la fecha de fin del ingreso: ");
            String fechaFin = sc.nextLine();

            Ingreso ingreso = new Ingreso(descripcion, valor, fechaInicio, fechaFin, categoriaSeleccionada, repeticion);
            TransaccionFinancieraController.agregarTransaccion(ingreso);

            System.out.println("Ingreso registrado correctamente.");
        } else {
            System.out.println("Categoría no encontrada.");
        }
    }

    private static void eliminarIngreso() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el código del ingreso a eliminar: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        boolean eliminado = TransaccionFinancieraController.eliminarTransaccion(codigo);

        if (eliminado) {
            System.out.println("Ingreso eliminado exitosamente.");
        } else {
            System.out.println("Ingreso no encontrado.");
        }
    }

    private static void finalizarIngreso() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el código del ingreso que desea finalizar: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese la fecha final a modificar en el formato dd/mm/aaaa: ");
        String fechaFin = sc.nextLine();

        TransaccionFinanciera transaccion = TransaccionFinancieraController.buscarTransaccion(codigo);
        if (transaccion instanceof Ingreso ingreso) {
            if (TransaccionFinanciera.esFechaMayor(fechaFin, ingreso.getFechaInicio())) {
                ingreso.setFechaFin(fechaFin);
                System.out.println("Fecha final del ingreso actualizada exitosamente.");
            } else {
                System.out.println("La fecha de fin no es mayor que la fecha de inicio.");
            }
        } else {
            System.out.println("No se ha encontrado un ingreso con el código proporcionado.");
        }
    }
}
