package org.example;

import java.util.HashMap;

public class Inventario {
    private HashMap<String, Ingrediente> inventario;

    public Inventario() {
        inventario = new HashMap<>();
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        String nombre = ingrediente.getNombre();
        if (inventario.containsKey(nombre)) {
            Ingrediente existente = inventario.get(nombre);
            existente.setCantidad(existente.getCantidad() + ingrediente.getCantidad());
        } else {
            inventario.put(nombre, ingrediente);
        }
    }
    public boolean verificarDisponibilidad(Receta receta) {
        for (Ingrediente ingredienteReceta : receta.getIngredientes()) { 
            Ingrediente ingredienteInventario = inventario.get(ingredienteReceta.getNombre());
            if (ingredienteInventario == null || ingredienteInventario.getCantidad() < ingredienteReceta.getCantidad()) {
                return false;
            }
        }
        return true;
    }
    

    public void utilizarIngredientes(Receta receta) {
        if (verificarDisponibilidad(receta)) {
            for (Ingrediente ingrediente : receta.getIngredientes()) {
                Ingrediente enInventario = inventario.get(ingrediente.getNombre());
                enInventario.setCantidad(enInventario.getCantidad() - ingrediente.getCantidad());
            }
            System.out.println("Ingredientes utilizados para preparar " + receta.getNombre());
        } else {
            System.out.println("No hay suficientes ingredientes para preparar " + receta.getNombre());
        }
    }

    public void mostrarInventario() {
        System.out.println("Inventario actual:");
        for (Ingrediente ingrediente : inventario.values()) {
            System.out.println(ingrediente);
        }
    }

    public Ingrediente buscarIngrediente(String nombre) {
        return inventario.getOrDefault(nombre, null);
    }

    public boolean eliminarIngrediente(String nombre) {
        if (inventario.containsKey(nombre)) {
            inventario.remove(nombre);
            return true;
        }
        return false;
    }

    public boolean actualizarCantidadIngrediente(String nombre, double nuevaCantidad) {
        if (inventario.containsKey(nombre)) {
            Ingrediente ingrediente = inventario.get(nombre);
            ingrediente.setCantidad(nuevaCantidad);
            return true;
        }
        return false;
    }
}
