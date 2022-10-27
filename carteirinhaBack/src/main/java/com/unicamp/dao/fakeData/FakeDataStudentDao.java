package com.unicamp.dao.fakeData;

import com.unicamp.dao.StudentDao;
import com.unicamp.entity.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("fakeData")
public class FakeDataStudentDao implements StudentDao {

    private static Map<Integer, Student> students;

    static {

        students = new HashMap<Integer, Student>(){

            {
                put(1, new Student(Integer.valueOf(193011), "Michel Pereira Bastos", "Ciencia da Computação", "103011MPB", new Date(30330091)));
                put(2, new Student(Integer.valueOf(213214), "Michele Pereira Bastos", "Engenharia Eletrica", "213214MPB", new Date(30450000)));
                put(3, new Student(Integer.valueOf(223581), "Luiz Silva Santos", "Matematica Aplicada", "223581LSB", new Date(30437001)));
            }
        };
    }

    @Override
    public Collection<Student> getAllStudents() {
        return this.students.values();
    }

    @Override
    public Student getStudentById(Integer id) {
        return this.students.get(id);
    }

    @Override
    public void removeStudentById(Integer id) {
        this.students.remove(id);
    }

    @Override
    public void insertStudentToDb(Student student) {
        this.students.put(student.getRa(), student);
    }
}