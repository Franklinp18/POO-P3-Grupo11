package controladores;

import modelo.Categoria;
import modelo.enums.TipoCategoria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class CategoriaController {
    private static List<Categoria> listaCategorias = new ArrayList<>();

    static {
        listaCategorias.add(new Categoria(TipoCategoria.GASTO, "Alquiler"));
        listaCategorias.add(new Categoria(TipoCategoria.GASTO, "Transporte"));
        listaCategorias.add(new Categoria(TipoCategoria.INGRESO, "Salario"));
        listaCategorias.add(new Categoria(TipoCategoria.INGRESO, "Premios"));
    }


    public static void administrarCategorias() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("////////////////////////////////////////////////////////////////");
            System.out.println("Categorias disponibles ingresos");
            int contadorI = 1;
            int contadorG = 1;

            for (Categoria categoria : listaCategorias) {
                if (categoria.getTipoCategoria().equals(TipoCategoria.INGRESO)) {
                    System.out.println(contadorI + ". " + categoria.getNombre());
                    contadorI++;
                }
            }

            System.out.println("Categorias disponibles gastos");
            for (Categoria categoria : listaCategorias) {
                if (categoria.getTipoCategoria().equals(TipoCategoria.GASTO)) {
                    System.out.println(contadorG + ". " + categoria.getNombre());
                    contadorG++;
                }
            }
            System.out.println("////////////////////////////////////////////////////////////////");

            System.out.println("Opciones:\n\n1. Agregar Categoria\n2. Eliminar Categoria\n3. Regresar al Menu principal\nIngrese un numero");
            opcion = leerEntero(sc);

            System.out.println("////////////////////////////////////////////////////////////////");
            String tipo;
            String nombre;
            TipoCategoria tipoCategoria;
            Categoria categoria;
            Iterator<Categoria> iterator;
            boolean categoriaExistente;

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el tipo de categoria (Ingreso/Gasto): ");
                    tipo = sc.nextLine().toUpperCase();
                    System.out.println("Ingrese el nombre de la categoria: ");
                    nombre = sc.nextLine();
                    tipoCategoria = TipoCategoria.valueOf(tipo);
                    categoria = new Categoria(tipoCategoria, nombre);

                    categoriaExistente = false;
                    for (Categoria cat : listaCategorias) {
                        if (cat.getNombre().equalsIgnoreCase(categoria.getNombre())) {
                            System.out.println("Nombre ya registrado");
                            categoriaExistente = true;
                            break;
                        }
                    }

                    if (!categoriaExistente) {
                        listaCategorias.add(categoria);
                        System.out.println("Categoría agregada correctamente.");
                    }
                    break;

                case 2:
                    System.out.println("Ingrese el tipo de categoria (Ingreso/Gasto): ");
                    tipo = sc.nextLine().toUpperCase();
                    System.out.println("Ingrese el nombre de la categoria: ");
                    nombre = sc.nextLine();
                    tipoCategoria = TipoCategoria.valueOf(tipo);

                    iterator = listaCategorias.iterator();
                    categoriaExistente = false;

                    while (iterator.hasNext()) {
                        Categoria cat = iterator.next();
                        if (cat.getNombre().equalsIgnoreCase(nombre) && cat.getTipoCategoria().equals(tipoCategoria)) {
                            System.out.println("Desea eliminar " + cat.getNombre() + "?  yes/no");
                            String confirmacion = sc.nextLine();
                            if (confirmacion.equalsIgnoreCase("yes")) {
                                iterator.remove();
                                System.out.println("Categoría " + nombre + " eliminada.");
                                categoriaExistente = true;
                            } else if (confirmacion.equalsIgnoreCase("no")) {
                                System.out.println("No se eliminó " + cat.getNombre());
                                categoriaExistente = true;
                            }
                            break;
                        }
                    }

                    if (!categoriaExistente) {
                        System.out.println("Categoría " + nombre + " no existe.");
                    }
                    break;

                case 3:
                    System.out.println("Regresando al menú principal...");
                    break;

                default:
                    System.out.println("Número no válido");
                    break;
            }
        }
    }

    private static int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            sc.next();
        }
        int numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }

    public static List<Categoria> getListaCategorias() {
        return listaCategorias;
    }
}
