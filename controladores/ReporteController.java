package controladores;

import modelo.Gasto;
import modelo.TransaccionFinanciera;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ReporteController {

    public static void mostrarReportes() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 4) {
            System.out.println("Opciones de Reporte:");
            System.out.println("1. Reporte de Ingresos");
            System.out.println("2. Reporte de Gastos");
            System.out.println("3. Proyección de Gastos");
            System.out.println("4. Regresar al Menú Principal");
            System.out.print("Ingrese una opción: ");
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1:
                    mostrarReporteIngresos();
                    break;
                case 2:
                    mostrarReporteGastos();
                    break;
                case 3:
                    mostrarProyeccionGastos();
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

    private static int leerEntero(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            scanner.next();
        }
        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }

    private static void mostrarReporteIngresos() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fechaActual.format(formatter);

        System.out.println("9.1 Reporte de Ingresos");
        System.out.println("Seleccionar Período:");
        System.out.println("1. Mes actual");
        System.out.println("2. Año");
        System.out.println("Ingrese una opción:");

        Scanner sc = new Scanner(System.in);
        int opcion = leerEntero(sc);

        if (opcion == 1) {
            System.out.println("Mes actual: " + fechaActual.getMonth());
            // Lógica para mostrar ingresos del mes actual
        } else if (opcion == 2) {
            System.out.println("Año actual: " + fechaActual.getYear());
            // Lógica para mostrar ingresos del año actual
        } else {
            System.out.println("Opción no válida.");
        }
    }

    private static void mostrarReporteGastos() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fechaActual.format(formatter);

        System.out.println("9.2 Reporte de Gastos");
        System.out.println("Seleccionar Período:");
        System.out.println("1. Mes actual");
        System.out.println("2. Año");
        System.out.println("Ingrese una opción:");

        Scanner sc = new Scanner(System.in);
        int opcion = leerEntero(sc);

        if (opcion == 1) {
            System.out.println("Mes actual: " + fechaActual.getMonth());
            // Lógica para mostrar gastos del mes actual
        } else if (opcion == 2) {
            System.out.println("Año actual: " + fechaActual.getYear());
            // Lógica para mostrar gastos del año actual
        } else {
            System.out.println("Opción no válida.");
        }
    }

    public static void mostrarProyeccionGastos() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("10. Proyección de Gastos:");

        LocalDate mesActual = fechaActual.withDayOfMonth(1);
        LocalDate mesAnterior1 = mesActual.minusMonths(1);
        LocalDate mesAnterior2 = mesActual.minusMonths(2);
        LocalDate mesAnterior3 = mesActual.minusMonths(3);

        Map<String, List<Double>> categoriasGastos = new HashMap<>();

        for (TransaccionFinanciera transaccion : TransaccionFinancieraController.getListaTransaccionFinanciera()) {
            if (transaccion instanceof Gasto gasto) {
                LocalDate fechaGasto = LocalDate.parse(gasto.getFechaInicio(), formatter);
                if ((fechaGasto.isEqual(mesAnterior3) || fechaGasto.isAfter(mesAnterior3)) && fechaGasto.isBefore(mesActual)) {
                    categoriasGastos.putIfAbsent(gasto.getCategoria().getNombre(), new ArrayList<>());
                    categoriasGastos.get(gasto.getCategoria().getNombre()).add(gasto.getValor());
                }
            }
        }

        System.out.printf("%-20s %-10s %-10s %-10s %-10s %n", "Categoría", "Junio", "Julio", "Agosto", "Proyección Septiembre");

        for (Map.Entry<String, List<Double>> entry : categoriasGastos.entrySet()) {
            String categoria = entry.getKey();
            List<Double> valores = entry.getValue();
            double promedio = valores.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            double junio = valores.size() > 0 ? valores.get(0) : 0;
            double julio = valores.size() > 1 ? valores.get(1) : 0;
            double agosto = valores.size() > 2 ? valores.get(2) : 0;
            System.out.printf("%-20s %-10.2f %-10.2f %-10.2f %-10.2f %n", categoria, junio, julio, agosto, promedio);
        }
    }
}
