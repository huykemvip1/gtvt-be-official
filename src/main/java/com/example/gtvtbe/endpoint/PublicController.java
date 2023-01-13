package com.example.gtvtbe.endpoint;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.exception.DataNotFoundException;
import com.example.gtvtbe.service.CollectionService;
import com.example.gtvtbe.service.CoursesService;
import com.example.gtvtbe.service.PublicService;
import com.example.gtvtbe.service.UsersService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public")
@Api(value = "Document Service",hidden = false)
@CrossOrigin
public class PublicController {
    private final PublicService publicService;
    private final CollectionService collectionsService;
    private final CoursesService courseService;


    public PublicController(PublicService publicService, UsersService usersService, CollectionService collectionsService, CoursesService courseService) {
        this.publicService = publicService;
        this.collectionsService = collectionsService;
        this.courseService = courseService;
    }

    @GetMapping("/subject-program")
    public ResponseEntity<Result<?>> getSubjectPrograms(){
        var result = publicService.getSubjectPrograms();
        return ResponseEntity.ok(result);
    }


    @GetMapping("/courses")
    public ResponseEntity<Result<?>> getCourses(){
        var result =  courseService.findAllCourses();
        return ResponseEntity.ok(result);
    }


    @GetMapping("/collections")
    public ResponseEntity<?> getCollections(@RequestParam(name = "page",defaultValue = "1") Integer page,
                                            @RequestParam(name = "pageSize",defaultValue = "5") Integer pageSize) {
        var result = collectionsService.getCollections(page, pageSize);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/collections/actionLike")
    public ResponseEntity<?> actionsCollectionLike(@RequestParam(name = "id") String id) throws DataNotFoundException {
        var result = collectionsService.actionCollectionLike(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/collections/actionFavorite")
    public ResponseEntity<?> actionsCollectionFavorite(@RequestParam(name = "id") String id) throws DataNotFoundException {
        var result = collectionsService.actionCollectionFavorite(id);
        return ResponseEntity.ok(result);
    }
}
