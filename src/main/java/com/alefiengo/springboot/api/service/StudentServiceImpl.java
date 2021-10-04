package com.alefiengo.springboot.api.service;

import com.alefiengo.springboot.api.entity.Student;
import com.alefiengo.springboot.api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StudentServiceImpl extends GenericServiceImpl<Student, StudentRepository> implements StudentService {

    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findStudentByLastNameAndFirstName(String lastName, String firstName) {
        return repository.findStudentByLastNameAndFirstName(lastName, firstName);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Student> findStudentByLastName(String lastName) {
        return repository.findStudentByLastName(lastName);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Student> findStudentByFirstName(String firstName) {
        return repository.findStudentByFirstName(firstName);
    }
}
