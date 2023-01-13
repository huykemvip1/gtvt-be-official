package com.example.gtvtbe.repository;

import com.example.gtvtbe.model.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentRepository extends JpaRepository<DocumentEntity,String> {

    /**
     *
     * @param name
     * @param idSubject
     * @return
     */
    @Query("select d from DocumentEntity d " +
            "where (?1 is null or ?1 = '' or d.name like %?1%) " +
            "and (?2 is null or d.subjectProgram.id = ?2)")
    List<DocumentEntity> findByNameAndIdSubject(String name, String idSubject);

}
