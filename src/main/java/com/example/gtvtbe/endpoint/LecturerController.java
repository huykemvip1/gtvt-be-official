package com.example.gtvtbe.endpoint;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.service.LecturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lecturers")
@CrossOrigin
public class LecturerController {

    private final LecturerService lecturerService;

    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping("")
    public ResponseEntity<Result<?>> getLecturers(){
        var result = lecturerService.getLecturers();
        return ResponseEntity.ok(result);
    }
}
