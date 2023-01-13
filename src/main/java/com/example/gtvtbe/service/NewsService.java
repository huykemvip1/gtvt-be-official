package com.example.gtvtbe.service;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.model.entity.NewsEntity;

public interface NewsService {
    Result<?> getByPage(Integer page, Integer pageSize);

    Result<?> save(NewsEntity request);

    Result<?> findById(String id);
}
