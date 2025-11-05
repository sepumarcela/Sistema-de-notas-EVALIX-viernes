package student.controllers; // ruta de la case

import student.models.Student;
import student.useCases.StudentUseCase; // Importacion de la clase TaskUseCase

// El contenedor del enrutador de las acciones.
public class StudentController {

    public StudentUseCase studentUseCase; // Declaracion de la clase TaskUseCase se convierte como tipo objecto

    // Constructor
    public StudentController() {
        this.studentUseCase = new StudentUseCase(); // Instancia de la clase TaskUseCase
    }

    // en ruta la accion de obtener todos
    public String all(){
        return this.studentUseCase.all(); // llamar todas las tareas.
    }

    // en ruta la accion de obtener por indice
    public String findByIndex(int index){
        return this.studentUseCase.findByIndex(index); // llamar la accion de buscar por indice.
    }

    // en ruta la accion de crear
    public String create(int id, String name, String email, String program){
        return this.studentUseCase.create(new Student(id, name, email, program)); // llamar la accion de crear tarea.
    }

    // en ruta la accion de actualizar
    public String update(int index, int id, String name, String email, String program){
        return this.studentUseCase.update(index, new Student(id, name, email, program)); // llamar la accion de actualizar tarea.
    }

    // en ruta la accion de eliminar
    public String delete (int index){
        return this.studentUseCase.delete(index);
    }
}