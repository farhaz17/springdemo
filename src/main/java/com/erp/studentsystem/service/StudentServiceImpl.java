package com.erp.studentsystem.service;

import antlr.StringUtils;
import com.erp.studentsystem.model.Student;
import com.erp.studentsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    private static String UPLOAD_FOLDER = "C://Users//zonemsp//Downloads//";

    @Override
    public Student saveStudent(String address, String name, MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Student student = new Student();
        student.setAddress(address);
        student.setName(name);
        student.setPhoto(fileName);
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student, int id) {
        Student studentDb = studentRepository.findById(id).get();
        studentDb.setName(student.getName());
        studentDb.setAddress(student.getAddress());
        return studentRepository.save(studentDb);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public String storageStudent(MultipartFile file) {
        try {
            // read and write the file to the selected location-
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Successfully Added";
    }
}
