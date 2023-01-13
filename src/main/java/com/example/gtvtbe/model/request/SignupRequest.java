package com.example.gtvtbe.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignupRequest implements Serializable {

    @NotNull(message = "Username must not be empty.")
    @JsonProperty("username")
    private String username;

    @NotNull(message = "Password must not be empty.")
    @JsonProperty("password")
    private String password;

    @Email(message = "The email is not correct.")
    @NotNull(message = "Email must not be empty.")
    @JsonProperty("email_security")
    private String emailSecurity;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotNull(message = "Question Security must not be empty.")
    @JsonProperty("question_security")
    private String questionSecurity;

    @NotNull(message = "Answer_security must not be empty.")
    @JsonProperty("answer_security")
    private String answerSecurity;

    @NotNull(message = "first name must not be empty.")
    @JsonProperty("first_name")
    private String firstName;

    @NotNull(message = "last name must not be empty.")
    @JsonProperty("last_name")
    private String lastName;

}
