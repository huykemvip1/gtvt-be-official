package com.example.gtvtbe.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionResponse {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("like_number")
    private Integer likeNumber;
    @JsonProperty("favorite_number")
    private Integer favoriteNumber;
    @JsonProperty("created_time")
    private Long createdTime;
}
