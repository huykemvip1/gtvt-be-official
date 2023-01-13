package com.example.gtvtbe.service.impl;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.exception.DataNotFoundException;
import com.example.gtvtbe.model.entity.CollectionEntity;
import com.example.gtvtbe.model.request.CollectionRequest;
import com.example.gtvtbe.model.response.CollectionPageResponse;
import com.example.gtvtbe.model.response.CollectionResponse;
import com.example.gtvtbe.repository.CollectionRepository;
import com.example.gtvtbe.service.CollectionService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class CollectionServiceImpl implements CollectionService {
    private final CollectionRepository collectionRepository;

    public CollectionServiceImpl(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    @Override
    public Result<CollectionPageResponse> getCollections(Integer page, Integer pageSize) {
        CollectionPageResponse response = new CollectionPageResponse();
        var getPage = collectionRepository.findAll(PageRequest.of(page-1,pageSize));
        response.setTotalPage(getPage.getTotalPages());
        response.setTotalElement(getPage.getTotalElements());
        response.setData(
                getPage.stream().map(item -> {
                    return new CollectionResponse(item.getId(),
                            item.getName(),
                            item.getLikeNumber(),
                            item.getFavorite(),
                            item.getCreatedTime());
                }).collect(Collectors.toList())
        );
        return Result.ok(response);
    }

    @Override
    public Result<CollectionResponse> actionCollectionLike(String id) throws DataNotFoundException {
        var entity = collectionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(""));
        entity.setLikeNumber(entity.getLikeNumber()+1);
        collectionRepository.save(entity);
        return Result.ok(new CollectionResponse(entity.getId(),
                entity.getName(),
                entity.getLikeNumber(),
                entity.getFavorite(),
                entity.getCreatedTime()));
    }

    @Override
    public Result<CollectionResponse> actionCollectionFavorite(String id) throws DataNotFoundException {
        var entity = collectionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(""));
        entity.setFavorite(entity.getFavorite()+1);
        collectionRepository.save(entity);
        return Result.ok(new CollectionResponse(entity.getId(),
                entity.getName(),
                entity.getLikeNumber(),
                entity.getFavorite(),
                entity.getCreatedTime()));
    }

    @Override
    public Result<String> save(CollectionRequest request) {
        CollectionEntity entity = new CollectionEntity();
        BeanUtils.copyProperties(request, entity);
        entity.setId(UUID.randomUUID().toString());
        entity.setFavorite(1);
        entity.setLikeNumber(1);
        entity.setCreatedTime(System.currentTimeMillis());
        CollectionEntity entity1 = collectionRepository.save(entity);
        return Result.ok(entity1.getId());
    }
}
