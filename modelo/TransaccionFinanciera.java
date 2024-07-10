package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class TransaccionFinanciera {
    protected static int contador = 1;
    protected int codigo;
    protected String descripcion;
    protected double valor;
    protected String fechaInicio;
    protected String fechaFin;

    public TransaccionFinanciera(String descripcion, double valor, String fechaInicio, String fechaFin) {
        this.codigo = contador;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        incrementarCodigo();
    }

    public static void incrementarCodigo() {
        contador++;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public static boolean esFechaMayor(String fecha1, String fecha2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate date1 = LocalDate.parse(fecha1, formatter);
            LocalDate date2 = LocalDate.parse(fecha2, formatter);
            return date1.isAfter(date2);
        } catch (DateTimeParseException e) {
            System.out.println("Error al analizar las fechas: " + e.getMessage());
            return false;
        }
    }

    public String mostrarInformacion() {
        return String.format("%-10s %-30s %-15s %-15s %-15s %n", this.codigo, this.descripcion, this.valor, this.fechaInicio, this.fechaFin);
    }
}
