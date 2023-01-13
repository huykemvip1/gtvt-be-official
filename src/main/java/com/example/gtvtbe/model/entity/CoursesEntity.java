package com.example.gtvtbe.model.entity;

import io.swagger.models.auth.In;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "Courses")
@EqualsAndHashCode
public class CoursesEntity {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "courses")
    private Set<UserEntity> users;

}
