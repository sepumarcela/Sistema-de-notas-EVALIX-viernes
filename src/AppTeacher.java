package teacher;

import teacher.controllers.TeacherController;
import teacher.models.Teacher;

public class AppTeacher {
    public static void main(String[] args) {
        System.out.println("===== Sistema de Notas Evalix - Gestión de Profesores =====\n");

        try {
            TeacherController teacherController = new TeacherController();

            // === LISTAR TODOS ===
            System.out.println("=== LISTA DE PROFESORES ===");
            System.out.println(teacherController.all());

            // === BUSCAR POR ÍNDICE ===
            System.out.println("=== BUSCAR POR ÍNDICE ===");
            System.out.println(teacherController.findByIndex(1));
            System.out.println();

            // === CREAR NUEVO ===
            System.out.println("=== CREAR NUEVO PROFESOR ===");
            Teacher newTeacher = new Teacher(4, "María López", "maria.lopez@u.edu.co", "Física");
            System.out.println(teacherController.create(
                newTeacher.getId(),
                newTeacher.getName(),
                newTeacher.getEmail(),
                newTeacher.getSubject()
            ));
            System.out.println(teacherController.all());

            // === ACTUALIZAR ===
            System.out.println("=== ACTUALIZAR PROFESOR ===");
            Teacher updatedTeacher = new Teacher(1, "Carlos Méndez Actualizado", "carlos.nuevo@u.edu.co", "Álgebra");
            System.out.println(teacherController.update(
                0,
                updatedTeacher.getId(),
                updatedTeacher.getName(),
                updatedTeacher.getEmail(),
                updatedTeacher.getSubject()
            ));
            System.out.println(teacherController.all());

            // === ELIMINAR ===
            System.out.println("=== ELIMINAR PROFESOR ===");
            System.out.println(teacherController.delete(0));
            System.out.println(teacherController.all());

            System.out.println("✓ Proceso completado correctamente.");

        } catch (Exception e) {
            System.out.println("✗ Ha ocurrido un error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}