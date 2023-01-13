package com.example.gtvtbe.repository;

import com.example.gtvtbe.model.entity.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CollectionRepository extends JpaRepository<CollectionEntity,String> {

}
