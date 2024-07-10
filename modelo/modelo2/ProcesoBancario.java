package modelo.modelo2;
import modelo.entidades.Banco;



public abstract class ProcesoBancario {
    protected static int contador = 1;
    protected int codigo;
    protected Banco banco;
    protected String fechaApertura;
    protected double interesPorMes;
    protected String fechaCierre;

    public static void incrementarCodigo() {
        ++contador;
    }

    public ProcesoBancario(Banco banco, String fechaApertura, double interesPorMes, String fechaCierre) {
        this.codigo = contador;
        this.banco = banco;
        this.fechaApertura = fechaApertura;
        this.interesPorMes = interesPorMes;
        this.fechaCierre = fechaCierre;
        incrementarCodigo();
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Banco getBanco() {
        return this.banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public String getFechaApertura() {
        return this.fechaApertura;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public double getInteresPorMes() {
        return this.interesPorMes;
    }

    public void setInteresPorMes(double interesPorMes) {
        this.interesPorMes = interesPorMes;
    }

    public String getFechaCierre() {
        return this.fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String mostrarInformacion() {
        return "";
    }
}
