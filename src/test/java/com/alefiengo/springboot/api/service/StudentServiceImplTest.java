package com.alefiengo.springboot.api.service;

import com.alefiengo.springboot.api.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    StudentService studentService;

    @Mock
    StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentService = new StudentServiceImpl(studentRepository);
    }

    @Test
    void findStudentByLastNameAndFirstName() {
        //when
        studentService.findStudentByLastNameAndFirstName(anyString(), anyString());

        //then
        verify(studentRepository).findStudentByLastNameAndFirstName(anyString(), anyString());
    }

    @Test
    void findStudentByLastName() {
        //when
        studentService.findStudentByLastName(anyString());

        //then
        verify(studentRepository).findStudentByLastName(anyString());
    }

    @Test
    void findStudentByFirstName() {
        //when
        studentService.findStudentByFirstName(anyString());

        //then
        verify(studentRepository).findStudentByFirstName(anyString());
    }
}