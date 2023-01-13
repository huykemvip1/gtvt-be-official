package com.example.gtvtbe.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponse {
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("content")
    private String content;
    @JsonProperty("author")
    private String author;
    @JsonProperty("created_time")
    private Long createdTime;
    @JsonProperty("category")
    private Integer category;
}
