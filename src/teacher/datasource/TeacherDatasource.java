package teacher.datasource;

import java.sql.*;
import teacher.models.Teacher;
import config.DatabaseConnection;

public class TeacherDatasource {

    private Connection connection;

    public TeacherDatasource() {
        this.connection = DatabaseConnection.getInstance().getConnection();
        initializeData();
    }

    private void initializeData() {
        try {
            String checkQuery = "SELECT COUNT(*) as total FROM teachers";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(checkQuery);
            
            if (rs.next() && rs.getInt("total") == 0) {
                create(new Teacher(1, "Carlos Méndez", "carlos.mendez@u.edu.co", "Matemáticas"));
                create(new Teacher(2, "Ana García", "ana.garcia@u.edu.co", "Programación"));
                create(new Teacher(3, "Luis Torres", "luis.torres@u.edu.co", "Base de Datos"));
                System.out.println("✓ Datos de ejemplo insertados en teachers");
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error al inicializar datos de teachers: " + e.getMessage());
        }
    }

    // READ ALL
    public String all() {
        try {
            String query = "SELECT * FROM teachers ORDER BY id";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            StringBuilder sb = new StringBuilder();
            int index = 0;
            
            while (rs.next()) {
                sb.append("[").append(index).append("] ")
                  .append(rs.getString("name"))
                  .append(" | ID: ").append(rs.getInt("id"))
                  .append(" | Email: ").append(rs.getString("email"))
                  .append(" | Materia: ").append(rs.getString("subject"))
                  .append("\n");
                index++;
            }
            
            rs.close();
            stmt.close();
            
            return sb.length() > 0 ? sb.toString() : "No hay profesores registrados.";
            
        } catch (SQLException e) {
            return "Error al listar profesores: " + e.getMessage();
        }
    }

    // READ BY INDEX
    public String findByIndex(int index) {
        try {
            String queryIds = "SELECT id FROM teachers ORDER BY id LIMIT 1 OFFSET ?";
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
            
            String query = "SELECT * FROM teachers WHERE id = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String result = "Profesor encontrado: " + rs.getString("name") 
                       + " | ID: " + rs.getInt("id")
                       + " | Email: " + rs.getString("email")
                       + " | Materia: " + rs.getString("subject");
                rs.close();
                pstmt.close();
                return result;
            }
            
            rs.close();
            pstmt.close();
            return "Profesor no encontrado.";
            
        } catch (SQLException e) {
            return "Error al buscar profesor: " + e.getMessage();
        }
    }

    // CREATE
    public String create(Teacher teacher) {
        try {
            String query = "INSERT INTO teachers (id, name, email, subject) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            
            pstmt.setInt(1, teacher.getId());
            pstmt.setString(2, teacher.getName());
            pstmt.setString(3, teacher.getEmail());
            pstmt.setString(4, teacher.getSubject());
            
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            
            if (rowsAffected > 0) {
                return "Profesor creado: " + teacher.getName() + " (ID: " + teacher.getId() + ")";
            }
            return "No se pudo crear el profesor.";
            
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                return "Error: Ya existe un profesor con ese ID o email.";
            }
            return "Error al crear profesor: " + e.getMessage();
        }
    }

    // UPDATE
    public String update(int index, Teacher teacherIn) {
        try {
            String queryIds = "SELECT id FROM teachers ORDER BY id LIMIT 1 OFFSET ?";
            PreparedStatement pstmt = connection.prepareStatement(queryIds);
            pstmt.setInt(1, index);
            ResultSet rsId = pstmt.executeQuery();
            
            if (!rsId.next()) {
                rsId.close();
                pstmt.close();
                return "Índice inválido para actualizar.";
            }
            
            int oldId = rsId.getInt("id");
            rsId.close();
            pstmt.close();
            
            String query = "UPDATE teachers SET id = ?, name = ?, email = ?, subject = ? WHERE id = ?";
            pstmt = connection.prepareStatement(query);
            
            pstmt.setInt(1, teacherIn.getId());
            pstmt.setString(2, teacherIn.getName());
            pstmt.setString(3, teacherIn.getEmail());
            pstmt.setString(4, teacherIn.getSubject());
            pstmt.setInt(5, oldId);
            
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            
            if (rowsAffected > 0) {
                return "Profesor actualizado: " + teacherIn.getName() + " (ID: " + teacherIn.getId() + ")";
            }
            return "No se pudo actualizar el profesor.";
            
        } catch (SQLException e) {
            return "Error al actualizar profesor: " + e.getMessage();
        }
    }

    // DELETE
    public String delete(int index) {
        try {
            String queryIds = "SELECT id, name FROM teachers ORDER BY id LIMIT 1 OFFSET ?";
            PreparedStatement pstmt = connection.prepareStatement(queryIds);
            pstmt.setInt(1, index);
            ResultSet rsId = pstmt.executeQuery();
            
            if (!rsId.next()) {
                rsId.close();
                pstmt.close();
                return "Índice inválido para eliminar.";
            }
            
            int id = rsId.getInt("id");
            String name = rsId.getString("name");
            rsId.close();
            pstmt.close();
            
            String query = "DELETE FROM teachers WHERE id = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            
            if (rowsAffected > 0) {
                return "Profesor eliminado: " + name + " (ID: " + id + ")";
            }
            return "No se pudo eliminar el profesor.";
            
        } catch (SQLException e) {
            return "Error al eliminar profesor: " + e.getMessage();
        }
    }
}