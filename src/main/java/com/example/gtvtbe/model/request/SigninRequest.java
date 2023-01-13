package com.example.gtvtbe.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SigninRequest {
    @NotNull(message = "Username must not be empty.")
    @JsonProperty("username")
    private String username;
    @NotNull(message = "Password must not be empty.")
    @JsonProperty("password")
    private String password;
}
