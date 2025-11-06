package teacher.repositories;

import teacher.datasource.TeacherDatasource;

public class TeacherRepository {

    public TeacherDatasource teacherDatasource;

    // CONSTRUCTOR
    public TeacherRepository() {
        this.teacherDatasource = new TeacherDatasource();
    }

    // OBTENER TODOS
    public String all() {
        return this.teacherDatasource.all();
    }

    // OBTENER POR ÍNDICE
    public String findByIndex(int index) {
        return this.teacherDatasource.findByIndex(index);
    }

    // CREAR NUEVO
    public String create(int id, String name, String email, String subject) {
        return this.teacherDatasource.create(
            new teacher.models.Teacher(id, name, email, subject)
        );
    }

    // ACTUALIZAR EXISTENTE
    public String update(int index, int id, String name, String email, String subject) {
        return this.teacherDatasource.update(
            index, 
            new teacher.models.Teacher(id, name, email, subject)
        );
    }

    // ELIMINAR POR ÍNDICE
    public String delete(int index) {
        return this.teacherDatasource.delete(index);
    }
}