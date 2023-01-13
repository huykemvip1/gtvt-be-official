package com.example.gtvtbe.service.impl;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.enumeration.EnumJobPosition;
import com.example.gtvtbe.exception.DataNotFoundException;
import com.example.gtvtbe.model.entity.CoursesEntity;
import com.example.gtvtbe.model.response.CourseResponse;
import com.example.gtvtbe.model.response.CoursesSearchResponse;
import com.example.gtvtbe.model.response.UsersOfCourseResponse;
import com.example.gtvtbe.repository.CourseRepository;
import com.example.gtvtbe.service.CoursesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class CourseServiceImpl implements CoursesService {

    @Autowired
    private MessageSource messageSource;
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Result<List<CourseResponse>> findAllCourses() {
        var response = courseRepository.findAll()
                .stream()
                .map(item -> {
                    Optional<CoursesEntity> entity = courseRepository.findByIdAndUsersJobPosition(item.getId(),
                            List.of(EnumJobPosition.GIANGVIEN.getId(),EnumJobPosition.TRUONGBOMON.getId())
                            );
                    System.out.println(entity.get().getUsers().iterator().next().getFirstName());
                    if (entity.isPresent()){
                        UsersOfCourseResponse lecturer = new UsersOfCourseResponse();
                        BeanUtils.copyProperties(entity.get().getUsers().iterator().next(),lecturer);
                        return new CourseResponse(item.getId(), item.getName(),lecturer);
                    }
                    return new CourseResponse(item.getId(), item.getName());
                }).toList();
        return Result.ok(response);
    }

    @Override
    public Result<CoursesSearchResponse> getCourseById(Integer id) throws DataNotFoundException {
        var response = courseRepository.findById(id)
                .map(item ->{
                    CoursesSearchResponse convertData = new CoursesSearchResponse();
                    List<UsersOfCourseResponse> students = new ArrayList<UsersOfCourseResponse>();
                    for (var entity : item.getUsers()){
                        if (Objects.equals(entity.getJobPosition(), EnumJobPosition.GIANGVIEN.getId())){
                            convertData.setLecture(new UsersOfCourseResponse(entity.getId(),
                                    entity.getFirstName(),
                                    entity.getLastName(),
                                    entity.getJobPosition()));
                        }
                        students.add(new UsersOfCourseResponse(entity.getId(),
                                entity.getFirstName(),
                                entity.getLastName(),
                                entity.getJobPosition()));
                    }
                    convertData.setStudents(students);
                    return convertData;
                })
                .orElseThrow(() -> new DataNotFoundException(messageSource.getMessage("entity.not.found",new Object[]{id},null)));
        return Result.ok(response);
    }



    private void convertData(Object source, Object target){
        try{
            BeanUtils.copyProperties(source, target);
        }catch(Exception e){
            throw e;
        }
    }
}
