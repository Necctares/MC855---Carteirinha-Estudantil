package com.unicamp.dao.mySql;

import java.util.Collection;
import java.util.Map;
import com.unicamp.dao.StudentDao;
import com.unicamp.entity.Student;

// TODO Implement MySql DAO
public class MySqlStudentDao implements StudentDao {
    private static Map<Integer, Student> students;

    public Collection<Student> getAllStudents() {
        return this.students.values();
    }

    @Override
    public Student getStudentById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removeStudentById(int id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void insertStudentToDb(Student student) {
        // TODO Auto-generated method stub
        
    }
}
