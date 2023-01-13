package com.example.gtvtbe.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "Users")
public class UserEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "dob")
    private Long dob;
    @Column(name = "gender")
    private Integer gender;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "address_details")                    // Địa chỉ chi tiết
    private String detailAddress;
    @Column(name = "district")                          // Địa chỉ huyện, quận
    private String district;
    @Column(name = "province")                          // Địa chỉ tỉnh
    private String province;
    @Column(name = "job_position")
    private Integer jobPosition;
    @Column(name = "job_title")
    private Integer jobTitle;
    @Column(name = "achievements")
    private String achievements;
    @Column(name = "facebook")
    private String facebook;
    @ManyToMany(cascade =CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "UsersCourses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<CoursesEntity> courses;
}
