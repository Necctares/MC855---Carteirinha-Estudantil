package com.unicamp.dao;

import com.unicamp.entity.Student;

import java.util.Collection;

public interface StudentDao {
    Collection<Student> getAllStudents();

    Student getStudentById(int id);

    void removeStudentById(int id);

    void insertStudentToDb(Student student);
}
