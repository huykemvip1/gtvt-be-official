package com.example.gtvtbe.service;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.exception.DataNotFoundException;
import com.example.gtvtbe.model.response.CourseResponse;
import com.example.gtvtbe.model.response.CoursesSearchResponse;
import com.example.gtvtbe.model.response.PageResponse;

import java.util.List;

public interface CoursesService {

    Result<List<CourseResponse>> findAllCourses();

    Result<CoursesSearchResponse> getCourseById(Integer id) throws DataNotFoundException;
}
