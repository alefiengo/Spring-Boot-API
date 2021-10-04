package com.alefiengo.springboot.api.repository;

import com.alefiengo.springboot.api.data.DataDummy;
import com.alefiengo.springboot.api.entity.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        //given
        studentRepository.save(DataDummy.student01());
        studentRepository.save(DataDummy.student02());
        studentRepository.save(DataDummy.student03());
    }

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
    }

    @Test
    @DisplayName("Find Student by Last Name and First Name")
    void findStudentByLastNameAndFirstName() {
        //when
        Optional<Student> expected = studentRepository.findStudentByLastNameAndFirstName("Fiengo", "Alejandro");

        //then
        assertThat(expected.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Find Student by Last Name")
    void findStudentByLastName() {
        //when
        List<Student> expected = (List<Student>) studentRepository.findStudentByLastName("Fiengo");

        //then
        assertThat(((List<Student>) expected).size() == 1).isTrue();
    }

    @Test
    @DisplayName("Find Student by First Name")
    void findStudentByFirstName() {
        //when
        List<Student> expected = (List<Student>) studentRepository.findStudentByFirstName("Alejandro");

        //then
        assertThat(((List<Student>) expected).size() == 1).isTrue();
    }
}