package com.example.gtvtbe.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "SubjectProgram")
public class SubjectProgram {
    @Id
    private Integer id;
    @Column(name = "name")
    private String name;
//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "subjectProgram")
//    private List<DocumentEntity> documents;
}
