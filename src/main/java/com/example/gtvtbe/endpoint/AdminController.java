package com.example.gtvtbe.endpoint;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.exception.DataNotFoundException;
import com.example.gtvtbe.model.request.CollectionRequest;
import com.example.gtvtbe.model.request.DocumentRequest;
import com.example.gtvtbe.service.AdminService;
import com.example.gtvtbe.service.CollectionService;
import com.example.gtvtbe.service.DocumentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@Api(value = "Where to manage of admin.")
public class AdminController{

    @Autowired
    private MessageSource messageSource;
    private final AdminService adminService;

    private final CollectionService collectionService;

    private final DocumentService documentService;

    public AdminController(AdminService adminService, CollectionService collectionService, DocumentService documentService) {
        this.adminService = adminService;
        this.collectionService = collectionService;
        this.documentService = documentService;
    }

    @GetMapping("/category/roles")
    public ResponseEntity<?> getCategories() throws JsonProcessingException {
        var result = adminService.getAuthorities();
        return ResponseEntity.ok().body(result);
    }


    @PostMapping("/active-accounts")
    public ResponseEntity<?> updateActiveAccounts(@RequestParam(name = "idAccount") String idAccount,
                                                          @RequestParam(name = "idRoles") String idRoles) throws DataNotFoundException {
        if (idAccount == null || idRoles == null){
            return ResponseEntity.ok(Result.badRequest(messageSource.getMessage("request.is.null",new Object[]{},null)));
        }
        var result = adminService.updateActiveAccount(idAccount,idRoles);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/collections/save")
    public ResponseEntity<?> saveCollections(@RequestBody CollectionRequest request){
        return ResponseEntity.ok(collectionService.save(request));
    }

    @PostMapping("/documents/save")
    public ResponseEntity<?> saveDocuments(@RequestBody DocumentRequest request){
        return ResponseEntity.ok(documentService.save(request));
    }
}
