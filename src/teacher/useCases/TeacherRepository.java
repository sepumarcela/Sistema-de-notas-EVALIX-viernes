package teacher.repositories;

import teacher.models.Teacher;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository {

    // Simulación de base de datos en memoria
    private List<Teacher> teachers = new ArrayList<>();

    // READ ALL
    public String all() {
        if (teachers.isEmpty()) {
            return "No hay profesores registrados.";
        }
        StringBuilder result = new StringBuilder("Lista de profesores:\n");
        for (int i = 0; i < teachers.size(); i++) {
            result.append(i).append(": ").append(teachers.get(i).getName()).append("\n");
        }
        return result.toString();
    }

    // READ BY INDEX
    public String findByIndex(int index) {
        if (index < 0 || index >= teachers.size()) {
            return "Índice fuera de rango.";
        }
        Teacher t = teachers.get(index);
        return "Profesor encontrado: " + t.getName();
    }

    // CREATE
    public void create(Teacher teacher) {
        teachers.add(teacher);
    }

    // UPDATE
    public void update(int index, Teacher teacher) {
        if (index < 0 || index >= teachers.size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
        teachers.set(index, teacher);
    }

    // DELETE
    public void delete(int index) {
        if (index < 0 || index >= teachers.size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
        teachers.remove(index);
    }
}
