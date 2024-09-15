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
        for (Ingrediente ingrediente : receta.getIngredientes()) {
            if (!inventario.containsKey(ingrediente.getNombre()) ||
                    inventario.get(ingrediente.getNombre()).getCantidad() < ingrediente.getCantidad()) {
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
}
