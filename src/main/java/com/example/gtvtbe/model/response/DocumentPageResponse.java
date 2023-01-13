package com.example.gtvtbe.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentPageResponse {
    @JsonProperty("data")
    private List<DocumentResponse> data;
    @JsonProperty("total_element")
    private Long totalElement;
    @JsonProperty("total_page")
    private Integer totalPage;
}
