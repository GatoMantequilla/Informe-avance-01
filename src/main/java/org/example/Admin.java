package org.example;

import java.util.ArrayList; 

public class Admin extends Usuario { 

    public Admin(String nombre, String clave, int id) { // Constructor de Admin con nombre, clave e ID
        super(nombre, clave, id); // Llama al constructor de Usuario
    }

    public void crearUsuario(String nombre, String clave, String tipo, int id, ArrayList<Usuario> usuarios) { // Método para crear un usuario
        Usuario nuevoUsuario; // Declara una variable para el nuevo usuario
        switch (tipo.toLowerCase()) { // Verifica el tipo de usuario a crear
            case "chef": // Si el tipo es chef
                nuevoUsuario = new Chef(nombre, clave, id, "Cocina Default"); // Crea un Chef
                break;
            case "reponedor": // Si el tipo es reponedor
                nuevoUsuario = new Reponedor(nombre, clave, id, "Horario Default"); // Crea un Reponedor
                break;
            default: // En cualquier otro caso
                nuevoUsuario = new Usuario(nombre, clave, id); // Crea un Usuario genérico
                break;
        }
        usuarios.add(nuevoUsuario); // Agrega el nuevo usuario a la lista
        System.out.println("Usuario creado exitosamente: " + nombre + " (" + tipo + ")"); // Confirma la creación
    }

    public void editarUsuario(int id, String nuevoNombre, String nuevaClave, ArrayList<Usuario> usuarios) {
        for (Usuario usuario : usuarios) { // Itera por cada usuario en la lista
            if (usuario.getId() == id) { // Verifica si el ID coincide
                if (nuevoNombre != null && !nuevoNombre.isBlank()) { // Si el nuevo nombre no está vacío o nulo
                    usuario.setNombre(nuevoNombre); // Actualiza el nombre del usuario
                }
                if (nuevaClave != null && !nuevaClave.isBlank()) { // Si la nueva clave no está vacía o nula
                    usuario.setClave(nuevaClave); // Actualiza la clave del usuario
                }
                System.out.println("Usuario editado exitosamente."); // Confirma la edición
                return;
            }
        }
        System.out.println("Usuario con ID " + id + " no encontrado."); // Mensaje si el usuario no existe
    }
    
    public void eliminarUsuario(int id, boolean confirmacion, ArrayList<Usuario> usuarios) {
        for (int i = 0; i < usuarios.size(); i++) { // Recorre la lista de usuarios
            if (usuarios.get(i).getId() == id) { // Si encuentra el ID en la lista
                System.out.println("Eliminando usuario: " + usuarios.get(i).getNombre()); // Muestra el usuario a eliminar
                if (confirmacion) { // Si la confirmación es verdadera
                    usuarios.remove(i); // Elimina el usuario de la lista
                    System.out.println("Usuario eliminado exitosamente."); // Confirma la eliminación
                } else {
                    System.out.println("Eliminación cancelada."); // Cancela la eliminación
                }
                return;
            }
        }
        System.out.println("Usuario con ID " + id + " no encontrado."); // Mensaje si no encuentra el usuario
    }
    
    public void listarUsuarios(ArrayList<Usuario> usuarios) { // Método para listar todos los usuarios
        System.out.println("Lista de usuarios:"); // Encabezado de la lista
        if (usuarios.isEmpty()) { // Verifica si la lista está vacía
            System.out.println("No hay usuarios en el sistema."); // Informa si no hay usuarios
        } else {
            for (Usuario usuario : usuarios) { // Itera sobre la lista de usuarios
                System.out.println("- ID: " + usuario.getId() + ", Nombre: " + usuario.getNombre() + ", Tipo: " + usuario.getClass().getSimpleName()); // Muestra los detalles del usuario
            }
        }
    }
}
