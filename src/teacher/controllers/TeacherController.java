package teacher.controllers;

import teacher.models.Teacher;
import teacher.useCases.TeacherUseCase;

/**
 * Controlador que gestiona las “rutas” del CRUD de Teacher
 */
public class TeacherController {

    public TeacherUseCase teacherUseCase;

    public TeacherController() {
        this.teacherUseCase = new TeacherUseCase();
    }

    public String all() {
        return this.teacherUseCase.all();
    }

    public String findByIndex(int index) {
        return this.teacherUseCase.findByIndex(index);
    }

    public String create(int id, String name, String email, String subject) {
        return this.teacherUseCase.create(new Teacher(id, name, email, subject));
    }

    public String update(int index, int id, String name, String email, String subject) {
        return this.teacherUseCase.update(index, new Teacher(id, name, email, subject));
    }

    public String delete(int index) {
        return this.teacherUseCase.delete(index);
    }
}
