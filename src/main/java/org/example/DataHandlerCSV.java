package org.example;

import java.io.*;
import java.util.ArrayList;

public class DataHandlerCSV {

    public static void guardarIngredientesCSV(ArrayList<Ingrediente> ingredientes, String rutaArchivo) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            writer.println("nombre,cantidad,unidad");
            for (Ingrediente ingrediente : ingredientes) {
                writer.printf("%s,%.2f,%s%n", ingrediente.getNombre(), ingrediente.getCantidad(), ingrediente.getUnidad());
            }
        }
    }
    
    public static ArrayList<Ingrediente> cargarIngredientesCSV(String rutaArchivo) throws IOException {
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            reader.readLine(); 
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(",");
                String nombre = campos[0];
                double cantidad = Double.parseDouble(campos[1]);
                String unidad = campos[2];
                ingredientes.add(new Ingrediente(nombre, cantidad, unidad));
            }
        }
        return ingredientes;
    }

    public static void guardarRecetasCSV(ArrayList<Receta> recetas, String rutaArchivo) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            writer.println("nombre,ingredientes");
            for (Receta receta : recetas) {
                String ingredientesCSV = receta.getIngredientesCSV();
                writer.printf("%s,%s%n", receta.getNombre(), ingredientesCSV);
            }
        }
    }

    public static ArrayList<Receta> cargarRecetasCSV(String rutaArchivo) throws IOException {
        ArrayList<Receta> recetas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            reader.readLine();
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(",");
                String nombre = campos[0];
                String ingredientesCSV = campos[1];
                ArrayList<Ingrediente> ingredientes = parsearIngredientesCSV(ingredientesCSV);
                recetas.add(new Receta(nombre, ingredientes));
            }
        }
        return recetas;
    }
    private static ArrayList<Ingrediente> parsearIngredientesCSV(String ingredientesCSV) {
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        String[] items = ingredientesCSV.split(";");
        for (String item : items) {
            String[] campos = item.split("-");
            if (campos.length == 3) {
                String nombre = campos[0];
                double cantidad = Double.parseDouble(campos[1]);
                String unidad = campos[2];
                ingredientes.add(new Ingrediente(nombre, cantidad, unidad));
            }
        }
        return ingredientes;
    }
}
