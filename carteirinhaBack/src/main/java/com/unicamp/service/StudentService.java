package com.unicamp.service;

import org.springframework.stereotype.Service;
import com.unicamp.dao.StudentDao;
import com.unicamp.entity.Student;

@Service
public class StudentService {

    private StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public Student findById(int ra) {
        Student student;
        try {
            student = studentDao.findById(Integer.valueOf(ra)).get();
        } catch (Exception e) {
            student = null;
        }
        return student;
    }

    public Student save(Student student) {
        Student aux = student;
        try {
            studentDao.save(student);
        } catch (Exception e) {
            aux = null;
            System.out.println(e.getMessage());
        }
        return aux;
    }

    public boolean delete(int ra) {
        boolean isOk = true;
        try {
            studentDao.deleteById(Integer.valueOf(ra));
        } catch (Exception e) {
            isOk = false;
        }
        return isOk;
    }
}