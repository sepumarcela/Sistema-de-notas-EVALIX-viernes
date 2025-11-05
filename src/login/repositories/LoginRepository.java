package login.repositories; // ruta de la case

import login.models.Login;
import login.datasource.LoginDatasource; // Importacion de la clase Task

// El contenedor del enrutador de las acciones.
public class LoginRepository {

    public LoginDatasource loginDataSource; // Declaracion de la clase TaskDatasource se convierte como tipo objecto

    // Constructor
    public LoginRepository() {
        this.loginDataSource = new LoginDatasource(); // Instancia de la clase TaskUseCase
    }

    // en ruta la accion de obtener todos
    public String all(){
        return this.loginDataSource.all(); // llamar todas las tareas.
    }

    // en ruta la accion de obtener por indice
    public String findByIndex(int index){
        return this.loginDataSource.findByIndex(index); // llamar la accion de buscar por indice.
    }

    // en ruta la accion de crear
    public String create(String tipoCliente, String email, String password){
         return this.loginDataSource. create(new Login(tipoCliente, email, password)); // llamar la accion de crear tarea.
    }

    // en ruta la accion de actualizar
    public String update(int index, String tipoCliente, String email, String password){
        return this.loginDataSource. update(index, new Login(tipoCliente, email, password)); // llamar la accion de actualizar tarea.
    }

    // en ruta la accion de eliminar
    public String delete (int index){
        return this.loginDataSource.delete(index);
    }
}