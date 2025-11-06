package teacher.datasource;

import config.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;
import teacher.models.Teacher;

public class TeacherDatasource {

    private List<Teacher> teachers;

    public TeacherDatasource() {
        DatabaseConnection db = DatabaseConnection.getInstance();

        if (db.get("teachers") == null) {
            db.put("teachers", new ArrayList<Teacher>());
        }

        this.teachers = (List<Teacher>) db.get("teachers");
        initializeData();
    }

    private void initializeData() {
        if (teachers.isEmpty()) {
            teachers.add(new Teacher(1, "Carlos Méndez", "carlos.mendez@u.edu.co", "Matemáticas"));
            teachers.add(new Teacher(2, "Laura Torres", "laura.torres@u.edu.co", "Literatura"));
            teachers.add(new Teacher(3, "Andrés Ruiz", "andres.ruiz@u.edu.co", "Historia"));
            System.out.println("✓ Datos de ejemplo insertados en teachers");
        }
    }

    // READ ALL
    public String all() {
        if (teachers.isEmpty()) return "No hay profesores registrados.";

        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Teacher t : teachers) {
            sb.append("[").append(i).append("] ")
              .append(t.getName())
              .append(" | ID: ").append(t.getId())
              .append(" | Email: ").append(t.getEmail())
              .append(" | Asignatura: ").append(t.getSubject())
              .append("\n");
            i++;
        }
        return sb.toString();
    }

    // READ BY INDEX
    public String findByIndex(int index) {
        if (index < 0 || index >= teachers.size()) return "Índice inválido.";
        Teacher t = teachers.get(index);
        return "Profesor encontrado: " + t.getName() +
               " | ID: " + t.getId() +
               " | Email: " + t.getEmail() +
               " | Asignatura: " + t.getSubject();
    }

    // CREATE
    public String create(Teacher teacher) {
        for (Teacher t : teachers) {
            if (t.getId() == teacher.getId() || t.getEmail().equalsIgnoreCase(teacher.getEmail())) {
                return "Error: Ya existe un profesor con ese ID o email.";
            }
        }
        teachers.add(teacher);
        return "Profesor agregado: " + teacher.getName() + " (ID: " + teacher.getId() + ")";
    }

    // UPDATE
    public String update(int index, Teacher updated) {
        if (index < 0 || index >= teachers.size()) return "Índice inválido.";
        teachers.set(index, updated);
        return "Profesor actualizado: " + updated.getName() + " (ID: " + updated.getId() + ")";
    }

    // DELETE
    public String delete(int index) {
        if (index < 0 || index >= teachers.size()) return "Índice inválido.";
        Teacher removed = teachers.remove(index);
        return "Profesor eliminado: " + removed.getName() + " (ID: " + removed.getId() + ")";
    }
}
