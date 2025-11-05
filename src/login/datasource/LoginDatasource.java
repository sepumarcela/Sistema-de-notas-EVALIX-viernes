package login.datasource;

import java.util.ArrayList;
import java.util.List;
import login.models.Login;

public class LoginDatasource {

    // Lista de logins simulando una base de datos
    private final List<Login> logins;

    // Constructor
    public LoginDatasource() {
        this.logins = new ArrayList<>();

        // Datos de ejemplo
        this.logins.add(new Login("Administrador", "admin@u.edu.co", "admin123"));
        this.logins.add(new Login("Estudiante", "marcela@u.edu.co", "marce2025"));
        this.logins.add(new Login("Profesor", "felipe@u.edu.co", "profFelipe"));
    }

    // READ ALL
    public String all() {
        try {
            if (logins.isEmpty()) return "No hay usuarios registrados.";

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < logins.size(); i++) {
                Login l = logins.get(i);
                sb.append("[").append(i).append("] ")
                  .append("Tipo: ").append(l.getTipoCliente()).append("\n")
                  .append("Email: ").append(l.getEmail()).append("\n")
                  .append("Password: ").append(l.getPassword()).append("\n\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return "Error al listar logins: " + e.getMessage();
        }
    }

    // READ BY INDEX
    public String findByIndex(int index) {
        try {
            if (index < 0 || index >= logins.size()) return "Índice inválido.";

            Login l = logins.get(index);

            StringBuilder sb = new StringBuilder();
            sb.append("Usuario encontrado:\n")
            .append("Tipo: ").append(l.getTipoCliente()).append("\n")
            .append("Email: ").append(l.getEmail()).append("\n")
            .append("Password: ").append(l.getPassword());
            return sb.toString();

        } catch (Exception e) {
            return "Error al buscar usuario: " + e.getMessage();
        }
    }

    // MÉTODO OPCIONAL: validar login (autenticación)
    public boolean validarLogin(String email, String password) {
        for (Login l : logins) {
            if (l.getEmail().equalsIgnoreCase(email) && l.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

   // CREATE
public String create(Login login) {
    try {
        logins.add(login);
        return "Usuario agregado exitosamente: " + login.getEmail();
    } catch (Exception e) {
        return "Error al agregar usuario: " + e.getMessage();
    }
}

// UPDATE
public String update(int index, Login login) {
    try {
        if (index < 0 || index >= logins.size()) {
            return "Índice inválido.";
        }
        logins.set(index, login);
        return "Usuario actualizado correctamente: " + login.getEmail();
    } catch (Exception e) {
        return "Error al actualizar usuario: " + e.getMessage();
    }
}

// DELETE
public String delete(int index) {
    try {
        if (index < 0 || index >= logins.size()) {
            return "Índice inválido.";
        }
        Login eliminado = logins.remove(index);
        return "Usuario eliminado: " + eliminado.getEmail();
    } catch (Exception e) {
        return "Error al eliminar usuario: " + e.getMessage();
    }
}
 
}
