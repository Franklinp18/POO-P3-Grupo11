package controladores;

import modelo.Gasto;
import modelo.TransaccionFinanciera;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
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

        LocalDate mesActualInicio = fechaActual.withDayOfMonth(1);
        LocalDate mesAnterior1Inicio = mesActualInicio.minusMonths(1);
        LocalDate mesAnterior2Inicio = mesActualInicio.minusMonths(2);
        LocalDate mesAnterior3Inicio = mesActualInicio.minusMonths(3);
        LocalDate mesSiguienteInicio = mesActualInicio.plusMonths(1);

        String nombreMesActual = mesActualInicio.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
        String nombreMesAnterior1 = mesAnterior1Inicio.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
        String nombreMesAnterior2 = mesAnterior2Inicio.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
        String nombreMesSiguiente = mesSiguienteInicio.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());

        Map<String, List<Double>> categoriasGastos = new HashMap<>();
        Map<String, Double> gastosMes1 = new HashMap<>();
        Map<String, Double> gastosMes2 = new HashMap<>();
        Map<String, Double> gastosMes3 = new HashMap<>();

        for (TransaccionFinanciera transaccion : TransaccionFinancieraController.getListaTransaccionFinanciera()) {
            if (transaccion instanceof Gasto gasto) {
                try {
                    LocalDate fechaGasto = LocalDate.parse(gasto.getFechaInicio(), formatter);
                    LocalDate fechaFinGasto = gasto.getFechaFin().isEmpty() ? mesSiguienteInicio : LocalDate.parse(gasto.getFechaFin(), formatter);
                    System.out.println("Procesando gasto: " + gasto.getValor() + " Fecha: " + fechaGasto + " Fecha Fin: " + fechaFinGasto);  // Depuración
                    while (fechaGasto.isBefore(mesSiguienteInicio) && (fechaFinGasto.isAfter(fechaGasto) || fechaFinGasto.isEqual(fechaGasto))) {
                        if ((fechaGasto.isEqual(mesAnterior3Inicio) || fechaGasto.isAfter(mesAnterior3Inicio)) && fechaGasto.isBefore(mesSiguienteInicio)) {
                            String categoria = gasto.getCategoria().getNombre();
                            categoriasGastos.putIfAbsent(categoria, new ArrayList<>());
                            categoriasGastos.get(categoria).add(gasto.getValor());

                            if (fechaGasto.isEqual(mesAnterior2Inicio) || (fechaGasto.isAfter(mesAnterior2Inicio) && fechaGasto.isBefore(mesAnterior1Inicio))) {
                                gastosMes1.put(categoria, gastosMes1.getOrDefault(categoria, 0.0) + gasto.getValor());
                            } else if (fechaGasto.isEqual(mesAnterior1Inicio) || (fechaGasto.isAfter(mesAnterior1Inicio) && fechaGasto.isBefore(mesActualInicio))) {
                                gastosMes2.put(categoria, gastosMes2.getOrDefault(categoria, 0.0) + gasto.getValor());
                            } else if (fechaGasto.isEqual(mesActualInicio) || fechaGasto.isAfter(mesActualInicio)) {
                                gastosMes3.put(categoria, gastosMes3.getOrDefault(categoria, 0.0) + gasto.getValor());
                            }
                        }
                        // Incrementar la fecha para el próximo mes si es recurrente
                        fechaGasto = fechaGasto.plusMonths(1);
                    }
                } catch (Exception e) {
                    System.out.println("Error parsing date: " + gasto.getFechaInicio());
                }
            }
        }

        System.out.printf("%-20s %-10s %-10s %-10s %-10s %n", "Categoría", nombreMesAnterior2, nombreMesAnterior1, nombreMesActual, "Proyección " + nombreMesSiguiente);

        for (String categoria : categoriasGastos.keySet()) {
            double mes1 = gastosMes1.getOrDefault(categoria, 0.0);
            double mes2 = gastosMes2.getOrDefault(categoria, 0.0);
            double mes3 = gastosMes3.getOrDefault(categoria, 0.0);
            double promedio = (mes1 + mes2 + mes3) / 3;

            System.out.printf("%-20s %-10.2f %-10.2f %-10.2f %-10.2f %n", categoria, mes1, mes2, mes3, promedio);
        }


        // Para depuración
        System.out.println("Detalles de los gastos capturados:");
        for (Map.Entry<String, List<Double>> entry : categoriasGastos.entrySet()) {
            System.out.println("Categoría: " + entry.getKey() + ", Valores: " + entry.getValue());
        }
    }
    }













