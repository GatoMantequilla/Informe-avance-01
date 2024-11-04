package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InventarioChef {
    Inventario inventario; 
    ArrayList<Receta> recetas; 

    public InventarioChef() {
        inventario = new Inventario();
        recetas = new ArrayList<>();
    }

        // Setter for recetas
    public void setRecetas(ArrayList<Receta> recetas) {
        this.recetas = recetas;
    }

    // Getter for inventario
    public Inventario getInventario() {
        return inventario;
    }
    public ArrayList<Receta> getRecetas() {
        return recetas;
    }
    
    public void agregarIngredienteAlInventario(Ingrediente ingrediente) {
        inventario.agregarIngrediente(ingrediente);
    }

    // Method to verify the availability of a recipe in the inventory
    public boolean verificarDisponibilidad(String nombreReceta) {
        Receta receta = buscarRecetaPorNombre(nombreReceta);
        return receta != null && inventario.verificarDisponibilidad(receta);
    }

    // Method to display the inventory
    public void mostrarInventario() {
        inventario.mostrarInventario();
    }

    public boolean eliminarReceta(String nombreReceta, boolean confirmacion) {
        Receta receta = buscarRecetaPorNombre(nombreReceta);
        if (receta != null) {
            if (confirmacion) {
                recetas.remove(receta);
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


    private Receta buscarRecetaPorNombre(String nombreReceta) {
        for (Receta receta : recetas) {
            if (receta.getNombre().equalsIgnoreCase(nombreReceta)) {
                return receta;
            }
        }
        return null;
    }

    public boolean editarReceta(String nombreReceta, ArrayList<Ingrediente> ingredientesEditados) {
        Receta receta = buscarRecetaPorNombre(nombreReceta);
        if (receta != null) {
            receta.setIngredientes(ingredientesEditados);
            System.out.println("Receta editada exitosamente.");
            return true;
        } else {
            System.out.println("Receta no encontrada.");
            return false;
        }
    }

    public void agregarReceta(Receta receta) {
        recetas.add(receta);
        System.out.println("Receta agregada exitosamente.");
    }

    public List<String> obtenerNombresRecetas() {
        List<String> nombresRecetas = new ArrayList<>();
        for (Receta receta : recetas) {
            nombresRecetas.add(receta.getNombre());
        }
        return nombresRecetas;
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

    public void verInventario() {
        inventario.mostrarInventario();
    }
}
