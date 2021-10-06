package com.alefiengo.springboot.api.repository;

import com.alefiengo.springboot.api.entity.Course;
import com.alefiengo.springboot.api.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course, Long> {

    @Query("select c from Course c where upper(c.code) = upper(?1)")
    Optional<Course> findCourseByCodeIgnoreCase(String code);

    @Query("select c from Course c where c.title like %?1%")
    Iterable<Course> findCourseByTitleContains(String title);

    @Query("select c from Course c where c.description like %?1%")
    Iterable<Course> findCourseByDescriptionContains(String description);

    @Query("select s from Course c join c.students s where c.id = ?1")
    Iterable<Student> findStudentsByCourseId(Long id);
}
