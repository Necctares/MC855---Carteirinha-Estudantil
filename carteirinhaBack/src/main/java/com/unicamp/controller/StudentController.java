package com.unicamp.controller;

import com.unicamp.entity.Student;
import com.unicamp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/byId", method = RequestMethod.GET)
    public Student getStudentById(@RequestParam int id) {
        return studentService.findById(id);
    }

    // TODO: Key check security
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public void deleteStudentById(@RequestParam int id, @RequestParam String key) {
        studentService.delete(id);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertStudent(@RequestParam String key, @RequestBody Student student) {
        studentService.save(student);
    }

    @Controller
    @RequestMapping("/student")
    public class StudentView {
        @RequestMapping(value = "/show", method = RequestMethod.GET)
        public String student(Model model, @RequestParam int id) {
            Student actual = studentService.findById(id);
            if (Objects.isNull(actual)) {
                model.addAttribute("student", "Não foi encontrado nenhum estudante com este RA");
                model.addAttribute("course", "Curso invalido");
            } else {
                model.addAttribute("student", actual.getName());
                model.addAttribute("course", actual.getCourse());
            }
            return "student";
        }
    }
}