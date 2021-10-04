package com.alefiengo.springboot.api.controller;

import com.alefiengo.springboot.api.controller.exception.BadRequestException;
import com.alefiengo.springboot.api.entity.Course;
import com.alefiengo.springboot.api.entity.Student;
import com.alefiengo.springboot.api.service.CourseService;
import com.alefiengo.springboot.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping
    public List<Student> getAll() {
        List<Student> students = (List<Student>) studentService.findAll();

        if (students.isEmpty()) {
            throw new BadRequestException("No students found.");
        }

        return students;
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        Optional<Student> oStudent = studentService.findById(id);

        if (!oStudent.isPresent()) {
            throw new BadRequestException(String.format("No student found. ID = %d", id));
        }

        return oStudent.get();
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        Student studentUpdate = null;
        Optional<Student> oStudent = studentService.findById(id);

        if (!oStudent.isPresent()) {
            throw new BadRequestException(String.format("No student found. ID = %d", id));
        }

        studentUpdate = oStudent.get();
        studentUpdate.setLastName(student.getLastName());
        studentUpdate.setFirstName(student.getFirstName());

        return studentService.save(studentUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Student> oStudent = studentService.findById(id);

        if (!oStudent.isPresent()) {
            throw new BadRequestException(String.format("No student found. ID = %d", id));
        }

        studentService.deleteById(id);
    }

    @PostMapping("/{idStudent}/courses/{idCourse}")
    public Student toAssignClassStudent(@PathVariable Long idStudent, @PathVariable Long idCourse) {
        Optional<Student> oStudent = studentService.findById(idStudent);

        if (!oStudent.isPresent()) {
            throw new BadRequestException(String.format("No student found. ID = %d", idStudent));
        }

        Optional<Course> oCourse = courseService.findById(idCourse);

        if (!oCourse.isPresent()) {
            throw new BadRequestException(String.format("No course found. ID = %d", idCourse));
        }

        Student student = oStudent.get();
        Course course = oCourse.get();

        student.getCourses().add(course);

        return studentService.save(student);
    }
}
