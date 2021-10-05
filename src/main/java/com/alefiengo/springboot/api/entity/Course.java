package com.alefiengo.springboot.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "course")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @NotNull
    @NotEmpty
    @Size(max = 10)
    @Column(name = "code", nullable = false, length = 10)
    @NonNull
    private String code;

    @NotNull
    @NotEmpty
    @Size(max = 150)
    @Column(name = "title", nullable = false, length = 150)
    @NonNull
    private String title;

    @Column(name = "description")
    @NonNull
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany(
            mappedBy = "courses",
            fetch = FetchType.LAZY
    )
    @JsonIgnoreProperties({"courses"})
    @ToString.Exclude
    private Set<Student> students;

    @PrePersist
    public void beforePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void beforeUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id.equals(course.id) && code.equals(course.code) && title.equals(course.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, title);
    }
}
