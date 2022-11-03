package com.unicamp.controller;

import com.unicamp.entity.Student;
import com.unicamp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }


    // TODO: Re-implementar metodos
    /*
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @RequestMapping(value = "/byId/{id}", method = RequestMethod.GET)
    public Student getStudentById(@PathVariable("id") int id){
        return studentService.getStudentById(id);
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public void deleteStudentById(@PathVariable("id") int id){
        studentService.removeStudentById(id);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertStudent(@RequestBody Student student){
        studentService.insertStudent(student);
    }*/

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String student(Model model, @PathVariable("id") int id) {

        Student actual = studentService.findById(id);
        if(Objects.isNull(actual)) {
            model.addAttribute("student", "Não foi encontrado nenhum estudante com este RA");
            model.addAttribute("course", "Curso invalido");
        } else {
            model.addAttribute("student", actual.getName());
            model.addAttribute("course", actual.getCourse());
        }

        return "student";
    }
}
