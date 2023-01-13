package com.example.gtvtbe.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtInformationResponse {
    @JsonProperty("token")
    private String token;
    @JsonProperty("expiration")
    private Long expiration;
    @JsonProperty("type_toke")
    private String typeToke;
    @JsonProperty("username")
    private String username;
}
