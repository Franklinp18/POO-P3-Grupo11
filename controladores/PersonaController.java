package controladores;

import modelo.entidades.Persona;
import java.util.ArrayList;
import java.util.List;

public class PersonaController {
    private static List<Persona> listaPersonas = new ArrayList<>();

    public static List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public static void agregarPersona(Persona persona) {
        listaPersonas.add(persona);
    }

    public static Persona buscarPersonaPorCedula(String cedula) {
        for (Persona persona : listaPersonas) {
            if (persona.getNumeroIdentidad().equals(cedula)) {
                return persona;
            }
        }
        return null;
    }

    public static void mostrarPersonas() {
        for (Persona persona : listaPersonas) {
            System.out.println(persona.mostrarInformacion());
        }
    }
}
