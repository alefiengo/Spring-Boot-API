package com.alefiengo.springboot.api.controller;

import com.alefiengo.springboot.api.entity.Course;
import com.alefiengo.springboot.api.entity.Student;
import com.alefiengo.springboot.api.service.CourseService;
import com.alefiengo.springboot.api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> message = new HashMap<>();
        List<Student> students = (List<Student>) studentService.findAll();

        if (students.isEmpty()) {
            message.put("success", Boolean.FALSE);
            message.put("message", "No students found.");
            return ResponseEntity.badRequest().body(message);
        }

        message.put("success", Boolean.TRUE);
        message.put("data", students);

        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Map<String, Object> message = new HashMap<>();
        Optional<Student> oStudent = studentService.findById(id);

        if (!oStudent.isPresent()) {
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No student found. ID = %d", id));
            return ResponseEntity.badRequest().body(message);
        }

        message.put("success", Boolean.TRUE);
        message.put("data", oStudent.get());

        return ResponseEntity.ok(message);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Student student, BindingResult result) {
        Map<String, Object> message = new HashMap<>();
        Map<String, Object> validations = new HashMap<>();

        if (result.hasErrors()) {
            result.getFieldErrors()
                    .forEach(error -> {
                        validations.put(error.getField(), error.getDefaultMessage());
                    });

            return ResponseEntity.badRequest().body(validations);
        }

        message.put("success", Boolean.TRUE);
        message.put("data", studentService.save(student));

        return ResponseEntity.ok(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Student student, BindingResult result) {
        Map<String, Object> message = new HashMap<>();
        Map<String, Object> validations = new HashMap<>();
        Student studentUpdate = null;
        Optional<Student> oStudent = studentService.findById(id);

        if (result.hasErrors()) {
            result.getFieldErrors()
                    .forEach(error -> {
                        validations.put(error.getField(), error.getDefaultMessage());
                    });

            return ResponseEntity.badRequest().body(validations);
        }

        if (!oStudent.isPresent()) {
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No student found. ID = %d", id));
            return ResponseEntity.badRequest().body(message);
        }

        studentUpdate = oStudent.get();
        studentUpdate.setLastName(student.getLastName());
        studentUpdate.setFirstName(student.getFirstName());

        message.put("success", Boolean.TRUE);
        message.put("data", studentService.save(studentUpdate));

        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> message = new HashMap<>();
        Optional<Student> oStudent = studentService.findById(id);

        if (!oStudent.isPresent()) {
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No student found. ID = %d", id));
            return ResponseEntity.badRequest().body(message);
        }

        studentService.deleteById(id);

        message.put("success", Boolean.TRUE);
        message.put("data", null);

        return ResponseEntity.ok(message);
    }

    @PostMapping("/{idStudent}/courses/{idCourse}")
    public ResponseEntity<?> toAssignClassStudent(@PathVariable Long idStudent, @PathVariable Long idCourse) {
        Map<String, Object> message = new HashMap<>();
        Optional<Student> oStudent = studentService.findById(idStudent);

        if (!oStudent.isPresent()) {
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No student found. ID = %d", idStudent));
            return ResponseEntity.badRequest().body(message);
        }

        Optional<Course> oCourse = courseService.findById(idCourse);

        if (!oCourse.isPresent()) {
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No course found. ID = %d", idCourse));
            return ResponseEntity.badRequest().body(message);
        }

        Student student = oStudent.get();
        Course course = oCourse.get();

        student.getCourses().add(course);

        message.put("success", Boolean.TRUE);
        message.put("data", studentService.save(student));

        return ResponseEntity.ok(message);
    }
}
