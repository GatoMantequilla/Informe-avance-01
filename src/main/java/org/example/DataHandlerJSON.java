package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DataHandlerJSON {
    // Configura el ObjectMapper para habilitar la indentación
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT); // Habilita la indentación para JSON

    // Método para guardar ingredientes en un archivo JSON
    public static void guardarIngredientes(ArrayList<Ingrediente> ingredientes, String rutaArchivo) throws IOException {
        objectMapper.writeValue(new File(rutaArchivo), ingredientes);
    }

    // Método para cargar ingredientes desde un archivo JSON
    public static ArrayList<Ingrediente> cargarIngredientes(String rutaArchivo) throws IOException {
        return objectMapper.readValue(new File(rutaArchivo),
            objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Ingrediente.class));
    }

    // Método para guardar recetas en un archivo JSON
    public static void guardarRecetas(ArrayList<Receta> recetas, String rutaArchivo) throws IOException {
        objectMapper.writeValue(new File(rutaArchivo), recetas);
    }

    // Método para cargar recetas desde un archivo JSON
    public static ArrayList<Receta> cargarRecetas(String rutaArchivo) throws IOException {
        return objectMapper.readValue(new File(rutaArchivo),
            objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Receta.class));
    }

    // Método para guardar usuarios en un archivo JSON
    public static void guardarUsuarios(ArrayList<Usuario> usuarios, String rutaArchivo) throws IOException {
        objectMapper.writeValue(new File(rutaArchivo), usuarios);
    }

    // Método para cargar usuarios desde un archivo JSON
    public static ArrayList<Usuario> cargarUsuarios(String rutaArchivo) throws IOException {
        return objectMapper.readValue(new File(rutaArchivo),
            objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Usuario.class));
    }

    // Método para guardar el inventario en un archivo JSON
    public static void guardarInventario(Inventario inventario, String rutaArchivo) throws IOException {
        objectMapper.writeValue(new File(rutaArchivo), inventario.getInventario());
    }

    // Método para cargar el inventario desde un archivo JSON
    public static void cargarInventario(Inventario inventario, String rutaArchivo) throws IOException {
        HashMap<String, Ingrediente> inventarioData = objectMapper.readValue(new File(rutaArchivo),
            objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, Ingrediente.class));
        inventario.setInventario(inventarioData);
    }
}
