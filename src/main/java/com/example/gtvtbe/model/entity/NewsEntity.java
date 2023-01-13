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
@Table(name = "News")
public class NewsEntity {
    @Id
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "content")
    private String content;
    @Column(name = "author")
    private String author;
    @Column(name = "created_time")
    private Long createdTime;
    @Column(name = "category")
    private Integer category;
}
