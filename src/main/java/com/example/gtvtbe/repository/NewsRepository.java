package com.example.gtvtbe.repository;

import com.example.gtvtbe.model.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsEntity,String> {

}
