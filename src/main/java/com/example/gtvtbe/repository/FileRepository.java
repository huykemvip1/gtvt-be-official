package com.example.gtvtbe.repository;

import com.example.gtvtbe.model.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FileRepository extends JpaRepository<FileEntity,Long> {
    @Query("select f from FileEntity f where f.fileName = :fileName")
    Optional<FileEntity> getFileEntityByFileName(@Param("fileName") String fileName);
}
