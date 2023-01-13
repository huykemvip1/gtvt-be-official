package com.example.gtvtbe.service;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.exception.DataNotFoundException;
import com.example.gtvtbe.model.request.CollectionRequest;
import com.example.gtvtbe.model.response.CollectionPageResponse;
import com.example.gtvtbe.model.response.CollectionResponse;

import java.util.List;

public interface CollectionService {
    Result<CollectionPageResponse> getCollections(Integer page, Integer pageSize);

    Result<CollectionResponse> actionCollectionLike(String id) throws DataNotFoundException;

    Result<CollectionResponse> actionCollectionFavorite(String id) throws DataNotFoundException;

    Result<String> save(CollectionRequest request);
}
