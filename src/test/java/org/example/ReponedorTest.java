package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReponedorTest {

    private Reponedor reponedor;
    private Inventario inventario;

    @BeforeEach
    void setUp() {
        reponedor = new Reponedor("Carlos", "clave123", 1, "09:00 - 17:00");
        inventario = new Inventario();
    }

    @Test
    void testActualizarCantidadIngrediente() {
        Ingrediente ingrediente = new Ingrediente("Azúcar", 5.0, "kg");
        inventario.agregarIngrediente(ingrediente);
        reponedor.actualizarCantidadIngrediente(inventario, "Azúcar", 10.0);
        

        Ingrediente ingredienteActualizado = inventario.buscarIngrediente("Azúcar");
        assertNotNull(ingredienteActualizado, "El ingrediente debería existir en el inventario");
        assertEquals(10.0, ingredienteActualizado.getCantidad(), "La cantidad de azúcar debería ser 10 kg");
    }

    @Test
    void testAgregarIngrediente() {
    
        reponedor.agregarIngrediente(inventario, "Harina", 2.5, "kg");

        Ingrediente ingrediente = inventario.buscarIngrediente("Harina");
        assertNotNull(ingrediente, "El ingrediente Harina debería estar en el inventario");
        assertEquals(2.5, ingrediente.getCantidad(), "La cantidad de harina debería ser 2.5 kg");
        assertEquals("kg", ingrediente.getUnidad(), "La unidad debería ser kg");
    }

    //assertNotNull verificar que un objeto no sea null

    @Test
    void testEliminarIngrediente() {
        Ingrediente ingrediente = new Ingrediente("Sal", 1.0, "kg");
        inventario.agregarIngrediente(ingrediente);
        
        boolean eliminado = reponedor.eliminarIngrediente(inventario, "Sal");
        

        assertTrue(eliminado, "El ingrediente Sal debería haber sido eliminado del inventario");
        assertNull(inventario.buscarIngrediente("Sal"), "El ingrediente Sal no debería estar en el inventario");
    }
}
