package modelo;

import modelo.entidades.*;

public class Prestamo extends TransaccionFinanciera {
    private Persona deudor;
    private String fechaDePrestamo;
    private int cuota;

    public Prestamo(String descripcion, double valor, String fechaInicio, String fechaFin, Persona deudor, String fechaDePrestamo, int cuota) {
        super(descripcion, valor, fechaInicio, fechaFin);
        this.deudor = deudor;
        this.fechaDePrestamo = fechaDePrestamo;
        this.cuota = cuota;
    }

    public Persona getDeudor() {
        return this.deudor;
    }

    public void setDeudor(Persona deudor) {
        this.deudor = deudor;
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

    @Override
    public String mostrarInformacion() {
        return String.format("%-15s %-15s %-15s %-30s %-20s %-15s %n", this.codigo, this.deudor.getNombre(), this.valor, this.descripcion, this.fechaDePrestamo, this.cuota);
    }
}
