package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Receta {
    private String nombre;
    private ArrayList<Ingrediente> ingredientes = new ArrayList<>();

    // Constructor con parámetros
    public Receta(String nombre, ArrayList<Ingrediente> ingredientes) {
        this.nombre = nombre;
        this.ingredientes = ingredientes != null ? ingredientes : new ArrayList<>();
    }

    // Constructor sin argumentos para Jackson
    public Receta() {
        this.ingredientes = new ArrayList<>();
    }

    // Método para obtener el nombre de la receta
    public String getNombre() {
        return nombre;
    }

    // Método utilizado para CSV, no JSON, por lo que se anota con @JsonIgnore para evitar la serialización en JSON
    @JsonIgnore
    public String getIngredientesCSV() {
        StringBuilder ingredientesCSV = new StringBuilder();
        for (Ingrediente ingrediente : ingredientes) {
            ingredientesCSV.append(ingrediente.getNombre()).append("-")
                    .append(ingrediente.getCantidad()).append("-")
                    .append(ingrediente.getUnidad()).append(";");
        }
        return ingredientesCSV.toString().replaceAll(";$", "");
    }

    // Método para mostrar los ingredientes en la consola
    public void mostrarIngredientes() {
        System.out.println("Ingredientes de " + nombre + ":");
        for (Ingrediente ingrediente : ingredientes) {
            System.out.println(ingrediente.getNombre() + " - " + ingrediente.getCantidad() + " " + ingrediente.getUnidad());
        }
    }

    // Método estático para crear una receta a partir de una cadena CSV
    public static Receta fromCSV(String nombre, String ingredientesCSV) {
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        String[] items = ingredientesCSV.split(";");
        for (String item : items) {
            String[] campos = item.split("-");
            if (campos.length == 3) {
                String nombreIngrediente = campos[0];
                double cantidad = Double.parseDouble(campos[1]);
                String unidad = campos[2];
                ingredientes.add(new Ingrediente(nombreIngrediente, cantidad, unidad));
            }
        }
        return new Receta(nombre, ingredientes);
    }

    // Método para establecer el nombre de la receta
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método para obtener la lista de ingredientes
    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    // Método para establecer la lista de ingredientes
    public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes != null ? ingredientes : new ArrayList<>();
    }

    // Método para agregar un ingrediente a la receta
    public void agregarIngrediente(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
    }

    // Método para eliminar un ingrediente por su nombre
    public boolean eliminarIngrediente(String nombreIngrediente) {
        return ingredientes.removeIf(ingrediente -> ingrediente.getNombre().equalsIgnoreCase(nombreIngrediente));
    }

    // Método toString para representar la receta en formato de texto
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Receta: ").append(nombre).append("\n");
        sb.append("Ingredientes:\n");
        for (Ingrediente ingrediente : ingredientes) {
            sb.append("- ").append(ingrediente.getNombre())
              .append(": ").append(ingrediente.getCantidad())
              .append(" ").append(ingrediente.getUnidad()).append("\n");
        }
        return sb.toString();
    }
}
