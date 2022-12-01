package com.unicamp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unicamp.entity.Student;
import com.unicamp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private final StudentService studentService;
    private ObjectMapper mapper = new ObjectMapper();

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/byId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode getStudentById(@RequestBody ObjectNode response) {
        return studentService.findById(response.get("ra").asInt(), response.get("key").asText());
    }

    @RequestMapping(value = "/del", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode deleteStudentById(@RequestBody ObjectNode response) {
        return studentService.delete(response.get("ra").asInt(), response.get("key").asText(), response.get("id").asInt());
    }

    @RequestMapping(value = "/insert", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode insertStudent(@RequestBody ObjectNode response) {
        return studentService.save(mapper.convertValue(response.get("student"), Student.class), response.get("key").asText(), response.get("id").asInt());
    }
}