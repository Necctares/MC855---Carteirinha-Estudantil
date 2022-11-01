package com.unicamp.dao.mySql;

import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.unicamp.dao.StudentDao;
import com.unicamp.entity.Student;

@Repository
@Qualifier("carteirinha")
public class MySqlStudentDao implements StudentDao {
    private static Map<Integer, Student> students;

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
