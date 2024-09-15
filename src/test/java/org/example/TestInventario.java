package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class TestInventario {


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
    void agregarIngrediente() {




    }

    @Test
    void verificarDisponibilidad() {
        inventario.agregarIngrediente(tomate);
        inventario.agregarIngrediente(queso);
        assertTrue(inventario.verificarDisponibilidad(Pizza));
        System.out.println("La prueba se realizo correctamente...");

    }

    @Test
    void utilizarIngredientes() {
    }

    @Test
    void mostrarInventario() {
    }
}

