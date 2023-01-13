package com.example.gtvtbe.repository;

import com.example.gtvtbe.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findByEmail(String email);
    List<UserEntity> findByJobPositionIn(List<Integer> jobPositions);

    @Query("select u from UserEntity u join u.courses c where " +
            " c.id = ?1 " +
            " and u.jobPosition = ?2")
    Page<UserEntity> findByCoursesId(Integer id, Integer jobPosition, Pageable pageable);

    @Query("select u from UserEntity u join u.courses c where " +
            " c.id = ?1 " +
            " and u.jobPosition in ?2")
    Optional<UserEntity> findLecturerByCoursesId(Integer id, List<Integer> ids);
}
