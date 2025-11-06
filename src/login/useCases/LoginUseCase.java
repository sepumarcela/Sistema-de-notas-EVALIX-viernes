package login.useCases;

import login.models.Login;
import login.repositories.LoginRepository;

public class LoginUseCase {

    private LoginRepository loginRepository;

    // Constructor
    public LoginUseCase() {
        this.loginRepository = new LoginRepository();
    }

    // READ ALL
    public String all() {
        try {
            return this.loginRepository.all();
        } catch (Exception e) {
            return "Error al obtener los logins: " + e.getMessage();
        }
    }

    // READ BY INDEX
    public String findByIndex(int index) {
        try {
            return this.loginRepository.findByIndex(index);
        } catch (Exception e) {
            return "No ha sido posible acceder.";
        }
    }

    // CREATE
    public String create(Login login) {
        try {
            return this.loginRepository.create(
                login.getTipoCliente(),
                login.getEmail(),
                login.getPassword()
            );
        } catch (Exception e) {
            return "Ha ocurrido un error, por favor inténtelo de nuevo: " + e.getMessage();
        }
    }

    // UPDATE
    public String update(int index, Login login) {
        try {
            return this.loginRepository.update(
                index,
                login.getTipoCliente(),
                login.getEmail(),
                login.getPassword()
            );
        } catch (Exception e) {
            return "Ha ocurrido un error, por favor inténtelo de nuevo: " + e.getMessage();
        }
    }

    // DELETE
    public String delete(int index) {
        try {
            return this.loginRepository.delete(index);
        } catch (Exception e) {
            return "No ha sido posible eliminar el login.";
        }
    }
}