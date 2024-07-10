package controladores;
import modelo.entidades.Banco;
import java.util.ArrayList;
import java.util.List;

public class BancoController {
    private static List<Banco> listaBancos = new ArrayList<>();

    public static List<Banco> getListaBancos() {
        return listaBancos;
    }

    public static void agregarBanco(Banco banco) {
        listaBancos.add(banco);
    }

    public static Banco buscarBancoPorRuc(String ruc) {
        for (Banco banco : listaBancos) {
            if (banco.getNumeroIdentidad().equals(ruc)) {
                return banco;
            }
        }
        return null;
    }

    public static void mostrarBancos() {
        for (Banco banco : listaBancos) {
            System.out.println(banco.mostrarInformacion());
        }
    }
}
