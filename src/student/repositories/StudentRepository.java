package student.repositories;

import student.datasource.StudentDatasource;

public class StudentRepository {

    public StudentDatasource studentDatasource;

    // CONSTRUCTOR
    public StudentRepository() {
        this.studentDatasource = new StudentDatasource();
    }

    // OBTENER TODOS
    public String all() {
        return this.studentDatasource.all();
    }

    // OBTENER POR ÍNDICE
    public String findByIndex(int index) {
        return this.studentDatasource.findByIndex(index);
    }

    // CREAR NUEVO
    public String create(int id, String name, String email, String program) {
        return this.studentDatasource.create(id, name, email, program);
    }

    // ACTUALIZAR EXISTENTE
    public String update(int index, int id, String name, String email, String program) {
        return this.studentDatasource.update(index, id, name, email, program);
    }

    // ELIMINAR POR ÍNDICE
    public String delete(int index) {
        return this.studentDatasource.delete(index);
    }
}
