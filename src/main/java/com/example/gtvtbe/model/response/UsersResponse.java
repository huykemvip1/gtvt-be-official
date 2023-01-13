package com.example.gtvtbe.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponse implements Serializable {
    @JsonProperty("id")
    private String id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("dob")
    private Long dob;
    @JsonProperty("gender")
    private Integer gender;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("email")
    private String email;
    @JsonProperty("address_details")                    // Địa chỉ chi tiết
    private String detailAddress;
    @JsonProperty("district")                          // Địa chỉ huyện, quận
    private String district;
    @JsonProperty("province")                          // Địa chỉ tỉnh
    private String province;
    @JsonProperty("job_position")
    private Integer jobPosition;
    @JsonProperty("job_title")
    private Integer jobTitle;
    @JsonProperty("facebook")
    private String facebook;
    @JsonProperty("achievements")
    private String[] achievements;
}
