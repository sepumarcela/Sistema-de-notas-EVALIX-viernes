package teacher.useCases;

import teacher.repositories.TeacherRepository;
import teacher.models.Teacher;

public class TeacherUseCase {

    private TeacherRepository teacherRepository;

    // Constructor
    public TeacherUseCase() {
        this.teacherRepository = new TeacherRepository();
    }

    // READ ALL
    public String all() {
        try {
            return this.teacherRepository.all();
        } catch (Exception e) {
            return "Error al obtener los profesores: " + e.getMessage();
        }
    }

    // READ BY INDEX
    public String findByIndex(int index) {
        try {
            return this.teacherRepository.findByIndex(index);
        } catch (Exception e) {
            return "No ha sido posible acceder al profesor.";
        }
    }

    // CREATE
    public String create(Teacher teacher) {
        try {
            return this.teacherRepository.create(
                teacher.getId(),
                teacher.getName(),
                teacher.getEmail(),
                teacher.getSubject()
            );
        } catch (Exception e) {
            return "Ha ocurrido un error al crear el profesor: " + e.getMessage();
        }
    }

    // UPDATE
    public String update(int index, Teacher teacher) {
        try {
            return this.teacherRepository.update(
                index,
                teacher.getId(),
                teacher.getName(),
                teacher.getEmail(),
                teacher.getSubject()
            );
        } catch (Exception e) {
            return "Ha ocurrido un error al actualizar el profesor: " + e.getMessage();
        }
    }

    // DELETE
    public String delete(int index) {
        try {
            return this.teacherRepository.delete(index);
        } catch (Exception e) {
            return "No ha sido posible eliminar al profesor.";
        }
    }
}