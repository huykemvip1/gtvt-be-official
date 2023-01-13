package com.example.gtvtbe.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> implements Serializable {
    @JsonProperty("data")
    private T data;
    @JsonProperty("total_page")
    private Integer page;
    @JsonProperty("total_elements")
    private Long pageSize;
}
