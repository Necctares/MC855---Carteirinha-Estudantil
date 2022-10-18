package com.unicamp.Dao.MySql;

import java.util.Collection;
import java.util.Map;
import com.unicamp.Entity.Student;

public class MySqlStudentDao {
    private static Map<Integer, Student> students;

    public Collection<Student> getAllStudents() {
        return this.students.values();
    }
}
