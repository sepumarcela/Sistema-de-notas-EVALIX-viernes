package teacher.useCases;

import java.util.List;
import teacher.datasource.TeacherDatasource;
import teacher.models.Teacher;

/**
 * Contiene la lógica de negocio del CRUD
 */
public class TeacherUseCase {

    private TeacherDatasource datasource;

    public TeacherUseCase() {
        this.datasource = new TeacherDatasource();
    }

    public String all() {
        List<Teacher> teachers = datasource.all();
        if (teachers.isEmpty()) {
            return "No hay profesores registrados.";
        }

        StringBuilder sb = new StringBuilder("Lista de profesores:\n");
        for (int i = 0; i < teachers.size(); i++) {
            sb.append(i).append(": ").append(teachers.get(i)).append("\n");
        }
        return sb.toString();
    }

    public String findByIndex(int index) {
        Teacher t = datasource.findByIndex(index);
        return (t != null) ? t.toString() : "❌ Profesor no encontrado.";
    }

    public String create(Teacher teacher) {
        return datasource.create(teacher);
    }

    public String update(int index, Teacher teacher) {
        return datasource.update(index, teacher);
    }

    public String delete(int index) {
        return datasource.delete(index);
    }
}
