package org.example;

import java.util.ArrayList;

public class Chef extends Usuario {
    private String cocinaSeleccionada;

    public Chef(String nombre, String clave, int id, String cocinaSeleccionada) {
        super(nombre, clave, id);
        this.cocinaSeleccionada = cocinaSeleccionada;
    }

    public String getCocinaSeleccionada() {
        return cocinaSeleccionada;
    }

    public void setCocinaSeleccionada(String cocinaSeleccionada) {
        this.cocinaSeleccionada = cocinaSeleccionada;
    }

    
    private String obtenerNombreReceta(String nombreReceta) {
        return nombreReceta; // Devuelve el nombre de la receta recibido como parámetro
    }
    
    
    private ArrayList<Ingrediente> obtenerIngredientesReceta(ArrayList<Ingrediente> ingredientesReceta) {
        return ingredientesReceta; // Devuelve la lista de ingredientes recibida
    }
    
    
    private Ingrediente obtenerIngrediente(String nombreIngrediente, double cantidad, String unidad) {
        return new Ingrediente(nombreIngrediente, cantidad, unidad); // Crea y devuelve un nuevo objeto Ingrediente
    }
    
    
    private void agregarRecetaAlInventario(InventarioChef inventarioChef, String nombreReceta, ArrayList<Ingrediente> ingredientesReceta) {
        Receta nuevaReceta = new Receta(nombreReceta, ingredientesReceta);
        inventarioChef.recetas.add(nuevaReceta);
        System.out.println("Receta creada exitosamente.");
    }

    public boolean editarReceta(InventarioChef inventarioChef, String nombreReceta, ArrayList<Ingrediente> ingredientesEditados) {
        Receta receta = buscarReceta(inventarioChef, nombreReceta); // Busca la receta por nombre
        if (receta != null) {
            receta.setIngredientes(ingredientesEditados); // Actualiza los ingredientes de la receta con la lista proporcionada
            System.out.println("Receta editada exitosamente."); // Confirma la edición
            return true;
        } else {
            System.out.println("Receta no encontrada."); // Mensaje si la receta no existe
            return false;
        }
    }
    
    
    private Receta buscarReceta(InventarioChef inventarioChef, String nombreReceta) {
        for (Receta receta : inventarioChef.recetas) {
            if (receta.getNombre().equalsIgnoreCase(nombreReceta)) {
                return receta;
            }
        }
        return null;
    }
    
    private void mostrarIngredientesReceta(Receta receta) {
        System.out.println("Ingredientes de la receta " + receta.getNombre() + ":");
        for (Ingrediente ingrediente : receta.getIngredientesList()) {
            System.out.println("- " + ingrediente.getNombre() + ": " + ingrediente.getCantidad() + " " + ingrediente.getUnidad());
        }
    }
    
    private void editarIngredientesReceta(Receta receta, ArrayList<Ingrediente> ingredientesEditados) {
        System.out.println("Editando los ingredientes de la receta: " + receta.getNombre()); // Muestra mensaje de edición
        receta.setIngredientes(ingredientesEditados); // Actualiza los ingredientes de la receta
    }
    
    
    private ArrayList<Ingrediente> obtenerIngredientesEditados(ArrayList<Ingrediente> ingredientesEditados) {
        return ingredientesEditados; // Devuelve la lista de ingredientes editados recibida
    }
    
    
    public boolean eliminarReceta(InventarioChef inventarioChef, String nombreReceta, boolean confirmacion) {
        Receta receta = buscarReceta(inventarioChef, nombreReceta); // Busca la receta en el inventario
        if (receta != null) {
            mostrarIngredientesReceta(receta); // Muestra los ingredientes de la receta
            if (confirmacion) { // Si la confirmación es verdadera
                inventarioChef.recetas.remove(receta); // Elimina la receta del inventario
                System.out.println("Receta eliminada exitosamente."); // Confirma la eliminación
                return true;
            } else {
                System.out.println("Eliminación cancelada."); // Cancela la eliminación
            }
        } else {
            System.out.println("Receta no encontrada."); // Mensaje si la receta no se encuentra
        }
        return false;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", cocinaSeleccionada='" + cocinaSeleccionada + "'";
    }
}
