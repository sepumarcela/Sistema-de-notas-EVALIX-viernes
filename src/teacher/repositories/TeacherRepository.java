package teacher.repositories;

import config.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;
import teacher.models.Teacher;

/**
 * Simula el repositorio que accede a la base de datos ficticia
 */
public class TeacherRepository {

    private final String TABLE_NAME = "teachers";
    private DatabaseConnection db;

    public TeacherRepository() {
        this.db = DatabaseConnection.getInstance();

        // Si no existe la “tabla”, se crea
        if (this.db.get(TABLE_NAME) == null) {
            this.db.put(TABLE_NAME, new ArrayList<Teacher>());
            System.out.println("Tabla ficticia 'teachers' creada.");
        }
    }

    @SuppressWarnings("unchecked")
    private List<Teacher> getTable() {
        return (List<Teacher>) db.get(TABLE_NAME);
    }

    public List<Teacher> all() {
        return getTable();
    }

    public Teacher findByIndex(int index) {
        List<Teacher> teachers = getTable();
        if (index >= 0 && index < teachers.size()) {
            return teachers.get(index);
        }
        return null;
    }

    public String create(Teacher teacher) {
        List<Teacher> teachers = getTable();
        teachers.add(teacher);
        db.put(TABLE_NAME, teachers);
        return "Profesor agregado correctamente.";
    }

    public String update(int index, Teacher teacher) {
        List<Teacher> teachers = getTable();
        if (index >= 0 && index < teachers.size()) {
            teachers.set(index, teacher);
            db.put(TABLE_NAME, teachers);
            return "Profesor actualizado correctamente.";
        }
        return "❌ Índice inválido.";
    }

    public String delete(int index) {
        List<Teacher> teachers = getTable();
        if (index >= 0 && index < teachers.size()) {
            teachers.remove(index);
            db.put(TABLE_NAME, teachers);
            return "Profesor eliminado correctamente.";
        }
        return "❌ No se pudo eliminar: índice fuera de rango.";
    }
}
