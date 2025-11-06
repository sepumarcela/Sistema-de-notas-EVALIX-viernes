package login.datasource;

import config.DatabaseConnection;
import java.sql.*;
import login.models.Login;

public class LoginDatasource {

    private Connection connection;

    public LoginDatasource() {
        this.connection = DatabaseConnection.getInstance().getConnection();
        initializeData();
    }

    private void initializeData() {
        try {
            String checkQuery = "SELECT COUNT(*) as total FROM logins";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(checkQuery);
            
            if (rs.next() && rs.getInt("total") == 0) {
                create(new Login("Administrador", "admin@u.edu.co", "admin123"));
                create(new Login("Estudiante", "marcela@u.edu.co", "marce2025"));
                create(new Login("Profesor", "felipe@u.edu.co", "profFelipe"));
                System.out.println("✓ Datos de ejemplo insertados en logins");
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error al inicializar datos de logins: " + e.getMessage());
        }
    }

    // READ ALL
    public String all() {
        try {
            String query = "SELECT * FROM logins ORDER BY id";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            StringBuilder sb = new StringBuilder();
            int index = 0;
            
            while (rs.next()) {
                sb.append("[").append(index).append("] ")
                  .append("Tipo: ").append(rs.getString("tipo_cliente")).append("\n")
                  .append("Email: ").append(rs.getString("email")).append("\n")
                  .append("Password: ").append(rs.getString("password")).append("\n\n");
                index++;
            }
            
            rs.close();
            stmt.close();
            
            return sb.length() > 0 ? sb.toString() : "No hay usuarios registrados.";
            
        } catch (SQLException e) {
            return "Error al listar logins: " + e.getMessage();
        }
    }

    // READ BY INDEX
    public String findByIndex(int index) {
        try {
            String queryIds = "SELECT id FROM logins ORDER BY id LIMIT 1 OFFSET ?";
            PreparedStatement pstmt = connection.prepareStatement(queryIds);
            pstmt.setInt(1, index);
            ResultSet rsId = pstmt.executeQuery();
            
            if (!rsId.next()) {
                rsId.close();
                pstmt.close();
                return "Índice inválido.";
            }
            
            int id = rsId.getInt("id");
            rsId.close();
            pstmt.close();
            
            String query = "SELECT * FROM logins WHERE id = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Usuario encontrado:\n")
                  .append("Tipo: ").append(rs.getString("tipo_cliente")).append("\n")
                  .append("Email: ").append(rs.getString("email")).append("\n")
                  .append("Password: ").append(rs.getString("password"));
                
                rs.close();
                pstmt.close();
                return sb.toString();
            }
            
            rs.close();
            pstmt.close();
            return "Usuario no encontrado.";
            
        } catch (SQLException e) {
            return "Error al buscar usuario: " + e.getMessage();
        }
    }

    // VALIDAR LOGIN (autenticación)
    public boolean validarLogin(String email, String password) {
        try {
            String query = "SELECT * FROM logins WHERE email = ? AND password = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            boolean valid = rs.next();
            
            rs.close();
            pstmt.close();
            
            return valid;
            
        } catch (SQLException e) {
            System.err.println("Error al validar login: " + e.getMessage());
            return false;
        }
    }

    // CREATE
    public String create(Login login) {
        try {
            String query = "INSERT INTO logins (tipo_cliente, email, password) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            
            pstmt.setString(1, login.getTipoCliente());
            pstmt.setString(2, login.getEmail());
            pstmt.setString(3, login.getPassword());
            
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            
            if (rowsAffected > 0) {
                return "Usuario agregado exitosamente: " + login.getEmail();
            }
            return "No se pudo agregar el usuario.";
            
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                return "Error: Ya existe un usuario con ese email.";
            }
            return "Error al agregar usuario: " + e.getMessage();
        }
    }

    // UPDATE
    public String update(int index, Login login) {
        try {
            String queryIds = "SELECT id FROM logins ORDER BY id LIMIT 1 OFFSET ?";
            PreparedStatement pstmt = connection.prepareStatement(queryIds);
            pstmt.setInt(1, index);
            ResultSet rsId = pstmt.executeQuery();
            
            if (!rsId.next()) {
                rsId.close();
                pstmt.close();
                return "Índice inválido.";
            }
            
            int id = rsId.getInt("id");
            rsId.close();
            pstmt.close();
            
            String query = "UPDATE logins SET tipo_cliente = ?, email = ?, password = ? WHERE id = ?";
            pstmt = connection.prepareStatement(query);
            
            pstmt.setString(1, login.getTipoCliente());
            pstmt.setString(2, login.getEmail());
            pstmt.setString(3, login.getPassword());
            pstmt.setInt(4, id);
            
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            
            if (rowsAffected > 0) {
                return "Usuario actualizado correctamente: " + login.getEmail();
            }
            return "No se pudo actualizar el usuario.";
            
        } catch (SQLException e) {
            return "Error al actualizar usuario: " + e.getMessage();
        }
    }

    // DELETE
    public String delete(int index) {
        try {
            String queryIds = "SELECT id, email FROM logins ORDER BY id LIMIT 1 OFFSET ?";
            PreparedStatement pstmt = connection.prepareStatement(queryIds);
            pstmt.setInt(1, index);
            ResultSet rsId = pstmt.executeQuery();
            
            if (!rsId.next()) {
                rsId.close();
                pstmt.close();
                return "Índice inválido.";
            }
            
            int id = rsId.getInt("id");
            String email = rsId.getString("email");
            rsId.close();
            pstmt.close();
            
            String query = "DELETE FROM logins WHERE id = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            
            if (rowsAffected > 0) {
                return "Usuario eliminado: " + email;
            }
            return "No se pudo eliminar el usuario.";
            
        } catch (SQLException e) {
            return "Error al eliminar usuario: " + e.getMessage();
        }
    }
}