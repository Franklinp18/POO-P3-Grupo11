package controladores;

import modelo.entidades.Banco;
import modelo.entidades.Entidad;
import modelo.entidades.Persona;
import modelo.modelo2.CuentaBancaria;
import modelo.Pago;
import modelo.Prestamo;
import modelo.TransaccionFinanciera;
import modelo.enums.TipoCuenta;
import modelo.modelo2.Inversion;
import modelo.modelo2.ProcesoBancario;

import java.util.ArrayList;
import java.util.List;

public class ProcesoBancarioController {
    private static final List<ProcesoBancario> listaProcesosBancarios = new ArrayList<>();
    private static final List<Entidad> listaEntidades = new ArrayList<>();

    static {
        // Inicializar datos predeterminados

        Banco banco1 = new Banco("Banco del Pacifico", "BPacifico@gmail.com", "09225465428001", "10/07/2023", "Juan R", "0984562354");
        CuentaBancaria cuentaBancaria1 = new CuentaBancaria(banco1, "01/01/2023", 1.5, "01/01/2025", TipoCuenta.AHORRO, "1034324", 600.0);
        Inversion inversion1 = new Inversion(banco1, "14/02/2002", 23.0, "14/02/2024", 5444.0);
        listaProcesosBancarios.add(cuentaBancaria1);
        listaProcesosBancarios.add(inversion1);
        listaEntidades.add(banco1);



    }

    public static List<ProcesoBancario> getListaProcesosBancarios() {
        return listaProcesosBancarios;
    }

    public static List<Entidad> getListaEntidades() {
        return listaEntidades;
    }

    public static void agregarProcesoBancario(ProcesoBancario procesoBancario) {
        listaProcesosBancarios.add(procesoBancario);
    }

    public static void agregarEntidad(Entidad entidad) {
        listaEntidades.add(entidad);
    }

    public static void eliminarProcesoBancario(int codigo) {
        listaProcesosBancarios.removeIf(proceso -> proceso.getCodigo() == codigo);
    }

    public static ProcesoBancario buscarProcesoBancario(int codigo) {
        for (ProcesoBancario proceso : listaProcesosBancarios) {
            if (proceso.getCodigo() == codigo) {
                return proceso;
            }
        }
        return null;
    }
}
