package org.example;

import java.util.ArrayList;
import java.util.List;

public class InventarioChef {
    Inventario inventario; 
    ArrayList<Receta> recetas; 

    public InventarioChef() {
        inventario = new Inventario();
        recetas = new ArrayList<>();
    }

    public boolean eliminarReceta(String nombreReceta, boolean confirmacion) {
        Receta receta = buscarRecetaPorNombre(nombreReceta); // Asume que tienes este método para buscar una receta
        if (receta != null) {
            if (confirmacion) {
                recetas.remove(receta); // Elimina la receta de la lista
                System.out.println("Receta eliminada exitosamente.");
                return true;
            } else {
                System.out.println("Eliminación cancelada.");
            }
        } else {
            System.out.println("Receta no encontrada.");
        }
        return false;
    }


    public boolean verificarDisponibilidad(String nombreReceta) {
        Receta receta = buscarRecetaPorNombre(nombreReceta); // Assuming this method exists
        if (receta == null) return false;
        return inventario.verificarDisponibilidad(receta); // Assuming this returns boolean
    }
    
    
    // Método para buscar la receta por nombre
    private Receta buscarRecetaPorNombre(String nombreReceta) {
        for (Receta receta : recetas) { // Recorre la lista de recetas
            if (receta.getNombre().equalsIgnoreCase(nombreReceta)) { // Busca coincidencia de nombre
                return receta; // Devuelve la receta si la encuentra
            }
        }
        return null; // Devuelve null si no encuentra la receta
    }
    
    // Método para verificar si los ingredientes de la receta están disponibles en el inventario
    private void verificarIngredientesDisponibles(Receta receta) {
        boolean disponible = inventario.verificarDisponibilidad(receta); // Llama al método de inventario
        if (disponible) {
            System.out.println("Todos los ingredientes están disponibles para la receta."); // Mensaje de disponibilidad
        } else {
            System.out.println("Faltan algunos ingredientes para esta receta."); // Mensaje de ingredientes faltantes
        }
    }


    public boolean editarReceta(String nombreReceta, ArrayList<Ingrediente> ingredientesEditados) {
        Receta receta = buscarRecetaPorNombre(nombreReceta); // Busca la receta por nombre
        if (receta != null) {
            receta.setIngredientes(ingredientesEditados); // Actualiza los ingredientes de la receta
            System.out.println("Receta editada exitosamente.");
            return true;
        } else {
            System.out.println("Receta no encontrada.");
            return false;
        }
    }
    
    public void agregarReceta(Receta receta) {
        recetas.add(receta); // Agrega la receta a la lista de recetas
        System.out.println("Receta agregada exitosamente."); // Confirma la adición
    }

    public List<String> obtenerNombresRecetas() {
        List<String> nombresRecetas = new ArrayList<>();
        for (Receta receta : recetas) { // Recorre la lista de recetas
            nombresRecetas.add(receta.getNombre()); // Agrega el nombre de cada receta a la lista
        }
        return nombresRecetas; // Devuelve la lista de nombres de recetas
    }

    public void verRecetas() {
        System.out.println("\n=== Recetas Disponibles ===");
        if (recetas.isEmpty()) {
            System.out.println("No hay recetas disponibles."); // Mensaje si no hay recetas
        } else {
            for (Receta receta : recetas) { // Recorre las recetas y las muestra
                System.out.println("Receta: " + receta.getNombre());
                receta.mostrarIngredientes(); // Muestra los ingredientes de la receta
            }
        }
        System.out.println();
    }



    public void verInventario() {
        inventario.mostrarInventario();
    }
}
