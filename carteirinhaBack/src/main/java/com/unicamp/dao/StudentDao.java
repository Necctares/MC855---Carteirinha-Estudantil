package com.unicamp.dao;

import com.unicamp.entity.Student;
import java.util.Collection;

public interface StudentDao {
    Collection<Student> getAllStudents();

    Student getStudentById(Integer id);

    void removeStudentById(Integer id);

    void insertStudentToDb(Student student);
}
