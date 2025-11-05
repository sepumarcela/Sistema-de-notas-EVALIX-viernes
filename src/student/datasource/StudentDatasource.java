package student.datasource;

import java.util.ArrayList;
import java.util.List;
import student.models.Student;

public class StudentDatasource {

  
    private final List<Student> students;

    // Constructor 
    public StudentDatasource() {
        this.students = new ArrayList<>();

        // Datos de ejemplo 
        this.students.add(new Student(1, "Marcela Sepúlveda", "marcela@u.edu.co", "Ingeniería de Software"));
        this.students.add(new Student(2, "Felipe Quintero", "felipe.quintero@u.edu.co", "Derecho"));
        this.students.add(new Student(3, "Mariana Rivera", "mariana.rivera@u.edu.co", "Diseño"));
    }

    // READ ALL
    public String all() {
        try {
            if (students.isEmpty()) return "No hay estudiantes registrados.";

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < students.size(); i++) {
                Student s = students.get(i);
                sb.append("[").append(i).append("] ")
                  .append(s.getName())
                  .append(" | ID: ").append(s.getId())
                  .append(" | Email: ").append(s.getEmail())
                  .append(" | Programa: ").append(s.getProgram())
                  .append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return "Error al listar estudiantes: " + e.getMessage();
        }
    }

    // READ BY INDEX
    public String findByIndex(int index) {
        try {
            if (index < 0 || index >= students.size()) return "Índice inválido.";
            Student s = students.get(index);
            return "Estudiante encontrado: " + s.getName() 
                   + " | ID: " + s.getId()
                   + " | Email: " + s.getEmail()
                   + " | Programa: " + s.getProgram();
        } catch (Exception e) {
            return "Error al buscar estudiante: " + e.getMessage();
        }
    }

    // CREATE 
    public String create(int id, String name, String email, String program) {
        return create(new Student(id, name, email, program));
    }

    // CREATE 
    public String create(Student student) {
        try {
            this.students.add(student);
            return "Estudiante creado: " + student.getName() + " (ID: " + student.getId() + ")";
        } catch (Exception e) {
            return "Error al crear estudiante: " + e.getMessage();
        }
    }

    // UPDATE
    public String update(int index, int id, String name, String email, String program) {
        return update(index, new Student(id, name, email, program));
    }

    // UPDATE 
    public String update(int index, Student studentIn) {
        try {
            if (index < 0 || index >= students.size()) return "Índice inválido para actualizar.";
            Student s = students.get(index);

            // actualizar campos
            s.setId(studentIn.getId());
            s.setName(studentIn.getName());
            s.setEmail(studentIn.getEmail());
            s.setProgram(studentIn.getProgram());

            return "Estudiante actualizado: " + s.getName() + " (ID: " + s.getId() + ")";
        } catch (Exception e) {
            return "Error al actualizar estudiante: " + e.getMessage();
        }
    }

    // DELETE
    public String delete(int index) {
        try {
            if (index < 0 || index >= students.size()) return "Índice inválido para eliminar.";
            Student removed = students.remove(index);
            return "Estudiante eliminado: " + removed.getName() + " (ID: " + removed.getId() + ")";
        } catch (Exception e) {
            return "Error al eliminar estudiante: " + e.getMessage();
        }
    }
   
}
