package student.useCases;

import java.util.ArrayList;
import java.util.List;
import student.models.Student;

// Contenedor de metodos y atributos
public class StudentUseCase { // Clase de las acciones a realizar el Use Case

    // Propiedad
    private final List<Student> students; // Definicion Lista en memoria

    // Constructor
    public StudentUseCase() {
        this.students = new ArrayList<>(); // Inicializacion - Preparar el uso de la lista.
    }

    // Read
    public String all(){
        try {
            StringBuilder result = new StringBuilder(); // preparando la concatenacion de los strings.
            for (int i = 0; i < students.size(); i++) { // Se procede a concatenar las tareas al string.
                Student student = students.get(i); // Se obtiene la tarea.

                result.append("Estudiante")
                 .append(i + 1).append(": ") // Número del registro
                  .append(student.getName())  // Nombre
                  .append(" | Email: ").append(student.getEmail()) // Email
                  .append(" | Programa: ").append(student.getProgram()) // Programa académico
                  .append(" | ID: ").append(student.getId()) // ID del estudiante
                  .append("\n"); // Nueva línea
            }

            return result.toString(); // Retornando la concatenacion de las tareas.
            
        } catch (Exception e) {
           return "Error al obtener los logins.";
        }
    }

    // Read by index
    public String findByIndex(int index) {
    try {
        Student studentFound = this.students.get(index);
        return "Estudiante Encontrado: "
        + studentFound.getName()
        + " |ID: " + studentFound.getId()
        +" | Email: " + studentFound.getEmail() 
        +" | Programa: " + studentFound.getProgram() ;
        }
        catch (Exception e) {
            return "No ha sido posible acceder al estudiante.";
        }
    }

    // Create
    public String create(Student student) {
        try {
            this.students.add(student); // Uso de la lista
            return "Estudiante creado correctamente:" 
             + student.getName() 
                + " | ID: " + student.getId()
                + " | Email: " + student.getEmail()
                + " | Programa: " + student.getProgram();
        }
        catch (Exception e) {
            return "Ha ocurrido un error, por favor inténtelo de nuevo.";
        }
    }

    // Update
    public String update(int index, Student studentIn) {
        try {
        Student studentFound = this.students.get(index);

        studentFound.setId(studentIn.getId());
        studentFound.setName(studentIn.getName());
        studentFound.setEmail(studentIn.getEmail());
        studentFound.setProgram(studentIn.getProgram());
             
        return "Estudiante actualizado correctamente:" + studentFound.getName() + " | Email:" + studentFound.getEmail()+
          " | Programa: " + studentFound.getProgram();
        }
        catch (Exception e) {
            return "Ha ocurrido un error al actualizar el estudiante, por favor inténtelo de nuevo.";
        }
    }


    // Delete
    public String delete(int index){
       try {
            students.remove(index);
            return "Se ha eliminado al estudiante correctamente.";
       } catch (Exception e) {
            return "no ha sido posible eliminar al estudiante.";
       }
    }
}