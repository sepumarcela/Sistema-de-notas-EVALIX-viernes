package login.controllers; // ruta de la case

import login.models.Login; // Importacion de la clase TaskUseCase
import login.useCases.LoginUseCase; // Importacion de la clase Task

// El contenedor del enrutador de las acciones.
public class LoginController {

    public LoginUseCase loginUseCase; // Declaracion de la clase TaskUseCase se convierte como tipo objecto

    // Constructor
    public LoginController() {
        this.loginUseCase = new LoginUseCase(); // Instancia de la clase TaskUseCase
    }

    // en ruta la accion de obtener todos
    public String all(){
        return this.loginUseCase.all(); // llamar todas las tareas.
    }

    // en ruta la accion de obtener por indice
    public String findByIndex(int index){
        return this.loginUseCase.findByIndex(index); // llamar la accion de buscar por indice.
    }

    // en ruta la accion de crear
    public String create(String tipoCliente, String email, String password){
        return this.loginUseCase.create(new Login(tipoCliente, email, password)); // llamar la accion de crear tarea.
    }

    // en ruta la accion de actualizar
    public String update(int index, String tipoCliente, String email, String password){
        return this.loginUseCase.update(index, new Login(tipoCliente, email, password)); // llamar la accion de actualizar tarea.
    }

    // en ruta la accion de eliminar
    public String delete (int index){
        return this.loginUseCase.delete(index);
    }
}