package com.example.gtvtbe.repository;

import com.example.gtvtbe.security.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolesRepository extends JpaRepository<Roles,String> {
    List<Roles> findAllById(Iterable<String> ids);
}
