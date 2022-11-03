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

    public Student findById(int ra){
        return studentDao.findById(Integer.valueOf(ra)).get();
    }
}