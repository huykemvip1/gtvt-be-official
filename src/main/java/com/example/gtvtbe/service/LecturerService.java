package com.example.gtvtbe.service;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.model.response.LecturersResponse;

import java.util.List;

public interface LecturerService {
    Result<List<LecturersResponse>> getLecturers();
}
