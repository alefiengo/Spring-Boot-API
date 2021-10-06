package com.alefiengo.springboot.api.service;

import com.alefiengo.springboot.api.entity.Course;
import com.alefiengo.springboot.api.entity.Student;
import com.alefiengo.springboot.api.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CourseServiceImpl extends GenericServiceImpl<Course, CourseRepository> implements CourseService {

    @Autowired
    public CourseServiceImpl(CourseRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> findCourseByCodeIgnoreCase(String code) {
        return repository.findCourseByCodeIgnoreCase(code);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Course> findCourseByTitleContains(String title) {
        return repository.findCourseByTitleContains(title);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Course> findCourseByDescriptionContains(String description) {
        return repository.findCourseByDescriptionContains(description);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Student> findStudentsByCourseId(Long id) {
        return repository.findStudentsByCourseId(id);
    }
}
