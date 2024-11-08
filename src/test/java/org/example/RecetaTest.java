package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

<<<<<<< HEAD
public class RecetaTest {

    private Receta receta;

    @BeforeEach
    void setUp() {
        receta = new Receta("Pasta", new ArrayList<>());
    }

    @Test
    void testAgregarIngrediente() {

        Ingrediente tomate = new Ingrediente("Tomate", 2, "unidades");
        receta.agregarIngrediente(tomate);
        assertTrue(receta.getIngredientes().contains(tomate), "El ingrediente Tomate debería estar en la receta");
    }

    @Test
    void testEliminarIngrediente() {
        Ingrediente ajo = new Ingrediente("Ajo", 1, "cabeza");
        receta.agregarIngrediente(ajo);
        boolean eliminado = receta.eliminarIngrediente("Ajo");

        assertTrue(eliminado, "El ingrediente Ajo debería haber sido eliminado de la receta");
        assertFalse(receta.getIngredientes().contains(ajo), "La receta no debería contener Ajo");
    }

    @Test
    void testFromCSV() {
        String ingredientesCSV = "Tomate-2-unidades;Ajo-1-cabeza;Pasta-200-gramos";
        Receta recetaDesdeCSV = Receta.fromCSV("Salsa", ingredientesCSV);
        assertEquals("Salsa", recetaDesdeCSV.getNombre(), "El nombre de la receta debería ser 'Salsa'");
        assertEquals(3, recetaDesdeCSV.getIngredientes().size(), "La receta debería tener 3 ingredientes");
=======
class RecetaTest {
    Inventario inventario;
    Ingrediente tomate;
    Ingrediente queso;
    Receta Pizza;
    @BeforeEach
    void prepararPrueba() {
        inventario = new Inventario();
        tomate = new Ingrediente("Tomate", 5, "kg");
        queso = new Ingrediente("Queso", 3, "kg");

        ArrayList<Ingrediente> ingredientesPizza = new ArrayList<>();
        ingredientesPizza.add(tomate);
        ingredientesPizza.add(queso);

        Pizza = new Receta("Pizza", ingredientesPizza);
    }
    @Test
    void getNombre() {
        assertEquals("Pizza",Pizza.getNombre());
        System.out.println("La prueba se realizo correctamente...");
    }

    @Test
    void verificarDisponibilidad() {
        inventario.agregarIngrediente(queso);
        inventario.agregarIngrediente(tomate);

        assertTrue(inventario.verificarDisponibilidad(Pizza));
        System.out.println("La prueba se realizo correctamente...");

    }

    @Test
    void getIngredientes() {
        Ingrediente[] ingredientes = new Ingrediente[2];
        ingredientes[0] = tomate;
        ingredientes[1] = queso;

        assertArrayEquals(ingredientes,Pizza.getIngredientes());

        System.out.println("La prueba se realizo correctamente...");
>>>>>>> main
    }

    @Test
    void testMostrarIngredientes() {
        receta.agregarIngrediente(new Ingrediente("Pasta", 200, "gramos"));
        receta.agregarIngrediente(new Ingrediente("Salsa", 100, "ml"));
        
        receta.mostrarIngredientes();
    }

    @Test
    void testSetIngredientes() {
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        ingredientes.add(new Ingrediente("Queso", 50, "gramos"));
        ingredientes.add(new Ingrediente("Tomate", 1, "unidad"));

        receta.setIngredientes(ingredientes);



        assertEquals(ingredientes, receta.getIngredientes(), "Los ingredientes deberian coincidir");
    }
}
