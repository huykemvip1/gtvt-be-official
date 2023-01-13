package com.example.gtvtbe.service.impl;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.exception.DataNotFoundException;
import com.example.gtvtbe.model.entity.DocumentEntity;
import com.example.gtvtbe.model.request.DocumentRequest;
import com.example.gtvtbe.model.response.DocumentPageResponse;
import com.example.gtvtbe.model.response.DocumentResponse;
import com.example.gtvtbe.model.response.SubjectProgramResponse;
import com.example.gtvtbe.repository.DocumentRepository;
import com.example.gtvtbe.repository.SubjectProgramRepository;
import com.example.gtvtbe.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private MessageSource messageSource;
    private final DocumentRepository documentRepository;

    private final SubjectProgramRepository subjectProgramRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository, SubjectProgramRepository subjectProgramRepository) {
        this.documentRepository = documentRepository;
        this.subjectProgramRepository = subjectProgramRepository;
    }

    @Override
    public Result<?> getByPage(Integer page, Integer pageSize) {
        var response  = new DocumentPageResponse();
        var getPage = documentRepository.findAll(PageRequest.of(page-1, pageSize));
        response.setData(getPage.get().map(item -> {
            return new DocumentResponse(item.getId(),
                    item.getName(),
                    item.getLikeNumber(),
                    item.getFavoriteNumber(),
                    item.getAuthor(),
                    item.getCreatedTime(),
                    new SubjectProgramResponse(item.getSubjectProgram().getId(),
                            item.getSubjectProgram().getName()));
        }).collect(Collectors.toList()));
        response.setTotalPage(getPage.getTotalPages());
        response.setTotalElement(getPage.getTotalElements());

        return Result.ok(response);
    }

    @Override
    public Result<?> getBySearch(String name, String idSubject) {
        List<DocumentResponse> response = new ArrayList<>();
        var getEntity = documentRepository.findByNameAndIdSubject(name, idSubject);
        response = getEntity.stream().map(item -> {
            return new DocumentResponse(item.getId(),
                    item.getName(),
                    item.getLikeNumber(),
                    item.getFavoriteNumber(),
                    item.getAuthor(),
                    item.getCreatedTime(),
                    new SubjectProgramResponse(item.getSubjectProgram().getId(),
                            item.getSubjectProgram().getName()));
        }).collect(Collectors.toList());
        return Result.ok(response);
    }

    @Override
    public Result<?> actionLike(String id,Integer numberLike) throws DataNotFoundException {
        DocumentEntity entity = documentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(messageSource.getMessage("entity.not.found", new Object[]{id}, null)));
        entity.setLikeNumber(entity.getLikeNumber()+1);

        Integer response = documentRepository.save(entity).getLikeNumber();
        return Result.ok(response);
    }

    @Override
    public Result<?> actionFavorite(String id,Integer numberFavorite) throws DataNotFoundException {
        DocumentEntity entity = documentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(messageSource.getMessage("entity.not.found", new Object[]{id}, null)));
        entity.setFavoriteNumber(entity.getFavoriteNumber()+1);

        Integer response = documentRepository.save(entity).getFavoriteNumber();
        return Result.ok(response);
    }

    @Override
    public Result<?> save(DocumentRequest request) {
        DocumentEntity entity = new DocumentEntity(
                UUID.randomUUID().toString(),
                request.getName(),
                1,
                1,
                request.getAuthor(),
                System.currentTimeMillis(),
                subjectProgramRepository.findById(request.getIdSubjectProgram()).get()
        );
        return Result.ok(documentRepository.save(entity));
    }
}
