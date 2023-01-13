package com.example.gtvtbe.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileResponse implements Serializable {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("file_name")
    private String fileName;
    @JsonProperty("path")
    private String path;
    @JsonProperty("content_type")
    private String contentType;
    @JsonProperty("size")
    private Long size;
}
