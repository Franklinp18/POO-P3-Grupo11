package controladores;

import modelo.entidades.Banco;
import modelo.entidades.Entidad;
import modelo.modelo2.CuentaBancaria;
import modelo.enums.TipoCuenta;
import modelo.TransaccionFinanciera;
import modelo.modelo2.ProcesoBancario;


import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class CuentaBancariaController {

    public static void administrarCuentasBancarias() {
        int opcion = 0;
        Scanner sc = new Scanner(System.in);

        while (opcion != 4) {
            mostrarCuentasBancarias();
            System.out.println("Opciones:");
            System.out.println("1. Registrar cuenta");
            System.out.println("2. Eliminar cuenta");
            System.out.println("3. Cerrar cuenta");
            System.out.println("4. Regresar al Menu principal");
            System.out.print("\nIngrese una opcion: ");
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1:
                    registrarCuenta(sc);
                    break;
                case 2:
                    eliminarCuenta(sc);
                    break;
                case 3:
                    cerrarCuenta(sc);
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

    private static void mostrarCuentasBancarias() {
        System.out.printf("%-10s %-30s %-15s %-15s %-15s %n", "Codigo", "Entidad Bancaria", "Tipo", "Numero", "Saldo");
        List<ProcesoBancario> listaProcesosBancarios = ProcesoBancarioController.getListaProcesosBancarios();
        for (ProcesoBancario procesoBancario : listaProcesosBancarios) {
            if (procesoBancario instanceof CuentaBancaria cuentaBancaria) {
                System.out.println(cuentaBancaria.mostrarInformacion());
            }
        }
    }

    private static int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            sc.next();
        }
        int numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }

    private static void registrarCuenta(Scanner sc) {
        System.out.print("Ingrese el nombre o ruc de la entidad bancaria: ");
        String identificador = sc.nextLine();
        Banco bancoEncontrado = buscarBanco(identificador);

        if (bancoEncontrado != null) {
            System.out.print("Ingrese el numero del banco: ");
            String numero = sc.nextLine();
            System.out.print("Ingrese el tipo de cuenta (Ahorro/Corriente): ");
            String tipo = sc.nextLine();
            TipoCuenta tipoCuenta = null;
            try {
                tipoCuenta = TipoCuenta.valueOf(tipo.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de cuenta ingresado inválido.");
                return;
            }
            System.out.print("Ingrese la fecha de apertura: ");
            String fechaApertura = sc.nextLine();
            System.out.print("Ingrese el saldo: ");
            double saldo = sc.nextDouble();
            sc.nextLine();
            System.out.print("Ingrese el interes aproximado ganado en el mes: ");
            double interes = sc.nextDouble();
            sc.nextLine();
            System.out.print("Ingrese la fecha del cierre de la cuenta: ");
            String fechaCierre = sc.nextLine();

            CuentaBancaria cuentaBancaria = new CuentaBancaria(bancoEncontrado, fechaApertura, interes, fechaCierre, tipoCuenta, numero, saldo);
            ProcesoBancarioController.agregarProcesoBancario(cuentaBancaria);
            System.out.println("Cuenta registrada correctamente.");
        } else {
            System.out.println("Entidad bancaria no encontrada.");
        }
    }

    private static void eliminarCuenta(Scanner sc) {
        System.out.print("Ingrese el código de la cuenta: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        List<ProcesoBancario> listaProcesosBancarios = ProcesoBancarioController.getListaProcesosBancarios();
        Iterator<ProcesoBancario> iterator = listaProcesosBancarios.iterator();
        boolean cuentaEncontrada = false;

        while (iterator.hasNext()) {
            ProcesoBancario procesoBancario = iterator.next();
            if (procesoBancario instanceof CuentaBancaria cuentaBancaria) {
                if (cuentaBancaria.getCodigo() == codigo) {
                    cuentaEncontrada = true;
                    System.out.print("¿Desea continuar? yes/no: ");
                    String confirmacion = sc.nextLine();
                    if (confirmacion.equalsIgnoreCase("yes")) {
                        iterator.remove();
                        System.out.println("La cuenta ha sido eliminada.");
                    } else if (confirmacion.equalsIgnoreCase("no")) {
                        System.out.println("Su cuenta no ha sido eliminada.");
                    } else {
                        System.out.println("Ingresó una opción no válida.");
                    }
                    break;
                }
            }
        }

        if (!cuentaEncontrada) {
            System.out.println("No se encontró una cuenta con el código proporcionado.");
        }
    }

    private static void cerrarCuenta(Scanner sc) {
        System.out.print("Ingrese el código de la cuenta que desea finalizar: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        List<ProcesoBancario> listaProcesosBancarios = ProcesoBancarioController.getListaProcesosBancarios();
        Iterator<ProcesoBancario> iterator = listaProcesosBancarios.iterator();

        while (iterator.hasNext()) {
            ProcesoBancario procesoBancario = iterator.next();
            if (procesoBancario instanceof CuentaBancaria cuentaBancaria) {
                if (cuentaBancaria.getCodigo() == codigo) {
                    System.out.print("Ingrese la fecha de cierre: ");
                    String fechaCierre = sc.nextLine();
                    if (TransaccionFinanciera.esFechaMayor(fechaCierre, cuentaBancaria.getFechaApertura())) {
                        cuentaBancaria.setFechaCierre(fechaCierre);
                        System.out.println("Fecha de cierre de la cuenta actualizada correctamente.");
                    } else {
                        System.out.println("La fecha de cierre no es mayor a la fecha de apertura.");
                    }
                }
            }
        }
    }

    private static Banco buscarBanco(String identificador) {
        List<Entidad> listaEntidades = ProcesoBancarioController.getListaEntidades();
        for (Entidad entidad : listaEntidades) {
            if (entidad instanceof Banco banco) {
                if (banco.getNombre().equals(identificador) || banco.getNumeroIdentidad().equals(identificador)) {
                    return banco;
                }
            }
        }
        return null;
    }
}
