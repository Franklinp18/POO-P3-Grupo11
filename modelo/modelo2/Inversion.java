package modelo.modelo2;

import modelo.entidades.Banco;

public class Inversion extends ProcesoBancario {
    private double cantidad;

    public Inversion(Banco banco, String fechaApertura, double interesPorMes, String fechaCierre, double cantidad) {
        super(banco, fechaApertura, interesPorMes, fechaCierre);
        this.cantidad = cantidad;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String mostrarInformacion() {
        return String.format("%-10s %-30s %-15s %-15s %-15s %-15s %n", this.codigo, this.banco.getNombre(), this.fechaApertura, this.cantidad, this.interesPorMes, this.fechaCierre);
    }
}
