package com.example.gtvtbe.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse implements Serializable {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("user")
    private UsersOfCourseResponse lecturer;

    public CourseResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
