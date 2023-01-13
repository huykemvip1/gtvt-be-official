package com.example.gtvtbe.service;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.exception.DataNotFoundException;
import com.example.gtvtbe.model.entity.SubjectProgram;
import com.example.gtvtbe.model.response.SubjectProgramResponse;
import com.example.gtvtbe.model.response.UsersResponse;

import java.util.List;

public interface PublicService {
    Result<List<SubjectProgramResponse>> getSubjectPrograms();


}
