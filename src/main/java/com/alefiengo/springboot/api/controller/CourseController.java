package com.alefiengo.springboot.api.controller;

import com.alefiengo.springboot.api.entity.Course;
import com.alefiengo.springboot.api.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

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
            message.put("message", String.format("No course found. ID = %d", id));
            return ResponseEntity.badRequest().body(message);
        }

        message.put("success", Boolean.TRUE);
        message.put("data", oCourse.get());

        return ResponseEntity.ok(message);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Course course) {
        Map<String, Object> message = new HashMap<>();

        message.put("success", Boolean.TRUE);
        message.put("data", courseService.save(course));

        return ResponseEntity.ok(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Course course) {
        Map<String, Object> message = new HashMap<>();
        Course courseUpdate = null;
        Optional<Course> oCourse = courseService.findById(id);

        if (!oCourse.isPresent()) {
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No course found. ID = %d", id));
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
            message.put("message", String.format("No course found. ID = %d", id));
            return ResponseEntity.badRequest().body(message);
        }

        courseService.deleteById(id);

        message.put("success", Boolean.TRUE);
        message.put("data", null);

        return ResponseEntity.ok(message);
    }
}
