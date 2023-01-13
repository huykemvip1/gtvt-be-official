package com.example.gtvtbe.repository;

import com.example.gtvtbe.model.entity.CoursesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<CoursesEntity,Integer> {

    @Query(value = "select c from CoursesEntity c join c.users u" +
            " where c.id = :courseId" +
            " and u.jobPosition in :jobPosition")
    Optional<CoursesEntity> findByIdAndUsersJobPosition(Integer courseId, List<Integer> jobPosition);
}
