package org.example;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        InventarioChef inventarioChef = new InventarioChef();

        File csvFile = new File("data/recipes.csv");
        File jsonFile = new File("data/recipes.json");


        Ingrediente tomate = new Ingrediente("Tomate", 5, "unidades");
        Ingrediente cebolla = new Ingrediente("Cebolla", 2, "unidades");
        Ingrediente ajo = new Ingrediente("Ajo", 1, "cabeza");


        ArrayList<Ingrediente> ingredientesSalsa = new ArrayList<>();
        ingredientesSalsa.add(tomate);
        ingredientesSalsa.add(cebolla);
        ingredientesSalsa.add(ajo);

        String nombreRecetaSalsa = "Salsa de Tomate";
        inventarioChef.agregarReceta(new Receta(nombreRecetaSalsa, ingredientesSalsa));
        System.out.println("Receta agregada: " + nombreRecetaSalsa);


        System.out.println("\nLista de recetas:");
        inventarioChef.verRecetas();

        
        ArrayList<Ingrediente> ingredientesEditados = new ArrayList<>();
        ingredientesEditados.add(new Ingrediente("Tomate", 10, "unidades")); 
        ingredientesEditados.add(new Ingrediente("Cebolla", 3, "unidades")); 
        ingredientesEditados.add(new Ingrediente("Pimiento", 2, "unidades")); 

        boolean recetaEditada = inventarioChef.editarReceta(nombreRecetaSalsa, ingredientesEditados);

        System.out.println("Receta editada: " + recetaEditada);

        System.out.println("\nLista de recetas después de la edición:");
        inventarioChef.verRecetas();

        boolean recetaEliminada = inventarioChef.eliminarReceta(nombreRecetaSalsa, true);
        System.out.println("Receta eliminada: " + recetaEliminada);

        System.out.println("\nLista de recetas después de la eliminación:");
        inventarioChef.verRecetas();
    }
}
