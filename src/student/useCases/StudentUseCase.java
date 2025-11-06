package student.useCases;

import student.models.Student;
import student.repositories.StudentRepository;

public class StudentUseCase {

    private StudentRepository studentRepository;

    // Constructor
    public StudentUseCase() {
        this.studentRepository = new StudentRepository();
    }

    // READ ALL
    public String all() {
        try {
            return this.studentRepository.all();
        } catch (Exception e) {
            return "Error al obtener los estudiantes: " + e.getMessage();
        }
    }

    // READ BY INDEX
    public String findByIndex(int index) {
        try {
            return this.studentRepository.findByIndex(index);
        } catch (Exception e) {
            return "No ha sido posible acceder al estudiante.";
        }
    }

    // CREATE
    public String create(Student student) {
        try {
            return this.studentRepository.create(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getProgram()
            );
        } catch (Exception e) {
            return "Ha ocurrido un error, por favor inténtelo de nuevo: " + e.getMessage();
        }
    }

    // UPDATE
    public String update(int index, Student student) {
        try {
            return this.studentRepository.update(
                index,
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getProgram()
            );
        } catch (Exception e) {
            return "Ha ocurrido un error al actualizar el estudiante: " + e.getMessage();
        }
    }

    // DELETE
    public String delete(int index) {
        try {
            return this.studentRepository.delete(index);
        } catch (Exception e) {
            return "No ha sido posible eliminar al estudiante.";
        }
    }
}