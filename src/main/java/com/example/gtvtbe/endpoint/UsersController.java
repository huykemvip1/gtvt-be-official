package com.example.gtvtbe.endpoint;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Api(value = "Users Service",hidden = false)
@CrossOrigin
@Slf4j
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Result<?>> findById(@PathVariable String id) throws Exception {
        var response =  usersService.findById(id);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/getByCoursesId")
    public ResponseEntity<Result<?>> getByCoursesId(@RequestParam(name = "idCourse")String id,
                                                    @RequestParam(name = "page") String page,
                                                    @RequestParam(name = "pageSize") String pageSize) throws Exception {

        var response = usersService.getByCoursesId(Integer.parseInt(id),Integer.parseInt(page), Integer.parseInt(pageSize));
        return ResponseEntity.ok(response);
    }
}
