package org.example;

import java.util.ArrayList;

public class Receta {
    private String nombre;
    private ArrayList<Ingrediente> ingredientes;

    public Receta(String nombre, ArrayList<Ingrediente> ingredientes) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public ArrayList<Ingrediente> getIngredientesList() {
        return ingredientes;
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
    }

    public boolean eliminarIngrediente(String nombreIngrediente) {
        return ingredientes.removeIf(ingrediente -> ingrediente.getNombre().equalsIgnoreCase(nombreIngrediente));
    }

    public String getIngredientesCSV() {
        StringBuilder ingredientesCSV = new StringBuilder();
        for (Ingrediente ingrediente : ingredientes) {
            ingredientesCSV.append(ingrediente.getNombre()).append("-")
                .append(ingrediente.getCantidad()).append("-")
                .append(ingrediente.getUnidad()).append(";");
        }
        return ingredientesCSV.toString().replaceAll(";$", "");
    }

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

        public void mostrarIngredientes() {
            System.out.println("Ingredientes de la receta " + nombre + ":");
            for (Ingrediente ingrediente : ingredientes) {
                System.out.println("- " + ingrediente.getNombre() + ": " + ingrediente.getCantidad() + " " + ingrediente.getUnidad());
            }
        }
}
