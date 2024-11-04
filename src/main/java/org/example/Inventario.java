package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.HashMap;

public class Inventario {
    private HashMap<String, Ingrediente> inventario;
    private static final ObjectMapper objectMapper = new ObjectMapper();

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
    
    public void guardarInventarioJSON(String rutaArchivo) {
        try {
            objectMapper.writeValue(new File(rutaArchivo), inventario);
            System.out.println("Inventario guardado en " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar el inventario en JSON: " + e.getMessage());
        }
    }

    public void cargarInventarioJSON(String rutaArchivo) {
        try {
            inventario = objectMapper.readValue(new File(rutaArchivo),
                objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, Ingrediente.class));
            System.out.println("Inventario cargado desde " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al cargar el inventario desde JSON: " + e.getMessage());
        }
    }

    public void guardarInventarioCSV(String rutaArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            writer.println("nombre,cantidad,unidad");
            for (Ingrediente ingrediente : inventario.values()) {
                writer.printf("%s,%.2f,%s%n", ingrediente.getNombre(), ingrediente.getCantidad(), ingrediente.getUnidad());
            }
            System.out.println("Inventario guardado en " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar el inventario en CSV: " + e.getMessage());
        }
    }

    public void cargarInventarioCSV(String rutaArchivo) {
        HashMap<String, Ingrediente> inventarioData = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String nombre = parts[0];
                double cantidad = Double.parseDouble(parts[1]);
                String unidad = parts[2];
                inventarioData.put(nombre, new Ingrediente(nombre, cantidad, unidad));
            }
            inventario = inventarioData;
            System.out.println("Inventario cargado desde " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al cargar el inventario desde CSV: " + e.getMessage());
        }
    }

    public HashMap<String, Ingrediente> getInventario() {
        return inventario;
    }
    
    public void setInventario(HashMap<String, Ingrediente> inventario) {
        this.inventario = inventario;
    }
    
}
