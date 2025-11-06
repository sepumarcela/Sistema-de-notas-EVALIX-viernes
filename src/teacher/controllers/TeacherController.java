package teacher.controllers;

import teacher.datasource.TeacherDatasource;
import teacher.models.Teacher;

public class TeacherController {

    private final TeacherDatasource datasource;

    public TeacherController() {
        this.datasource = new TeacherDatasource();
    }

    public String all() {
        return datasource.all();
    }

    public String findByIndex(int index) {
        return datasource.findByIndex(index);
    }

    public String create(int id, String name, String email, String subject) {
        Teacher t = new Teacher(id, name, email, subject);
        return datasource.create(t);
    }

    public String update(int index, int id, String name, String email, String subject) {
        Teacher t = new Teacher(id, name, email, subject);
        return datasource.update(index, t);
    }

    public String delete(int index) {
        return datasource.delete(index);
    }
}
