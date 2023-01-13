package com.example.gtvtbe.service;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.exception.DataNotFoundException;
import com.example.gtvtbe.model.request.DocumentRequest;

public interface DocumentService {
    Result<?> getByPage(Integer page, Integer pageSize);

    Result<?> getBySearch(String name, String idSubject);

    Result<?> actionLike(String id,Integer numberLike) throws DataNotFoundException;

    Result<?> actionFavorite(String id,Integer numberFavorite) throws DataNotFoundException;

    Result<?> save(DocumentRequest request);
}
