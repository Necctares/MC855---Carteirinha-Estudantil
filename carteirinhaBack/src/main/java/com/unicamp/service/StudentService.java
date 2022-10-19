package com.unicamp.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unicamp.dao.StudentDao;
import com.unicamp.entity.Student;

@Service
public class StudentService {

    @Autowired
    @Qualifier("fakeData")
    private StudentDao studentDao;

    public Collection<Student> getAllStudents(){
        return this.studentDao.getAllStudents();
    }

    public Student getStudentById(int id){
        return this.studentDao.getStudentById(id);
    }

    public void removeStudentById(int id) {
        this.studentDao.removeStudentById(id);
    }

    public void insertStudent(Student student) {
        this.studentDao.insertStudentToDb(student);
    }
}