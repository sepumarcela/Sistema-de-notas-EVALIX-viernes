package teacher.datasource;

import java.util.List;
import teacher.models.Teacher;
import teacher.repositories.TeacherRepository;

/**
 * Datasource intermedio entre el caso de uso y el repositorio.
 */
public class TeacherDatasource {

    private TeacherRepository repository;

    public TeacherDatasource() {
        this.repository = new TeacherRepository();
    }

    public List<Teacher> all() {
        return repository.all();
    }

    public Teacher findByIndex(int index) {
        return repository.findByIndex(index);
    }

    public String create(Teacher teacher) {
        return repository.create(teacher);
    }

    public String update(int index, Teacher teacher) {
        return repository.update(index, teacher);
    }

    public String delete(int index) {
        return repository.delete(index);
    }
}
