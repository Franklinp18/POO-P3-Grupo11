package modelo.entidades;

public abstract class Entidad {
    protected static int contador = 1;
    protected int codigo;
    protected String nombre;
    protected String email;
    protected String numeroIdentidad;
    protected String fechaRegistro;

    public Entidad(String nombre, String email, String numeroIdentidad) {
        this.codigo = contador++;
        this.nombre = nombre;
        this.email = email;
        this.numeroIdentidad = numeroIdentidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getNumeroIdentidad() {
        return numeroIdentidad;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public abstract String mostrarInformacion();
}
