package modelo;
import modelo.*;
import modelo.enums.TipoCategoria;

public class Categoria {
    private TipoCategoria tipoCategoria;
    private String nombre;

    public Categoria(TipoCategoria tipoCategoria, String nombre) {
        this.tipoCategoria = tipoCategoria;
        this.nombre = nombre;
    }

    public TipoCategoria getTipoCategoria() {
        return this.tipoCategoria;
    }

    public void setTipoCategoria(TipoCategoria tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}