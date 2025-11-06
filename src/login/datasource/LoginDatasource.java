package login.datasource;

import config.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;
import login.models.Login;

public class LoginDatasource {

    private List<Login> logins;

    public LoginDatasource() {
        DatabaseConnection db = DatabaseConnection.getInstance();
        // Si no existe la “tabla” logins, la creamos
        if (db.get("logins") == null) {
            db.put("logins", new ArrayList<Login>());
        }
        this.logins = (List<Login>) db.get("logins");
        initializeData();
    }

    private void initializeData() {
        if (logins.isEmpty()) {
            logins.add(new Login("Administrador", "admin@u.edu.co", "admin123"));
            logins.add(new Login("Estudiante", "marcela@u.edu.co", "marce2025"));
            logins.add(new Login("Profesor", "felipe@u.edu.co", "profFelipe"));
            System.out.println("✓ Datos de ejemplo insertados en logins");
        }
    }

    // READ ALL
    public String all() {
        if (logins.isEmpty()) {
            return "No hay usuarios registrados.";
        }

        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (Login l : logins) {
            sb.append("[").append(index).append("] ")
              .append("Tipo: ").append(l.getTipoCliente()).append("\n")
              .append("Email: ").append(l.getEmail()).append("\n")
              .append("Password: ").append(l.getPassword()).append("\n\n");
            index++;
        }
        return sb.toString();
    }

    // FIND BY INDEX
    public String findByIndex(int index) {
        if (index < 0 || index >= logins.size()) {
            return "Índice inválido.";
        }
        Login l = logins.get(index);
        return "Usuario encontrado:\n" +
                "Tipo: " + l.getTipoCliente() + "\n" +
                "Email: " + l.getEmail() + "\n" +
                "Password: " + l.getPassword();
    }

    // VALIDAR LOGIN
    public boolean validarLogin(String email, String password) {
        for (Login l : logins) {
            if (l.getEmail().equals(email) && l.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // CREATE
    public String create(Login login) {
        for (Login l : logins) {
            if (l.getEmail().equals(login.getEmail())) {
                return "Error: Ya existe un usuario con ese email.";
            }
        }
        logins.add(login);
        return "Usuario agregado exitosamente: " + login.getEmail();
    }

    // UPDATE
    public String update(int index, Login login) {
        if (index < 0 || index >= logins.size()) {
            return "Índice inválido.";
        }
        logins.set(index, login);
        return "Usuario actualizado correctamente: " + login.getEmail();
    }

    // DELETE
    public String delete(int index) {
        if (index < 0 || index >= logins.size()) {
            return "Índice inválido.";
        }
        String email = logins.get(index).getEmail();
        logins.remove(index);
        return "Usuario eliminado: " + email;
    }
}
