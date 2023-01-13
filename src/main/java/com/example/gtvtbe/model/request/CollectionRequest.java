package com.example.gtvtbe.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionRequest {
    @JsonProperty("name")
    private String name;
    @JsonProperty("like_number")
    private Integer likeNumber;
    @JsonProperty("favorite_number")
    private Integer favoriteNumber;
    @JsonProperty("created_time")
    private Long createdTime;
}
