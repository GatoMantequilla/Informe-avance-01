package org.example;

public class Usuario {
   private String nombre;
   private String clave;
   private int id;

   public Usuario(String nombre, String clave, int id) {
       this.nombre = nombre;
       this.clave = clave;
       this.id = id;
   }

   public String getNombre() {
       return nombre;
   }

   public void setNombre(String nombre) {
       this.nombre = nombre;
   }

   public String getClave() {
       return clave;
   }

   public void setClave(String clave) {
       this.clave = clave;
   }

   public int getId() {
       return id;
   }

   public void setId(int id) {
       this.id = id;
   }

   @Override
   public String toString() {
       return "Usuario{" +
               "nombre='" + nombre + '\'' +
               ", id=" + id +
               '}';
   }
}
