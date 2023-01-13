package com.example.gtvtbe.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse{
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
}
