package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    private static final String JSON_FILE = "recetas.json";
    private static final String INVENTARIO_JSON_FILE = "inventario.json";
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static void main(String[] args) {
        InventarioChef inventarioChef = new InventarioChef();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menu de Inventario Chef ---");
            System.out.println("1. Agregar Receta");
            System.out.println("2. Editar Receta");
            System.out.println("3. Eliminar Receta");
            System.out.println("4. Verificar Disponibilidad de Receta");
            System.out.println("5. Ver Recetas");
            System.out.println("6. Guardar Recetas en JSON");
            System.out.println("7. Cargar Recetas desde JSON");
            System.out.println("8. Agregar Ingrediente al Inventario");
            System.out.println("9. Guardar Inventario en JSON");
            System.out.println("10. Cargar Inventario desde JSON");
            System.out.println("11. Ver Inventario");
            System.out.println("12. Salir");
            System.out.print("Elige una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarReceta(inventarioChef, scanner);
                    break;
                case 2:
                    editarReceta(inventarioChef, scanner);
                    break;
                case 3:
                    eliminarReceta(inventarioChef, scanner);
                    break;
                case 4:
                    verificarDisponibilidad(inventarioChef, scanner);
                    break;
                case 5:
                    inventarioChef.verRecetas();
                    break;
                case 6:
                    guardarEnJSON(inventarioChef);
                    break;
                case 7:
                    cargarDesdeJSON(inventarioChef);
                    break;
                case 8:
                    agregarIngredienteAlInventario(inventarioChef, scanner);
                    break;
                case 9:
                    guardarInventarioEnJSON(inventarioChef);
                    break;
                case 10:
                    cargarInventarioDesdeJSON(inventarioChef);
                    break;
                case 11:
                    inventarioChef.mostrarInventario();
                    break;
                case 12:
                    salir = true;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intenta de nuevo.");
            }
        }
        scanner.close();
    }

    private static void guardarEnJSON(InventarioChef inventarioChef) {
        try {
            objectMapper.writeValue(new File(JSON_FILE), inventarioChef.getRecetas());
            System.out.println("Recetas guardadas en " + JSON_FILE);
        } catch (IOException e) {
            System.out.println("Error al guardar en JSON: " + e.getMessage());
        }
    }

    private static void cargarDesdeJSON(InventarioChef inventarioChef) {
        try {
            List<Receta> recetas = objectMapper.readValue(new File(JSON_FILE),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Receta.class));
            inventarioChef.setRecetas(new ArrayList<>(recetas));
            System.out.println("Recetas cargadas desde " + JSON_FILE);
        } catch (IOException e) {
            System.out.println("Error al cargar desde JSON: " + e.getMessage());
        }
    }

    private static void guardarInventarioEnJSON(InventarioChef inventarioChef) {
        try {
            objectMapper.writeValue(new File(INVENTARIO_JSON_FILE), inventarioChef.getInventario().getInventario());
            System.out.println("Inventario guardado en " + INVENTARIO_JSON_FILE);
        } catch (IOException e) {
            System.out.println("Error al guardar inventario en JSON: " + e.getMessage());
        }
    }

    private static void cargarInventarioDesdeJSON(InventarioChef inventarioChef) {
        try {
            HashMap<String, Ingrediente> inventarioData = objectMapper.readValue(new File(INVENTARIO_JSON_FILE),
                    objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, Ingrediente.class));
            inventarioChef.getInventario().setInventario(inventarioData);
            System.out.println("Inventario cargado desde " + INVENTARIO_JSON_FILE);
        } catch (IOException e) {
            System.out.println("Error al cargar inventario desde JSON: " + e.getMessage());
        }
    }

    private static void agregarReceta(InventarioChef inventarioChef, Scanner scanner) {
        System.out.print("Nombre de la receta: ");
        String nombreReceta = scanner.nextLine();
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        String continuar;
        do {
            System.out.print("Nombre del ingrediente: ");
            String nombreIngrediente = scanner.nextLine();
            System.out.print("Cantidad: ");
            double cantidad = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Unidad (gramos, litros, etc.): ");
            String unidad = scanner.nextLine();
            ingredientes.add(new Ingrediente(nombreIngrediente, cantidad, unidad));
            System.out.print("¿Deseas agregar otro ingrediente? (s/n): ");
            continuar = scanner.nextLine();
        } while (continuar.equalsIgnoreCase("s"));

        Receta nuevaReceta = new Receta(nombreReceta, ingredientes);
        inventarioChef.agregarReceta(nuevaReceta);
        System.out.println("Receta agregada exitosamente.");
    }

    private static void editarReceta(InventarioChef inventarioChef, Scanner scanner) {
        System.out.print("Nombre de la receta a editar: ");
        String nombreReceta = scanner.nextLine();
        ArrayList<Ingrediente> nuevosIngredientes = new ArrayList<>();
        String continuar;
        do {
            System.out.print("Nombre del ingrediente: ");
            String nombreIngrediente = scanner.nextLine();
            System.out.print("Cantidad: ");
            double cantidad = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Unidad (gramos, litros, etc.): ");
            String unidad = scanner.nextLine();
            nuevosIngredientes.add(new Ingrediente(nombreIngrediente, cantidad, unidad));
            System.out.print("¿Deseas agregar otro ingrediente? (s/n): ");
            continuar = scanner.nextLine();
        } while (continuar.equalsIgnoreCase("s"));

        boolean editado = inventarioChef.editarReceta(nombreReceta, nuevosIngredientes);
        if (editado) {
            System.out.println("Receta editada exitosamente.");
        } else {
            System.out.println("Receta no encontrada.");
        }
    }

    private static void eliminarReceta(InventarioChef inventarioChef, Scanner scanner) {
        System.out.print("Nombre de la receta a eliminar: ");
        String nombreReceta = scanner.nextLine();
        System.out.print("¿Estas seguro de que deseas eliminar esta receta? (s/n): ");
        String confirmacion = scanner.nextLine();
        if (confirmacion.equalsIgnoreCase("s")) {
            boolean eliminado = inventarioChef.eliminarReceta(nombreReceta, true);
            if (eliminado) {
                System.out.println("Receta eliminada exitosamente.");
            } else {
                System.out.println("Receta no encontrada.");
            }
        } else {
            System.out.println("Eliminacion cancelada.");
        }
    }

    private static void agregarIngredienteAlInventario(InventarioChef inventarioChef, Scanner scanner) {
        System.out.print("Nombre del ingrediente: ");
        String nombreIngrediente = scanner.nextLine();
        System.out.print("Cantidad: ");
        double cantidad = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Unidad (gramos, litros, etc.): ");
        String unidad = scanner.nextLine();
        Ingrediente ingrediente = new Ingrediente(nombreIngrediente, cantidad, unidad);
        inventarioChef.agregarIngredienteAlInventario(ingrediente);
        System.out.println("Ingrediente agregado al inventario.");
    }

    private static void verificarDisponibilidad(InventarioChef inventarioChef, Scanner scanner) {
        System.out.print("Nombre de la receta a verificar: ");
        String nombreReceta = scanner.nextLine();
    
        boolean disponible = inventarioChef.verificarDisponibilidad(nombreReceta);
        if (disponible) {
            System.out.println("Todos los ingredientes están disponibles para la receta.");
        } else {
            System.out.println("Faltan algunos ingredientes para esta receta.");
        }
    }
}
