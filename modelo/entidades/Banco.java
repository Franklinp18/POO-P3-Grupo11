package modelo.entidades;

import java.time.LocalDate;

public class Banco extends Entidad {
    private String nombreOficialCredito;
    private String telefonoOficialCredito;

    public Banco(String nombre, String email, String numeroIdentidad, String fechaRegistro, String nombreOficialCredito, String telefonoOficialCredito) {
        super(nombre, email, numeroIdentidad);
        this.nombreOficialCredito = nombreOficialCredito;
        this.telefonoOficialCredito = telefonoOficialCredito;
    }

    public Banco(String nombre, String email, String cedula, LocalDate fechaRegistro) {
        super();
    }

    public Banco(String nombre, String email, String cedula, String fechaRegistro) {
    }

    public String getNombreOficialCredito() {
        return this.nombreOficialCredito;
    }

    public void setNombreOficialCredito(String nombreOficialCredito) {
        this.nombreOficialCredito = nombreOficialCredito;
    }

    public String getTelefonoOficialCredito() {
        return this.telefonoOficialCredito;
    }

    public void setTelefonoOficialCredito(String telefonoOficialCredito) {
        this.telefonoOficialCredito = telefonoOficialCredito;
    }

    public String mostrarInformacion() {
        return String.format("%-10s %-15s %-20s %-25s %-25s %-15s %n", this.codigo, this.fechaRegistro, this.numeroIdentidad, this.nombre, this.email, "Oficial: " + this.nombreOficialCredito + "\tTelefono: " + this.telefonoOficialCredito);
    }
}

