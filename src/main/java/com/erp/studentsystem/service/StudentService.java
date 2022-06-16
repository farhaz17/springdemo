package com.erp.studentsystem.service;

import com.erp.studentsystem.model.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {
    public Student saveStudent(String address, String name, MultipartFile file);
    public String storageStudent(MultipartFile file);
    public List<Student> getAllStudents();
    public Student updateStudent(Student student, int id);
    public void deleteStudent(int id);
}
