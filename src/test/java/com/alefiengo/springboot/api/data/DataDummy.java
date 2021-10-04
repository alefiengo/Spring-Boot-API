package com.alefiengo.springboot.api.data;

import com.alefiengo.springboot.api.entity.Course;
import com.alefiengo.springboot.api.entity.Student;

public class DataDummy {

    public static Student student01() {
        return new Student(null, "Fiengo", "Alejandro");
    }

    public static Student student02() {
        return new Student(null, "Melendrez", "Cintia");
    }

    public static Student student03() {
        return new Student(null, "Perez", "Juan");
    }

    public static Course course01(boolean withId) {
        Course course = (withId) ? new Course((long) 1, "COD-001", "Course 1", "Description to Course 1") :
                new Course(null, "COD-001", "Course 1", "Description to Course 1");
        return course;
    }

    public static Course course02() {
        return new Course(null, "COD-002", "Course 2", "Description to Course 2");
    }

    public static Course course03() {
        return new Course(null, "COD-003", "Course 3", "Description to Course 3");
    }
}
