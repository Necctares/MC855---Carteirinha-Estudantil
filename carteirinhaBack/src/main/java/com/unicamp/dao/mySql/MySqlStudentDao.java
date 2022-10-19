package com.unicamp.dao.mySql;

import java.util.Collection;
import java.util.Map;
import com.unicamp.entity.Student;

public class MySqlStudentDao {
    private static Map<Integer, Student> students;

    public Collection<Student> getAllStudents() {
        return this.students.values();
    }
}
