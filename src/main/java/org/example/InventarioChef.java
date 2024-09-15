package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class InventarioChef {
    public ArrayList<Ingrediente> inventario = new ArrayList<>();
    public ArrayList<Receta> recetas = new ArrayList<>();

    public static void main(String[] args) {
        InventarioChef sistema = new InventarioChef();
        sistema.mostrarMenu();
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarOpcionesMenu();
            opcion = obtenerOpcionUsuario(scanner);
            ejecutarOpcion(opcion);
        } while (opcion != 6);

        scanner.close();
    }

    private void mostrarOpcionesMenu() {
        System.out.println("----------- Inventario Chef (IC) -----------");
        System.out.println("1. Agregar ingrediente al inventario");
        System.out.println("2. Ver inventario");
        System.out.println("3. Crear receta");
        System.out.println("4. Ver recetas");
        System.out.println("5. Verificar disponibilidad de ingredientes para una receta");
        System.out.println("6. Salir");
    }

    private int obtenerOpcionUsuario(Scanner scanner) {
        System.out.print("Selecciona una opción: ");
        return scanner.nextInt();
    }

    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                agregarIngrediente();
                break;
            case 2:
                verInventario();
                break;
            case 3:
                crearReceta();
                break;
            case 4:
                verRecetas();
                break;
            case 5:
                verificarDisponibilidad();
                break;
            case 6:
                System.out.println("Saliendo del sistema...");
                break;
            default:
                System.out.println("Opción inválida. Inténtalo de nuevo.");
        }
    }


    public void agregarIngrediente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre del ingrediente: ");
        String nombre = scanner.nextLine();
        System.out.print("Cantidad: ");
        double cantidad = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Unidad (gramos, litros, etc.): ");
        String unidad = scanner.nextLine();

        Ingrediente ingrediente = new Ingrediente(nombre, cantidad, unidad);
        inventario.add(ingrediente);

        System.out.println("Ingrediente agregado exitosamente.\n");
    }

    public void verInventario() {
        System.out.println("\n=== Inventario Actual ===");
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            for (Ingrediente ingrediente : inventario) {
                System.out.println(ingrediente.getNombre() + " - " + ingrediente.getCantidad() + " " + ingrediente.getUnidad());
            }
        }
        System.out.println();
    }

    public void crearReceta() {
        Scanner scanner = new Scanner(System.in);
        String nombreReceta = obtenerNombreReceta(scanner);
        ArrayList<Ingrediente> ingredientesReceta = obtenerIngredientesReceta(scanner);

        Receta receta = new Receta(nombreReceta, ingredientesReceta);
        recetas.add(receta);
        System.out.println("Receta creada exitosamente.\n");
    }

    private String obtenerNombreReceta(Scanner scanner) {
        System.out.print("Nombre de la receta: ");
        return scanner.nextLine();
    }

    private ArrayList<Ingrediente> obtenerIngredientesReceta(Scanner scanner) {
        ArrayList<Ingrediente> ingredientesReceta = new ArrayList<>();
        String continuar;

        do {
            Ingrediente ingrediente = obtenerIngrediente(scanner);
            ingredientesReceta.add(ingrediente);

            System.out.print("¿Agregar otro ingrediente? (s/n): ");
            continuar = scanner.nextLine();
        } while (continuar.equalsIgnoreCase("s"));

        return ingredientesReceta;
    }

    private Ingrediente obtenerIngrediente(Scanner scanner) {
        System.out.print("Nombre del ingrediente: ");
        String nombreIngrediente = scanner.nextLine();
        System.out.print("Cantidad: ");
        double cantidad = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Unidad (gramos, litros, etc.): ");
        String unidad = scanner.nextLine();

        return new Ingrediente(nombreIngrediente, cantidad, unidad);
    }

    public void verRecetas() {
        System.out.println("\n=== Recetas Disponibles ===");
        if (recetas.isEmpty()) {
            System.out.println("No hay recetas disponibles.");
        } else {
            for (Receta receta : recetas) {
                System.out.println("Receta: " + receta.getNombre());
                receta.mostrarIngredientes();
            }
        }
        System.out.println();
    }

    public void verificarDisponibilidad() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre de la receta a verificar: ");
        String nombreReceta = scanner.nextLine();

        Receta recetaBuscada = null;
        for (Receta receta : recetas) {
            if (receta.getNombre().equalsIgnoreCase(nombreReceta)) {
                recetaBuscada = receta;
                break;
            }
        }

        if (recetaBuscada != null) {
            boolean disponible = recetaBuscada.verificarDisponibilidad(inventario);
            if (disponible) {
                System.out.println("Todos los ingredientes están disponibles para la receta.");
            } else {
                System.out.println("Faltan algunos ingredientes para esta receta.");
            }
        } else {
            System.out.println("Receta no encontrada.");
        }
    }
}
