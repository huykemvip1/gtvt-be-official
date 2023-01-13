package com.example.gtvtbe.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionPageResponse {
    @JsonProperty("data")
    private List<CollectionResponse> data;
    @JsonProperty("total_element")
    private Long totalElement;
    @JsonProperty("total_page")
    private Integer totalPage;
}
