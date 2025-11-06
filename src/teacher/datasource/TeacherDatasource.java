package teacher.datasource;

import config.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;
import teacher.models.Teacher;

public class TeacherDatasource {

    private List<Teacher> teachers;

    public TeacherDatasource() {
        DatabaseConnection db = DatabaseConnection.getInstance();

        // Si la tabla no existe en la base de datos, la creamos
        if (db.get("teachers") == null) {
            db.put("teachers", new ArrayList<Teacher>());
        }

        // Cargamos la lista de profesores desde la "base de datos"
        this.teachers = (List<Teacher>) db.get("teachers");

        // Inicializamos los datos si está vacía
        initializeData();
    }

    private void initializeData() {
        if (teachers.isEmpty()) {
            teachers.add(new Teacher(1, "Carlos Méndez", "carlos.mendez@u.edu.co", "Matemáticas"));
            teachers.add(new Teacher(2, "Lucía Rojas", "lucia.rojas@u.edu.co", "Biología"));
            teachers.add(new Teacher(3, "Andrés Torres", "andres.torres@u.edu.co", "Química"));
        }
    }

    public List<Teacher> getAll() {
        return teachers;
    }

    public Teacher getByIndex(int index) {
        if (index >= 0 && index < teachers.size()) {
            return teachers.get(index);
        }
        return null;
    }

    public void add(Teacher teacher) {
        teachers.add(teacher);
    }

    public void update(int index, Teacher teacher) {
        if (index >= 0 && index < teachers.size()) {
            teachers.set(index, teacher);
        }
    }

    public void delete(int index) {
        if (index >= 0 && index < teachers.size()) {
            teachers.remove(index);
        }
    }
}
