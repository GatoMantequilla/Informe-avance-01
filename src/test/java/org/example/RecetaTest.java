package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
    }
}