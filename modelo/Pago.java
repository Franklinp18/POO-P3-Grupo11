package modelo;

import modelo.entidades.Entidad;

public class Pago extends TransaccionFinanciera {
    private Entidad acredor;
    private double interes;
    private String fechaDePrestamo;
    private int cuota;

    public Pago(String descripcion, double valor, String fechaInicio, String fechaFin, Entidad acredor, double interes, String fechaDePrestamo, int cuota) {
        super(descripcion, valor, fechaInicio, fechaFin);
        this.acredor = acredor;
        this.interes = interes;
        this.fechaDePrestamo = fechaDePrestamo;
        this.cuota = cuota;
    }

    public String mostrarInformacion() {
        return String.format("%-15s %-20s %-15s %-30s %-20s %-15s %n", this.codigo, this.acredor.getNombre(), this.valor, this.descripcion, this.fechaDePrestamo, this.cuota);
    }

    public Entidad getAcredor() {
        return this.acredor;
    }

    public void setAcredor(Entidad acredor) {
        this.acredor = acredor;
    }

    public double getInteres() {
        return this.interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public String getFechaDePrestamo() {
        return this.fechaDePrestamo;
    }

    public void setFechaDePrestamo(String fechaDePrestamo) {
        this.fechaDePrestamo = fechaDePrestamo;
    }

    public int getCuota() {
        return this.cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }
}
