package com.webknot.campus.eventmanagement.controller;

import com.webknot.campus.eventmanagement.dto.StudentDTO;
import com.webknot.campus.eventmanagement.model.College;
import com.webknot.campus.eventmanagement.model.Student;
import com.webknot.campus.eventmanagement.service.CollegeService;
import com.webknot.campus.eventmanagement.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final CollegeService collegeService; // need to fetch College by id

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody StudentDTO studentDTO) {
        College college = collegeService.getCollegeById(studentDTO.getCollegeId())
                .orElseThrow(() -> new RuntimeException("College not found"));

        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setCollege(college);

        Student savedStudent = studentService.createStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
