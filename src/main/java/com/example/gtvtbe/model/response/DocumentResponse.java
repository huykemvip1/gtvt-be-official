package com.example.gtvtbe.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentResponse {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("like_number")
    private Integer likeNumber;
    @JsonProperty("favorite_number")
    private Integer favoriteNumber;
    @JsonProperty("author")
    private String author;
    @JsonProperty("created_time")
    private Long createdTime;
    @JsonProperty("subject_program")
    private SubjectProgramResponse subjectProgramResponse;
}
