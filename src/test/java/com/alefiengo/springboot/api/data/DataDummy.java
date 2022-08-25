package com.alefiengo.springboot.api.data;

import com.alefiengo.springboot.api.entity.Course;
import com.alefiengo.springboot.api.entity.Student;

public class DataDummy {

    public static Student student01() {
        return new Student((long) 1, "Ebden", "Rocco");
    }

    public static Student student02() {
        return new Student((long) 2, "Lomax", "Aiden");
    }

    public static Student student03() {
        return new Student((long) 3, "Larsen", "Regina");
    }

    public static Course course01() {
        return new Course((long) 1, "COD-001", "Course 1", "Description to Course 1");
    }

    public static Course course02() {
        return new Course((long) 2, "COD-002", "Course 2", "Description to Course 2");
    }

    public static Course course03() {
        return new Course((long) 3, "COD-003", "Course 3", "Description to Course 3");
    }
}
