package com.alefiengo.springboot.api.service;

import com.alefiengo.springboot.api.data.DataDummy;
import com.alefiengo.springboot.api.entity.Course;
import com.alefiengo.springboot.api.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

class CourseServiceImplTest {

    CourseService courseService;
    CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        courseRepository = mock(CourseRepository.class);
        courseService = new CourseServiceImpl(courseRepository);
    }

    @Test
    void findCourseByCodeIgnoreCase() {
        //given
        String code = "cla-001";
        when(courseRepository.findCourseByCodeIgnoreCase(code))
                .thenReturn(Optional.of(DataDummy.course01(true)));

        //when
        Optional<Course> expected = courseService.findCourseByCodeIgnoreCase(code);

        //then
        assertThat(expected.isPresent()).isTrue();

        verify(courseRepository).findCourseByCodeIgnoreCase(code);
    }

    @Test
    void findCourseByTitleContains() {
        //given
        String title = "Course";
        when(courseRepository.findCourseByTitleContains(title))
                .thenReturn(Arrays.asList(DataDummy.course01(true)));

        //when
        List<Course> expected = (List<Course>) courseService.findCourseByTitleContains(title);

        //then
        assertThat(expected.get(0)).isEqualTo(DataDummy.course01(true));

        verify(courseRepository).findCourseByTitleContains(title);
    }

    @Test
    void findCourseByDescriptionContains() {
        //given
        String description = "Course";
        when(courseRepository.findCourseByDescriptionContains(description))
                .thenReturn(Arrays.asList(DataDummy.course01(true)));

        //when
        List<Course> expected = (List<Course>) courseService.findCourseByDescriptionContains(description);

        //then
        assertThat(expected.get(0)).isEqualTo(DataDummy.course01(true));

        verify(courseRepository).findCourseByDescriptionContains(description);
    }
}