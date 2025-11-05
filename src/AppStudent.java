import student.models.Student;
import student.useCases.StudentUseCase;

public class AppStudent {
    public static void main(String[] args) {

        StudentUseCase StudentUseCase = new StudentUseCase();

        System.out.println("===  LISTA DE ESTUDIANTES ===");
        System.out.println(StudentUseCase.all());

        System.out.println("===  BUSCAR POR ÍNDICE ===");
        System.out.println(StudentUseCase.findByIndex(1));

        System.out.println("===  CREAR NUEVO ===");

       Student newStudent = new Student(4, "Laura Martínez", "laura@u.edu.co", "Psicología");
    System.out.println(StudentUseCase.create(newStudent));
    System.out.println(StudentUseCase.all());

        System.out.println("==  ACTUALIZAR ==");
    Student updatedStudent = new Student(1, "Marcela Sepúlveda", "marce.actualizada@u.edu.co", "Ingeniería");
    System.out.println(StudentUseCase.update(0, updatedStudent));
    System.out.println(StudentUseCase.all());

        System.out.println("===  ELIMINAR ===");
        System.out.println(StudentUseCase.delete(0));
        System.out.println(StudentUseCase.all());
    }
}
