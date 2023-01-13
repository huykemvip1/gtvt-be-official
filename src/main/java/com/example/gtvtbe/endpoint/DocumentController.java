package com.example.gtvtbe.endpoint;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.exception.DataNotFoundException;
import com.example.gtvtbe.service.DocumentService;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/documents")
@Api(value = "Document Service",hidden = false)
@CrossOrigin
public class DocumentController {

    @Autowired
    private MessageSource messageSource;

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("")
    public ResponseEntity<Result<?>> getByPage(@RequestParam(name = "page",defaultValue = "1") Integer page,
                                               @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize){
        var result = documentService.getByPage(page, pageSize);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public ResponseEntity<Result<?>> getBySearch(@RequestParam(name = "name",required = true) String name,
                                                 @RequestParam(name = "idSubject") String idSubject){
        var result  = documentService.getBySearch(name, idSubject);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/like")
    public ResponseEntity<Result<?>> actionLike(@RequestParam(name = "id") String id,
            @RequestParam(name = "numberLike",required = true) Integer numberLike) throws DataNotFoundException {
        if (id == null || numberLike == null){
            return ResponseEntity.ok(Result.badRequest(messageSource.getMessage("request.is.null",new Object[]{},null)));
        }
        var result = documentService.actionLike(id,numberLike);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/favorite")
    public ResponseEntity<Result<?>> actionFavorite(@RequestParam(name = "id") String id,
            @RequestParam(name = "numberFavorite",required = true) Integer numberFavorite) throws DataNotFoundException {
        if (id == null || numberFavorite == null){
            return ResponseEntity.ok(Result.badRequest(messageSource.getMessage("request.is.null",new Object[]{},null)));
        }
        var result = documentService.actionFavorite(id,numberFavorite);
        return ResponseEntity.ok(result);
    }
}
