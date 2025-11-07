package teacher;

import config.DatabaseConnection;
import java.util.Scanner;
import teacher.controllers.TeacherController;

/**
 * Aplicación principal para gestionar profesores (CRUD)
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
                        String idStr = sc.nextLine().trim();
                        
                        // Validación: ID debe ser número
                        if (idStr.isEmpty()) {
                            System.out.println("❌ Error: El ID no puede estar vacío");
                            break;
                        }
                        int id = Integer.parseInt(idStr);
                        
                        System.out.print("Nombre: "); 
                        String name = sc.nextLine().trim();
                        
                        // Validación: Nombre no puede estar vacío
                        if (name.isEmpty()) {
                            System.out.println("❌ Error: El nombre no puede estar vacío");
                            break;
                        }
                        
                        System.out.print("Email: "); 
                        String email = sc.nextLine().trim();
                        
                        // Validación: Email no puede estar vacío
                        if (email.isEmpty()) {
                            System.out.println("❌ Error: El email no puede estar vacío");
                            break;
                        }
                        
                        System.out.print("Materia: "); 
                        String subject = sc.nextLine().trim();
                        
                        // Validación: Materia no puede estar vacía
                        if (subject.isEmpty()) {
                            System.out.println("❌ Error: La materia no puede estar vacía");
                            break;
                        }
                        
                        String result = controller.create(id, name, email, subject);
                        System.out.println(result);
                    }
                    
                    case "2" -> {
                        String result = controller.all();
                        System.out.println(result);
                    }
                    
                    case "3" -> {
                        // Mostrar lista antes de buscar
                        System.out.println(controller.all());
                        
                        System.out.print("\nÍndice: "); 
                        String idxStr = sc.nextLine().trim();
                        
                        // Validación: Índice debe ser número
                        if (idxStr.isEmpty()) {
                            System.out.println("❌ Error: El índice no puede estar vacío");
                            break;
                        }
                        int idx = Integer.parseInt(idxStr);
                        
                        String result = controller.findByIndex(idx);
                        System.out.println(result);
                    }
                    
                    case "4" -> {
                        // Mostrar lista antes de actualizar
                        System.out.println(controller.all());
                        
                        System.out.print("\nÍndice: "); 
                        String idxStr = sc.nextLine().trim();
                        
                        // Validación: Índice debe ser número
                        if (idxStr.isEmpty()) {
                            System.out.println("❌ Error: El índice no puede estar vacío");
                            break;
                        }
                        int idx = Integer.parseInt(idxStr);
                        
                        System.out.print("Nuevo ID: "); 
                        String idStr = sc.nextLine().trim();
                        
                        // Validación: ID debe ser número
                        if (idStr.isEmpty()) {
                            System.out.println("❌ Error: El ID no puede estar vacío");
                            break;
                        }
                        int id = Integer.parseInt(idStr);
                        
                        System.out.print("Nuevo nombre: "); 
                        String name = sc.nextLine().trim();
                        
                        // Validación: Nombre no puede estar vacío
                        if (name.isEmpty()) {
                            System.out.println("❌ Error: El nombre no puede estar vacío");
                            break;
                        }
                        
                        System.out.print("Nuevo email: "); 
                        String email = sc.nextLine().trim();
                        
                        // Validación: Email no puede estar vacío
                        if (email.isEmpty()) {
                            System.out.println("❌ Error: El email no puede estar vacío");
                            break;
                        }
                        
                        System.out.print("Nueva materia: "); 
                        String subject = sc.nextLine().trim();
                        
                        // Validación: Materia no puede estar vacía
                        if (subject.isEmpty()) {
                            System.out.println("❌ Error: La materia no puede estar vacía");
                            break;
                        }
                        
                        String result = controller.update(idx, id, name, email, subject);
                        System.out.println(result);
                    }
                    
                    case "5" -> {
                        // Mostrar lista antes de eliminar
                        System.out.println(controller.all());
                        
                        System.out.print("\nÍndice: "); 
                        String idxStr = sc.nextLine().trim();
                        
                        // Validación: Índice debe ser número
                        if (idxStr.isEmpty()) {
                            System.out.println("❌ Error: El índice no puede estar vacío");
                            break;
                        }
                        int idx = Integer.parseInt(idxStr);
                        
                        String result = controller.delete(idx);
                        System.out.println(result);
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
            
            // Pausa para ver resultados antes de volver al menú
            if (run && !op.equals("0")) {
                System.out.println("\nPresione ENTER para continuar...");
                sc.nextLine();
            }
        }

        db.closeConnection();
        sc.close();
        System.out.println("\nConexión cerrada. ¡Hasta luego! 👋");
    }
}