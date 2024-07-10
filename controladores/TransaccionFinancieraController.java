package controladores;

import modelo.*;
import modelo.entidades.Banco;
import modelo.entidades.Persona;
import modelo.enums.TipoCategoria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TransaccionFinancieraController {
    private static ArrayList<TransaccionFinanciera> listaTransaccionFinanciera = new ArrayList<>();

    static {
        Categoria categoria1 = new Categoria(TipoCategoria.GASTO, "Alquiler");
        Categoria categoria2 = new Categoria(TipoCategoria.GASTO, "Transporte");
        Categoria categoria3 = new Categoria(TipoCategoria.INGRESO, "Salario");
        Categoria categoria4 = new Categoria(TipoCategoria.INGRESO, "Premios");

        Gasto gasto1 = new Gasto("Alquiler casa", 350.0, "01/01/2024", "01/01/2024", categoria1, "Mes");
        Gasto gasto2 = new Gasto("Expreso colegio", 1000.0, "01/04/2024", "30/01/2025", categoria2, "Mes");
        Ingreso ingreso1 = new Ingreso("Sueldo", 450.0, "01/07/2024", "00/00/0000", categoria3, "Mes");
        Ingreso ingreso2 = new Ingreso("Premio obtenido en concurso", 100.0, "30/06/2025", "00/00/0000", categoria4, "Mes");

        Persona persona1 = new Persona("Pedro Lopez", "pedrolopez@espol.edu.ec", "0952483931", "fechaFormateada", "0959458667");
        Banco banco1 = new Banco("Banco del Pacifico", "BPacifico@gmail.com", "09225465428001", "fechaFormateada", "Juan R", "0984562354");

        Prestamo prestamo1 = new Prestamo("Sueldo", 500.0, "00/00/0000", "00/00/0000", persona1, "02/02/2024", 50);
        Pago pago1 = new Pago("Para la compra del auto", 1500.0, "00/00/0000", "00/00/0000", banco1, 12.0, "02/02/2024", 600);

        listaTransaccionFinanciera.add(gasto1);
        listaTransaccionFinanciera.add(gasto2);
        listaTransaccionFinanciera.add(ingreso1);
        listaTransaccionFinanciera.add(ingreso2);
        listaTransaccionFinanciera.add(prestamo1);
        listaTransaccionFinanciera.add(pago1);
    }

    public static List<TransaccionFinanciera> getListaTransaccionFinanciera() {
        return listaTransaccionFinanciera;
    }

    public static void agregarTransaccion(TransaccionFinanciera transaccion) {
        listaTransaccionFinanciera.add(transaccion);
    }

    public static boolean eliminarTransaccion(int codigo) {
        Iterator<TransaccionFinanciera> iterator = listaTransaccionFinanciera.iterator();
        while (iterator.hasNext()) {
            TransaccionFinanciera transaccion = iterator.next();
            if (transaccion.getCodigo() == codigo) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public static TransaccionFinanciera buscarTransaccion(int codigo) {
        for (TransaccionFinanciera transaccion : listaTransaccionFinanciera) {
            if (transaccion.getCodigo() == codigo) {
                return transaccion;
            }
        }
        return null;
    }


}



