package student.datasource;

import config.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;
import student.models.Student;

public class StudentDatasource {

    private List<Student> students;

    public StudentDatasource() {
        DatabaseConnection db = DatabaseConnection.getInstance();

        // Si no existe la “tabla” students, la creamos
        if (db.get("students") == null) {
            db.put("students", new ArrayList<Student>());
        }

        this.students = (List<Student>) db.get("students");
        initializeData();
    }

    private void initializeData() {
        if (students.isEmpty()) {
            students.add(new Student(1, "Marcela Sepúlveda", "marcela@u.edu.co", "Ingeniería de Software"));
            students.add(new Student(2, "Felipe Quintero", "felipe.quintero@u.edu.co", "Derecho"));
            students.add(new Student(3, "Mariana Rivera", "mariana.rivera@u.edu.co", "Diseño"));
            System.out.println("✓ Datos de ejemplo insertados en students");
        }
    }

    // READ ALL
    public String all() {
        if (students.isEmpty()) {
            return "No hay estudiantes registrados.";
        }

        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (Student s : students) {
            sb.append("[").append(index).append("] ")
              .append(s.getName())
              .append(" | ID: ").append(s.getId())
              .append(" | Email: ").append(s.getEmail())
              .append(" | Programa: ").append(s.getProgram())
              .append("\n");
            index++;
        }
        return sb.toString();
    }

    // READ BY INDEX
    public String findByIndex(int index) {
        if (index < 0 || index >= students.size()) {
            return "Índice inválido.";
        }
        Student s = students.get(index);
        return "Estudiante encontrado: " + s.getName()
                + " | ID: " + s.getId()
                + " | Email: " + s.getEmail()
                + " | Programa: " + s.getProgram();
    }

    // CREATE
    public String create(Student student) {
        for (Student s : students) {
            if (s.getId() == student.getId() || s.getEmail().equalsIgnoreCase(student.getEmail())) {
                return "Error: Ya existe un estudiante con ese ID o email.";
            }
        }
        students.add(student);
        return "Estudiante creado: " + student.getName() + " (ID: " + student.getId() + ")";
    }

    // UPDATE
    public String update(int index, Student updatedStudent) {
        if (index < 0 || index >= students.size()) {
            return "Índice inválido para actualizar.";
        }
        students.set(index, updatedStudent);
        return "Estudiante actualizado: " + updatedStudent.getName() + " (ID: " + updatedStudent.getId() + ")";
    }

    // DELETE
    public String delete(int index) {
        if (index < 0 || index >= students.size()) {
            return "Índice inválido para eliminar.";
        }
        Student s = students.remove(index);
        return "Estudiante eliminado: " + s.getName() + " (ID: " + s.getId() + ")";
    }
}
