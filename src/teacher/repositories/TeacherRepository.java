package teacher.repositories;

import java.util.List;
import teacher.datasource.TeacherDatasource;
import teacher.models.Teacher;

public class TeacherRepository {

    private TeacherDatasource teacherDatasource;

    public TeacherRepository() {
        this.teacherDatasource = new TeacherDatasource();
    }

    // OBTENER TODOS
    public List<Teacher> all() {
        return teacherDatasource.getAll();
    }

    // OBTENER POR ÍNDICE
    public Teacher findByIndex(int index) {
        return teacherDatasource.getByIndex(index);
    }

    // CREAR NUEVO
    public void create(int id, String name, String email, String subject) {
        Teacher newTeacher = new Teacher(id, name, email, subject);
        teacherDatasource.add(newTeacher);
    }

    // ACTUALIZAR EXISTENTE
    public void update(int index, int id, String name, String email, String subject) {
        Teacher updatedTeacher = new Teacher(id, name, email, subject);
        teacherDatasource.update(index, updatedTeacher);
    }

    // ELIMINAR POR ÍNDICE
    public void delete(int index) {
        teacherDatasource.delete(index);
    }
}
