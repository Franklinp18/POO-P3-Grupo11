package modelo;

public class Ingreso extends TransaccionFinanciera {
    private Categoria categoria;
    private String repeticion;

    public Ingreso(String descripcion, double valor, String fechaInicio, String fechaFin, Categoria categoria, String repeticion) {
        super(descripcion, valor, fechaInicio, fechaFin);
        this.categoria = categoria;
        this.repeticion = repeticion;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getRepeticion() {
        return this.repeticion;
    }

    public void setRepeticion(String repeticion) {
        this.repeticion = repeticion;
    }

    @Override
    public String mostrarInformacion() {
        return String.format("%-10s %-30s %-15s %-15s %-15s %-15s %-15s %n",
                this.getCodigo(), this.getDescripcion(), this.getValor(),
                this.getFechaInicio(), this.getFechaFin(),
                this.categoria.getNombre(), this.repeticion);
    }

    public String mostrarReporte() {
        return String.format("%-20s %-20s%n", this.categoria.getNombre(), this.getValor());
    }
}
