package vista;

import controladores.*;

import java.util.Scanner;

public class Vista {

    /**
     * Muestra el menú principal del sistema de control de finanzas personales y maneja la navegación del usuario a través de las diferentes opciones.
     */
    public static void mostrarMenuPrincipal() {
        int opcion = 0;
        Scanner scanner = new Scanner(System.in);

        while (opcion != 11) {
            System.out.println("////////////////////////////////////////////////////////////////");
            System.out.println("Bienvenido al Sistema de Control de Finanzas Personales");
            System.out.println("1. Administrar categorías");
            System.out.println("2. Administrar ingresos");
            System.out.println("3. Administrar gastos");
            System.out.println("4. Cuentas por cobrar");
            System.out.println("5. Administrar cuentas por pagar");
            System.out.println("6. Administrar cuentas bancarias");
            System.out.println("7. Administrar inversiones");
            System.out.println("8. Administrar personas y bancos");
            System.out.println("9. Reportes");
            System.out.println("10. Proyección de gastos");
            System.out.println("11. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    CategoriaController.administrarCategorias();
                    break;
                case 2:
                    IngresoController.administrarIngresos();
                    break;
                case 3:
                    GastoController.administrarGastos();
                    break;
                case 4:
                    PrestamoController.administrarPrestamos();
                    break;
                case 5:
                    PagoController.administrarPagos();
                    break;
                case 6:
                    CuentaBancariaController.administrarCuentasBancarias();
                    break;
                case 7:
                    InversionController.administrarInversiones();
                    break;
                case 8:
                    PersonaBancoController.administrarPersonasBancos();
                    break;
                case 9:
                    ReporteController.mostrarReportes();
                    break;
                case 10:
                    ReporteController.mostrarProyeccionGastos();
                    break;
                case 11:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Número no válido");
                    break;
            }
        }
    }

    /**
     * Lee un número entero desde el teclado, asegurándose de que la entrada sea válida.
     *
     * @param scanner el objeto Scanner para leer la entrada del usuario.
     * @return el número entero leído.
     */
    private static int leerEntero(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            scanner.next();
        }
        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }


    public static void main(String[] args) {
        mostrarMenuPrincipal();
    }
}
