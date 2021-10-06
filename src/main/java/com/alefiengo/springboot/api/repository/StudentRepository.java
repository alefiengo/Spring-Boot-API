package com.alefiengo.springboot.api.repository;

import com.alefiengo.springboot.api.entity.Course;
import com.alefiengo.springboot.api.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {

    @Query("select s from Student s where s.lastName = ?1 and s.firstName = ?2")
    Optional<Student> findStudentByLastNameAndFirstName(String lastName, String firstName);

    @Query("select s from Student s where s.lastName like %?1%")
    Iterable<Student> findStudentByLastName(String lastName);

    @Query("select s from Student s where s.firstName like %?1%")
    Iterable<Student> findStudentByFirstName(String firstName);

    @Query("select c from Student s join s.courses c where s.id = ?1")
    Iterable<Course> findCoursesByStudentId(Long id);
}
