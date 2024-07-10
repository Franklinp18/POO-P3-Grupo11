package controladores;

import modelo.Categoria;
import modelo.Gasto;
import modelo.Ingreso;
import modelo.TransaccionFinanciera;
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
        Ingreso ingreso1 = new Ingreso("sueldo", 450.0, "01/07/2024", "00/00/0000", categoria3, "Mes");
        Ingreso ingreso2 = new Ingreso("Premio obtenido en concurso", 100.0, "30/06/2025", "00/00/0000", categoria4, "Mes");

        listaTransaccionFinanciera.add(gasto1);
        listaTransaccionFinanciera.add(gasto2);
        listaTransaccionFinanciera.add(ingreso1);
        listaTransaccionFinanciera.add(ingreso2);
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



