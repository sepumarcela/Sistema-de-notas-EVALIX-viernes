package login.useCases;

import java.util.ArrayList;
import java.util.List;
import login.models.Login;

// Contenedor de metodos y atributos
public class LoginUseCase { // Clase de las acciones a realizar el Use Case

    // Propiedad
    private final List<Login> logins; // Definicion Lista en memoria

    // Constructor
    public LoginUseCase() {
        this.logins = new ArrayList<>(); // Inicializacion - Preparar el uso de la lista.
    }

    // Read
    public String all(){
        try {
            StringBuilder result = new StringBuilder(); // preparando la concatenacion de los strings.
            for (int i = 0; i < logins.size(); i++) { // Se procede a concatenar las tareas al string.
                Login login = logins.get(i); // Se obtiene la tarea.
                result.append("Usuario ")
                .append(i)
                .append(": ")
                .append(login.getEmail())
                .append(" Tipo: ")
                .append(login.getTipoCliente())
                .append("\n"); // Concatenar la tarea al string.
            }

            return result.toString(); // Retornando la concatenacion de las tareas.
            
        } catch (Exception e) {
           return "Error al obtener los logins.";
        }
    }

    // Read by index
    public String findByIndex(int index) {
    try {
        Login loginFound = this.logins.get(index);
        return "Email Encontrado: " + loginFound.getEmail() + " | Selecciona una opción: " + loginFound.getPassword();
        }
        catch (Exception e) {
            return "no ha sido posible acceder.";
        }
    }

    // Create
    public String create(Login login) {
        try {
            this.logins.add(login); // Uso de la lista
            return "Email Encontrado:" + login.getEmail() + " Selecciona una opción:" + login.getPassword();
        }
        catch (Exception e) {
            return "Ha ocurrido un error, por favor intentelo nuevo";
        }
    }

    // Update
    public String update(int index, Login loginIn) {
        try {
        Login loginFound = this.logins.get(index);
        loginFound.setEmail(loginIn.getEmail());
        loginFound.setPassword(loginIn.getPassword());
        loginFound.setTipoCliente(loginIn.getTipoCliente());
             
            return "Email Actualizado:" + loginFound.getEmail() + " Selecciona una opción:" + loginFound.getTipoCliente();
        }
        catch (Exception e) {
            return "Ha ocurrido un error, por favor intentelo nuevo";
        }
    }


    // Delete
    public String delete(int index){
       try {
            logins.remove(index);
            return "Se ha eliminado la tarea correctamente.";
       } catch (Exception e) {
            return "no ha sido posible eliminar la tarea.";
       }
    }
}