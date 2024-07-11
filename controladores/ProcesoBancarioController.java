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
        Persona persona1 = new Persona("Pedro Lopez", "pedrolopez@espol.edu.ec", "0952483931", "10/07/2023", "0959458667");
        listaProcesosBancarios.add(cuentaBancaria1);
        listaProcesosBancarios.add(inversion1);
        listaEntidades.add(banco1);
        listaEntidades.add(persona1);
    }

    /**
     * Obtiene la lista de todos los procesos bancarios.
     *
     * @return la lista de procesos bancarios.
     */
    public static List<ProcesoBancario> getListaProcesosBancarios() {
        return listaProcesosBancarios;
    }

    /**
     * Obtiene la lista de todas las entidades.
     *
     * @return la lista de entidades.
     */
    public static List<Entidad> getListaEntidades() {
        return listaEntidades;
    }

    /**
     * Agrega un proceso bancario a la lista de procesos bancarios.
     *
     * @param procesoBancario el proceso bancario a agregar.
     */
    public static void agregarProcesoBancario(ProcesoBancario procesoBancario) {
        listaProcesosBancarios.add(procesoBancario);
    }

    /**
     * Agrega una entidad a la lista de entidades.
     *
     * @param entidad la entidad a agregar.
     */
    public static void agregarEntidad(Entidad entidad) {
        listaEntidades.add(entidad);
    }

    /**
     * Elimina un proceso bancario de la lista de procesos bancarios según el código.
     *
     * @param codigo el código del proceso bancario a eliminar.
     */
    public static void eliminarProcesoBancario(int codigo) {
        listaProcesosBancarios.removeIf(proceso -> proceso.getCodigo() == codigo);
    }

    /**
     * Busca un proceso bancario en la lista de procesos bancarios según el código.
     *
     * @param codigo el código del proceso bancario a buscar.
     * @return el proceso bancario encontrado o null si no se encuentra.
     */
    public static ProcesoBancario buscarProcesoBancario(int codigo) {
        for (ProcesoBancario proceso : listaProcesosBancarios) {
            if (proceso.getCodigo() == codigo) {
                return proceso;
            }
        }
        return null;
    }
}
