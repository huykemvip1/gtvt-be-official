package com.example.gtvtbe.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ForgotAccountRequest {

    @NotNull(message = "Username must not be null")
    @JsonProperty("username")
    private String username;
    @Email(message = "Request email")
    @NotNull(message = "Email must not be null")
    @JsonProperty("email")
    private String email;
    @NotNull(message = "Question must not be null")
    @JsonProperty("question_security")
    private String questionSecurity;
    @NotNull(message = "Answer must not be null")
    @JsonProperty("answer_security")
    private String answerSecurity;
}
