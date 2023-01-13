package com.example.gtvtbe.model.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "Collections")
public class CollectionEntity {
    @Id
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "like_number")
    private Integer likeNumber;
    @Column(name = "favorite")
    private Integer favorite;
    @Column(name = "created_time")
    private Long createdTime;
}
