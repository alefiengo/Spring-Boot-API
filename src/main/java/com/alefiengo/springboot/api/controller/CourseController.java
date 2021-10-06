package com.alefiengo.springboot.api.controller;

import com.alefiengo.springboot.api.entity.Course;
import com.alefiengo.springboot.api.entity.Student;
import com.alefiengo.springboot.api.service.CourseService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> message = new HashMap<>();
        List<Course> courses = (List<Course>) courseService.findAll();

        if (courses.isEmpty()) {
            message.put("success", Boolean.FALSE);
            message.put("message", "No courses found.");
            return ResponseEntity.badRequest().body(message);
        }

        message.put("success", Boolean.TRUE);
        message.put("data", courses);

        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Map<String, Object> message = new HashMap<>();
        Optional<Course> oCourse = courseService.findById(id);

        if (!oCourse.isPresent()) {
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No course with ID: '%d', found.", id));
            return ResponseEntity.badRequest().body(message);
        }

        message.put("success", Boolean.TRUE);
        message.put("data", oCourse.get());

        return ResponseEntity.ok(message);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Course course, BindingResult result) {
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
        message.put("data", courseService.save(course));

        return ResponseEntity.ok(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Course course, BindingResult result) {
        Map<String, Object> message = new HashMap<>();
        Map<String, Object> validations = new HashMap<>();
        Course courseUpdate = null;
        Optional<Course> oCourse = courseService.findById(id);

        if (result.hasErrors()) {
            result.getFieldErrors()
                    .forEach(error -> {
                        validations.put(error.getField(), error.getDefaultMessage());
                    });

            return ResponseEntity.badRequest().body(validations);
        }

        if (!oCourse.isPresent()) {
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No course with ID: '%d', found.", id));
            return ResponseEntity.badRequest().body(message);
        }

        courseUpdate = oCourse.get();
        courseUpdate.setCode(course.getCode());
        courseUpdate.setTitle(course.getTitle());
        courseUpdate.setDescription(course.getDescription());

        message.put("success", Boolean.TRUE);
        message.put("data", courseService.save(courseUpdate));

        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> message = new HashMap<>();
        Optional<Course> oCourse = courseService.findById(id);

        if (!oCourse.isPresent()) {
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No course with ID: '%d', found.", id));
            return ResponseEntity.badRequest().body(message);
        }

        courseService.deleteById(id);

        message.put("success", Boolean.TRUE);
        message.put("data", null);

        return ResponseEntity.ok(message);
    }

    @GetMapping("/code")
    public ResponseEntity<?> getCourseByCode(@RequestParam String code) {
        Map<String, Object> message = new HashMap<>();
        Optional<Course> oCourse = courseService.findCourseByCodeIgnoreCase(code);

        if (!oCourse.isPresent()) {
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No course with code: '%s', found.", code));
            return ResponseEntity.badRequest().body(message);
        }

        message.put("success", Boolean.TRUE);
        message.put("data", oCourse.get());

        return ResponseEntity.ok(message);
    }

    @GetMapping("/title")
    public ResponseEntity<?> getCourseByTitle(@RequestParam String title) {
        Map<String, Object> message = new HashMap<>();
        List<Course> courses = (List<Course>) courseService.findCourseByTitleContains(title);

        if (courses.isEmpty()) {
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No course with title contains: '%s', found.", title));
            return ResponseEntity.badRequest().body(message);
        }

        message.put("success", Boolean.TRUE);
        message.put("data", courses);

        return ResponseEntity.ok(message);
    }

    @GetMapping("/description")
    public ResponseEntity<?> getCourseByDescription(@RequestParam String description) {
        Map<String, Object> message = new HashMap<>();
        List<Course> courses = (List<Course>) courseService.findCourseByDescriptionContains(description);

        if (courses.isEmpty()) {
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No course with description contains: '%s', found.", description));
            return ResponseEntity.badRequest().body(message);
        }

        message.put("success", Boolean.TRUE);
        message.put("data", courses);

        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<?> getStudentsByCourseId(@PathVariable Long id) {
        Map<String, Object> message = new HashMap<>();
        List<Student> students = (List<Student>) courseService.findStudentsByCourseId(id);

        if (students.isEmpty()) {
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No students in to course with ID: '%d', found.", id));
            return ResponseEntity.badRequest().body(message);
        }

        message.put("success", Boolean.TRUE);
        message.put("data", students);

        return ResponseEntity.ok(message);
    }
}
