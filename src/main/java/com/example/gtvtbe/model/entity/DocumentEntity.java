package com.example.gtvtbe.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "Documents")
@EqualsAndHashCode
public class DocumentEntity {
    @Id
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "like_number")
    private Integer likeNumber;
    @Column(name = "favorite_number")
    private Integer favoriteNumber;
    @Column(name = "author")
    private String author;
    @Column(name = "created_time")
    private Long createdTime;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subjectProgram_id", referencedColumnName = "id")
    private SubjectProgram subjectProgram;

}
