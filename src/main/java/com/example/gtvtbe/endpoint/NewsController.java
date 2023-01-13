package com.example.gtvtbe.endpoint;

import com.example.gtvtbe.model.entity.NewsEntity;
import com.example.gtvtbe.service.NewsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/news")
@Api(value = "News Service",hidden = false)
@CrossOrigin
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("")
    public ResponseEntity<?> getPage(@RequestParam(name = "page") Integer page,
                                     @RequestParam(name = "pageSize") Integer pageSize){
        var result = newsService.getByPage(page, pageSize);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        var result = newsService.findById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody NewsEntity request){
        var result = newsService.save(request);
        return ResponseEntity.ok(result);
    }
}
