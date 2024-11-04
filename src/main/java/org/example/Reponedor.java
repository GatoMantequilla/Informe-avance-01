package org.example;

public class Reponedor extends Usuario {
   private String horarioReposicion;

   public Reponedor(String nombre, String clave, int id, String horarioReposicion) {
       super(nombre, clave, id);
       this.horarioReposicion = horarioReposicion;
   }

   public String getHorarioReposicion() {
       return horarioReposicion;
   }

   public void setHorarioReposicion(String horarioReposicion) {
       this.horarioReposicion = horarioReposicion;
   }

   public void agregarIngrediente(Inventario inventario, String nombre, double cantidad, String unidad) {
    Ingrediente nuevoIngrediente = new Ingrediente(nombre, cantidad, unidad); // Crea el nuevo ingrediente con los datos recibidos
    inventario.agregarIngrediente(nuevoIngrediente); // Agrega o actualiza el ingrediente en el inventario
    System.out.println("Ingrediente agregado o actualizado exitosamente en el inventario."); // Confirma la operación
    }


    public void actualizarCantidadIngrediente(Inventario inventario, String nombre, double nuevaCantidad) {
        Ingrediente ingrediente = inventario.buscarIngrediente(nombre); // Busca el ingrediente por nombre
        if (ingrediente != null) { // Si el ingrediente existe en el inventario
            ingrediente.setCantidad(nuevaCantidad); // Actualiza la cantidad del ingrediente
            System.out.println("Cantidad actualizada exitosamente."); // Confirma la actualización
        } else {
            System.out.println("Ingrediente no encontrado en el inventario."); // Informa si el ingrediente no está en el inventario
        }
    }


    public boolean eliminarIngrediente(Inventario inventario, String nombreIngrediente) {
        boolean eliminado = inventario.eliminarIngrediente(nombreIngrediente); // Asumimos que este método devuelve un boolean
        if (eliminado) {
            System.out.println("Ingrediente eliminado exitosamente del inventario.");
        } else {
            System.out.println("Ingrediente no encontrado en el inventario.");
        }
        return eliminado;
    }
    
    
   @Override
   public String toString() {
       return super.toString() + ", horarioReposicion='" + horarioReposicion + "'";
   }
}
