import teacher.models.Teacher;
import teacher.useCases.TeacherUseCase;

public class AppTeacher {

    public static void main(String[] args) {
        TeacherUseCase teacherUseCase = new TeacherUseCase();

        System.out.println("=== LISTA DE PROFESORES ===");
        System.out.println(teacherUseCase.all());

        System.out.println("=== BUSCAR POR ÍNDICE ===");
        System.out.println(teacherUseCase.findByIndex(1));

        System.out.println("=== CREAR NUEVO ===");
        Teacher newTeacher = new Teacher(4, "María López", "maria.lopez@u.edu.co", "Física");
        teacherUseCase.create(newTeacher);
        System.out.println(teacherUseCase.all());

        System.out.println("=== ACTUALIZAR ===");
        Teacher updatedTeacher = new Teacher(1, "Carlos Méndez Actualizado", "carlos.nuevo@u.edu.co", "Álgebra");
        teacherUseCase.update(0, updatedTeacher);
        System.out.println(teacherUseCase.all());

        System.out.println("=== ELIMINAR ===");
        teacherUseCase.delete(0);
        System.out.println(teacherUseCase.all());
    }
}
