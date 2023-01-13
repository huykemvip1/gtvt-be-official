package com.example.gtvtbe.security.domain;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Account")
@EqualsAndHashCode
@Builder
public class Account {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "is_active")
    private boolean isActive;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "AccountRole",
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Roles> roles;
    @Column(name = "question_security")
    private String questionSecurity;
    @Column(name = "answer_security")
    private String answerSecurity;
    @Column(name = "email_security")
    private String emailSecurity;
    @Column(name = "id_Users")
    private String idUsers;
}
