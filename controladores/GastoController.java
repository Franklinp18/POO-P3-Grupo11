package controladores;

import modelo.Categoria;
import modelo.Gasto;
import modelo.TransaccionFinanciera;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GastoController {

    public static void administrarGastos() {
        int opcion = 0;

        while (opcion != 4) {
            mostrarGastos();
            System.out.println("Opciones:");
            System.out.println("1 Registrar Gasto");
            System.out.println("2 Eliminar Gasto");
            System.out.println("3 Finalizar Gasto");
            System.out.println("4 Regresar al menú principal");
            System.out.print("Ingrese una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarGasto();
                    break;
                case 2:
                    eliminarGasto();
                    break;
                case 3:
                    finalizarGasto();
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

    private static void mostrarGastos() {
        System.out.printf("%-10s %-30s %-15s %-15s %-15s %-15s %-15s%n", "Código", "Descripción", "Valor", "Fecha inicio", "Fecha fin", "Categoría", "Repetición");
        for (TransaccionFinanciera transaccion : TransaccionFinancieraController.getListaTransaccionFinanciera()) {
            if (transaccion instanceof Gasto) {
                Gasto gasto = (Gasto) transaccion;
                System.out.println(gasto.mostrarInformacion());
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

    private static void registrarGasto() {
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
            System.out.print("Ingrese la fecha de inicio del gasto: ");
            String fechaInicio = sc.nextLine();
            System.out.print("Ingrese la repetición del gasto: ");
            String repeticion = sc.nextLine();
            System.out.print("Ingrese la fecha de fin del gasto (opcional): ");
            String fechaFin = sc.nextLine();

            Gasto gasto = new Gasto(descripcion, valor, fechaInicio, fechaFin, categoriaSeleccionada, repeticion);
            TransaccionFinancieraController.agregarTransaccion(gasto);

            System.out.println("Gasto registrado correctamente.");
        } else {
            System.out.println("Categoría no encontrada.");
        }
    }

    private static void eliminarGasto() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el código del gasto a eliminar: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        boolean eliminado = false;

        Iterator<TransaccionFinanciera> iterator = TransaccionFinancieraController.getListaTransaccionFinanciera().iterator();
        while (iterator.hasNext()) {
            TransaccionFinanciera transaccion = iterator.next();
            if (transaccion instanceof Gasto) {
                Gasto gasto = (Gasto) transaccion;
                if (gasto.getCodigo() == codigo) {
                    System.out.println("Desea eliminar este gasto? (yes/no):");
                    String confirmacion = sc.nextLine();
                    if (confirmacion.equalsIgnoreCase("yes")) {
                        iterator.remove();
                        eliminado = true;
                        System.out.println("Gasto eliminado exitosamente.");
                    } else {
                        System.out.println("Gasto no eliminado.");
                    }
                    break;
                }
            }
        }

        if (!eliminado) {
            System.out.println("Gasto no encontrado.");
        }
    }

    private static void finalizarGasto() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el código del gasto que desea finalizar: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese la fecha final a modificar en el formato dd/mm/aaaa: ");
        String fechaFin = sc.nextLine();

        TransaccionFinanciera transaccion = TransaccionFinancieraController.buscarTransaccion(codigo);
        if (transaccion instanceof Gasto) {
            Gasto gasto = (Gasto) transaccion;
            if (TransaccionFinanciera.esFechaMayor(fechaFin, gasto.getFechaInicio())) {
                gasto.setFechaFin(fechaFin);
                System.out.println("Fecha final del gasto actualizada exitosamente.");
            } else {
                System.out.println("La fecha de fin no es mayor que la fecha de inicio.");
            }
        } else {
            System.out.println("No se ha encontrado un gasto con el código proporcionado.");
        }
    }
}
