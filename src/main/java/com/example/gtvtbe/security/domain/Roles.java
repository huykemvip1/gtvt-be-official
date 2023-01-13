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
@Table(name = "Roles")
@EqualsAndHashCode
@Builder
public class Roles {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
//    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "roles")
//    private Set<Account> accounts = new HashSet<>();
}
