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

    public void mostrarIngredientes() {
        System.out.println("Ingredientes de " + nombre + ":");
        for (Ingrediente ingrediente : ingredientes) {
            System.out.println(ingrediente.getNombre() + " - " + ingrediente.getCantidad() + " " + ingrediente.getUnidad());
        }
    }

    public boolean verificarDisponibilidad(ArrayList<Ingrediente> inventario) {
        for (Ingrediente ingredienteReceta : ingredientes) {
            boolean encontrado = false;
            for (Ingrediente ingredienteInventario : inventario) {
                if (ingredienteReceta.getNombre().equalsIgnoreCase(ingredienteInventario.getNombre()) &&
                        ingredienteReceta.getCantidad() <= ingredienteInventario.getCantidad()) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                return false;
            }
        }
        return true;
    }

    public Ingrediente[] getIngredientes() {
        return ingredientes.toArray(new Ingrediente[0]);
    }
}
