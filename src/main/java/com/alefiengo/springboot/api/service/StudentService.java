package com.alefiengo.springboot.api.service;

import com.alefiengo.springboot.api.entity.Student;

import java.util.Optional;

public interface StudentService extends GenericService<Student> {

    Optional<Student> findStudentByLastNameAndFirstName(String lastName, String firstName);

    Iterable<Student> findStudentByLastName(String lastName);

    Iterable<Student> findStudentByFirstName(String firstName);
}
