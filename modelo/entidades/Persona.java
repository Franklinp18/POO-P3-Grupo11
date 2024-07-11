package modelo.entidades;


public class Persona extends Entidad {
    private String telefono;

    public Persona(String nombre, String email, String numeroIdentidad, String fechaRegistro, String telefono) {
        super(nombre, email, numeroIdentidad);
        this.fechaRegistro = String.valueOf(fechaRegistro);
        this.telefono = telefono;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String mostrarInformacion() {
        return String.format("%-10s %-15s %-20s %-25s %-25s %-15s %n", this.codigo, this.fechaRegistro, this.numeroIdentidad, this.nombre, this.email, this.telefono);
    }
}
