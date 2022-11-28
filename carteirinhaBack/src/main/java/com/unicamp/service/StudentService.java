package com.unicamp.service;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unicamp.Utils.AuthCheck;
import com.unicamp.Utils.JsonMessage;
import com.unicamp.dao.StudentDao;
import com.unicamp.entity.Student;

@Service
public class StudentService {

    private StudentDao studentDao;
    private ObjectMapper mapper = new ObjectMapper();

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public ObjectNode findById(int ra, String key) {
        ObjectNode node;
        try {
            AuthCheck.authenticate(key, ra);
            node = JsonMessage.buildMessage("success", "", studentDao.findById(Integer.valueOf(ra)).get(), mapper);
        } catch (Exception e) {
            node = JsonMessage.buildMessage("success", e.getMessage(), mapper);
        }
        return node;
    }

    public ObjectNode save(Student student, String key, Integer id) {
        ObjectNode node;
        try {
            AuthCheck.authenticateAdmin(key, id);
            node = JsonMessage.buildMessage("success", "", studentDao.save(student), mapper);
        } catch (Exception e) {
            node = JsonMessage.buildMessage("success", e.getMessage(), mapper);
        }
        return node;
    }

    public ObjectNode delete(int ra, String key, Integer id) {
        ObjectNode node;
        try {
            AuthCheck.authenticateAdmin(key, id);
            studentDao.deleteById(Integer.valueOf(ra));
            node = JsonMessage.buildMessage("success", "", mapper);
        } catch (Exception e) {
            node = JsonMessage.buildMessage("success", e.getMessage(), mapper);
        }
        return node;
    }
}