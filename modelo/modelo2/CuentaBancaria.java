package modelo.modelo2;
import modelo.entidades.Banco;
import modelo.enums.TipoCuenta;


public class CuentaBancaria extends ProcesoBancario {
    private TipoCuenta tipoCuenta;
    private String numero;
    private double saldo;

    public CuentaBancaria(Banco banco, String fechaApertura, double interesPorMes, String fechaCierre, TipoCuenta tipoCuenta, String numero, double saldo) {
        super(banco, fechaApertura, interesPorMes, fechaCierre);
        this.tipoCuenta = tipoCuenta;
        this.numero = numero;
        this.saldo = saldo;
    }

    public TipoCuenta getTipoCuenta() {
        return this.tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String mostrarInformacion() {
        return String.format("%-10s %-30s %-15s %-15s %-15s %n", this.codigo, this.banco.getNombre(), this.tipoCuenta, this.numero, this.saldo);
    }
}
