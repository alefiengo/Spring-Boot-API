package com.alefiengo.springboot.api.controller;

import com.alefiengo.springboot.api.controller.exception.BadRequestException;
import com.alefiengo.springboot.api.entity.Course;
import com.alefiengo.springboot.api.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public List<Course> getAll() {
        List<Course> courses = (List<Course>) courseService.findAll();

        if (courses.isEmpty()) {
            throw new BadRequestException("No courses found.");
        }

        return courses;
    }

    @GetMapping("/{id}")
    public Course getById(@PathVariable Long id) {
        Optional<Course> oCourse = courseService.findById(id);

        if (!oCourse.isPresent()) {
            throw new BadRequestException(String.format("No course found. ID = %d", id));
        }

        return oCourse.get();
    }

    @PostMapping
    public Course create(@RequestBody Course course) {
        return courseService.save(course);
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable Long id, @RequestBody Course course) {
        Course courseUpdate = null;
        Optional<Course> oCourse = courseService.findById(id);

        if (!oCourse.isPresent()) {
            throw new BadRequestException(String.format("No course found. ID = %d", id));
        }

        courseUpdate = oCourse.get();
        courseUpdate.setCode(course.getCode());
        courseUpdate.setTitle(course.getTitle());
        courseUpdate.setDescription(course.getDescription());

        return courseService.save(courseUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Course> oCourse = courseService.findById(id);

        if (!oCourse.isPresent()) {
            throw new BadRequestException(String.format("No course found. ID = %d", id));
        }

        courseService.deleteById(id);
    }
}
