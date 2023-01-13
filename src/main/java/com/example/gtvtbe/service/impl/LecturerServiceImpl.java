package com.example.gtvtbe.service.impl;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.enumeration.EnumJobPosition;
import com.example.gtvtbe.model.response.LecturersResponse;
import com.example.gtvtbe.model.response.UsersResponse;
import com.example.gtvtbe.repository.UsersRepository;
import com.example.gtvtbe.service.LecturerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LecturerServiceImpl implements LecturerService {
    private final UsersRepository usersRepository;

    public LecturerServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Result<List<LecturersResponse>> getLecturers() {
        List<LecturersResponse> response = new ArrayList<>();
        response =  usersRepository.findByJobPositionIn(List.of(EnumJobPosition.GIANGVIEN.getId(),EnumJobPosition.TRUONGBOMON.getId()))
                .stream().map(item -> new LecturersResponse(item.getId(),
                        item.getFirstName(),
                        item.getLastName(),
                        item.getJobTitle(),
                        item.getJobPosition()))
                .collect(Collectors.toList());

        return Result.ok(response);
    }
}
