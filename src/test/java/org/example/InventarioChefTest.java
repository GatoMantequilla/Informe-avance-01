package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class InventarioChefTest {

    private InventarioChef inventarioChef;
    private Receta receta;

    @BeforeEach
    void setUp() {
        inventarioChef = new InventarioChef();
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        ingredientes.add(new Ingrediente("Tomate", 2, "unidades"));
        receta = new Receta("Salsa de Tomate", ingredientes);
    }

    @Test
    void testAgregarReceta() {
        inventarioChef.agregarReceta(receta);
        assertTrue(inventarioChef.recetas.contains(receta));
    }

    @Test
    void testEditarReceta() {
        inventarioChef.agregarReceta(receta);
        ArrayList<Ingrediente> nuevosIngredientes = new ArrayList<>();
        nuevosIngredientes.add(new Ingrediente("Cebolla", 1, "unidad"));
        boolean editado = inventarioChef.editarReceta("Salsa de Tomate", nuevosIngredientes);
        assertTrue(editado);
        assertEquals(nuevosIngredientes, receta.getIngredientes());

    }

    @Test
    void testEliminarReceta() {
        inventarioChef.agregarReceta(receta);
        boolean eliminado = inventarioChef.eliminarReceta("Salsa de Tomate", true);
        assertTrue(eliminado);
        assertFalse(inventarioChef.recetas.contains(receta));
    }

    @Test
    void testVerificarDisponibilidad() {
        inventarioChef.agregarReceta(receta);
        inventarioChef.inventario.agregarIngrediente(new Ingrediente("Tomate", 5, "unidades"));
        assertTrue(inventarioChef.verificarDisponibilidad("Salsa de Tomate"));
    }
}
