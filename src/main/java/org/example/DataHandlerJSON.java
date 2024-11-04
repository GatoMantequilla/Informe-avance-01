package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DataHandlerJSON {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void guardarIngredientes(ArrayList<Ingrediente> ingredientes, String rutaArchivo) throws IOException {
        objectMapper.writeValue(new File(rutaArchivo), ingredientes);
    }

    public static ArrayList<Ingrediente> cargarIngredientes(String rutaArchivo) throws IOException {
        return objectMapper.readValue(new File(rutaArchivo),
            objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Ingrediente.class));
    }

    public static void guardarRecetas(ArrayList<Receta> recetas, String rutaArchivo) throws IOException {
        objectMapper.writeValue(new File(rutaArchivo), recetas);
    }

    public static ArrayList<Receta> cargarRecetas(String rutaArchivo) throws IOException {
        return objectMapper.readValue(new File(rutaArchivo),
            objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Receta.class));
    }

    public static void guardarUsuarios(ArrayList<Usuario> usuarios, String rutaArchivo) throws IOException {
        objectMapper.writeValue(new File(rutaArchivo), usuarios);
    }

    public static ArrayList<Usuario> cargarUsuarios(String rutaArchivo) throws IOException {
        return objectMapper.readValue(new File(rutaArchivo),
            objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Usuario.class));
    }
}
