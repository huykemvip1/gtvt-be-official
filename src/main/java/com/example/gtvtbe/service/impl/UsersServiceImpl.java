package com.example.gtvtbe.service.impl;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.enumeration.EnumJobPosition;
import com.example.gtvtbe.exception.DataNotFoundException;
import com.example.gtvtbe.model.entity.CoursesEntity;
import com.example.gtvtbe.model.entity.UserEntity;
import com.example.gtvtbe.model.response.*;
import com.example.gtvtbe.repository.UsersRepository;
import com.example.gtvtbe.service.UsersService;
import com.example.gtvtbe.util.Common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    @Autowired
    private MessageSource messageSource;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Result<UsersResponse> findById(String id) throws Exception {
        UsersResponse response = new UsersResponse();
        UserEntity entity = usersRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(messageSource.getMessage("entity.not.found", new Object[]{id}, null)));
        convertData(entity, response);
        response.setAchievements(Common.stringToArray(entity.getAchievements()));
        return Result.ok(response);
    }

    @Override
    public Result<PageResponse<CoursesSearchResponse>> getByCoursesId(Integer id, Integer page, Integer pageSize) {
        var getPage = usersRepository.findByCoursesId(id, EnumJobPosition.SINHVIEN.getId(), PageRequest.of(page - 1, pageSize));
        var lecturer = usersRepository.findLecturerByCoursesId(id, List.of(EnumJobPosition.GIANGVIEN.getId(), EnumJobPosition.TRUONGBOMON.getId()));
        var response = new PageResponse<CoursesSearchResponse>();
        response.setPage(getPage.getTotalPages());
        response.setPageSize(getPage.getTotalElements());
        CoursesSearchResponse coursesSearchResponse = new CoursesSearchResponse();
        coursesSearchResponse.setStudents(getPage.get().map(item -> {
                    UsersOfCourseResponse usersOfCourseResponse = new UsersOfCourseResponse(
                            item.getId(),
                            item.getFirstName(),
                            item.getLastName(),
                            item.getJobPosition()
                    );

                    return usersOfCourseResponse;
                }).collect(Collectors.toList())
        );
        coursesSearchResponse.setLecture(lecturer.map(item -> {
            CoursesEntity course = item.getCourses().iterator().next();
            coursesSearchResponse.setId(course.getId());
            coursesSearchResponse.setName(course.getName());
            return new UsersOfCourseResponse(
                    item.getId(),
                    item.getFirstName(),
                    item.getLastName(),
                    item.getJobPosition()
            );
        }).get());
        response.setData(coursesSearchResponse);
        return Result.ok(response);
    }

    private void convertData(Object source, Object target) {
        try {
            BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            throw e;
        }
    }
}
