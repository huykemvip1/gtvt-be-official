package com.example.gtvtbe.service.impl;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.enumeration.EnumJobPosition;
import com.example.gtvtbe.exception.DataNotFoundException;
import com.example.gtvtbe.model.response.SubjectProgramResponse;
import com.example.gtvtbe.model.response.UsersResponse;
import com.example.gtvtbe.repository.SubjectProgramRepository;
import com.example.gtvtbe.repository.UsersRepository;
import com.example.gtvtbe.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PublicServiceImpl implements PublicService {
    @Autowired
    private MessageSource messageSource;
    private final SubjectProgramRepository subjectProgramRepository;
    private final UsersRepository usersRepository;


    public PublicServiceImpl(SubjectProgramRepository subjectProgramRepository, UsersRepository usersRepository) {
        this.subjectProgramRepository = subjectProgramRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public Result<List<SubjectProgramResponse>> getSubjectPrograms() {
        List<SubjectProgramResponse> responses = subjectProgramRepository.findAll()
                .stream()
                .map(item -> {
                    return new SubjectProgramResponse(item.getId(), item.getName());
                }).toList();
        return Result.ok(responses);
    }
    


}
