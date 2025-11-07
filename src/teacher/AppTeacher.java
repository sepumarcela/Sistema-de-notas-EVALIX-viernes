package teacher;

import config.DatabaseConnection;
import teacher.controllers.TeacherController;
import java.util.Scanner;

/**
 * Aplicación principal para gestionar profesores (CRUD)
 * Usa una base de datos ficticia en memoria.
 */
public class AppTeacher {

    public static void main(String[] args) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        TeacherController controller = new TeacherController();
        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== SISTEMA DE PROFESORES ===");
        System.out.println("Conexión activa: " + db.testConnection());

        boolean run = true;
        while (run) {
            System.out.println("""
                \n--- MENÚ ---
                1. Crear profesor
                2. Listar profesores
                3. Buscar por índice
                4. Actualizar profesor
                5. Eliminar profesor
                0. Salir
                """);
            System.out.print("Opción: ");
            String op = sc.nextLine();

            try {
                switch (op) {
                    case "1" -> {
                        System.out.print("ID: "); 
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Nombre: "); 
                        String name = sc.nextLine();
                        System.out.print("Email: "); 
                        String email = sc.nextLine();
                        System.out.print("Materia: "); 
                        String subject = sc.nextLine();
                        System.out.println(controller.create(id, name, email, subject));
                    }
                    case "2" -> System.out.println(controller.all());
                    case "3" -> {
                        System.out.print("Índice: "); 
                        int idx = Integer.parseInt(sc.nextLine());
                        System.out.println(controller.findByIndex(idx));
                    }
                    case "4" -> {
                        System.out.print("Índice: "); 
                        int idx = Integer.parseInt(sc.nextLine());
                        System.out.print("Nuevo ID: "); 
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Nuevo nombre: "); 
                        String name = sc.nextLine();
                        System.out.print("Nuevo email: "); 
                        String email = sc.nextLine();
                        System.out.print("Nueva materia: "); 
                        String subject = sc.nextLine();
                        System.out.println(controller.update(idx, id, name, email, subject));
                    }
                    case "5" -> {
                        System.out.print("Índice: "); 
                        int idx = Integer.parseInt(sc.nextLine());
                        System.out.println(controller.delete(idx));
                    }
                    case "0" -> {
                        run = false;
                        System.out.println("👋 Saliendo del sistema...");
                    }
                    default -> System.out.println("❌ Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: ingrese valores numéricos válidos.");
            } catch (Exception e) {
                System.out.println("❌ Error inesperado: " + e.getMessage());
            }
        }

        db.closeConnection();
        sc.close();
        System.out.println("\nConexión cerrada. ¡Hasta luego! 👋");
    }
}