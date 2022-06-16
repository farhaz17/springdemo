package com.erp.studentsystem.controller;

import com.erp.studentsystem.model.Student;
import com.erp.studentsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

//    @PostMapping("/add")
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String add(String address, String name, @RequestParam("photo") MultipartFile file){
        studentService.storageStudent(file);
        studentService.saveStudent(address, name, file);
        return "Successfully Added";
    }


    @GetMapping("getAll")
    public List<Student> getAllStudent(){
        return studentService.getAllStudents();
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int id)
    {
        return studentService.updateStudent(student, id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id)
    {
        studentService.deleteStudent(id);
        return "Deleted Successfully";
    }
}
