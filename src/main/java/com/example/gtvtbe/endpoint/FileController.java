package com.example.gtvtbe.endpoint;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.model.response.FileResponse;
import com.example.gtvtbe.service.FileService;
import io.swagger.annotations.Api;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/files")
@Api(value = "File Service",hidden = false)
public class FileController {
    private static final String ERROR_FILE_MESSAGE = "File not exist";
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/upload/{name}")
    public ResponseEntity<Result<?>> uploadFile(@PathVariable String name
            ,@RequestParam("file")MultipartFile file) throws IOException {
        if (file.isEmpty()){
            return ResponseEntity.ok(Result.badRequest(ERROR_FILE_MESSAGE));
        }
        var result =  fileService.uploadFile(file,name);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/download/{nameFile}")
    public ResponseEntity<?> downloadFile(@PathVariable String nameFile) throws Exception {
        InputStream inputStream = fileService.downloadFile(nameFile);
        byte[] bytes = IOUtils.toByteArray(inputStream);
        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+ nameFile)
                .body(resource);
    }
    @GetMapping("/view/{nameFile}")
    public ResponseEntity<?> viewFile(@PathVariable String nameFile) throws Exception {
        var response = fileService.viewFile(nameFile);
        String pathFile = Paths.get(response.getPath()).resolve(response.getFileName()).toString().replace("\\","/");
        InputStream inputStream = null ;
        try {
            inputStream = new FileInputStream(pathFile);
        }catch (Exception e) {
            throw e;
        }
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(response.getContentType()))
                .contentLength(response.getSize())
                .body(inputStream.readAllBytes());
    }
}
