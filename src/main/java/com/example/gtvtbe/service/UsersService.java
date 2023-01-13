package com.example.gtvtbe.service;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.model.response.CoursesSearchResponse;
import com.example.gtvtbe.model.response.PageResponse;
import com.example.gtvtbe.model.response.UsersResponse;

public interface UsersService {
    Result<UsersResponse> findById(String id) throws Exception;

    Result<PageResponse<CoursesSearchResponse>> getByCoursesId(Integer id, Integer page, Integer pageSize);
}
